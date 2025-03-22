package com.yinnohs.gamesprer.notification.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yinnohs.gamesprer.notification.infrastructure.dto.CreateNotificationRequest;
import com.yinnohs.gamesprer.notification.infrastructure.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/api/v1/scraper-notifications")
@RequiredArgsConstructor
@Slf4j
public class ScraperNotificationController {
    
    private final NotificationService notificationService;

    @PostMapping("/notify/user")
    public ResponseEntity<?> postMethodName(@RequestBody CreateNotificationRequest request) {
        log.info("Received notification request for user: {}", request.userId());
        notificationService.sendPrivateReFindNotification(request);
        return ResponseEntity.ok("Notification Sent to:" + request.userId());
    }

    // Add test endpoint
    @GetMapping("/test-connection/{userId}")
    public ResponseEntity<?> testConnection(@PathVariable String userId) {
        CreateNotificationRequest testRequest = new CreateNotificationRequest(
            "Test Connection",
            "Testing WebSocket connection",
            "TEST",
            userId,
            "Test Game"
        );
        notificationService.sendPrivateReFindNotification(testRequest);
        return ResponseEntity.ok("Test notification sent");
    }
}
