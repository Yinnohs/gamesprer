package com.yinnohs.gamesprer.user.application;

import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.domain.port.in.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class FindAllUsersUseCase implements Function<Void, List<User>> {

    private final UserService userService;

    @Override
    public List<User> apply(Void unused) {
        return userService.findAll();
    }
}
