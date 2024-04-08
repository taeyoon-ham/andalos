package com.taeyoon.api.infra.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.taeyoon.api.infra.security.authentication.filter.CustomJwtAuthenticationFilter;
import com.taeyoon.api.infra.security.authentication.provider.CustomJwtProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
//@EnableWebSecurity(debug = true)
@EnableWebSecurity
public class SecurityConfig {

	@Value("${jwt.default-secret-key}")
	private String SECRET_KEY = ""; // 32글자 이상
	private final CustomJwtProvider customJwtProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/*
		 * security 에서의 authority 는 유저별 권한에 의한 resource 사용권한을 판별할 때 사용하는 것이 아니라
		 * 조금 더 넓은 범위의 권한을 체크할 때 사용하는것이 유용하다.
		 * 예)
		 *  /api/service url 은 회원
		 *  /api/admin   url 은 어드민
		 *  와 같은 url 을 서비스별로 분리하여 사용할 때
		 */
		http
			.csrf(
				AbstractHttpConfigurer::disable) // rest 에서는 cookie, session 을 사용하지 않기때문에 disable 처리, disable 하지 않으면 rest api 요청시 csrf token 이 없어 403 에러 발생
			.addFilterBefore(new CustomJwtAuthenticationFilter(customJwtProvider, SECRET_KEY),
				UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers(
						AntPathRequestMatcher.antMatcher("/api/**")
					).authenticated() // api 가 포함된 url 은 인증된 사용자만.
					.requestMatchers(
						AntPathRequestMatcher.antMatcher("/api/service/**")
					).hasAuthority("ROLE_MEMBER") // /api/service/** 는 회원만.
					.requestMatchers(
						AntPathRequestMatcher.antMatcher("/api/admin/**")
					).hasAuthority("ROLE_ADMIN") // /api/admin/** 는 어드민만.
					.requestMatchers(
						AntPathRequestMatcher.antMatcher("/**")
					).permitAll()
			);
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {

		// 정적 리소스 spring security 대상에서 제외
		return (web) -> web
			.ignoring()
			.requestMatchers(
				PathRequest.toStaticResources().atCommonLocations()
			);
	}
}