package com.taeyoon.api.infra.security.authentication.token;

public interface TokenFactory {
    AccessToken createAccessToken();
    RefreshToken createRefreshToken(AccessToken accessToken);
}
