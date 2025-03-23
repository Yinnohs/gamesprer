package com.yinnohs.gamesprer.tfa.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yinnohs.gamesprer.tfa.application.GenerateMFAQrCode;
import com.yinnohs.gamesprer.tfa.application.GenerateMFASecretUseCase;
import com.yinnohs.gamesprer.tfa.domain.service.TwoFactorAuthService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TFABeanConfiguration {
    
    private final TwoFactorAuthService twoFactorAuthService;
    
    @Bean
    public GenerateMFASecretUseCase generateMFASecretUseCase(){
        return new GenerateMFASecretUseCase(twoFactorAuthService);
    }

    @Bean
    public GenerateMFAQrCode generateMFAQrCode(){
        return new GenerateMFAQrCode(twoFactorAuthService);
    }
}
