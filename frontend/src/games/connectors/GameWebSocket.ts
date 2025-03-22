import type { Notification } from '@/types/notification'

export interface GameWebSocket {
  connect(callback: (notification: Notification) => void): void
  disconnect(): void
}
