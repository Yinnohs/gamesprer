<script setup lang="ts">
defineProps({
    title:{
      type: String,
      required: true,
      default: 'Stinky Monkey'
    },
    imageUrl:{
        type: String,
        required: true,
        default: 'no image'
    }
  })
</script>

<template>
  <div  class="flex  justify-center items-center rounded-md w-full h-[50%]">
    <img
      :alt="title"
      :src="imageUrl"
      class="rounded-xl blur-0 w-15 w-[90%] h-full"
    />
  </div>
</template>
