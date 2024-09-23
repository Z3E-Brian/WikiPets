package org.una.programmingIII.WikiPets.Controller;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.una.programmingIII.WikiPets.Dto.LoginResponse;
import org.una.programmingIII.WikiPets.Dto.RefreshTokenDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.ChangePasswordInput;
import org.una.programmingIII.WikiPets.Input.LogInInput;
import org.una.programmingIII.WikiPets.Input.RecoverPasswordInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Service.AuthenticationService;
import org.una.programmingIII.WikiPets.Service.JWTService;
import org.una.programmingIII.WikiPets.Service.RefreshTokenService;
import org.una.programmingIII.WikiPets.Service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private RefreshTokenService refreshTokenService;

    @Mock
    private JWTService jwtService;

    @Mock
    private GenericMapper<User, UserDto> userMapper;

    @Mock
    private UserService userService;
    @Mock
    private GenericMapperFactory mapperFactory;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        when(mapperFactory.createMapper(User.class, UserDto.class)).thenReturn(userMapper);
        authController = new AuthController();
        LogInInput input = new LogInInput("test@example.com", "password");
        UserDto userDto = new UserDto();
        User user = new User();

        when(authenticationService.authenticate(input.getEmail(), input.getPassword())).thenReturn(userDto);
        when(userMapper.convertToEntity(userDto)).thenReturn(user);
        when(jwtService.generateAccessToken(user)).thenReturn("accessToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("refreshToken");

        LoginResponse response = authController.login(input);

        assertEquals("accessToken", response.getRefreshToken());
        assertEquals("refreshToken", response.getRefreshToken());
        verify(authenticationService, times(1)).authenticate(input.getEmail(), input.getPassword());
    }

    @Test
    void testLoginFailure() {
        LogInInput input = new LogInInput("test@example.com", "password");

        when(authenticationService.authenticate(anyString(), anyString())).thenThrow(new RuntimeException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> authController.login(input));
    }

    @Test
    void testRefreshTokenSuccess() {
        String refreshToken = "validRefreshToken";
        when(refreshTokenService.refreshAccessToken(refreshToken)).thenReturn("newAccessToken");

        RefreshTokenDto response = authController.refreshToken(refreshToken);

        assertEquals("newAccessToken", response.getToken());
        verify(refreshTokenService, times(1)).refreshAccessToken(refreshToken);
    }

    @Test
    void testRefreshTokenFailure() {
        String refreshToken = "invalidRefreshToken";
        when(refreshTokenService.refreshAccessToken(refreshToken)).thenThrow(new AuthenticationException("Invalid refresh token") {
        });

        assertThrows(BadCredentialsException.class, () -> authController.refreshToken(refreshToken));
    }

    @Test
    void testChangePasswordSuccess() {
        ChangePasswordInput input = new ChangePasswordInput("test@example.com", "oldPassword", "newPassword");

        authController.changePassword(input);

        verify(authenticationService, times(1)).changePassword(input.getEmail(), input.getPassword(), input.getNewPassword());
    }

    @Test
    void testChangePasswordFailure() {
        ChangePasswordInput input = new ChangePasswordInput("test@example.com", "oldPassword", "newPassword");
        doThrow(new AuthenticationException("Invalid credentials") {
        }).when(authenticationService).changePassword(anyString(), anyString(), anyString());

        assertThrows(BadCredentialsException.class, () -> authController.changePassword(input));
    }

    @Test
    void testInitiateRecoverPasswordSuccess() throws MessagingException {
        String email = "test@example.com";
        when(authenticationService.initiatePasswordRecovery(email)).thenReturn("Success");

        String result = authController.initiateRecoverPassword(email);

        assertEquals("Success", result);
        verify(authenticationService, times(1)).initiatePasswordRecovery(email);
    }

    @Test
    void testInitiateRecoverPasswordFailure() throws MessagingException {
        String email = "test@example.com";
        when(authenticationService.initiatePasswordRecovery(email)).thenThrow(new MessagingException("Email error"));

        assertThrows(RuntimeException.class, () -> authController.initiateRecoverPassword(email));
    }

    @Test
    void testRecoverPasswordSuccess() {
        RecoverPasswordInput input = new RecoverPasswordInput();
        when(authenticationService.completePasswordRecovery(input)).thenReturn("Success");

        String result = authController.recoverPassword(input);

        assertEquals("Success", result);
        verify(authenticationService, times(1)).completePasswordRecovery(input);
    }

    @Test
    void testRecoverPasswordFailure() {
        RecoverPasswordInput input = new RecoverPasswordInput();
        when(authenticationService.completePasswordRecovery(input)).thenThrow(new AuthenticationException("Invalid credentials") {
        });

        assertThrows(BadCredentialsException.class, () -> authController.recoverPassword(input));
    }

    @Test
    void testCreateUserSuccess() {
        UserInput input = new UserInput();
        UserDto userDto = new UserDto();

        when(userService.createUser(input)).thenReturn(userDto);

        UserDto result = authController.createUser(input);

        assertEquals(userDto, result);
        verify(userService, times(1)).createUser(input);
    }

    @Test
    void testCreateUserFailure() {
        UserInput input = new UserInput();
        when(userService.createUser(input)).thenThrow(new RuntimeException("User creation failed"));

        assertThrows(CustomException.class, () -> authController.createUser(input));
    }
}
