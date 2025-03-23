<script setup lang="ts">
import { onMounted, ref } from 'vue'
import GameCard from '@/components/card/GameCard.vue'
import FindInput from '@/components/input/FindInput.vue'
import type { Game } from '@/classes/Game';
import { useUserStore } from '@/user/store/user.store';
import AppButton from '@/components/buttons/AppButton.vue';
import { useLink } from 'vue-router';
import { useGamesStore } from '@/games/store/game.store';
import { storeToRefs } from 'pinia';

const { tokenRef, userBootstrap, userRef } = useUserStore()
const { gamesRef } = storeToRefs(useGamesStore())
const gameStore = useGamesStore()

const link = useLink({
  to: '/login'
})

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


const handleFindGames = async() => {
  if (!gameTitle.value || !userRef?.id) return
  isLoading.value = true
  gameStore.cleanGames()
  await gameStore.fetchGames(gameTitle.value, tokenRef, userRef.id)
  gameTitle.value = ''
  isLoading.value = false
}


onMounted(() => {
  if(!checkStoragedUser()){
    link.navigate()
  }
})

</script>

<template>
  <section class="flex flex-col w-full h-screen justify-around items-center">
    <form @submit.prevent="handleFindGames" class="flex w-full px-12 py-6 flex-col justify-center items-center pb-4 shadow-zinc-900 gap-2 md:w-[50%]">
      <h1 class="text-2xl text-teal-500 md:text-4xl">Busca un juego</h1>
      <div class="flex flex-col gap-4 w-full items-center justify-center md:flex-row">
        <FindInput :value="gameTitle" @update:modelValue="gameTitle = $event" :isLoading="isLoading" />
        <AppButton> Buscar</AppButton>
      </div>
    </form>
    <div v-if="isLoading">
      <h1>Buscando juegos espere un momento... (si no llegan a aparecer en unos segundos, esperar al mensaje)</h1>
    </div>

    <div class="overflow-y-scroll grid grid-cols-1 gap-7 py-6 w-full h-[90%] justify-items-center md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <GameCard v-for="game in  gamesRef" v-bind:key="game.id" :game="game as Game"/>
    </div>
  </section>
</template>
