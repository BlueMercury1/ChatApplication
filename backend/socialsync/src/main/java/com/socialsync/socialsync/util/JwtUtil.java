package com.socialsync.socialsync.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "this-is-long-and-secure-secret-key";
    private final Long EXPIRATION_TIME = 1000L * 60 * 60;

    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claims(new HashMap<>())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .issuedAt(new Date())
                .issuer("DEEP TECH")
                .signWith(secretKey())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token has expired!", e);
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Token is unsupported!", e);
        } catch (MalformedJwtException e) {
            throw new JwtException("Token is invalid!", e);
        } catch (SignatureException e) {
            throw new JwtException("Token signature is not valid!", e);
        } catch (IllegalArgumentException e) {
            throw new JwtException("Token is missing or incorrect!", e);
        }
    }

    public String getUsernameFromToken(String jwtToken) {
        return extractAllClaims(jwtToken).getSubject();
    }

    public Date getExpirationDate(String jwtToken) {
        return extractAllClaims(jwtToken).getExpiration();
    }

    public boolean isTokenExpired(String jwtToken) {
        return getExpirationDate(jwtToken).before(new Date());
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        if (!isTokenExpired(jwtToken)) {
            return getUsernameFromToken(jwtToken).equals(userDetails.getUsername());
        }
        return false;

    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        map.put("status", HttpStatus.UNAUTHORIZED);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
