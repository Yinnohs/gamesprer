<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<template>
  <RouterLink
    class="group relative inline-block text-sm font-medium text-teal-500 focus:outline-none focus:ring active:text-teal-500"
    to="/login"
  >
    <span
      class="absolute inset-0 translate-x-0.5 translate-y-0.5 bg-teal-500 transition-transform group-hover:translate-x-0 group-hover:translate-y-0"
    ></span>

    <span class="relative block border border-current bg-zinc-700 px-8 py-3 text-xl"> Login </span>
  </RouterLink>
</template>
