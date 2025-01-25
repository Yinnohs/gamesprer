import axios from "axios"
import type { HttpClientAdapter } from "@/adapters/definition/HttpClientAdapter"

export class HttpClientAdapterImpl implements HttpClientAdapter{
  private readonly client = axios.create()

  async get<Expected>( url: string, token: string| null = null  ) :Promise<Expected>{

    let options = {}

    if(token  !== null){
      options = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    }

    return (await axios.get(url, options)).data
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

    return (await axios.post(url, body, options)).data
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

    return (await axios.delete(url, options)).data
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

    return (await axios.post(url, body, options)).data
  }
}
