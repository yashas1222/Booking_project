package com.bms.auth_service.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    public static final long Expiration_time =  1000L * 60 * 60 * 24 * 30;

    @Value("${jwt.secret}")
    private String secret_key;

private Key key;

@PostConstruct
private void initKey(){
    this.key = Keys.hmacShaKeyFor(secret_key.getBytes());
}

public  String generateToken(String email){
    return Jwts
            .builder()
            .setSubject(email)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + Expiration_time))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
}

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
