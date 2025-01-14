package com.yinnohs.gamesprer.games.infrastructure.service;

import com.yinnohs.gamesprer.games.domain.GameInfo;
import com.yinnohs.gamesprer.games.domain.GameInfoService;
import com.yinnohs.gamesprer.games.infrastructure.mapper.GameInfoMapper;
import com.yinnohs.gamesprer.games.infrastructure.repository.GameInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameInfoServiceImpl implements GameInfoService {

    private final GameInfoRepository repository;
    private final GameInfoMapper mapper;
    private final ApiService api;


    @Override
    public List<GameInfo> findGameInfoByTitleAndScrap(String gameTitle) {
        var gameList = findGameWhereTitleNameSimilarTo(gameTitle);

        if (gameList.isEmpty()){
            executeScraper(gameTitle);
            gameList = findGameWhereTitleNameSimilarTo(gameTitle);
        }

        return  gameList;
    }

    @Override
    public List<GameInfo> findAllGamesFromToday() {
        var currentDate = getCurrentDate();
        return repository
                .findCurrentDateGameInfo(currentDate)
                .stream()
                .map(mapper::toDomainModel)
                .toList();
    }

    private List<GameInfo> findGameWhereTitleNameSimilarTo(String gameTitle) {
        var currentDate = getCurrentDate();
        return repository.findInfoWithSimilarTitle(gameTitle+".*", currentDate )
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
