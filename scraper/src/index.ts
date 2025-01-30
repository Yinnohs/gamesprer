import { serve } from '@hono/node-server'
import { Hono } from 'hono'
import { ScraperService } from './games/service/ScraperService.js'

const app = new Hono()
const service = new ScraperService()

app.get('/:gameTitle', async (c) => {
  try {
    console.log("[INFO]: Running Scraper");
    const gameTitle =  c.req.param('gameTitle')
    await service.getGamesInformationFromSources(gameTitle)
  } catch (error) {
    console.log(error);
    return c.body( 'could not scrape requested data',500, {
      'Content-Type': 'text/plain',
    })
  }

  return c.body( 'requested data scraped successfully', 200, {
    'Content-Type': 'text/plain',
  })

})

const port = 3000
console.log(`Server is running on http://localhost:${port}`)

serve({
  fetch: app.fetch,
  port
})