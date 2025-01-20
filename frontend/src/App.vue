<script setup lang="ts">
import Navbar from '@/components/navbar/NavBar.vue'
</script>

<template>
  <div class="grid grid-cols-layout h-screen w-screen bg-zinc-800 text-white">
    <Navbar />
    <RouterView />
  </div>
</template>
