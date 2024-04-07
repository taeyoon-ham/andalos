package com.taeyoon.api.infra.exception.server;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    public InternalServerErrorException(String msg) {
        super(msg);
    }

    public InternalServerErrorException() {
        super();
    }
}
