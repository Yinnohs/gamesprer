/* eslint-disable @typescript-eslint/no-unused-vars */
import type { GameWebSocket } from "./GameWebSocket";
import SockJs from 'sockjs-client'
import Stomp, { Frame, type Message } from 'stompjs'

export class GameWebSocketStompAdapter implements GameWebSocket {

  private userId: string
  private socket: WebSocket| null = null
  private stompClient: Stomp.Client | null = null

  constructor(userId :string){
    this.userId = userId
  }
  static instance(userId: string): GameWebSocket {
    return new GameWebSocketStompAdapter(userId)
  }

  disconnect(): void {
    if(this.stompClient != null){
      this.stompClient.disconnect(()=>{
        console.log("desconectando del socket");
      }, {});
    }
  }

  public connect(): void{
    this.socket = new SockJs('/ws')
    this.stompClient = Stomp.over(this.socket)
    this.stompClient.connect({},this.socketOnConnect,this.handleError)
  };

  private socketOnConnect(frame?: Frame){
    this.stompClient?.subscribe('/topic/app/notify/user')

  }

  private handleMessage(message: Message){
    console.log("MENSAJE DEL SOCKET DESDE EL SCRAPER");
    console.log(message)

  }

  private handleError(error: Frame | string){
    console.log(error);
  }

}
