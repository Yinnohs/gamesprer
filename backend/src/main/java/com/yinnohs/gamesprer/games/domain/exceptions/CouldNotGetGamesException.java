package com.yinnohs.gamesprer.games.domain.exceptions;

public class CouldNotGetGamesException extends RuntimeException{
    public CouldNotGetGamesException(String message) {
        super(message);
    }

    public CouldNotGetGamesException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouldNotGetGamesException(Throwable cause) {
        super(cause);
    }
}
