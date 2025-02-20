package com.yinnohs.gamesprer.notification.infrastructure.configuration.service;

import java.util.UUID;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.yinnohs.gamesprer.notification.domain.Notification;
import com.yinnohs.gamesprer.notification.infrastructure.dto.CreateNotificationRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendPrivateReFindNotification(CreateNotificationRequest message) {
        log.info("Sending private notification to user: {} to re-find games with title: ", message.userId(), message.gameToFindTitle()); 
        
        Notification notification = Notification.builder()
            .id(UUID.randomUUID().toString())
            .title(message.title())
            .message(message.message())
            .type(message.type())
            .UserId(message.userId())
            .gameToFindTitle(message.gameToFindTitle())
            .build();
        
        messagingTemplate.convertAndSendToUser(notification.getUserId(), "/topic/re-find", notification);
    }
}
