package com.taeyoon.api.infra.exception;

import java.util.Date;
import java.util.Objects;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

@RestControllerAdvice(basePackages = "com.taeyoon.api.interfaces.rest")
@Slf4j
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {
	private final HttpServletRequest request;

	@ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<ErrorResponse> badRequestException(RuntimeException ex) {
		return res(ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<ErrorResponse> handleBookNotFound(RuntimeException ex) {
		return res(ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	/*
	"errors": [
		{
			"codes": [
				"NotBlank.reqSignUp.telNo",
				"NotBlank.telNo",
				"NotBlank.java.lang.String",
				"NotBlank"
			],
			"arguments": [
				{
					"codes": [
						"reqSignUp.telNo",
						"telNo"
					],
					"arguments": null,
					"defaultMessage": "telNo",
					"code": "telNo"
				}
			],
			"defaultMessage": "필수입력 항목 입니다.",
			"objectName": "reqSignUp",
			"field": "telNo",
			"rejectedValue": null,
			"bindingFailure": false,
			"code": "NotBlank"
		},
		{
			"codes": [
				"NotBlank.reqSignUp.lastName",
				"NotBlank.lastName",
				"NotBlank.java.lang.String",
				"NotBlank"
			],
			"arguments": [
				{
					"codes": [
						"reqSignUp.lastName",
						"lastName"
					],
					"arguments": null,
					"defaultMessage": "lastName",
					"code": "lastName"
				}
			],
			"defaultMessage": "필수입력 항목 입니다.",
			"objectName": "reqSignUp",
			"field": "lastName",
			"rejectedValue": null,
			"bindingFailure": false,
			"code": "NotBlank"
		},
		{
			"codes": [
				"NotBlank.reqSignUp.firstName",
				"NotBlank.firstName",
				"NotBlank.java.lang.String",
				"NotBlank"
			],
			"arguments": [
				{
					"codes": [
						"reqSignUp.firstName",
						"firstName"
					],
					"arguments": null,
					"defaultMessage": "firstName",
					"code": "firstName"
				}
			],
			"defaultMessage": "필수입력 항목 입니다.",
			"objectName": "reqSignUp",
			"field": "firstName",
			"rejectedValue": null,
			"bindingFailure": false,
			"code": "NotBlank"
		},
		{
			"codes": [
				"NotBlank.reqSignUp.countryCode",
				"NotBlank.countryCode",
				"NotBlank.java.lang.String",
				"NotBlank"
			],
			"arguments": [
				{
					"codes": [
						"reqSignUp.countryCode",
						"countryCode"
					],
					"arguments": null,
					"defaultMessage": "countryCode",
					"code": "countryCode"
				}
			],
			"defaultMessage": "필수입력 항목 입니다.",
			"objectName": "reqSignUp",
			"field": "countryCode",
			"rejectedValue": null,
			"bindingFailure": false,
			"code": "NotBlank"
		}
	],
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<ErrorResponse> methodArgumentNotValidException(final MethodArgumentNotValidException ex) {
		String field = ((DefaultMessageSourceResolvable)Objects.requireNonNull(
			ex.getBindingResult().getAllErrors().getFirst().getArguments())[0]).getDefaultMessage();
		//        String message = MessageUtils.getMessage(ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage(), MessageConstant.MESSAGE_ERROR_BAD_REQUEST);
		String message = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
		return res(ex.getClass().getSimpleName(), "[" + field + "] " + message, HttpStatus.BAD_REQUEST);
	}

	@NotNull
	private ResponseEntity<ErrorResponse> res(String error, String errorMessage, HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus.value())
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
