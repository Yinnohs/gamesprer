package com.yinnohs.gamesprer.games.infrastructure.repository;

import com.yinnohs.gamesprer.games.domain.GameInfo;
import com.yinnohs.gamesprer.games.infrastructure.document.GameInfoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GameInfoRepository extends MongoRepository<GameInfo ,String> {
    @Query("{'title': { '$regex': '?0', '$options': 'i' }, 'scrapedAt': { $gte: ?1 }}")
    List<GameInfoDocument> findInfoWithSimilarTitle(String gameTitle, Date currentDayHour);

    @Query("{'scrapedAt': { $gte: ?0 }}")
    List<GameInfoDocument> findCurrentDateGameInfo(Date currentDayHour);
}
