package com.taeyoon.api.infra.exception;

import com.taeyoon.api.infra.exception.client.BadRequestException;
import com.taeyoon.api.infra.exception.client.NotFoundException;
import com.taeyoon.api.infra.exception.server.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Date;

@RestControllerAdvice(basePackages = "com.taeyoon.api.interfaces.rest")
@Slf4j
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {
    private final HttpServletRequest request;
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<ErrorResponse> badRequestException(RuntimeException ex) {
        return res(ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "Not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<ErrorResponse> handleBookNotFound(RuntimeException ex) {
        return res(ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @NotNull
    private ResponseEntity<ErrorResponse> res(String error, String errorMessage, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus.value())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(ErrorResponse.builder()
                        .timestamp(new Date())
                        .status(httpStatus.value())
                        .error(error)
                        .message(errorMessage)
                        .path(request.getRequestURI())
                        .build());
    }
}
