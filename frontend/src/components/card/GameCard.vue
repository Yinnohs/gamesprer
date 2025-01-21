<script setup lang="ts">
import { Game } from '@/classes/Game';

defineProps({
  game: {
    type: Game,
    required: false,
    default: {
      title: 'Stinky Monkey',
      price: 9.99,
      imageUrl: 'https://img.freepik.com/free-psd/empty-promotion-sale-banner-background_47987-12008.jpg?t=st=1737471274~exp=1737474874~hmac=b5ed35a1963d3385a9984bac624c08054a453c7df281c7e549ded8a0297fb3d7&w=826',
      pageName: 'Page',
      link: 'https://www.google.com',
    }
  },
})
</script>

<template>
  <a :href="game.url" target="_blank" class="relative flex w-96 h-96 flex-col justify-around rounded-md hover:cursor-pointer">
    <!-- card -->
    <div  class="relative flex items-center  flex-1 rounded-md">
      <img
        :alt="game.title"
        :src="game.imageUrl"
        class="absolute inset-0 h-full w-full object-cover rounded-md blur-0"
      />
      <div class="bg-zinc-900/60 w-full h-full absolute z-10"></div>
      <div  class="z-20 absolute w-full h-full flex flex-col justify-center gap-6 items-center hover:shadow-2xl shadow-zinc-900 duration-300">
      <h2 class="text-2xl text text-justify text-teal-500 text-bold">{{ game.title }}</h2>
      <p class="text-xl text-zinc-200">{{ game.price }} $</p>
      </div>
    </div>
    <!-- text -->
    <h4 class="absolute z-20 w-full text-center text-md bottom-0 text-teal-500">{{ game.pageName }}</h4>
  </a>
</template>

