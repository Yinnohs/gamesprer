package com.yinnohs.gamesprer.user.application;

import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.domain.port.in.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateRefreshTokenUseCase {

    private final UserService userService;

    public void apply(String refreshToken, String userId){
        User user = userService.findBy("id", userId);
        user.setRefreshToken(refreshToken);
        userService.updateRefreshToken(user);
    }
    
}
