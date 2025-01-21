import { v7 } from "uuid"
import { persistGameData } from "./db.js"
import type { GamesData } from "./types.ts"

const dataTotest: GamesData[] = [{
    id: v7(),
    url: 'https://www.humblebundle.com/store/hyperdevotion-noire-goddess-black-heart',
    title: 'Hyperdevotion Noire: Goddess Black Heart',
    price: 12.49,
    pageName: 'humbleBundle',
    scrapedAt: new Date(),
    imageUrl: 'https://some-testing-image'
  },]

  persistGameData(dataTotest)