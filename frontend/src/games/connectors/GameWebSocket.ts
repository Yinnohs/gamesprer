import type { Notification } from '@/classes/Notification'

export interface GameWebSocket {
  connect(callback: (notification: Notification) => void): void
  disconnect(): void
}
