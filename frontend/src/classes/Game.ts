export interface GameData {
  id: string;
  url: string;
  title: string;
  price: number;
  pageName: string;
  scrapedAt: Date;
  imageUrl: string;
}

export class Game implements GameData {
  id: string;
  url: string;
  title: string;
  price: number;
  pageName: string;
  scrapedAt: Date;
  imageUrl: string;

  constructor(
    id: string,
    url: string,
    title: string,
    price: number,
    pageName: string,
    scrapedAt: Date,
    imageUrl: string,
  ) {
    this.id = id;
    this.url = url;
    this.title = title;
    this.price = price;
    this.pageName = pageName;
    this.scrapedAt = scrapedAt;
    this.imageUrl = imageUrl;
  }
}
