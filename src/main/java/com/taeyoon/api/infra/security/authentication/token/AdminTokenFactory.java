package com.taeyoon.api.infra.security.authentication.token;

import java.util.Map;

import com.taeyoon.api.infra.constants.Consts;
import com.taeyoon.api.infra.security.authentication.provider.CustomJwtProvider;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public class AdminTokenFactory extends DefaultTokenFactory {

	private String secretKey;
	private String aud;
	private String sub;
	private Integer durationSeconds; // 토큰 만료시간 (초) 설정

	@Override
	protected AccessToken create() {
		Map<String, Object> claims = this.defaultClaimsMap();
		String token = CustomJwtProvider.createToken(claims, expiredDate, getSecretKey());
		return AccessToken.builder()
			.token(token)
			.jti(jti)
			.iss(getIss())
			.aud(getAud())
			.sub(getSub())
			.build();
	}

	@Override
	public RefreshToken createRefreshToken(AccessToken accessToken) {
		return null;
	}

	@Override
	protected String getIss() {
		return "account." + Consts.DOMAIN;
	}

	@Override
	protected String getAud() {
		return this.aud;
	}

	@Override
	protected String getSub() {
		return this.sub;
	}

	@Override
	protected String getSecretKey() {
		return this.secretKey;
	}

	@Override
	protected Integer getDurationSeconds() {
		return this.durationSeconds;
	}
}
