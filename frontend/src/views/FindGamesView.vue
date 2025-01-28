<script setup lang="ts">
import { onMounted, ref } from 'vue'
import GameCard from '@/components/card/GameCard.vue'
import FindInput from '@/components/input/FindInput.vue'
import type { Game } from '@/classes/Game';
import { useUserStore } from '@/user/store/user.store';
import AppButton from '@/components/buttons/AppButton.vue';
import { useLink } from 'vue-router';
import { UseAdaptersStore } from '@/adapters/store/adapters.store';

const { tokenRef, userBootstrap } = useUserStore()
const { httpClient } = UseAdaptersStore()
const link = useLink({
  to: '/login'
})
const gamesData = ref<Game[]>([])
const gameTitle = ref('')
const isLoading = ref(false)

const checkStoragedUser = (): boolean => {
  if (tokenRef === ''){
      if(!userBootstrap()){
        return false
      }
    }
    return true
}

const fetchGames = async (gameTitle :string) => {
  try {
    if (!gameTitle) return

    const url = `/api/gameinfo/${gameTitle}`
    const response = await httpClient.get<Game[]>(url, tokenRef)
    return response
  } catch (error) {
    console.error(error)
  }
}

const handleFindGames = async() => {
  if (!gameTitle.value)return
  isLoading.value = true
  const games =  await fetchGames(gameTitle.value)
  gameTitle.value = ''
  isLoading.value = false
  if(!games){
    console.log('should thorw an error');
    return
  }
  gamesData.value = games
}


onMounted(() => {
  if(!checkStoragedUser()){
    link.navigate()
  }
})

</script>

<template>
  <section class="flex flex-col w-full h-screen justify-around items-center">
    <form @submit.prevent="handleFindGames" class="flex w-[50%] px-12 py-6 flex-col justify-center items-center pb-4 shadow-zinc-900 gap-2">
      <h1 class="text-4xl text-teal-500">Busca un juego</h1>
      <div class="flex flex-row gap-4 w-full items-center justify-center">
        <FindInput :value="gameTitle" @update:modelValue="gameTitle = $event" :isLoading="isLoading" />
        <AppButton> Buscar</AppButton>
      </div>
    </form>
    <div v-if="isLoading">
      <h1>Buscando Juegos...</h1>
    </div>

    <div class="overflow-y-scroll grid grid-cols-1 gap-7 py-6 w-full h-[90%] justify-items-center md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <GameCard v-for="game in gamesData" v-bind:key="game.id" :game="game as Game"/>
    </div>
  </section>
</template>
