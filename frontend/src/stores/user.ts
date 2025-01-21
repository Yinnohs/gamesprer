import { AppUser } from '@/classes/User'
import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'

const userLocalStorageKey = 'user'
const tokenLocalStorageKey = 'token'

export const useUserStore = defineStore('user', () => {
  const userRef = ref<AppUser>({} as AppUser)
  const tokenRef = ref<string>('')

  const setToken = (token: string) => {
    tokenRef.value = token
    localStorage.setItem(tokenLocalStorageKey, token)
  }

  const setUser = (user: AppUser) => {
    userRef.value = user
    localStorage.setItem(userLocalStorageKey, JSON.stringify(user))
  }

  const isUserActive = async () => {
    const request = await axios.get('/api/token/check')
    if (request.status !== 200) return false
    return true
  }

  const userBootstrap = () => {
    const userString = localStorage.getItem(userLocalStorageKey)
    const token = localStorage.getItem(tokenLocalStorageKey)
    if (!userString || !token) return false
    userRef.value = JSON.parse(userString)
    tokenRef.value = token
    return true
  }

  return { userRef, tokenRef, setToken, setUser, isUserActive, userBootstrap }
})
