package com.yinnohs.gamesprer.user.infrastructure.dto;

public record UserResponseDto(
        String id,
        String name,
        String surname,
        String email,
        String address,
        String phoneNumber,
        String refreshToken
) {
}
