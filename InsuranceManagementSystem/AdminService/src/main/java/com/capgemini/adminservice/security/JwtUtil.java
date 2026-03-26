package com.capgemini.adminservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtUtil {

    private final String SECRET = "TXlTdXBlclNlY3JldEtleU15U3VwZXJTZWNyZXRLZXkxMjM0NQ==";

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getDecoder().decode(SECRET))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}