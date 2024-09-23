package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private GenericMapperFactory mapperFactory;
    @Mock
    private GenericMapper<UserInput, UserDto> userMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(UserInput.class, UserDto.class)).thenReturn(userMapper);
        userController = new UserController(userService, mapperFactory);
    }

    @Test
    void testUserControllerConstructor() {
        assertNotNull(userController);
    }

    @Test
    void testGetUsers() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedUsers = new HashMap<>();
        when(userService.getUsers(page, size)).thenReturn(expectedUsers);

        Map<String, Object> result = userController.getUsers(page, size);
        assertEquals(expectedUsers, result);
        verify(userService).getUsers(page, size);
    }

    @Test
    void testGetUsersNotFoundException() {
        int page = 1;
        int size = 10;
        when(userService.getUsers(page, size)).thenThrow(new NotFoundElementException("No users found"));

        Exception exception = assertThrows(NotFoundElementException.class, () -> {
            userController.getUsers(page, size);
        });
        assertEquals("Could not retrieve usersNo users found", exception.getMessage());
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        UserDto expectedUser = new UserDto();
        when(userService.getUserById(userId)).thenReturn(expectedUser);

        UserDto result = userController.getUserById(userId);

        assertEquals(expectedUser, result);
        verify(userService).getUserById(userId);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(true);

        Boolean result = userController.deleteUser(userId);

        assertTrue(result);
        verify(userService).deleteUser(userId);
    }

    @Test
    void testUpdateUser() {
        UserInput input = new UserInput();
        UserDto expectedUserDto = new UserDto();
        when(userMapper.convertToDTO(input)).thenReturn(expectedUserDto);
        when(userService.updateUser(expectedUserDto)).thenReturn(expectedUserDto);

        UserDto result = userController.updateUser(input);

        assertEquals(expectedUserDto, result);
        verify(userMapper).convertToDTO(input);
        verify(userService).updateUser(expectedUserDto);
    }

    @Test
    void testAddDogBreedInUser() {

        Long userId = 1L;
        Long dogBreedId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.addDogBreedInUser(userId, dogBreedId)).thenReturn(expectedUserDto);

        UserDto result = userController.addDogBreedInUser(userId, dogBreedId);
        assertEquals(expectedUserDto, result);
        verify(userService).addDogBreedInUser(userId, dogBreedId);
    }

    @Test
    void testDeleteDogBreedInUser() {
        Long userId = 1L;
        Long dogBreedId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.deleteDogBreedInUser(userId, dogBreedId)).thenReturn(expectedUserDto);

        UserDto result = userController.deleteDogBreedInUser(userId, dogBreedId);

        assertEquals(expectedUserDto, result);
        verify(userService).deleteDogBreedInUser(userId, dogBreedId);
    }

    @Test
    void testAddCatBreedInUser() {

        Long userId = 1L;
        Long catBreedId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.addCatBreedInUser(userId, catBreedId)).thenReturn(expectedUserDto);


        UserDto result = userController.addCatBreedInUser(userId, catBreedId);


        assertEquals(expectedUserDto, result);
        verify(userService).addCatBreedInUser(userId, catBreedId);
    }

    @Test
    void testDeleteCatBreedInUser() {

        Long userId = 1L;
        Long catBreedId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.deleteCatBreedInUser(userId, catBreedId)).thenReturn(expectedUserDto);

        UserDto result = userController.deleteCatBreedInUser(userId, catBreedId);

        assertEquals(expectedUserDto, result);
        verify(userService).deleteCatBreedInUser(userId, catBreedId);
    }

    @Test
    void testAddReviewInUser() {
        Long userId = 1L;
        Long reviewId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.addReviewInUser(userId, reviewId)).thenReturn(expectedUserDto);

        UserDto result = userController.addReviewInUser(userId, reviewId);

        assertEquals(expectedUserDto, result);
        verify(userService).addReviewInUser(userId, reviewId);
    }

    @Test
    void testDeleteReviewInUser() {
        Long userId = 1L;
        Long reviewId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.deleteReviewInUser(userId, reviewId)).thenReturn(expectedUserDto);

        UserDto result = userController.deleteReviewInUser(userId, reviewId);

        assertEquals(expectedUserDto, result);
        verify(userService).deleteReviewInUser(userId, reviewId);
    }


    @Test
    void testGenerateFalseUsers() {
        int numberOfUsers = 5;

        boolean result = userController.generateFalseUsers(numberOfUsers);
        verify(userService).generateFalseUsers(numberOfUsers);
        assertTrue(result);
    }


}
