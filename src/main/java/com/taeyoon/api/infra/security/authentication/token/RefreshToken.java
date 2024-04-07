package com.taeyoon.api.infra.security.authentication.token;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RefreshToken {
    private String jti;
    private String token;
    private String iss ;
    private String aud ;
    private String sub;
    private Long exp;
}
