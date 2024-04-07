package com.taeyoon.api.infra.exception.client;


public class InvalidArgumentException extends RuntimeException {

    private final String[] messageArgs;

    public InvalidArgumentException() {
        super();
        this.messageArgs = new String[0];
    }

    public InvalidArgumentException(String msg, Throwable t) {
        super(msg, t);
        this.messageArgs = new String[0];
    }

    public InvalidArgumentException(String msg) {
        super(msg);
        messageArgs = new String[0];
    }

    public InvalidArgumentException(String msg, String... messageArgs) {
        super(msg);
        this.messageArgs = messageArgs;
    }
}