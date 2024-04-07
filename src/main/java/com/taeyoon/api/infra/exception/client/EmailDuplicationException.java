package com.taeyoon.api.infra.exception.client;

public class EmailDuplicationException extends RuntimeException {

	private final String[] messageArgs;

	public EmailDuplicationException() {
		super();
		this.messageArgs = new String[0];
	}

	public EmailDuplicationException(String msg, Throwable t) {
		super(msg, t);
		this.messageArgs = new String[0];
	}

	public EmailDuplicationException(String msg) {
		super(msg);
		messageArgs = new String[0];
	}

	public EmailDuplicationException(String msg, String... messageArgs) {
		super(msg);
		this.messageArgs = messageArgs;
	}
}