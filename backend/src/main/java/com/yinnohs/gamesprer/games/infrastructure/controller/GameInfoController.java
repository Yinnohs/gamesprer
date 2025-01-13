package com.yinnohs.gamesprer.games.infrastructure.controller;

import com.yinnohs.gamesprer.games.domain.GameInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gameinfo")
@RequiredArgsConstructor
public class GameInfoController {

    private final GameInfoService service;

    @GetMapping("{gameTitle}")
    public ResponseEntity<?> findAllGamesData(@PathVariable("gameTitle")  String gameTitle){
        var data = service.findGameWhereTitleNameSimilarTo(gameTitle);
        return ResponseEntity.ok(data);
    }
}
