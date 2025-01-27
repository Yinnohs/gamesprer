import { serve } from '@hono/node-server'
import { Hono } from 'hono'
import { scrapeData } from './scraper.js'

const app = new Hono()

app.get('/:gamename', async (c) => {

  try {

    const gameName =  c.req.param('gamename')
    await scrapeData(gameName)
    

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