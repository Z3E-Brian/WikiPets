package org.una.programmingIII.WikiPets.Service;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.una.programmingIII.WikiPets.Input.RecoverPasswordInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Service.JWTService.TokenPurpose;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Password recovery tests. The JWTService is real (with a test-only key) so the tokens used here
 * are the same kind of tokens the application issues.
 */
class AuthenticationServiceTest {

    private static final String EMAIL = "owner@example.com";
    private static final String OTHER_EMAIL = "victim@example.com";

    @Mock
    private UserService userService;
    @Mock
    private GenericMapperFactory mapperFactory;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailService emailService;

    private JWTService jwtService;
    private AuthenticationService authenticationService;
    private User user;
    private User otherUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JWTService(JWTServiceTest.TEST_SECRET, JWTServiceTest.ONE_MINUTE, JWTServiceTest.ONE_MINUTE, JWTServiceTest.ONE_MINUTE);
        authenticationService = new AuthenticationService(userService, mapperFactory, passwordEncoder, jwtService, emailService);

        user = new User();
        user.setEmail(EMAIL);
        user.setPassword("old-hash");
        otherUser = new User();
        otherUser.setEmail(OTHER_EMAIL);
        otherUser.setPassword("other-old-hash");

        when(userService.findByEmail(EMAIL)).thenReturn(user);
        when(userService.findByEmail(OTHER_EMAIL)).thenReturn(otherUser);
        when(passwordEncoder.encode("new-password")).thenReturn("new-hash");
    }

    @Test
    void initiatePasswordRecovery_SendsPasswordResetToken() throws MessagingException {
        String result = authenticationService.initiatePasswordRecovery(EMAIL);

        ArgumentCaptor<String> body = ArgumentCaptor.forClass(String.class);
        verify(emailService).sendSimpleEmail(eq(EMAIL), anyString(), body.capture());
        String sentToken = body.getValue().substring(body.getValue().lastIndexOf(' ') + 1);
        assertTrue(jwtService.isTokenValid(sentToken, TokenPurpose.PASSWORD_RESET));
        assertFalse(jwtService.isTokenValid(sentToken, TokenPurpose.ACCESS));
        assertEquals(AuthenticationService.PASSWORD_RECOVERY_REQUESTED, result);
    }

    @Test
    void initiatePasswordRecovery_UnknownEmail_SameAnswerAndNoEmail() throws MessagingException {
        String result = authenticationService.initiatePasswordRecovery("unknown@example.com");

        assertEquals(AuthenticationService.PASSWORD_RECOVERY_REQUESTED, result);
        verify(emailService, never()).sendSimpleEmail(anyString(), anyString(), anyString());
    }

    @Test
    void completePasswordRecovery_ValidToken_ChangesPassword() {
        String token = jwtService.generatePasswordResetToken(EMAIL);

        String result = authenticationService.completePasswordRecovery(new RecoverPasswordInput(EMAIL, "new-password", token));

        assertEquals("Password updated successfully", result);
        assertEquals("new-hash", user.getPassword());
        verify(userService).updateUser(user);
    }

    @Test
    void completePasswordRecovery_TokenOfAnotherUser_Rejected() {
        String ownToken = jwtService.generatePasswordResetToken(EMAIL);

        String result = authenticationService.completePasswordRecovery(new RecoverPasswordInput(OTHER_EMAIL, "new-password", ownToken));

        assertEquals(AuthenticationService.INVALID_RECOVERY_TOKEN, result);
        assertEquals("other-old-hash", otherUser.getPassword());
        verify(userService, never()).updateUser(any(User.class));
    }

    @Test
    void completePasswordRecovery_ExpiredToken_Rejected() {
        JWTService expiredTokenIssuer = new JWTService(JWTServiceTest.TEST_SECRET, -JWTServiceTest.ONE_MINUTE, -JWTServiceTest.ONE_MINUTE, -JWTServiceTest.ONE_MINUTE);
        String expiredToken = expiredTokenIssuer.generatePasswordResetToken(EMAIL);

        String result = authenticationService.completePasswordRecovery(new RecoverPasswordInput(EMAIL, "new-password", expiredToken));

        assertEquals(AuthenticationService.INVALID_RECOVERY_TOKEN, result);
        verify(userService, never()).updateUser(any(User.class));
    }

    @Test
    void completePasswordRecovery_AccessToken_Rejected() {
        String accessToken = jwtService.generateAccessToken(user);

        String result = authenticationService.completePasswordRecovery(new RecoverPasswordInput(EMAIL, "new-password", accessToken));

        assertEquals(AuthenticationService.INVALID_RECOVERY_TOKEN, result);
        verify(userService, never()).updateUser(any(User.class));
    }

    @Test
    void completePasswordRecovery_MalformedToken_Rejected() {
        String result = authenticationService.completePasswordRecovery(new RecoverPasswordInput(EMAIL, "new-password", "not-a-token"));

        assertEquals(AuthenticationService.INVALID_RECOVERY_TOKEN, result);
        verify(userService, never()).updateUser(any(User.class));
    }
}
