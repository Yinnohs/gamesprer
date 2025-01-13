package com.yinnohs.gamesprer.games.domain;

import java.util.List;

public interface GameInfoService {
    List<GameInfo> findGameWhereTitleNameSimilarTo(String gameTitle);
    List<GameInfo> findAllGamesFromToday();
    List<GameInfo> findExactGameByTitle(String title);
}
