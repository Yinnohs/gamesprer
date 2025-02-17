<script setup lang="ts">
import { Game } from '@/classes/Game';
import CardInformation from '@/components/card/CardInformation.vue';
import CardImage from '@/components/card/CardImage.vue';

defineProps({
  game: {
    type: Game,
    required: false,
    default: {
      title: 'Stinky Monkey',
      price: 9.99,
      imageUrl: 'https://img.freepik.com/free-psd/empty-promotion-sale-banner-background_47987-12008.jpg?t=st=1737471274~exp=1737474874~hmac=b5ed35a1963d3385a9984bac624c08054a453c7df281c7e549ded8a0297fb3d7&w=826',
      pageName: 'Page',
      url: 'https://www.google.com',
    }
  },
})

</script>

<template>
  <a :href="game.url" target="_blank" class="flex w-full h-96 flex-col justify-evenly items-center pb-2 rounded-md hover:cursor-pointer hover:shadow-lg hover:bg-zinc-900/40 transition-all duration-100">
      <CardImage :title="game.title" :imageUrl="game.imageUrl"/>
      <CardInformation :price="game.price" :page-name="game.pageName" :title="game.title"/>
  </a>
</template>
