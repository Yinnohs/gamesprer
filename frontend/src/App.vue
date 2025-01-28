<script setup lang="ts">
import Navbar from '@/components/navbar/NavBar.vue'
</script>

<template>
  <div class="flex flex-row h-screen w-min-screen bg-zinc-800 text-white">
    <Navbar/>
    <RouterView />
  </div>
</template>
