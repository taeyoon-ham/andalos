package com.taeyoon.api.infra.security.authentication.token;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class DefaultTokenFactory implements TokenFactory {

    protected String jti;
    protected Date expiredDate;
    @Override
    public Token createToken() {
        // 1. jti 생성
        jti = makeJti();
        // 2. 만료시간 생성
        expiredDate = makeExpiredDate();

        return create();
    }

    // jti 생성
    protected String makeJti() {
        return getAud() + "-" + getSub(); // jwt token identity
    }

    // 만료시간 생성
    protected Date makeExpiredDate() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.ofSeconds(getDurationSeconds());
        LocalDateTime expired = now.plus(duration);
        return Date.from(expired.toInstant(ZoneOffset.UTC));
    }

    protected Map<String, Object> defaultClaimsMap() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", getIss());
        claims.put("aud", getAud());
        claims.put("sub", getSub());
        claims.put("jti", jti);
        return claims;
    }
    protected abstract Token create();
    protected abstract String getIss();

    protected abstract String getAud();

    protected abstract String getSub();

    protected abstract String getSecretKey();

    protected abstract Integer getDurationSeconds();
}
