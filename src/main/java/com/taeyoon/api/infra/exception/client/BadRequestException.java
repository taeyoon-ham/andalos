package com.taeyoon.api.infra.exception.client;

import java.util.List;

import org.springframework.hateoas.Link;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

	private final String[] msgArgs;
	private final List<Link> linkList;

	public BadRequestException(String msgKey) {
		super(msgKey);
		this.msgArgs = null;
		this.linkList = null;
	}

	public BadRequestException(String msgKey, String... msgArgs) {
		super(msgKey);
		this.msgArgs = msgArgs;
		this.linkList = null;
	}

	public BadRequestException(String msgKey, List<Link> linkList) {
		super(msgKey);
		this.msgArgs = null;
		this.linkList = linkList;
	}

	public BadRequestException(String msgKey, List<Link> linkList, String... msgArgs) {
		super(msgKey);
		this.msgArgs = msgArgs;
		this.linkList = linkList;
	}
}