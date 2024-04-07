package com.taeyoon.api.infra.security.authentication.token;

public interface TokenFactory {
    Token createToken();
    RefreshToken createRefreshToken(Token token);
}
