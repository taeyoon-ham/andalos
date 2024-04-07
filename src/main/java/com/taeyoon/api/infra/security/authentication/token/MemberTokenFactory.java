package com.taeyoon.api.infra.security.authentication.token;

import com.taeyoon.api.infra.security.authentication.provider.JwtCustomProvider;
import com.taeyoon.api.utils.Constants;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
@Slf4j
@Builder
public class MemberTokenFactory extends DefaultTokenFactory {

    private String secretKey;
    private String aud;
    private String sub;
    private Integer durationSeconds; // 토큰 만료시간 (초) 설정

    @Override
    protected Token create() {
        Map<String, Object> claims = this.defaultClaimsMap();
        String token = JwtCustomProvider.createToken(claims, expiredDate, getSecretKey());
        return Token.builder()
                .token(token)
                .jti(jti)
                .iss(getIss())
                .aud(getAud())
                .sub(getSub())
                .build();
    }

    @Override
    public RefreshToken createRefreshToken(Token token) {
        return null;
    }

    @Override
    protected String getIss() {
        return "account."+ Constants.DOMAIN;
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
