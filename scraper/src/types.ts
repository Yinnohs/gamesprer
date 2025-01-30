export type GamesData = {
    id:string
    url: string
    title: string
    price: number
    pageName: string
    scrapedAt: Date
    imageUrl: string
}

export type OmitedIdGameData = Omit<GamesData, 'id'>