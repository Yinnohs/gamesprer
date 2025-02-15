package com.yinnohs.gamesprer.games.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinnohs.gamesprer.games.application.usecases.FindGamesBySimilarTitle;


@RestController
@RequestMapping("/api/v1/gameinfo")
@RequiredArgsConstructor
public class GameController {

    private final FindGamesBySimilarTitle findGamesBySimilarTitleUseCase;

    @GetMapping("{gameTitle}")
    public ResponseEntity<?> findAllGamesData(@PathVariable("gameTitle")  String gameTitle){
        var data = findGamesBySimilarTitleUseCase.execute(gameTitle);
        return ResponseEntity.ok(data);
    }
}
