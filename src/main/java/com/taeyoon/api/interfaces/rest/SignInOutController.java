package com.taeyoon.api.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taeyoon.api.application.dto.SignInOutDto;
import com.taeyoon.api.application.service.UserService;
import com.taeyoon.api.infra.exception.client.BadRequestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "회원인증 API", description = "로그인 / 로그아웃 API 입니다.")
public class SignInOutController extends CommonController {
	private final UserService userService;

	@Tag(name = "회원인증 API")
	@Operation(summary = "로그인", description = "로그인을 합니다.")
	@PostMapping(value = "/service/v1/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SignInOutDto.SignInRes> signIn(@RequestBody SignInOutDto.SignInReq req) {
		log.info("req={}, {}", req.getLoginId(), req.getPassword());
		return res(SignInOutDto.SignInRes.builder().build());
	}

	@Tag(name = "회원인증 API")
	@Operation(summary = "로그아웃", description = "로그아웃을 합니다.")
	@SecurityRequirement(name = "bearerAuth")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@GetMapping(value = "/api/service/v1/sign-out")
	public void signOut() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String token = (String)authentication.getCredentials();
		log.info("logout token={}", token);
		throw new BadRequestException("eeee");
	}
}
