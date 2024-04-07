package com.taeyoon.api.infra.security.authentication.filter;

import com.taeyoon.api.infra.security.authentication.provider.AuthorizationNotFoundException;
import com.taeyoon.api.infra.security.authentication.provider.JwtCustomProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
public class JwtAuthenticationCustomFilter extends OncePerRequestFilter {

    private final JwtCustomProvider jwtCustomProvider;
    private final String secretKey;

    public JwtAuthenticationCustomFilter(JwtCustomProvider jwtCustomProvider, String secretKey) {
        this.jwtCustomProvider = jwtCustomProvider;
        this.secretKey = secretKey;
    }
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = JwtCustomProvider.resolveToken(request);
            JwtCustomProvider.validateToken(token, secretKey);
            Authentication auth = jwtCustomProvider.getAuthentication(token, secretKey);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (AuthorizationNotFoundException ae) {
            // skip
        } catch (ExpiredJwtException ee) {
            log.warn(ee.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token has expired.");
            return; // 다음 진행이 되지 않아야 401 로 응답됨.
        } catch (Exception e) {
            log.warn(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is invalid.");
            return; // 다음 진행이 되지 않아야 401 로 응답됨.
        }

        filterChain.doFilter(request, response);
    }
}
