package com.yinnohs.gamesprer.games.application.usecases;

import java.util.List;

import com.yinnohs.gamesprer.games.domain.Game;
import com.yinnohs.gamesprer.games.domain.GameService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindGamesBySimilarTitle {

    private final GameService gameService;

    public List<Game> execute(String title) {
        return gameService.findGameBySimilarTitle(title);
    }
}
