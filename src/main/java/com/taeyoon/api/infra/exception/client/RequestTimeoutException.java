package com.taeyoon.api.infra.exception.client;

public class RequestTimeoutException extends RuntimeException {

    public RequestTimeoutException(String msg, Throwable t) {
        super(msg, t);
    }

    public RequestTimeoutException(String msg) {
        super(msg);
    }

    public RequestTimeoutException() {
        super();
    }
}
