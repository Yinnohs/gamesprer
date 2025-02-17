import playwright , { type Page } from "playwright";
import { GamesData, OmitedIdGameData } from "../../types";
import { getRandom } from "random-useragent";
import { v7 as uuid } from "uuid";
import { persistGameData } from "../dao/db.js";

export class ScraperService{
    constructor(){}

    private async generatePageObject(): Promise<Page>{
        const agent = getRandom()
        const browser = await playwright.chromium.launch({headless: true})
        const context  = await browser.newContext({userAgent: agent})
        const page = await context.newPage()

        page.setDefaultTimeout(30000)
        await page.setViewportSize({width:800, height:600})
        return page
    }

    private async getDataFromHumbleBundle(url: string): Promise<OmitedIdGameData[]>{
        const page = await this.generatePageObject()
        await page.goto(url)
        await page.waitForTimeout(800)
    
        const data = await page.$$eval("div.js-entity", (gamesCards)=>{
            
            function formatPrice (price :string | undefined): number {
                if (price === undefined || price.trim() === '') return 0.0
                return parseFloat(price.substring(1))
            }

            const formattedData:Omit<GamesData, 'id'>[] = [] 
            gamesCards.forEach((mainCard)=>{
                const gameUrl = mainCard.querySelector("a")?.href || ''
                const gameTitle = mainCard.querySelector("span.entity-title")?.getHTML() || ''
                const gamePrice = mainCard.querySelector("span.price")?.getHTML() || '0'
                const imageUrl = mainCard.querySelector("img")?.src || ''    
                
                if(gameUrl === '' || imageUrl === '' || gamePrice === '0') return
                formattedData.push({
                    url: gameUrl,
                    title: gameTitle.trim().toLocaleLowerCase(),
                    price: formatPrice(gamePrice),
                    pageName: 'Humble Bundle',
                    scrapedAt : new Date(),
                    imageUrl
                })
            })
    
            return formattedData
        })
        page.close()
        return data
    }
    
    private async getDataFromInstantGaming(url: string): Promise<OmitedIdGameData[]>{
        const page = await this.generatePageObject();
        
        await page.goto(url)
        await page.waitForTimeout(800)
    
        const data =  await page.$$eval("div.item", (gamesCards)=>{
             
            function formatPrice (price :string | undefined): number {
                if (price === undefined || price.trim() === '') return 0.0
                return parseFloat(price.slice(0,-1))
            }
            const formattedData:Omit<GamesData, 'id'>[] = [] 
            gamesCards.forEach((mainCard)=>{
                const gameUrl = mainCard.querySelector("a")?.href || ''
                const gameTitle = mainCard.querySelector("span.title")?.getHTML() || ''
                const gamePrice = mainCard.querySelector("div.price")?.getHTML() || '0'
                const imageUrl = mainCard.querySelector("img")?.src || ''
                if(gameUrl === '' || imageUrl === '' || gamePrice === '0') return

                formattedData.push({
                    url: gameUrl,
                    title: gameTitle.trim().toLocaleLowerCase(),
                    price: formatPrice(gamePrice),
                    pageName: 'Instant Gaming',
                    scrapedAt: new Date(),
                    imageUrl: imageUrl
                })
            })
    
            return formattedData
        })
        page.close()
        return data
    }

    private async getGameFromHrk (url :string){
        const page = await this.generatePageObject();
        
        await page.goto(url)
        await page.waitForTimeout(800)

        
        const data =  await page.$$eval("div.item", (gamesCards)=>{     
            function formatPrice (price :string | undefined): number {
                if (price === undefined || price.trim() === '') return 0.0
                return parseFloat(price.slice(1, price.length))
            }
            const formattedData:Omit<GamesData, 'id'>[] = [] 
             gamesCards.forEach((mainCard)=>{
                const gameUrl = mainCard.querySelector("a")?.href|| ''
                const gameTitle = mainCard.querySelector("a.alg_click")?.getHTML() || ''
                const gamePrice = mainCard.querySelector("div.price")?.getHTML() || '0'
                const imageUrl = mainCard.querySelector("img")?.src|| ''

                if( gameUrl === '' || imageUrl === '' || gamePrice === '0' ) return

                formattedData.push({
                    url: gameUrl,
                    title: gameTitle.trim().toLocaleLowerCase(),
                    price: formatPrice(gamePrice),
                    pageName: 'HRK',
                    scrapedAt: new Date(),
                    imageUrl: imageUrl
                })
            })
            return formattedData
        })
        page.close()
        return data
    }

