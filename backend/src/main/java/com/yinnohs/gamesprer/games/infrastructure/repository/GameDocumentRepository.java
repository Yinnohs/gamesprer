package com.yinnohs.gamesprer.games.infrastructure.repository;

import com.yinnohs.gamesprer.games.infrastructure.document.GameDocument;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GameDocumentRepository extends MongoRepository<GameDocument ,String> {
    @Query(value = "{ 'title': { '$regex': '?0', '$options': 'i' }, 'scrapedAt': { $gte: ?1 }, 'price':{ $gte: ?2 } }", sort = "{ 'price': 1 }")
    List<GameDocument> findInfoWithSimilarTitle(String gameTitle, Date currentDayHour, Decimal128 minPrice);

    @Query(value = "{'scrapedAt': { $gte: ?0 } }", sort = "{ 'price': 1 }")
    List<GameDocument> findCurrentDateGameInfo(Date currentDayHour);
}
