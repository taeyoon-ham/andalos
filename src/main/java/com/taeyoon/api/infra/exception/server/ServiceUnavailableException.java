package com.taeyoon.api.infra.exception.server;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String msg, Throwable t) {
        super(msg, t);
    }

    public ServiceUnavailableException(String msg) {
        super(msg);
    }

    public ServiceUnavailableException() {
        super();
    }
}
