import type { GameWebSocket } from "./GameWebSocket"
import SockJS from 'sockjs-client'
import Stomp, { Frame, type Message } from 'stompjs'
import type { Notification } from '@/classes/Notification'

export class GameWebSocketStompAdapter implements GameWebSocket {
  private userId: string
  private socket: WebSocket | null = null
  private stompClient: Stomp.Client | null = null
  private onMessageCallback: ((notification: Notification) => void) | null = null

  constructor(userId: string) {
    this.userId = userId
  }

  static instance(userId: string): GameWebSocket {
    return new GameWebSocketStompAdapter(userId)
  }

  disconnect(): void {
    if (this.stompClient?.connected) {
      this.stompClient.disconnect(() => {
        console.log('WebSocket disconnected successfully')
      })
    }
    this.socket = null
    this.stompClient = null
  }

  public connect(callback: (notification: Notification) => void): void {
    this.onMessageCallback = callback

    // Make sure to use the correct WebSocket URL
    this.socket = new SockJS(`http://90.171.77.134:5052/ws?userId=${this.userId}`)
    this.stompClient = Stomp.over(this.socket)

    const headers = {
      Authorization: `Bearer ${this.getAuthToken()}`
    }

    this.stompClient.connect(
      headers,
      () => this.socketOnConnect(),
      this.handleError.bind(this)
    )
  }

  private socketOnConnect(): void {
    if (!this.stompClient) return

    // Subscribe to multiple possible destinations to debug
    const destinations = [
        `/user/${this.userId}/topic/re-find`,
    ]

    destinations.forEach(destination => {
      this.stompClient!.subscribe(
          destination,
          this.handleMessage.bind(this),
          {
              id: `user-notif-${this.userId}-${destination}`,
              Authorization: `Bearer ${this.getAuthToken()}`
          }
      )
    })
  }

  private handleMessage(message: Message): void {
    try {
        const notification: Notification = JSON.parse(message.body)

        if (this.onMessageCallback) {
            this.onMessageCallback(notification)
        }
    } catch (error) {
        console.error('Error handling message:', error)
        console.error('Message body:', message.body)
    }
  }

  private handleError(error: Frame | string): void {
    console.error('WebSocket error:', error)
    // Implement reconnection logic if needed
    setTimeout(() => {
      if (!this.stompClient?.connected) {
        console.log('Attempting to reconnect...')
        this.connect(this.onMessageCallback!)
      }
    }, 5000)
  }

  private getAuthToken(): string {
    return localStorage.getItem('token') || ''
  }
}
