package com.yinnohs.gamesprer.user.infrastructure.dto;

public record UpdateUserAddressRequest(
        String userId,
        String userAddress
) {
}
