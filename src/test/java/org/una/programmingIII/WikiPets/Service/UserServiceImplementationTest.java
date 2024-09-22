package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplementationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<User, UserDto> userMapper;

    @InjectMocks
    private UserServiceImplementation userServiceImplementation;

    PasswordEncoder passwordEncoder;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");

        when(mapperFactory.createMapper(User.class, UserDto.class)).thenReturn(userMapper);
        when(userMapper.convertToDTO(user)).thenReturn(userDto);
        when(userMapper.convertToEntity(userDto)).thenReturn(user);


        userServiceImplementation = new UserServiceImplementation(userRepository, mapperFactory, passwordEncoder,{
        });
    }

  /*  @Test
    public void createUserTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDto result = userServiceImplementation.createUser(userDto);
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getName(), result.getName());
    }*/

    @Test
    public void updateUserTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDto result = userServiceImplementation.updateUser(userDto);
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getName(), result.getName());
    }

    @Test
    public void getUserByIdTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserDto result = userServiceImplementation.getUserById(1L);
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getName(), result.getName());
    }

    @Test
    public void getUserByIdNotFoundTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userServiceImplementation.getUserById(1L));
    }

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<UserDto> result = userServiceImplementation.getAllUsers();
        assertEquals(1, result.size());
        assertEquals(userDto.getId(), result.get(0).getId());
        assertTrue(result.get(0).getName().contains("John Doe"));
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userRepository).deleteById(1L);
        userServiceImplementation.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}