package com.taeyoon.api.infra.exception.client;

public class InvalidTokenException extends RuntimeException {

    private final String[] messageArgs;

    public InvalidTokenException() {
        this.messageArgs = new String[0];
    }
    public InvalidTokenException(String message) {
        super(message);
        this.messageArgs = new String[0];
    }

    public InvalidTokenException(String message, String... messageArgs) {
        super(message);
        this.messageArgs = messageArgs;
    }
}
