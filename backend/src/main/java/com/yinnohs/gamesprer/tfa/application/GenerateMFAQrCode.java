package com.yinnohs.gamesprer.tfa.application;

import com.yinnohs.gamesprer.tfa.domain.service.TwoFactorAuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerateMFAQrCode {

    private final TwoFactorAuthService twoFactorAuthService;

    public String execute(String secretKey){
        return twoFactorAuthService.generateQrCodeImageUri(secretKey);
    }
}
