package org.una.programmingIII.WikiPets.Service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Input.RecoverPasswordInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.User;

import java.time.LocalDate;

@Service
public class AuthenticationService {
    static final String PASSWORD_RECOVERY_REQUESTED = "If the account exists, we sent an email with the instructions to recover the password";
    static final String INVALID_RECOVERY_TOKEN = "Invalid token";

    private final UserService userService;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final GenericMapper<User, UserDto> userMapper;
    private final EmailService emailService;

    @Autowired
    AuthenticationService(UserService userService, GenericMapperFactory mapperFactory, PasswordEncoder passwordEncoder,JWTService jwtService,EmailService emailService) {
        this.userService = userService;
        this.userMapper = mapperFactory.createMapper(User.class, UserDto.class);
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public UserDto authenticate(String email, String password) {
        User user;
        try {
            user = userService.findByEmail(email);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid credentials");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return userMapper.convertToDTO(user);
    }

    /**
     * Sends a single-purpose password reset token to the email. The answer is the same whether the
     * account exists or not, so this mutation can not be used to find out which emails are registered.
     */
    public String initiatePasswordRecovery(String email) throws MessagingException {
        User user = userService.findByEmail(email);
        if (user != null) {
            String token = jwtService.generatePasswordResetToken(user.getEmail());
            emailService.sendSimpleEmail(user.getEmail(),
                    "Password recovery",
                    "Use this token to recover your password: " + token);
        }
        return PASSWORD_RECOVERY_REQUESTED;
    }

    /**
     * Changes the password only if the token is a valid password reset token issued for the same
     * email that is being recovered.
     */
    public String completePasswordRecovery(RecoverPasswordInput recoverPasswordInput) {
        String token = recoverPasswordInput.getToken();
        if (!jwtService.isTokenValid(token, JWTService.TokenPurpose.PASSWORD_RESET)
                || !jwtService.getEmailFromToken(token).equals(recoverPasswordInput.getEmail())) {
            return INVALID_RECOVERY_TOKEN;
        }
        User user = userService.findByEmail(recoverPasswordInput.getEmail());
        if (user == null) {
            return INVALID_RECOVERY_TOKEN;
        }
        user.setPassword(passwordEncoder.encode(recoverPasswordInput.getPassword()));
        user.setLastUpdate(LocalDate.now());
        userService.updateUser(user);
        return "Password updated successfully";
    }

    public void changePassword(String email, String password, String newPassword) {
        User user = userService.findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.updateUser(user);
    }


}
