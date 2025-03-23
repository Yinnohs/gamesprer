export interface HttpClient{
    get<Res>(headers:Record<string,string>, url:string, token?:string):Promise<Res | string>
    post<Req, Res >(body: Req, headers:Record<string,string>,url:string, token?:string): Promise<Res | string>
}