package com.taeyoon.api.interfaces.rest;

import com.taeyoon.api.application.dto.SignUpDto;
import com.taeyoon.api.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name="회원인증 API", description = "회원가입 API 입니다.")
public class SignUpController {
    private final UserService userService;
    @Tag(name="회원인증 API")
    @Operation(summary = "로그인", description = "로그인을 합니다.")
    @PostMapping(value = "/service/v1/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signUp(@RequestBody @Valid SignUpDto.ReqSignUp req) {
        userService.create(req);
    }
}
