package com.snapp.expensetracker.security;

import com.snapp.expensetracker.validator.exception.ExpenseTrackerApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            throw new ExpenseTrackerApiException(HttpStatus.BAD_REQUEST, "Invalid Jwt Signature");
        }catch (MalformedJwtException e){
            throw new ExpenseTrackerApiException(HttpStatus.BAD_REQUEST, "Invalid Jwt Token");
        }catch (ExpiredJwtException e){
            throw new ExpenseTrackerApiException(HttpStatus.BAD_REQUEST, "Expired Jwt Token");
        }catch (UnsupportedJwtException e){
            throw new ExpenseTrackerApiException(HttpStatus.BAD_REQUEST, "Unsupported Jwt Token");
        }catch (IllegalArgumentException e){
            throw new ExpenseTrackerApiException(HttpStatus.BAD_REQUEST, "Jwt claims starting is empty");
        }
    }
}