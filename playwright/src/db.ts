import { MongoClient } from "mongodb"
import { type GamesData } from "./types.js"



export  async function persistGameData(gameData: GamesData[]){
    const uri = process.env.MONGO_DB_URL || 'mongodb://127.0.0.1:27017/gamesprer'
    const client = new MongoClient(uri,{
        authSource: 'admin',
        auth:{
            username:'yinnohs',
            password:'1234'
        },
    })

    try {
        await client.connect()
        const database = client.db('gamesprer')
        const collection = database.collection<GamesData>('gameInfo')   
        const result = await collection.insertMany(gameData);

        return result;
    } catch (error) {
        console.log(error);
        
    } finally{
        await client.close()
    }
    

}
