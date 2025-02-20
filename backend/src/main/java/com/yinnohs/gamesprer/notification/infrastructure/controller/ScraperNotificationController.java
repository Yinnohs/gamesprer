package com.yinnohs.gamesprer.notification.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinnohs.gamesprer.notification.infrastructure.configuration.service.NotificationService;
import com.yinnohs.gamesprer.notification.infrastructure.dto.CreateNotificationRequest;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/api/v1/scraper-notification")
@RequiredArgsConstructor
public class ScraperNotificationController {
    
    private final NotificationService notificationService;

    @PostMapping("/notify/user")
    public ResponseEntity<?> postMethodName(@RequestBody CreateNotificationRequest request) {
        notificationService.sendPrivateReFindNotification(request);
        return ResponseEntity.ok().build();
    }
    
}
