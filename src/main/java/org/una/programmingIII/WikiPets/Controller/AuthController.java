package org.una.programmingIII.WikiPets.Controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
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

@Controller
public class AuthController {
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JWTService jwtService;
    private final GenericMapper<User, UserDto> userMapper;
    private final UserService userService;


    @Autowired
    AuthController(AuthenticationService authenticationService, RefreshTokenService refreshTokenService, JWTService jwtService, GenericMapperFactory mapperFactory, UserService userService) {
        this.authenticationService = authenticationService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
        this.userMapper = mapperFactory.createMapper(User.class, UserDto.class);
        this.userService = userService;
    }

    @MutationMapping
    public LoginResponse login(@Argument LogInInput input) {
        try {
            UserDto userDto = authenticationService.authenticate(input.getEmail(), input.getPassword());
            User user = userMapper.convertToEntity(userDto);
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            return new LoginResponse(userDto, accessToken, refreshToken);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @MutationMapping
    public RefreshTokenDto refreshToken(@Argument String refreshToken) {
        try {
            String newAccessToken = refreshTokenService.refreshAccessToken(refreshToken);
            return new RefreshTokenDto(newAccessToken);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid refresh token");
        }
    }

    @MutationMapping
    public void changePassword(@Argument ChangePasswordInput input) {
        try {
            authenticationService.changePassword(input.getEmail(), input.getPassword(), input.getNewPassword());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @MutationMapping
    public String initiateRecoverPassword(@Argument String email) {
        try {
            return authenticationService.initiatePasswordRecovery(email);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @MutationMapping
    public String recoverPassword(@Argument RecoverPasswordInput input) {
        try {
            return authenticationService.completePasswordRecovery(input);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
    @MutationMapping
    public UserDto createUser(@Argument UserInput input) {
        try {
            return userService.createUser(input);
        } catch (Exception e) {
            throw new CustomException("Could not create user"+ e.getMessage(), e);
        }
    }
}

