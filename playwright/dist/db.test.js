import { v7 } from "uuid";
import { persistGameData } from "./db.js";
const dataTotest = [{
        id: v7(),
        url: 'https://www.humblebundle.com/store/hyperdevotion-noire-goddess-black-heart',
        title: 'Hyperdevotion Noire: Goddess Black Heart',
        price: 12.49,
        pageName: 'humbleBundle',
        scrapedAt: new Date()
    },];
persistGameData(dataTotest);
