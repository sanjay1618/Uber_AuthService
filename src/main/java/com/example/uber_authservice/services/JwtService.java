package com.example.uber_authservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService implements CommandLineRunner{
    @Value("${jwt.expiry}")
    private Long expiry;

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Map<String, Object> claims, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claims(claims)
                .expiration(expiryDate)
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(key)
                .compact();

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