    private async getGameFromEneba (url :string){
        const page = await this.generatePageObject();
        
        await page.goto(url)
        await this.scrollInPage(page)
        
        const data =  await page.$$eval("div.pFaGHa", (gamesCards)=>{     
            function formatPrice (price :string | undefined): number {
                if (price === undefined || price.trim() === '') return 0.0
                return parseFloat(price.substring(1))
            }

            const formattedData:Omit<GamesData, 'id'>[] = [] 
             gamesCards.forEach((mainCard)=>{
                const gameUrl = mainCard.querySelector("a")?.href|| ''
                const gameTitle = mainCard.querySelector("span.YLosEL")?.getHTML() || ''
                const gamePrice = mainCard.querySelector("span.L5ErLT")?.getHTML() || '0'
                const imageUrl = mainCard.querySelector("img")?.src|| ''

                const price = formatPrice(gamePrice)

                if( gameUrl === '' || imageUrl === '' || gamePrice === '0' || isNaN(price)) return

                formattedData.push({
                    url: gameUrl,
                    title: gameTitle.trim().toLocaleLowerCase(),
                    price: price,
                    pageName: 'Eneba',
                    scrapedAt: new Date(),
                    imageUrl: imageUrl
                })
            })
            return formattedData
        })
        page.close()
        return data
    }

    private replaceWhiteSpaces(gamtetitle: string){
        const modifiedTitle = gamtetitle.trim(); 
        return modifiedTitle.replace(/\s+/g, '+');
    }
    private async scrollInPage( page: Page ) {
        await page.evaluate(async () => {
            await new Promise<void>((resolve) => {
              const distance = 100; // Distance to scroll per step
              const delay = 50; // Delay between each scroll in milliseconds
              const scrollInterval = setInterval(() => {
                window.scrollBy(0, distance);
                if (window.innerHeight + window.scrollY >= document.body.scrollHeight) {
                  clearInterval(scrollInterval);
                  resolve();
                }
              }, delay);
            });
          });
        }

    public async getGamesInformationFromSources( gameTitle: string):Promise<void>{
        const urlHumbleBundle = `https://www.humblebundle.com/store/search?search=${gameTitle}`;
        const urlInstantGame = `https://www.instant-gaming.com/en/search/?query=${gameTitle}`
        const hrkGameTitle = this.replaceWhiteSpaces(gameTitle)
        const urlHrk = `https://www.hrkgame.com/es/games/products/?search=${hrkGameTitle}+europe`
        const urlEneba = `https://www.eneba.com/marketplace?text=${gameTitle}&sortBy=RELEVANCE_DESC`;

        const instantGaming = this.getDataFromInstantGaming(urlInstantGame)
        const humbleBundle = this.getDataFromHumbleBundle(urlHumbleBundle)
        const hrk = this.getGameFromHrk(urlHrk)
        const eneba = this.getGameFromEneba(urlEneba)

        const scrapingResults  = await Promise.allSettled([eneba, hrk, instantGaming, humbleBundle])

        const aggregatedGames: OmitedIdGameData[] = []
        scrapingResults.forEach((call)=>{
            if(call.status === 'fulfilled' && call.value !== undefined){
                console.log('[INFO]: Games were scrapped');
                aggregatedGames.push(...call.value)
                return
            }

            if(call.status === 'rejected'){
                console.log(call.reason)
            }
        })

        const finalGamesArray: GamesData[] = aggregatedGames.map((game)=>({...game, id: uuid()}))

        if(finalGamesArray.length === 0){
            console.log("[ERROR]: Something went wrong getting the games data");
            throw new Error('Error getting games data')
        }
        
        console.log('[INFO]: Adding Games data to the database');
        persistGameData(finalGamesArray)
        console.log('[INFO]: Done with game data'); 
    }
}