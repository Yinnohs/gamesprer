package com.yinnohs.gamesprer.games.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yinnohs.gamesprer.games.application.usecases.FindGamesBySimilarTitle;
import com.yinnohs.gamesprer.games.domain.ports.in.GameService;
import com.yinnohs.gamesprer.games.domain.ports.out.ApiService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GameBeanConfiguration {
    private final GameService service;
    private final ApiService apiService;
    @Bean
    public FindGamesBySimilarTitle findGamesBySimilarTitle() {
        return new FindGamesBySimilarTitle(service, apiService);
    }
}
