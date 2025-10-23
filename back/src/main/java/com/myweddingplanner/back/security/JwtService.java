package com.myweddingplanner.back.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final Key key;
    private final long expirationMs;
    private final long refreshExpirationMs;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") String expirationProp,
            @Value("${jwt.refreshExpiration}") String refreshExpirationProp
    ) {
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalStateException("JWT: 'jwt.secret' no está definido.");
        }
        String trimmed = secret.trim();
        if (trimmed.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalStateException("JWT: 'jwt.secret' es demasiado corto (mínimo 32 bytes para HS256).");
        }
        this.key = Keys.hmacShaKeyFor(trimmed.getBytes(StandardCharsets.UTF_8));

        try {
            this.expirationMs = Long.parseLong(expirationProp.trim());
            this.refreshExpirationMs = Long.parseLong(refreshExpirationProp.trim());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("JWT: 'jwt.expiration' o 'jwt.refreshExpiration' no son números válidos (ms).", e);
        }
    }

    public String generateToken (String subject, Map<String, Object> extraClaims){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return parseAllClaims(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token, String username){
        try{
            final String subject = extractUsername(token);

            return subject.equals(username) && !isExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isExpired(String token){
        return parseAllClaims(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    private Jws<Claims> parseAllClaims (String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
