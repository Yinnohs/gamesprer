package com.yinnohs.gamesprer.games.infrastructure.service;

import com.yinnohs.gamesprer.games.domain.Game;
import com.yinnohs.gamesprer.games.domain.GameService;
import com.yinnohs.gamesprer.games.infrastructure.mapper.GameMapper;
import com.yinnohs.gamesprer.games.infrastructure.repository.GameDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameInfoServiceImpl implements GameService {

    private final GameDocumentRepository repository;
    private final GameMapper mapper;
    private final ApiService api;


    @Override
    public List<Game> findGameBySimilarTitle(String gameTitle) {
        var gameList = findGameWhereTitleNameSimilarTo(gameTitle);

        if (gameList.isEmpty()){
            executeScraper(gameTitle);
            gameList = findGameWhereTitleNameSimilarTo(gameTitle);
        }

        return  gameList;
    }

    @Override
    public List<Game> findAllGamesFromToday() {
        var currentDate = getCurrentDate();
        return repository
                .findCurrentDateGameInfo(currentDate)
                .stream()
                .map(mapper::toDomainModel)
                .toList();
    }

    private List<Game> findGameWhereTitleNameSimilarTo(String gameTitle) {
        var currentDate = getCurrentDate();
        return repository.findInfoWithSimilarTitle(gameTitle + ".*", currentDate )
                .stream()
                .map(mapper::toDomainModel)
                .toList();
    }

    private void executeScraper(String gameTitle){
        try {
            api.callExternalApi(gameTitle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Date getCurrentDate (){
        var currentDate =  LocalDateTime.now()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .toLocalDate();

        return Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
