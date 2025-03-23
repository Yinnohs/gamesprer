import { HttpClient } from "./HttpClient";
import axios, { Axios } from 'axios'

export class HttpClientAxiosImplementation implements HttpClient{
    
    private client: Axios
    constructor(){
        this.client = new Axios()
    }
    async get<Res>(headers: Record<string, string>, url: string, token?: string): Promise<Res | string> {
        try {
            if(!token || token.trim() === ''){
                return axios.get(url,{
                    headers: {
                        ...headers,
                        "Content-Type": "application/json"
                    }
                })

            }
            return axios.get(url,{
                headers: {
                    ...headers,
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            })
            
        } catch (error) {
            console.log(error);
            return "Error has ocurred" as Res
        }
    }
    async post<Req, Res>(body: Req, headers: Record<string, string>, url: string, token?: string): Promise<Res | string> {
        try {
            if(!token || token.trim() === ''){
                return axios.post(url,body,{
                    headers: {
                        ...headers,
                        "Content-Type": "application/json"
                    }
                })

            }
            return axios.post(url,body,{
                headers: {
                    ...headers,
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            })
            
        } catch (error) {
            console.log(error);
            return "Error has ocurred"
        }
    }


}