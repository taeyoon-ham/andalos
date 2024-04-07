package com.taeyoon.api.infra.exception.server;

public class BadGatewayException extends RuntimeException {

    public BadGatewayException(String msg, Throwable t) {
        super(msg, t);
    }

    public BadGatewayException(String msg) {
        super(msg);
    }

    public BadGatewayException() {
        super();
    }
}
