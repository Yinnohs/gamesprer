package com.yinnohs.gamesprer.games.infrastructure.service;

import com.yinnohs.gamesprer.games.domain.model.Game;
import com.yinnohs.gamesprer.games.domain.ports.in.GameService;

import com.yinnohs.gamesprer.games.infrastructure.mapper.GameMapper;
import com.yinnohs.gamesprer.games.infrastructure.repository.GameDocumentRepository;
import lombok.RequiredArgsConstructor;

import org.bson.types.Decimal128;
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

    @Override
    public List<Game> findGameBySimilarTitle(String gameTitle) {
        var currentDate = getCurrentDate();
        var minPrice = getMinPrice();
        return repository.findInfoWithSimilarTitle(gameTitle , currentDate, minPrice)
                .stream()
                .map(mapper::toDomainModel)
                .toList();
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

    private Decimal128 getMinPrice(){
        return Decimal128.parse("0.5");
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
