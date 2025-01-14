package com.yinnohs.gamesprer.games.infrastructure.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    @Value("${api.url}")
    private String serviceUrl;

    public String callExternalApi(String gameTitle){
        ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl + "/" + gameTitle , String.class);
        return response.getBody();
    }
}
