package com.taeyoon.api.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageUtils {
    private static MessageSource messageSource;
    private final MessageSource initMessageSource;
    @PostConstruct
    private void initStaticDao() {
        messageSource = this.initMessageSource;
    }
    public static String getMessage(String message) {
//        return getMessage(message, null,  LocaleUtils.getLocale(), (String[])null);
        return getMessage(message,  Locale.getDefault(), (String[])null);
    }
    public static String getMessage(String message, String... arguments) {
        return getMessage(message,  Locale.getDefault(), arguments);
    }
    public static String getMessage(String message, Locale locale, String... arguments) {
        // 차후 in memory or db 에서 조회하는 방식으로 변경
//        String msg = SpringUtils.getMessageService().findMessage(message, locale, arguments);
        String msg;
        try {
            msg = messageSource.getMessage(message, arguments, locale == null ? Locale.getDefault() : locale);
        } catch (Exception e) {
            msg = message;
        }
        return msg;
    }
}
