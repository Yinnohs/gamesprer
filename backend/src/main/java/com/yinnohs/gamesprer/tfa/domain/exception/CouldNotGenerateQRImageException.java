package com.yinnohs.gamesprer.tfa.domain.exception;

public class CouldNotGenerateQRImageException extends RuntimeException {
    public CouldNotGenerateQRImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouldNotGenerateQRImageException(String message) {
        super(message);
    }

    public CouldNotGenerateQRImageException(Throwable cause) {
        super(cause);
    }
}
