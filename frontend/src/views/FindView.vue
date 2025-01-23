<script setup lang="ts">
import { ref } from 'vue'
import GameCard from '@/components/card/GameCard.vue'
import FindInput from '@/components/input/FindInput.vue'
import type { Game } from '@/classes/Game';
import axios from 'axios';
import { useUserStore } from '@/user/store/user.store';
import AppButton from '@/components/buttons/AppButton.vue';
import { useLink } from 'vue-router';

const { tokenRef, userBootstrap } = useUserStore()
const link = useLink({
  to: '/login'
})
const gamesData = ref<Game[]>([])
const gameTitle = ref('')
const isLoading = ref(false)

const checkStoragedUser = (): boolean => {
  isLoading.value = true
  if (tokenRef === ''){
      if(!userBootstrap()){
        return false
      }
    }
    return true
}

const fetchGames = async () => {
  try {
    if (!gameTitle.value) return
    if (!checkStoragedUser()) {
      link.navigate()
      return
    }

    const response = await axios.get(`/api/gameinfo/${gameTitle.value}`, {
      headers: {
        Authorization: `Bearer ${tokenRef}`
      }
    })
    gameTitle.value = ''
    gamesData.value = response.data
  } catch (error) {
    console.error(error)
  }finally{
    isLoading.value = false
  }
}

</script>

<template>
  <section class="flex flex-col w-full h-screen justify-around items-center">
    <form @submit.prevent="fetchGames" class="flex w-[50%] px-12 py-6 flex-col justify-center items-center pb-4 shadow-zinc-900 gap-2">
      <h1 class="text-4xl text-teal-500">Busca un juego</h1>
      <div class="flex flex-row gap-4 w-full items-center justify-center">
        <FindInput :value="gameTitle" @update:modelValue="gameTitle = $event" />
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
