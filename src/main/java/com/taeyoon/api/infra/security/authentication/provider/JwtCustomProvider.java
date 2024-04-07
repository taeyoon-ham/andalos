package com.taeyoon.api.infra.security.authentication.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtCustomProvider {

    private final UserDetailsService userServiceImpl;

    public static String createToken(Map<String, Object> claimsMap, Date expiredDate, String secretKey) {
        Claims claims = Jwts.claims(claimsMap);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(getKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    private static String getSubject(String token, String secretKey) {
        secretKey = getBase64EncodeSecretKey(secretKey);
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // header.Authorization 에서 bearer token 취득
    public static String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null) throw new AuthorizationNotFoundException("The request header does not contain Authorization.");
        return authorization.split(" ")[1].trim();
    }

    public static boolean validateToken(String token, String secretKey) {
        secretKey = getBase64EncodeSecretKey(secretKey);
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        return claims.getBody().getExpiration().after(new Date());
    }

    private static Key getKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    private static String getBase64EncodeSecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes());
    }

    public Authentication getAuthentication(String token, String secretKey) {
        UserDetails userDetails = userServiceImpl.loadUserByUsername(getSubject(token, secretKey));
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }
}
