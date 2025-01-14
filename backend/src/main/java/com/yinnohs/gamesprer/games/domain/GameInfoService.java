package com.yinnohs.gamesprer.games.domain;

import java.util.List;

public interface GameInfoService {
    List<GameInfo> findGameInfoByTitleAndScrap(String gameTitle);
    List<GameInfo> findAllGamesFromToday();
}
