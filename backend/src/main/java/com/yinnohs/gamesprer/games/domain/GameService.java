package com.yinnohs.gamesprer.games.domain;

import java.util.List;

public interface GameService {
    List<Game> findGameBySimilarTitle(String gameTitle);
    List<Game> findAllGamesFromToday();
}
