package com.yinnohs.gamesprer.games.infrastructure.mapper;

import com.yinnohs.gamesprer.games.domain.model.Game;
import com.yinnohs.gamesprer.games.infrastructure.document.GameDocument;
import org.springframework.stereotype.Service;

@Service
public class GameMapper {

    public GameDocument toDocument(Game gameInfo){
        return GameDocument
                .builder()
                .id(gameInfo.getId())
                .title(gameInfo.getTitle())
                .url(gameInfo.getUrl())
                .price(gameInfo.getPrice())
                .pageName(gameInfo.getPageName())
                .scrapedAt(gameInfo.getScrapedAt())
                .imageUrl(gameInfo.getImageUrl())
                .build();
    }

    public Game toDomainModel(GameDocument gameInfo){
        return Game
                .builder()
                .id(gameInfo.getId())
                .title(gameInfo.getTitle())
                .url(gameInfo.getUrl())
                .price(gameInfo.getPrice())
                .pageName(gameInfo.getPageName())
                .imageUrl(gameInfo.getImageUrl())
                .scrapedAt(gameInfo.getScrapedAt())
                .build();
    }
}
