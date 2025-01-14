import playwright, { type Page } from 'playwright'
import { getRandom } from 'random-useragent'
import { type GamesData } from './types.js'
import { persistGameData } from './db.js'
import {v7 as uuid} from 'uuid'



export async function scrapeData(gameName:string){
    
    const urlHumbleBundle = `https://www.humblebundle.com/store/search?search=${gameName}`;
    const urlInstantGame = `https://www.instant-gaming.com/en/search/?query=${gameName}`

    // create random user agent
    const agent = getRandom()

    //here goes the code
    const browser = await playwright.chromium.launch({headless: true})
    const context = await browser.newContext({userAgent: agent})
    const page = await context.newPage()

    await page.setDefaultTimeout(30000)
    await page.setViewportSize({width:800, height:600})
    
    const humbleBundleGames = await getDataFromHumbleBundle(page, urlHumbleBundle);
    const instantGaminGames = await getDataFromInstantGaming(page, urlInstantGame)

    const aggregatedData =  humbleBundleGames.concat(instantGaminGames)
    const identifiedData = aggregatedData.map((element)=>{
        return{
            id:uuid(),
            ...element
        }
    })
    
    persistGameData(identifiedData)

    await browser.close()
}

async function getDataFromHumbleBundle(page: Page, url: string ): Promise<Omit<GamesData, 'id'>[]>{
    await page.goto(url)
    await page.waitForTimeout(2000)

    const data = await page.$$eval("div.js-entity", (gamesCards)=>{
        
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

async function getDataFromInstantGaming(page: Page, url: string ): Promise<Omit<GamesData, 'id'>[]>{
    await page.goto(url)

    await page.waitForTimeout(3000)

    const data =  await page.$$eval("div.item", (gamesCards)=>{
         
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
