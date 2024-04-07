package com.taeyoon.api.interfaces.rest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Slf4j
public class CommonController {

    protected final <T> ResponseEntity<T> res(T data) {
        int status = HttpServletResponse.SC_OK;
        return ResponseEntity.status(status)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(data);
    }
}
