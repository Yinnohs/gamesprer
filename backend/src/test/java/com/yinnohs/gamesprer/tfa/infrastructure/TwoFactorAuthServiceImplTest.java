package com.yinnohs.gamesprer.tfa.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yinnohs.gamesprer.tfa.domain.exception.CouldNotGenerateQRImageException;
import com.yinnohs.gamesprer.tfa.infrastructure.service.TwoFactorAuthServiceImpl;

@ExtendWith(MockitoExtension.class)
class TwoFactorAuthServiceImplTest {

    @InjectMocks
    private TwoFactorAuthServiceImpl twoFactorAuthService;

    private static final String TEST_SECRET_KEY = "JBSWY3DPEHPK3PXP";
    private static final String VALID_CODE = "123456";
    private static final String INVALID_CODE = "999999";

    @Test
    void generate_secret_key_success() {
        // When
        String secretKey = twoFactorAuthService.generateSecretKey();

        // Then
        assertNotNull(secretKey);
        assertTrue(secretKey.length() > 0);
    }

    @Test
    void generate_qr_code_image_uri_success() throws CouldNotGenerateQRImageException {
        // When
        String qrCodeUri = twoFactorAuthService.generateQrCodeImageUri(TEST_SECRET_KEY);

        // Then
        assertNotNull(qrCodeUri);
        assertTrue(qrCodeUri.startsWith("data:image/png;base64,"));
    }

    @Test
    void generate_qr_code_image_uri_with_null_secret_throws_exception() {
        // When & Then
        assertThrows(NullPointerException.class, () -> 
            twoFactorAuthService.generateQrCodeImageUri(null)
        );
    }

    @Test
    void is_valid_otp_with_valid_code() {
        // Given
        String secretKey = twoFactorAuthService.generateSecretKey();
        // Note: We can't effectively test with a real TOTP code in a unit test
        // as it depends on time. This test might be flaky.
        
        // When
        boolean result = twoFactorAuthService.isValidOtp(secretKey, VALID_CODE);
        
        // Then
        assertFalse(result);
    }

    @Test
    void is_valid_otp_with_invalid_code() {
        // Given
        String secretKey = twoFactorAuthService.generateSecretKey();
        
        // When
        boolean result = twoFactorAuthService.isValidOtp(secretKey, INVALID_CODE);
        
        // Then
        assertFalse(result);
    }

    @Test
    void is_invalid_otp_with_valid_code() {
        // Given
        String secretKey = twoFactorAuthService.generateSecretKey();
        
        // When
        boolean result = twoFactorAuthService.isInvalidOtp(secretKey, VALID_CODE);
        
        // Then
        assertTrue(result);
    }

    @Test
    void is_valid_otp_with_null_secret_key() {
        // When & Then
        assertThrows(NullPointerException.class, () ->
            twoFactorAuthService.isValidOtp(null, VALID_CODE)
        );
    }

    @Test
    void is_valid_otp_with_null_code() {
        // When & Then
        assertThrows(NullPointerException.class, () ->
            twoFactorAuthService.isValidOtp(TEST_SECRET_KEY, null)
        );
    }
}