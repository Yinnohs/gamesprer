package com.yinnohs.gamesprer.notification.infrastructure.controller;

import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinnohs.gamesprer.notification.domain.Notification;
import com.yinnohs.gamesprer.notification.infrastructure.dto.CreateNotificationRequest;


public class NotificationController {

    @MessageMapping("/re-find")
    @SendTo("/topic/re-find")
    public String sendReFindNotification(CreateNotificationRequest message) throws JsonProcessingException {
        
        Notification notification = Notification.builder()
            .id(UUID.randomUUID().toString())
            .title(message.title())
            .message(message.message())
            .type(message.type())
            .UserId(message.userId())
            .gameToFindTitle(message.gameToFindTitle())
            .build();

        String jsonNotification = transformToJsoString(notification);
        return jsonNotification;
    }

    public String transformToJsoString(Notification notification) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(notification);
        return message;
    }


}
