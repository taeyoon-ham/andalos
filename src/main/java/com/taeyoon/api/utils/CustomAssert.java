package com.taeyoon.api.utils;

import com.taeyoon.api.infra.exception.client.InvalidArgumentException;

public class CustomAssert {
	public static void isTrue(boolean expression, String msg) {
		if (!expression) {
			throw new InvalidArgumentException(msg);
		}
	}

	public static void isTrue(boolean expression, String msg, String... msgArgs) {
		if (!expression) {
			throw new InvalidArgumentException(msg, msgArgs);
		}
	}

	public static void notNull(Object object, String msg) {
		if (object == null) {
			throw new InvalidArgumentException(msg);
		}
	}

	public static void notNull(Object object, String msg, String... msgArgs) {
		if (object == null) {
			throw new InvalidArgumentException(msg, msgArgs);
		}
	}
}
