import { serve } from '@hono/node-server'
import { Hono } from 'hono'
import { ScraperService } from './games/service/ScraperService.js'
import { HttpClientAxiosImplementation } from './http/HttpClientAxiosImplementation.js'
import { HttpClient } from './http/HttpClient.js'
import { NotificationService } from './notifications/NotificationService.js'

const app = new Hono()
const service = new ScraperService()
const httpClient: HttpClient = new HttpClientAxiosImplementation()
const notificationService: NotificationService = new NotificationService(httpClient)

app.get('/:gameTitle/', async (c) => {
  try {
    console.log("[INFO]: Running Scraper");
    const gameTitle =  c.req.param('gameTitle')
    const userId =  c.req.query('userId')
    service.getGamesInformationFromSources(gameTitle!)
    .then(()=>{notificationService.sendNotificationReFindNotification(gameTitle, userId!)})
    return c.body( 'requested data scraped successfully', 200, {
      'Content-Type': 'text/plain',
    })
  } catch (error) {
    console.log(error);
    return c.body( 'could not scrape requested data',500, {
      'Content-Type': 'text/plain',
    })
  }
})

const port = 3000
console.log(`Server is running on http://localhost:${port}`)

serve({
  fetch: app.fetch,
  port
})