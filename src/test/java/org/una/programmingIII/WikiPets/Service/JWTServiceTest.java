package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Service.JWTService.TokenPurpose;

import static org.junit.jupiter.api.Assertions.*;

class JWTServiceTest {

    static final String TEST_SECRET = "test-only-secret-key-with-at-least-32-bytes!";
    static final long ONE_MINUTE = 60_000;

    private JWTService jwtService;
    private User user;

    @BeforeEach
    void setUp() {
        jwtService = new JWTService(TEST_SECRET, ONE_MINUTE, ONE_MINUTE, ONE_MINUTE);
        user = new User();
        user.setEmail("user@example.com");
    }

    @Test
    void accessTokenIsOnlyValidAsAccessToken() {
        String token = jwtService.generateAccessToken(user);

        assertTrue(jwtService.isTokenValid(token, TokenPurpose.ACCESS));
        assertFalse(jwtService.isTokenValid(token, TokenPurpose.REFRESH));
        assertFalse(jwtService.isTokenValid(token, TokenPurpose.PASSWORD_RESET));
        assertEquals("user@example.com", jwtService.getEmailFromToken(token));
    }

    @Test
    void refreshTokenIsNotValidAsAccessToken() {
        String token = jwtService.generateRefreshToken(user);

        assertTrue(jwtService.isTokenValid(token, TokenPurpose.REFRESH));
        assertFalse(jwtService.isTokenValid(token, TokenPurpose.ACCESS));
    }

    @Test
    void passwordResetTokenIsNotValidAsAccessToken() {
        String token = jwtService.generatePasswordResetToken("user@example.com");

        assertTrue(jwtService.isTokenValid(token, TokenPurpose.PASSWORD_RESET));
        assertFalse(jwtService.isTokenValid(token, TokenPurpose.ACCESS));
        assertEquals("user@example.com", jwtService.getEmailFromToken(token));
    }

    @Test
    void expiredTokenIsNotValid() {
        JWTService expiredTokenIssuer = new JWTService(TEST_SECRET, -ONE_MINUTE, -ONE_MINUTE, -ONE_MINUTE);

        assertFalse(jwtService.isTokenValid(expiredTokenIssuer.generateAccessToken(user), TokenPurpose.ACCESS));
        assertFalse(jwtService.isTokenValid(expiredTokenIssuer.generatePasswordResetToken("user@example.com"), TokenPurpose.PASSWORD_RESET));
    }

    @Test
    void tokenSignedWithAnotherKeyIsNotValid() {
        JWTService otherIssuer = new JWTService("another-secret-key-with-at-least-32-bytes!!", ONE_MINUTE, ONE_MINUTE, ONE_MINUTE);

        assertFalse(jwtService.isTokenValid(otherIssuer.generateAccessToken(user), TokenPurpose.ACCESS));
    }

    @Test
    void malformedTokenIsNotValid() {
        assertFalse(jwtService.isTokenValid("not-a-jwt", TokenPurpose.ACCESS));
        assertFalse(jwtService.isTokenValid("", TokenPurpose.ACCESS));
        assertFalse(jwtService.isTokenValid(null, TokenPurpose.ACCESS));
    }
}
