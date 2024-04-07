package com.taeyoon.api.infra.exception.client;

public class ExpiredTokenException extends RuntimeException {
    private final String[] messageArgs;

    public ExpiredTokenException() {
        this.messageArgs = new String[0];
    }
    public ExpiredTokenException(String message) {
        super(message);
        this.messageArgs = new String[0];
    }

    public ExpiredTokenException(String msg, String... messageArgs) {
        super(msg);
        this.messageArgs = messageArgs;
    }
}
