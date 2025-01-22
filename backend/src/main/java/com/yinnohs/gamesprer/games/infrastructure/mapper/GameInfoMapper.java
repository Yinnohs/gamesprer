package com.yinnohs.gamesprer.games.infrastructure.mapper;

import com.yinnohs.gamesprer.games.domain.GameInfo;
import com.yinnohs.gamesprer.games.infrastructure.document.GameInfoDocument;
import org.springframework.stereotype.Service;

@Service
public class GameInfoMapper {

    public GameInfoDocument toDocument(GameInfo gameInfo){
        return GameInfoDocument
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

    public GameInfo toDomainModel(GameInfoDocument gameInfo){
        return GameInfo
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
