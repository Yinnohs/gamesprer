import { AppUser } from '@/classes/User'
import { useNotificationStore } from '@/notification/notification.store'
import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'

const userLocalStorageKey = 'user'
const tokenLocalStorageKey = 'token'

export const useUserStore = defineStore('user', () => {
  const { connect, disconnect } = useNotificationStore()
  const resetStoreReferences = () => {
    userRef.value = {} as AppUser
    tokenRef.value = ''
    isLoggedRef.value = false;
  }
  const isLoggedRef = ref<boolean>(false)
  const userRef = ref<AppUser>()
  const tokenRef = ref<string>('')

  const setToken = (token: string) => {
    tokenRef.value = token
    localStorage.setItem(tokenLocalStorageKey, token)
  }

  const setUser = (user: AppUser) => {
    userRef.value = user
    localStorage.setItem(userLocalStorageKey, JSON.stringify(user))
  }

  const toggleLoggedIn = async () => {
    try {
      if (userRef.value && userRef.value?.id !== undefined) {
        await connect(userRef.value.id)
        isLoggedRef.value = true
      }
    } catch (error) {
      console.error('Failed to connect to WebSocket:', error)
      isLoggedRef.value = false
    }
  }

  const isUserActive = async () => {
    const request = await axios.get('/api/token/check')
    if (request.status !== 200) return false
    isLoggedRef.value = true
    return true
  }

  const userBootstrap = () => {
    if (!userRef.value && tokenRef.value !== '') return true
    const userString = localStorage.getItem(userLocalStorageKey)
    const token = localStorage.getItem(tokenLocalStorageKey)
    if (!userString || !token) return false
    userRef.value = JSON.parse(userString)
    tokenRef.value = token
    isLoggedRef.value = true
    return true
  }

  const cleanUserDataFromLocalStorage = () => {
    localStorage.removeItem(userLocalStorageKey)
    localStorage.removeItem(tokenLocalStorageKey)
  }

  const cleanUserInformation = () => {
    cleanUserDataFromLocalStorage()
    resetStoreReferences()
    isLoggedRef.value = false
    disconnect()
  }

  return {
    userRef,
    tokenRef,
    isLoggedRef,
    toogleLoggedIn: toggleLoggedIn,
    setToken,
    setUser,
    isUserActive,
    userBootstrap,
    cleanUserInformation,
  }
})
