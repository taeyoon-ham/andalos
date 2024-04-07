package com.taeyoon.api.infra.exception.client;

import java.util.List;

import org.springframework.hateoas.Link;

public class BadRequestException extends RuntimeException {

	private final List<Link> linkList;

	public BadRequestException(String msg) {
		super(msg);
		this.linkList = null;
	}

	public BadRequestException(String msg, Throwable t) {
		super(msg, t);
		this.linkList = null;
	}

	public BadRequestException(String msg, List<Link> linkList) {
		super(msg);
		this.linkList = linkList;
	}

	public List<Link> getLinkList() {
		return linkList;
	}
}