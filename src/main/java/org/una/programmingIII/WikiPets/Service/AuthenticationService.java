package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.User;

@Service
public class AuthenticationService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final GenericMapper<User, UserDto> userMapper;

    @Autowired
    AuthenticationService(UserService userService, GenericMapperFactory mapperFactory, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = mapperFactory.createMapper(User.class, UserDto.class);
        this.passwordEncoder = passwordEncoder;
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
    public void initiatePasswordRecovery(String email) {
        User user = userService.findByEmail(email);

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
