package com.taeyoon.api.interfaces.rest;

import com.taeyoon.api.application.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class SignUpController {
    private final UserService userService;
    @GetMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signUp() {
        userService.create();
    }
}
