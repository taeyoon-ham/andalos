package com.taeyoon.api.infra.exception.client;

public class AuthorizationNotFoundException extends RuntimeException {
	public AuthorizationNotFoundException(String msg) {
		super(msg);
	}
}
