package com.yinnohs.gamesprer.user.application;

import com.yinnohs.gamesprer.user.domain.port.in.UserService;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteUserUseCase implements Function<String, Void> {
    private final UserService userService;

    @Override
    public Void apply(String userId) {
        userService.deleteUserById(userId);
        return null;
    }
}
