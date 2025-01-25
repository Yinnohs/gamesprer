package com.yinnohs.gamesprer.games.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yinnohs.gamesprer.games.application.usecases.FindGamesBySimilarTitle;
import com.yinnohs.gamesprer.games.domain.ports.in.GameService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GameBeanConfiguration {
    private final GameService service;
    @Bean
    public FindGamesBySimilarTitle findGamesBySimilarTitle() {
        return new FindGamesBySimilarTitle(service);
    }
}
