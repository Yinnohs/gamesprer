package com.yinnohs.gamesprer.user.infrastructure.dto;

public record UpdateUserEmailRequest(
        String userId,
        String email
) {
}
