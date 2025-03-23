package com.yinnohs.gamesprer.tfa.infrastructure.service;

import org.springframework.stereotype.Service;

import com.yinnohs.gamesprer.tfa.domain.exception.CouldNotGenerateQRImageException;
import com.yinnohs.gamesprer.tfa.domain.service.TwoFactorAuthService;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.samstevens.totp.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TwoFactorAuthServiceImpl implements TwoFactorAuthService {

    public String generateSecretKey(){
        return new DefaultSecretGenerator().generate();
    }

    public String generateQrCodeImageUri(String secretKey) throws CouldNotGenerateQRImageException{
        if(secretKey == null){
            throw new NullPointerException("Secret key cannot be null");
        }
        QrData data = new QrData.Builder()
        .label("GAMESPRER - 2FA")
        .secret(secretKey)
        .issuer("gamesprer")
        .algorithm(HashingAlgorithm.SHA256)
        .digits(6)
        .period(30)
        .build();

        QrGenerator generator = new ZxingPngQrGenerator();
        byte[] imageData = new byte[0];
        try {
            imageData = generator.generate(data);
        } catch (QrGenerationException e) {
            log.error("Error generating QR code", e);
            throw new CouldNotGenerateQRImageException(e.getCause());
        }
        
        return Utils.getDataUriForImage(imageData, generator.getImageMimeType());
    }

    public boolean isValidOtp(String secretKey, String code){
        if(secretKey == null){
            throw new NullPointerException("Secret key cannot be null");
        }
        if(code == null){
            throw new NullPointerException("Code cannot be null");
        }
        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        return verifier.isValidCode(secretKey, code);
    }

    public boolean isInvalidOtp(String secretKey, String code){
        if(secretKey == null){
            throw new NullPointerException("Secret key cannot be null");
        }
        if(code == null){
            throw new NullPointerException("Code cannot be null");
        }
        return !isValidOtp(secretKey, code);
    }
}
