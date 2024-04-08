package com.taeyoon.api.infra.constants;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MsgUtils {
	private static MessageSource messageSource;
	private final MessageSource initMessageSource;

	@PostConstruct
	private void initStaticDao() {
		messageSource = this.initMessageSource;
	}

	public static String getMessage(String msgKey) {
		//        return getMessage(message, null,  LocaleUtils.getLocale(), (String[])null);
		return getMessage(msgKey, LocaleContextHolder.getLocale(), (String[])null);
	}

	public static String getMessage(String msgKey, @Nullable String... args) {
		return getMessage(msgKey, LocaleContextHolder.getLocale(), args);
	}

	public static String getMessage(String msgKey, Locale locale, @Nullable String... args) {
		// todo 차후 in memory or db 에서 조회하는 방식으로 변경
		//        String msg = SpringUtils.getMessageService().findMessage(message, locale, arguments);
		log.info("locale={}", LocaleContextHolder.getLocale());
		String msg;
		try {
			msg = messageSource.getMessage(msgKey, args, locale == null ? LocaleContextHolder.getLocale() : locale);
		} catch (Exception e) {
			msg = msgKey;
		}
		return msg;
	}
}
