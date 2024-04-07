package com.taeyoon.api.interfaces.rest;

import com.taeyoon.api.infra.persistence.UserRepositoryHelper;
import com.taeyoon.api.application.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserRepositoryHelper userRepositoryHelper;
    private final UserService userService;
    public void setUser() {
        userService.create();
    }
}
