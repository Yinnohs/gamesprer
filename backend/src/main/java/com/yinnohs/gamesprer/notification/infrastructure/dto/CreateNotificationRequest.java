package com.yinnohs.gamesprer.notification.infrastructure.dto;

public record CreateNotificationRequest(
    String title,
    String message,
    String type,
    String userId,
    String gameToFindTitle
) {}
