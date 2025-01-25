package com.yinnohs.gamesprer.games.infrastructure.repository;

import com.yinnohs.gamesprer.games.infrastructure.document.GameDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GameDocumentRepository extends MongoRepository<GameDocument ,String> {
    @Query(value = "{ 'title': { '$regex': '?0', '$options': 'i' }, 'scrapedAt': { $gte: ?1 }, 'price':{ $gte: 0.5 } }", sort = "{ 'price': 1 }")
    List<GameDocument> findInfoWithSimilarTitle(String gameTitle, Date currentDayHour);

    @Query("{'scrapedAt': { $gte: ?0 }}")
    List<GameDocument> findCurrentDateGameInfo(Date currentDayHour);
}
