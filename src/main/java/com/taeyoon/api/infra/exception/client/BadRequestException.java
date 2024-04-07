package com.taeyoon.api.infra.exception.client;

public class BadRequestException extends RuntimeException {

    private final String[] messageArgs;

    public BadRequestException() {
        super();
        this.messageArgs = new String[0];
    }

    public BadRequestException(String msg, Throwable t) {
        super(msg, t);
        this.messageArgs = new String[0];
    }

    public BadRequestException(String msg) {
        super(msg);
        this.messageArgs = new String[0];
    }

    public BadRequestException(String msg, String... messageArgs) {
        super(msg);
        this.messageArgs = messageArgs;
    }
}