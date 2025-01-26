import { defineStore } from "pinia";
import type { HttpClientAdapter } from "../definition/HttpClientAdapter";
import { HttpClientAxiosAdapterImpl} from "../impl/HttpClientAxiosAdapter";
import { reactive, type Reactive, } from "vue";


export const UseAdaptersStore = defineStore('adapters', ()=> {

  const httpClient: Reactive<HttpClientAdapter> = reactive(new HttpClientAxiosAdapterImpl())

  return { httpClient }
})
