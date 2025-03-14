import type { GameWebSocket } from "@/games/connectors/GameWebSocket";
import { GameWebSocketStompAdapter } from "@/games/connectors/GameWebSocketStompAdapter";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useNotificationStore = defineStore('notification', ()=>{

  const notificationManager = ref<GameWebSocket | null >(null)
  const connect = (userId:string)=>{
    notificationManager.value = GameWebSocketStompAdapter.instance(userId)
    notificationManager.value.connect()
  }

  const disconnect = ()=>{
    if(notificationManager.value != null){
      notificationManager.value?.disconnect()
    }
  }


  return{
    connect,
    disconnect
  }
})
