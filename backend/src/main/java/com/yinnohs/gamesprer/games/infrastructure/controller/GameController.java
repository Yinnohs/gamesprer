package com.yinnohs.gamesprer.games.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinnohs.gamesprer.games.application.usecases.FindGamesBySimilarTitle;


@RestController
@RequestMapping("/api/v1/gameinfo")
@RequiredArgsConstructor
public class GameController {

    private final FindGamesBySimilarTitle findGamesBySimilarTitleUseCase;

    @GetMapping("{gameTitle}")
    public ResponseEntity<?> findAllGamesData(@PathVariable("gameTitle")  String gameTitle, @RequestParam("userId") String userId){
        var data = findGamesBySimilarTitleUseCase.execute(gameTitle, userId);
        return ResponseEntity.ok(data);
    }
}
