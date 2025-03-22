import type { GameWebSocket } from "@/games/connectors/GameWebSocket";
import { GameWebSocketStompAdapter } from "@/games/connectors/GameWebSocketStompAdapter";
import { useGamesStore } from "@/games/store/game.store";
import type { Notification } from '@/types/notification';
import { useUserStore } from "@/user/store/user.store";
import { defineStore } from "pinia";
import { ref } from "vue";
import { useToast } from "vue-toastification";

export const useNotificationStore = defineStore('notification', () => {
  const notificationManager = ref<GameWebSocket | null>(null);
  const notifications = ref<Notification[]>([]);
  const toast = useToast()
  const { fetchGames } = useGamesStore()
  const userStore = useUserStore()

  const connect = (userId: string) => {
    console.log('Connecting WebSocket for user:', userId);
    notificationManager.value = GameWebSocketStompAdapter.instance(userId);
    notificationManager.value.connect(handleReFindNotification)
  };

  const handleReFindNotification = (notification: Notification) => {
    if(notification.type !== 'error'){
      notifications.value.push(notification);
      toast.success(notification.message,{
        closeButton:true,
        timeout:2000
      })
    }

    const token = userStore.tokenRef
    if(token !== ''){
      console.log('Token found, fetching games for:', notification.gameToFindTitle)
      fetchGames(notification.gameToFindTitle, token)
    } else {
      console.log('No token found, skipping game fetch')
    }
  }

  const disconnect = () => {
    if (notificationManager.value) {
      notificationManager.value.disconnect();
      notificationManager.value = null;
    }
    notifications.value = [];
  };

  return {
    connect,
    disconnect,
    notifications
  };
});
