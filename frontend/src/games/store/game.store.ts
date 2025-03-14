import type { GameData } from "@/classes/Game";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useGamesStore = defineStore('games', ()=>{

  const gamesRef = ref<GameData[]>([])

  const setGames = (games: GameData[])=>{
    gamesRef.value = games
  }

  const cleanGames = ()=>{
    gamesRef.value = []
  }

  return{
    gamesRef,
    setGames,
    cleanGames
  }

})
