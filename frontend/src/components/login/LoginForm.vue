<script setup lang="ts">
import { reactive } from 'vue'
import { useUserStore } from '@/user/store/user.store'
import type { LoginResponse, UserLogin } from '@/classes/Auth'
import { z } from 'zod'
import { loginSchema } from '@/schema/LoginSchema'
import { useLink } from 'vue-router'
import { UseAdaptersStore } from '@/adapters/store/adapters.store'

const { setUser, setToken, toogleLoggedIn } = useUserStore()
const { httpClient } = UseAdaptersStore()
const navigation = useLink({ to: '/find' })
type UserLoginKeys = keyof UserLogin

const loginForm = reactive<UserLogin>({
  email: '',
  password: '',
})

const formErrors = reactive<{ [key in UserLoginKeys]: string }>({
  email: '',
  password: '',
})

const checkFormPayload = (formData: UserLogin): boolean => {
  try {
    loginSchema.parse(formData)
    return true
  } catch (zError) {
    if (zError instanceof z.ZodError) {
      zError.errors.forEach((error) => {
        formErrors[error.path[0] as UserLoginKeys] = error.message
      })
    }
    console.log(formErrors)

    return false
  }
}

const handleSubmit = async () => {
  if (!checkFormPayload(loginForm)) return
  const url = '/api/auth/login'

  try {
    const response = await httpClient.post<UserLogin , LoginResponse>(url, null, loginForm)
    setToken(response.token)
    setUser(response.user)
    toogleLoggedIn()
    navigation.navigate()
  } catch (error) {
    console.log(error)
  }
}
</script>

<template>
  <div class="flex justify-center items-center mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
    <div class="mx-auto max-w-lg">
      <h1 class="text-center text-2xl font-bold text-teal-600 sm:text-3xl">Encuentra</h1>

      <p class="mx-auto mt-4 max-w-md text-center text-gray-400">
        Las mejores ofertas de videojuegos y consolas en un solo lugar.
      </p>

      <form
        @submit.prevent="handleSubmit"
        class="mb-0 mt-6 space-y-4 rounded-lg bg-zinc-700 p-4 shadow-lg sm:p-6 lg:p-8"
      >
        <p class="text-center text-lg font-medium text-white">Accede a tu cuenta</p>

        <div>
          <label for="email" class="sr-only">Email</label>

          <div class="relative">
            <input
              v-model="loginForm.email"
              type="email"
              class="w-full rounded-lg bg-zinc-600 border-zinc-500 p-4 pe-12 text-sm shadow-sm"
              placeholder="Enter email"
            />

            <span class="absolute inset-y-0 end-0 grid place-content-center px-4">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="size-4 text-gray-400"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"
                />
              </svg>
            </span>
          </div>
        </div>

        <div>
          <label for="password" class="sr-only">Password</label>

          <div class="relative">
            <input
              v-model="loginForm.password"
              type="password"
              class="w-full rounded-lg bg-zinc-600 border-gray-400 p-4 pe-12 text-sm shadow-sm"
              placeholder="Enter password"
            />

            <span class="absolute inset-y-0 end-0 grid place-content-center px-4">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="size-4 text-gray-400"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                />
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
                />
              </svg>
            </span>
          </div>
        </div>

        <button
          type="submit"
          class="block w-full rounded-lg bg-teal-600 px-5 py-3 text-sm font-medium text-white"
        >
          Sign in
        </button>
      </form>
    </div>
  </div>
</template>
