package com.taeyoon.api.infra.security.authentication.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CustomJwtProviderTest {

	@Autowired
	private CustomJwtProvider customJwtProvider;

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