package com.yinnohs.gamesprer.games.domain.ports.in;

import java.util.List;

import com.yinnohs.gamesprer.games.domain.model.Game;

public interface GameService {
    List<Game> findGameBySimilarTitle(String gameTitle);
    List<Game> findAllGamesFromToday();
}
