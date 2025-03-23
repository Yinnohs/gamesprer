package com.yinnohs.gamesprer.games.application.usecases;

import java.util.List;

import com.yinnohs.gamesprer.games.domain.exceptions.CouldNotGetGamesException;
import com.yinnohs.gamesprer.games.domain.model.Game;
import com.yinnohs.gamesprer.games.domain.ports.in.GameService;
import com.yinnohs.gamesprer.games.domain.ports.out.ApiService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindGamesBySimilarTitle {

    private final GameService gameService;
    private final ApiService apiService;

    public List<Game> execute(String title, String userId) {
        var games = gameService.findGameBySimilarTitle(title);
        if (games.isEmpty()) {
            executeScraper(title, userId);
            games = gameService.findGameBySimilarTitle(title);
        }
        return games;
    }

    private void executeScraper(String gameTitle, String userId){
        try {
            apiService.signalScraper(gameTitle, userId);
        } catch (Exception e) {
            throw new CouldNotGetGamesException(e);
        }
    }
}
