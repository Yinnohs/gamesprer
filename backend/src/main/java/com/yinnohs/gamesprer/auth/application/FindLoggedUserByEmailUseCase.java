package com.yinnohs.gamesprer.auth.application;

import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.domain.port.in.UserService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.function.Function;

@RequiredArgsConstructor
public class FindLoggedUserByEmailUseCase implements Function<String , User> {

    private final UserService userService;


    @Override
    public User apply(String email) {
        User user = userService.findBy("email", email);
        user.setLastLoginAt(LocalDateTime.now());
        return user;
    }
}
