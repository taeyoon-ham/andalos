package com.taeyoon.api.infra.exception;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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

	@ExceptionHandler({BadRequestException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<EntityModel<ErrorResponse>> badRequestException(BadRequestException ex) {
		return res(ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.BAD_REQUEST, ex.getLinkList());
	}

	@ExceptionHandler({IllegalArgumentException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<EntityModel<ErrorResponse>> illegalArgumentException(IllegalArgumentException ex) {
		return res(ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.BAD_REQUEST, null);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ApiResponse(responseCode = "400", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<EntityModel<ErrorResponse>> methodArgumentNotValidException(
		final MethodArgumentNotValidException ex) {
		String field = ((DefaultMessageSourceResolvable)Objects.requireNonNull(
			ex.getBindingResult().getAllErrors().getFirst().getArguments())[0]).getDefaultMessage();
		String message = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
		return res(ex.getClass().getSimpleName(), "[" + field + "] " + message, HttpStatus.BAD_REQUEST, null);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<EntityModel<ErrorResponse>> notFoundException(NotFoundException ex) {
		return res(ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.NOT_FOUND, null);
	}

	@NotNull
	private ResponseEntity<EntityModel<ErrorResponse>> res(String error, String errorMessage, HttpStatus httpStatus,
		List<Link> linkList) {
		EntityModel<ErrorResponse> em = EntityModel.of(ErrorResponse.builder()
			.timestamp(new Date())
			.status(httpStatus.value())
			.error(error)
			.message(errorMessage)
			.path(request.getRequestURI())
			.build());
		if (linkList != null) {
			for (Link link : linkList) {
				em.add(link);
			}
		}
		return ResponseEntity.status(httpStatus.value())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body(em);
	}
}
