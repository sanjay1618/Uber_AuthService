package com.example.uber_authservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService implements CommandLineRunner{
    @Value("${jwt.expiry}")
    private Long expiry;

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Map<String, Object> claims, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);

        return Jwts.builder()
                .claims(claims)
                .expiration(expiryDate)
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey())
                .subject(email)
                .compact();
    }

    public Key getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String extractUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    private Boolean validateToken(String token, String email) {
        final String userEmail = getClaimsFromToken(token).getSubject();
        return userEmail.equals(email) && isTokenExpired(token);
    }



    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email","sanjay@gmail.com");
        claims.put("phoneNumber","9840");
        String token =createToken(claims,"sahdsjdsh");
        System.out.println(token);

    }

}
