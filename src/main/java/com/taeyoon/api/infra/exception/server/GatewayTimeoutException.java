package com.taeyoon.api.infra.exception.server;

public class GatewayTimeoutException extends RuntimeException {

    public GatewayTimeoutException(String msg, Throwable t) {
        super(msg, t);
    }

    public GatewayTimeoutException(String msg) {
        super(msg);
    }

    public GatewayTimeoutException() {
        super();
    }
}
