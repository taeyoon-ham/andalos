package com.taeyoon.api.infra.security.authentication.provider;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class JwtCustomProviderTest {

    @Autowired
    private JwtCustomProvider jwtCustomProvider;
    @Test
    void createToken() {
//        String roleUser = JwtCustomProvider.createToken("1", List.of("ROLE_USER"));
//        log.info("roleUse={}", roleUser);
    }

    @Test
    void resolveToken() {
//        assertTrue(JwtCustomProvider.validateToken(JwtCustomProvider.createToken("1", List.of("ROLE_USER"))));
    }

    @Test
    void validateToken() {
    }
}