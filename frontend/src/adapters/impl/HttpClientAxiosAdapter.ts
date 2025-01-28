import axios, { type AxiosInstance } from "axios"
import type { HttpClientAdapter } from "@/adapters/definition/HttpClientAdapter"

export class HttpClientAxiosAdapterImpl implements HttpClientAdapter{
  private readonly client :AxiosInstance;

  constructor(){
    this.client = axios.create()
  }


  async get<Expected>( url: string, token: string| null = null  ) :Promise<Expected>{
    let options = {}

    if(token  !== null){
      options = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    }

    return (await this.client.get(url, options)).data
  }

  async post<Payload, Expected>( url: string, token: string | null = null, body: Payload ) :Promise<Expected>{
    let options = {}
    if(token  !== null){
      options = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    }

    return (await this.client.post(url, body, options)).data
  }

  async delete<Expected>(url: string, token: string | null = null) : Promise<Expected>{
    let options = {}

    if(token  !== null){
      options = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    }

    return (await this.client.delete(url, options)).data
  }

  async put<Payload, Expected>( url: string, token: string | null = null, body: Payload) :Promise<Expected>{
    let options = {}

    if(token  !== null){
      options = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    }

    return (await this.client.post(url, body, options)).data
  }
}
