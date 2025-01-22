<!-- eslint-disable vue/no-mutating-props -->
<script setup lang="ts">
defineProps({
  value: {
    type: String,
    required: true
  }
})
const emit = defineEmits(['update:modelValue'])
const handleInput = ($event : Event) => {
  const target = $event.target as HTMLInputElement | null;
  if (target) {
    emit('update:modelValue', target.value)
  }
}
</script>

<template>
  <div class="relative">
    <label for="Search" class="sr-only"> Search </label>

    <input
      type="text"
      id="Search"
      @input="handleInput"
      :value="value"
      placeholder="Buscar juegos..."
      class="w-full rounded-md text-lg bg-zinc-700 py-4 pe-10 shadow-sm sm:text-sm placeholder:text-center pl-3 text-zinc-300 focus:border focus:border-teal-500"
    />

    <span class="absolute inset-y-0 end-0 grid w-10 place-content-center">
      <button type="button" class="text-zinc-300 hover:text-zinc-300">
        <span class="sr-only">Search</span>

        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="size-4"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
          />
        </svg>
      </button>
    </span>
  </div>
</template>
