package com.yinnohs.gamesprer.auth.infrastructure.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Valid
@ConfigurationProperties(prefix = "rsa")
public record RsaKeyConfigProperties(
        @NotNull
        RSAPublicKey publicKey,
        @NotNull
        RSAPrivateKey privateKey
) {
}
