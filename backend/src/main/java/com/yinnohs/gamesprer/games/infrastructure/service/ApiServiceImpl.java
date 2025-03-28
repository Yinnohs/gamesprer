package com.yinnohs.gamesprer.games.infrastructure.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yinnohs.gamesprer.games.domain.ports.out.ApiService;

@Service
public class ApiServiceImpl implements ApiService {
    private final RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    @Value("${api.url}")
    private String serviceUrl;

    @Override
    public String signalScraper(String gameTitle, String userId){
        ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl + "/" + gameTitle + "/?userId=" + userId, String.class);
        return response.getBody();
    }

    
}
