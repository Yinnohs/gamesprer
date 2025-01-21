<script setup lang="ts">
import { ref } from 'vue'
import GameCard from '@/components/card/GameCard.vue'
import FindInput from '@/components/input/FindInput.vue'
import type { Game } from '@/classes/Game';
import axios from 'axios';
import { useUserStore } from '@/stores/user';
import AppButton from '@/components/buttons/AppButton.vue';

const { tokenRef } = useUserStore()
const gamesData = ref<Game[]>([])
const gameTitle = ref('')

const fetchGames = async () => {
  try {
    const response = await axios.get(`/api/gameinfo/${gameTitle.value}`, {
      headers: {
        Authorization: `Bearer ${tokenRef}`
      }
    })
    gamesData.value = response.data
  } catch (error) {
    console.error(error)
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
    <div class="overflow-y-scroll grid grid-cols-4 gap-4 py-6 w-full h-[90%] justify-items-center">
      <GameCard v-for="game in gamesData" v-bind:key="game.id" :game="game"/>
    </div>
  </section>
</template>
