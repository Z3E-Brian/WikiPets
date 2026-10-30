package org.una.programmingIII.WikiPets.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Model.User;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private static final String PURPOSE_CLAIM = "purpose";

    /**
     * What a token was issued for. Every token carries its purpose in the "purpose" claim and is
     * only accepted for that purpose, so a refresh or password reset token can not be used as an
     * access token (and an access token can not be used to reset a password).
     */
    public enum TokenPurpose {
        ACCESS("access"),
        REFRESH("refresh"),
        PASSWORD_RESET("password_reset");

        private final String claimValue;

        TokenPurpose(String claimValue) {
            this.claimValue = claimValue;
        }

        public String getClaimValue() {
            return claimValue;
        }
    }

    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;
    private final long passwordResetTokenExpiration;
    private final Key key;

    @Autowired
    public JWTService(@Value("${jwt.secret}") String secretKey,
                      @Value("${jwt.access-token-expiration}") long accessTokenExpiration,
                      @Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration,
                      @Value("${jwt.password-reset-token-expiration}") long passwordResetTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.passwordResetTokenExpiration = passwordResetTokenExpiration;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateAccessToken(User user) {
        return generateToken(user.getEmail(), TokenPurpose.ACCESS, accessTokenExpiration);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user.getEmail(), TokenPurpose.REFRESH, refreshTokenExpiration);
    }

    public String generatePasswordResetToken(String email) {
        return generateToken(email, TokenPurpose.PASSWORD_RESET, passwordResetTokenExpiration);
    }

    /**
     * Returns true only if the token is correctly signed, has not expired and was issued for the
     * given purpose.
     */
    public boolean isTokenValid(String token, TokenPurpose purpose) {
        try {
            String tokenPurpose = parseClaims(token).get(PURPOSE_CLAIM, String.class);
            return purpose.getClaimValue().equals(tokenPurpose);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    private String generateToken(String email, TokenPurpose purpose, long expiration) {
        return Jwts.builder()
                .setSubject(email)
                .claim(PURPOSE_CLAIM, purpose.getClaimValue())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
