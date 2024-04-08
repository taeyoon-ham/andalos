package com.taeyoon.api.application.exception;

import lombok.Getter;

@Getter
public class EmailDuplicationException extends RuntimeException {

	private final String[] msgArgs;

	public EmailDuplicationException() {
		super();
		this.msgArgs = new String[0];
	}

	public EmailDuplicationException(String msg, Throwable t) {
		super(msg, t);
		this.msgArgs = new String[0];
	}

	public EmailDuplicationException(String msg) {
		super(msg);
		msgArgs = new String[0];
	}

	public EmailDuplicationException(String msg, String... msgArgs) {
		super(msg);
		this.msgArgs = msgArgs;
	}
}