package com.snapp.expensetracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecretKey;

    @Value("${app.jwt-expiration-date-in-millisecond}")
    private int jwtExpirationInMilliSecond;

    public String generateToken(Authentication auth){
        String username = auth.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMilliSecond);

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireDate)
                .setIssuedAt(currentDate)
                .signWith(SignatureAlgorithm.RS512, jwtSecretKey)
                .compact();
        return token;
    }
}