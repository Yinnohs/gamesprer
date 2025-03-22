import { UseAdaptersStore } from "@/adapters/store/adapters.store";
import type { Game, GameData } from "@/classes/Game";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useGamesStore = defineStore('games', ()=>{

  const { httpClient } = UseAdaptersStore()
  const gamesRef = ref<GameData[]>([])

  const setGames = (games: GameData[])=>{
    gamesRef.value = games
  }

  const cleanGames = ()=>{
    gamesRef.value = []
  }

  const fetchGames = async (gameTitle :string, token :string) => {
    try {
      if (!gameTitle) return
      debugger;
      const url = `/api/gameinfo/${gameTitle}`
      const response = await httpClient.get<Game[]>(url, token)
      setGames(response)
    } catch (error) {
      console.error(error)
    }
  }

  return{
    gamesRef,
    setGames,
    cleanGames,
    fetchGames
  }

})
