package com.taeyoon.api.infra.exception.client;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UnAuthorizedException(String msg) {
        super(msg);
    }

    public UnAuthorizedException() {
        super();
    }
}
