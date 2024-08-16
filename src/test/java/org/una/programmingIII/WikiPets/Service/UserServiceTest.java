package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Mapper.UserMapper;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Model.UserDto;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsersTest() {
        User user = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        when(userMapper.toUserDto(any(User.class))).thenReturn(new UserDto());

        List<UserDto> userDtos = userService.getAllUsers();

        assertNotNull(userDtos);
        assertEquals(1, userDtos.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserByIdTest() {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.toUserDto(any(User.class))).thenReturn(new UserDto());

        UserDto userDto = userService.getUserById(1L);

        assertNotNull(userDto);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void createUserTest() {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserDto(any(User.class))).thenReturn(userDto);
        when(userMapper.toUser(any(UserDto.class))).thenReturn(user);

        UserDto createdUserDto = userService.createUser(userDto);

        assertNotNull(createdUserDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserDto(any(User.class))).thenReturn(userDto);
        when(userMapper.toUser(any(UserDto.class))).thenReturn(user);

        UserDto updatedUserDto = userService.updateUser(userDto);

        assertNotNull(updatedUserDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
