package com.yinnohs.gamesprer.tfa.application;

import com.yinnohs.gamesprer.tfa.domain.service.TwoFactorAuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerateMFASecretUseCase {
    private final TwoFactorAuthService twoFactorAuthService;

    public String execute(){
        return twoFactorAuthService.generateSecretKey();
    }
}
