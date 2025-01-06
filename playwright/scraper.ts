import playwright, { Page } from 'playwright'
import { getRandom } from 'random-useragent'
import {GamesData} from './types'
import { persistGameData } from './db'
import {v7 as uuid} from 'uuid'

const gameName = process.argv[2]
if (!gameName) {
    console.error('Please provide a game name as an argument.');
    process.exit(1);
}

const urlHumbleBundle = `https://www.humblebundle.com/store/search?search=${gameName}`;
const urlInstantGame = `https://www.instant-gaming.com/en/search/?query=${gameName}`

;(async ()=>{

    // create random user agent
    const agent = getRandom()

    //here goes the code
    const browser = await playwright.chromium.launch({headless: true})
    const context = await browser.newContext({userAgent: agent})
    const page = await context.newPage()

    await page.setDefaultTimeout(30000)
    await page.setViewportSize({width:800, height:600})
    
    const humbleBundleGames = await getDataFromHumbleBundle(page);
    const instantGaminGames = await getDataFromInstantGaming(page)

    const aggregatedData =  humbleBundleGames.concat(instantGaminGames)
    const identifiedData = aggregatedData.map((element)=>{
        return{
            id:uuid(),
            ...element
        }
    })
    
    console.log("persist-data");
    console.log(identifiedData);
    persistGameData(identifiedData)

    await browser.close()
})().catch((error)=>{
    console.log(error);
    process.exit(1)
})

async function getDataFromHumbleBundle(page: Page ): Promise<Omit<GamesData, 'id'>[]>{
    await page.goto(urlHumbleBundle)

    await page.waitForTimeout(2000)

    const data = await page.$$eval("div.entity", (gamesCards)=>{

        function formatPrice (price :string | undefined): number {
            console.log(price);
            if (price === undefined || price.trim() === '') return 0.0
            return parseFloat(price.substring(1))
        }

        const formattedData : Omit<GamesData, 'id'>[] = gamesCards.map((mainCard)=>{
            const gameUrl = mainCard.querySelector("a")?.href || ''
            const gameTitle = mainCard.querySelector("span.entity-title")?.getHTML() || ''
            const gamePrice = mainCard.querySelector("span.price")?.getHTML() || ''

            return{
                url: gameUrl,
                title: gameTitle,
                price: formatPrice(gamePrice),
                pageName: 'humbleBundle',
                scrapedAt : new Date()
            }
        })

        return formattedData
    })

    return data
}

async function getDataFromInstantGaming(page: Page ): Promise<Omit<GamesData, 'id'>[]>{
    await page.goto(urlInstantGame)

    await page.waitForTimeout(1000)
    await page.evaluate(()=> window.scroll(0, 400))
    await page.evaluate(()=> window.scroll(0, 400))
    await page.evaluate(()=> window.scroll(0, 400))
    await page.waitForTimeout(1000)

    const data =  await page.$$eval("div.item", (gamesCards)=>{

        console.log(gamesCards.length);
         
        function formatPrice (price :string | undefined): number {
            if (price === undefined || price.trim() === '') return 0.0
            return parseFloat(price.slice(0,-1))
        }

        const formattedData : Omit<GamesData, 'id'>[] = gamesCards.map((mainCard)=>{
            const gameUrl = mainCard.querySelector("a")?.href || ''
            const gameTitle = mainCard.querySelector("span.title")?.getHTML() || ''
            const gamePrice = mainCard.querySelector("div.price")?.getHTML() || ''

            return{
                url: gameUrl,
                title: gameTitle,
                price: formatPrice(gamePrice),
                pageName: 'instantGaming',
                scrapedAt : new Date()
            }
        })

        return formattedData
    })

    return data
}
