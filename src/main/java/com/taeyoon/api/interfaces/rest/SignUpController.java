package com.taeyoon.api.interfaces.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taeyoon.api.application.dto.SignUpDto;
import com.taeyoon.api.application.exception.EmailDuplicationException;
import com.taeyoon.api.application.service.UserService;
import com.taeyoon.api.infra.exception.client.BadRequestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "회원인증 API", description = "회원가입 API 입니다.")
public class SignUpController extends CommonController {
	private final UserService userService;

	@Tag(name = "회원인증 API")
	@Operation(summary = "회원가입", description = "회원가입을 합니다.")
	@PostMapping(value = "/service/v1/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<SignUpDto.SignUpRes>> signUp(@RequestBody @Valid SignUpDto.SignUpReq req) {
		Link selfLink = linkTo(methodOn(SignUpController.class).signUp(req)).withSelfRel().withType("POST");
		try {
			SignUpDto.SignUpRes res = userService.create(req);
			return res(EntityModel.of(res).add(selfLink));
		} catch (EmailDuplicationException ex) {
			throw new BadRequestException(ex.getMessage(), List.of(selfLink));
		}
	}
}
