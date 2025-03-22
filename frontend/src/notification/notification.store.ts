import type { GameWebSocket } from "@/games/connectors/GameWebSocket";
import { GameWebSocketStompAdapter } from "@/games/connectors/GameWebSocketStompAdapter";
import type { Notification } from '@/types/notification';
import { defineStore } from "pinia";
import { ref } from "vue";

export const useNotificationStore = defineStore('notification', () => {
  const notificationManager = ref<GameWebSocket | null>(null);
  const notifications = ref<Notification[]>([]);

  const connect = (userId: string) => {
    console.log('Connecting WebSocket for user:', userId);
    notificationManager.value = GameWebSocketStompAdapter.instance(userId);
    notificationManager.value.connect((notification: Notification) => {
      console.log('Received notification in store:', notification);
      notifications.value.push(notification);
    });
  };

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
