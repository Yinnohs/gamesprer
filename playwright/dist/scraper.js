import playwright from 'playwright';
import { getRandom } from 'random-useragent';
import { persistGameData } from './db.js';
import { v7 as uuid } from 'uuid';
export async function scrapeData(gameName) {
    console.log('[INFO]: STARTING SCRAPPING');
    const urlHumbleBundle = `https://www.humblebundle.com/store/search?search=${gameName}`;
    const urlInstantGame = `https://www.instant-gaming.com/en/search/?query=${gameName}`;
    // create random user agent
    const agent = getRandom();
    //here goes the code
    const browser = await playwright.chromium.launch({ headless: true });
    const context = await browser.newContext({ userAgent: agent });
    const page = await context.newPage();
    await page.setDefaultTimeout(30000);
    await page.setViewportSize({ width: 800, height: 600 });
    const humbleBundleGames = await getDataFromHumbleBundle(page, urlHumbleBundle);
    const instantGaminGames = await getDataFromInstantGaming(page, urlInstantGame);
    const aggregatedData = humbleBundleGames.concat(instantGaminGames);
    const identifiedData = aggregatedData.map((element) => {
        return {
            id: uuid(),
            ...element
        };
    });
    console.log('[INFO]: DATA:', identifiedData);
    persistGameData(identifiedData);
    await browser.close();
}
async function getDataFromHumbleBundle(page, url) {
    await page.goto(url);
    await page.waitForTimeout(2000);
    const data = await page.$$eval("div.js-entity", (gamesCards) => {
        function formatPrice(price) {
            console.log(price);
            if (price === undefined || price.trim() === '')
                return 0.0;
            return parseFloat(price.substring(1));
        }
        const formattedData = gamesCards.map((mainCard) => {
            const gameUrl = mainCard.querySelector("a")?.href || '';
            const gameTitle = mainCard.querySelector("span.entity-title")?.getHTML() || '';
            const gamePrice = mainCard.querySelector("span.price")?.getHTML() || '';
            const imageUrl = mainCard.querySelector("img")?.src || '';
            return {
                url: gameUrl,
                title: gameTitle.trim().toLocaleLowerCase(),
                price: formatPrice(gamePrice),
                pageName: 'Humble Bundle',
                scrapedAt: new Date(),
                imageUrl
            };
        });
        return formattedData;
    });
    return data;
}
async function getDataFromInstantGaming(page, url) {
    await page.goto(url);
    await page.waitForTimeout(3000);
    const data = await page.$$eval("div.item", (gamesCards) => {
        function formatPrice(price) {
            if (price === undefined || price.trim() === '')
                return 0.0;
            return parseFloat(price.slice(0, -1));
        }
        const formattedData = gamesCards.map((mainCard) => {
            const gameUrl = mainCard.querySelector("a")?.href || '';
            const gameTitle = mainCard.querySelector("span.title")?.getHTML() || '';
            const gamePrice = mainCard.querySelector("div.price")?.getHTML() || '';
            const imageUrl = mainCard.querySelector("img")?.src || '';
            return {
                url: gameUrl,
                title: gameTitle.trim().toLocaleLowerCase(),
                price: formatPrice(gamePrice),
                pageName: 'Instant Gaming',
                scrapedAt: new Date(),
                imageUrl: imageUrl
            };
        });
        return formattedData;
    });
    return data;
}
