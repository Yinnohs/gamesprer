<script setup lang="ts">
import Navbar from '@/components/navbar/NavBar.vue'
</script>

<template>
  <div class="flex flex-col h-screen w-min-screen bg-zinc-800 text-white md:flex-row">
    <Navbar/>
    <RouterView />
  </div>
</template>
