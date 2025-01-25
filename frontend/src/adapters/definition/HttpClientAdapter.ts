export interface HttpClientAdapter{
    get<Expected>( url: string, token: string| null  ) :Promise<Expected>
    post<Payload, Expected>( url: string, token: string| null, body: Payload ) :Promise<Expected>
    delete<Expected>(url: string, token: string| null ) : Promise<Expected>
    put<Payload, Expected>( url: string, token: string| null , body: Payload ) :Promise<Expected>
}
