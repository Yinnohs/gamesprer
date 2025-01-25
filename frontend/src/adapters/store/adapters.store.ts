import { defineStore } from "pinia";
import type { HttpClientAdapter } from "../definition/HttpClientAdapter";
import { HttpClientAdapterImpl } from "../impl/HttpClientAxiosAdapter";


export const UseAdaptersStore = defineStore('adapters', ()=>{

  const httpClient: HttpClientAdapter = new HttpClientAdapterImpl()

  return {httpClient}
})
