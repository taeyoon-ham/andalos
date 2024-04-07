package com.taeyoon.api.infra.security.authentication.provider;

public class AuthorizationNotFoundException extends RuntimeException {
    public AuthorizationNotFoundException(String msg) {
        super(msg);
    }
}
