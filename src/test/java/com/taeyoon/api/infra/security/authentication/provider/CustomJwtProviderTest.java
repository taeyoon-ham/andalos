package com.taeyoon.api.infra.security.authentication.provider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.taeyoon.api.domain.user.dto.MemberDto;
import com.taeyoon.api.infra.exception.client.AuthorizationNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CustomJwtProviderTest {

	@Mock
	UserDetailsService userServiceImpl;

	@InjectMocks
	CustomJwtProvider customJwtProvider;

	String sub = "12345";
	String secretKey = System.getenv("JWT_DEFAULT_SECRET_KEY");
	String token = null;

	private Map<String, Object> makeClaims() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("iss", "iss");
		claims.put("aud", "aud");
		claims.put("sub", sub);
		claims.put("jti", "jti-1");
		return claims;
	}

	private Date expiredDate() {
		Duration duration = Duration.ofSeconds(60 * 60 * 2);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expired = now.plus(duration);
		return Date.from(expired.toInstant(ZoneOffset.UTC));
	}

	@BeforeEach
	void init() {
		token = CustomJwtProvider.createToken(makeClaims(), expiredDate(), secretKey);
	}

	@Test
	@Order(1)
	@DisplayName("토큰생성")
	void createToken() {
		assertNotNull(token);
	}

	@Test
	@Order(2)
	@DisplayName("토큰획득")
	void resolveToken() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer " + token);
		String resolveToken = CustomJwtProvider.resolveToken(request);
		assertEquals(token, resolveToken);
		// SecurityContextHolderFilter
	}

	@Test
	@Order(2)
	@DisplayName("토큰획득 - authorization 없을경우")
	void resolveTokenNoHeader() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(null);
		AuthorizationNotFoundException exception = assertThrows(AuthorizationNotFoundException.class,
			() -> CustomJwtProvider.resolveToken(request));
		assertEquals("The request header does not contain Authorization.", exception.getMessage());
	}

	@Test
	@Order(4)
	@DisplayName("sub 값 조회")
	void getSubject() {
		String resolveSub = CustomJwtProvider.getSubject(token, secretKey);
		assertEquals(sub, resolveSub);
	}

	@Test
	@Order(5)
	@DisplayName("토큰 유효성체크")
	void validateToken() {
		boolean result = CustomJwtProvider.validateToken(token, secretKey);
		assertTrue(result);
	}

	@Test
	@Order(6)
	@DisplayName("토큰에의한 인증정보 조회")
	void getAuthentication() {
		when(userServiceImpl.loadUserByUsername(any())).thenReturn(MemberDto.builder().id(2L).build());
		Authentication authentication = customJwtProvider.getAuthentication(token, secretKey);
		assertEquals(2L, ((MemberDto)authentication.getPrincipal()).getId());
	}
}