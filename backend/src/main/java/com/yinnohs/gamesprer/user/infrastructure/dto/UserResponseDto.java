package com.yinnohs.gamesprer.user.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDto(
        String id,
        String name,
        String surname,
        String email,
        String address,
        String phoneNumber,
        String refreshToken,
        boolean mfaEnabled,
        String secretImageUri
) {
}
