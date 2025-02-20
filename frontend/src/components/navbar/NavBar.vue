<script setup lang="ts">
import { RouterLink, useLink } from 'vue-router'
import { useUserStore } from '@/user/store/user.store'
import { storeToRefs } from 'pinia';

const userStore = useUserStore()
const { isLoggedRef } = storeToRefs(userStore)
const link = useLink({
  to: '/',
})

const logout = () => {
  userStore.cleanUserInformation()
  link.navigate()
}
</script>
<template>
  <div
  v-if="isLoggedRef"
    class="z-50 sticky flex h-screen w-16 flex-col justify-between border-e border-zinc-700 bg-zinc-800"
  >
    <div>
      <div class="inline-flex size-16 items-center justify-center">
        <RouterLink
          to="/"
          class="grid size-10 place-content-center rounded-lg text-bold bg-zinc-900 text-xs text-zinc-400 hover:bg-zinc-700 hover:text-zinc-200 hover:cursor-pointer"
        >
          G
        </RouterLink>
      </div>

      <div>
        <div class="p-1">
          <ul class="space-y-1 border-t border-zinc-700 pt-4">
            <li>
              <RouterLink
                to="/"
                class="group relative flex justify-center rounded px-2 py-1.5 text-zinc-100 hover:bg-zinc-700"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="size-5 opacity-75"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"
                  />
                </svg>

                <span
                  class="invisible absolute start-full top-1/2 ms-4 -translate-y-1/2 rounded bg-zinc-900 px-2 py-1.5 text-xs font-medium text-white group-hover:visible"
                >
                  Home
                </span>
              </RouterLink>
            </li>
          </ul>
        </div>
      </div>

      <div class="p-1">
          <ul class="space-y-1 border-t border-zinc-700 pt-4">
            <li>
              <RouterLink
                to="/find"
                class="group relative flex justify-center rounded px-2 py-1.5 text-zinc-100 hover:bg-zinc-700"
              >
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
                <span
                  class="invisible absolute start-full top-1/2 ms-4 -translate-y-1/2 rounded bg-zinc-900 px-2 py-1.5 text-xs font-medium text-white group-hover:visible"
                >
                  Games
                </span>
              </RouterLink>
            </li>
          </ul>
        </div>
      </div>

    <div s class="sticky inset-x-0 bottom-0 border-t border-zinc-700 bg-zinc-800 p-2">
      <div action="#">
        <button

          @click="logout"
          type="button"
          class="group relative flex w-full justify-center rounded-lg px-2 py-1.5 text-sm text-zinc-500 hover:bg-zinc-700 hover:text-zinc-100"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="size-5 opacity-75"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"
            />
          </svg>

          <span
            class="invisible absolute start-full top-1/2 ms-4 -translate-y-1/2 rounded bg-zinc-900 px-2 py-1.5 text-xs font-medium text-white group-hover:visible"
          >
            Logout
          </span>
        </button>
      </div>
    </div>
  </div>
</template>
