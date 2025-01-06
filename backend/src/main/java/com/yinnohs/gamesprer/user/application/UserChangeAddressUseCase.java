package com.yinnohs.gamesprer.user.application;

import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.domain.port.in.UserService;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UserChangeAddressUseCase implements BiFunction<String, String, User> {
    private final UserService userService;

    @Override
    public User apply(String userId, String phoneNumber) {
        return userService.updateUserPhoneNumber(userId,phoneNumber);
    }
}
