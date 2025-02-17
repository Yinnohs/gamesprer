<script setup lang="ts">
  defineProps({
    title:{
      type: String,
      required: true,
      default: 'Stinky Monkey'
    },
    price:{
        type: Number,
        required: true,
        default: 0.00
    },

    pageName:{
        type: String,
        required: true,
        default: 'Default name'
    }
  })
</script>

<template>
  <div class="flex flex-col justify-evenly gap-h-5 w-full h-40">
      <h2 class="text-2xl text text-center text-teal-700 text-bold w-full overflow-hidden">{{ title }}</h2>
      <div class="flex w-full justify-center gap-x-5">
        <p class=" text-teal-500 text-md ">{{ price }}€</p>
        <p class="text-teal-500 text-center text-md">{{ pageName }}</p>
      </div>
  </div>
</template>
