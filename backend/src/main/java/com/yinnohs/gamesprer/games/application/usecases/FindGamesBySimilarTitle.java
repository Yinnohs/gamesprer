package com.yinnohs.gamesprer.games.application.usecases;

import java.util.List;

import com.yinnohs.gamesprer.games.domain.model.Game;
import com.yinnohs.gamesprer.games.domain.ports.in.GameService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindGamesBySimilarTitle {

    private final GameService gameService;

    public List<Game> execute(String title) {
        return gameService.findGameBySimilarTitle(title);
    }
}
