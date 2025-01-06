package com.yinnohs.gamesprer.user.application;

import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.domain.port.in.UserService;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;


@RequiredArgsConstructor
public class FindSpecificUserUseCase implements BiFunction<String, String , User> {

    private final UserService userService;

    @Override
    public User apply(String findType, String value) {
        String sanitizeType = findType.toLowerCase().trim();
        return userService.findBy(sanitizeType, value);
    }
}
