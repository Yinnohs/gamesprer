import { MongoClient } from "mongodb";
export async function persistGameData(gameData) {
    const uri = process.env.MONGO_DB_URL || 'mongodb://127.0.0.1:27017/gamesprer';
    const client = new MongoClient(uri, {
        authSource: 'admin',
        auth: {
            username: 'yinnohs',
            password: '1234'
        },
    });
    try {
        await client.connect();
        const database = client.db('gamesprer');
        const collection = database.collection('games');
        const result = await collection.insertMany(gameData);
        return result;
    }
    catch (error) {
        console.log(error);
    }
    finally {
        await client.close();
    }
}
