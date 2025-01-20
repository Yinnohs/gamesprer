<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<template>
  <RouterLink
    class="group relative inline-block text-sm font-medium text-zinc-300 focus:outline-none focus:ring active:text-teal-500"
    to="/login"
  >
    <span
      class="absolute inset-0 translate-x-0.5 translate-y-0.5 bg-teal-500 transition-transform group-hover:translate-x-0 group-hover:translate-y-0"
    ></span>

    <span
      class="relative block border border-current bg-gradient-to-r text-bold from-teal-200 to-teal-600 px-8 py-3 text-xl"
    >
      Login
    </span>
  </RouterLink>
</template>
