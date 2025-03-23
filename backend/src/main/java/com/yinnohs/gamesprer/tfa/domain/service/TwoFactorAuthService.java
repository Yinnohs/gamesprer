package com.yinnohs.gamesprer.tfa.domain.service;

import com.yinnohs.gamesprer.tfa.domain.exception.CouldNotGenerateQRImageException;

public interface TwoFactorAuthService {
    public boolean isValidOtp(String secretKey, String code);
    public boolean isInvalidOtp(String secretKey, String code);
    public String generateQrCodeImageUri(String secretKey) throws CouldNotGenerateQRImageException;
    public String generateSecretKey();
    
}
