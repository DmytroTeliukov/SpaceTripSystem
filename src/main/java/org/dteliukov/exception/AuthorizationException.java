package org.dteliukov.exception;

public class AuthorizationException extends Exception{

    private final String message;

    public AuthorizationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
