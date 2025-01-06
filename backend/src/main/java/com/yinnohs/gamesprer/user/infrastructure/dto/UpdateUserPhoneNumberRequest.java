package com.yinnohs.gamesprer.user.infrastructure.dto;

public record UpdateUserPhoneNumberRequest(
        String userId,
        String phoneNumber
) {
}
