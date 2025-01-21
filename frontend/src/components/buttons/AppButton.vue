<script setup lang="ts">
</script>

<template>
  <button
    class="group relative inline-flex items-center bg-zinc-700 border border-teal-500 shadow-md shadow-zinc-900 overflow-hidden rounded b px-8 py-3 text-teal-500 focus:outline-none focus:ring active:bg-indigo-500"
  >
    <span class="absolute -end-full transition-all group-hover:end-4">
      <svg
        class="size-5 rtl:rotate-180"
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M17 8l4 4m0 0l-4 4m4-4H3"
        />
      </svg>
    </span>

    <span class="text-sm font-medium transition-all group-hover:me-4">
      <slot></slot>
    </span>
  </button>
</template>
