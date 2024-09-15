package org.una.programmingIII.WikiPets.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Model.User;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private final String secretKey = "oReZWVw8m1LCkVe6Zs+Y7z/XLlKJ6JvD8oY0fhS3R8k=";
    private final int accessTokenExpiration = 2 * 60 * 1000;
    private final int refreshTokenExpiration = 20 * 60 * 1000;
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
   public String generateAccessToken(User user) {
       return Jwts.builder()
               .setSubject(user.getEmail())
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
               .signWith(key, SignatureAlgorithm.HS256)
               .compact();
   }
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean isAccessTokenExpired(String token) {
        try {
            Date expirationDate = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expirationDate.before(new Date());
        } catch (JwtException e) {
            return false;}
    }
}
