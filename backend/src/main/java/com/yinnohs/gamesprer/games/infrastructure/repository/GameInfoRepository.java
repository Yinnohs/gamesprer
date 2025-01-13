package com.yinnohs.gamesprer.games.infrastructure.repository;

import com.yinnohs.gamesprer.games.domain.GameInfo;
import com.yinnohs.gamesprer.games.infrastructure.document.GameInfoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameInfoRepository extends MongoRepository<GameInfo ,String> {
    @Query("{'title': /.*?0.*, 'scrapedAt': {$gte: ?1}}")
    List<GameInfoDocument> findInfoWithSimilarTitle(String gameTitle, LocalDateTime currentDay);

    @Query("{'title': ?0, 'scrapedAt': {$gte: ?1}}")
    List<GameInfoDocument> findDataFromExactTitle(String gameTitle, LocalDateTime currentDay);
}
