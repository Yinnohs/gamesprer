package com.yinnohs.gamesprer.games.infrastructure.service;

import com.yinnohs.gamesprer.games.domain.GameInfo;
import com.yinnohs.gamesprer.games.domain.GameInfoService;
import com.yinnohs.gamesprer.games.infrastructure.mapper.GameInfoMapper;
import com.yinnohs.gamesprer.games.infrastructure.repository.GameInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameInfoServiceImpl implements GameInfoService {

    private final GameInfoRepository repository;
    private final GameInfoMapper mapper;

    @Override
    public List<GameInfo> findGameWhereTitleNameSimilarTo(String gameTitle) {
        var gameList =  repository.findInfoWithSimilarTitle(gameTitle, getCurrentDay())
                .stream()
                .map(mapper::toDomainModel)
                .toList();

        if (gameList.isEmpty()){
            executeScraper(gameTitle);
        }

        return gameList;
    }

    @Override
    public List<GameInfo> findAllGamesFromToday() {
        return List.of();
    }

    @Override
    public List<GameInfo> findExactGameByTitle(String gameTitle) {
        var gameList =  repository.findDataFromExactTitle(gameTitle, getCurrentDay())
                .stream()
                .map(mapper::toDomainModel)
                .toList();

        if (gameList.size() == 0){
            executeScraper(gameTitle);
        }

        return gameList;
    }

    private void executeScraper(String gameTitle){
        try {
            Process process = Runtime.getRuntime().exec("npm scrapper " + gameTitle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDateTime getCurrentDay (){
        return  LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
    }
}
