package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Controller.UserController;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private GenericMapper<UserInput, UserDto> userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Arrange
        int page = 1;
        int size = 10;
        Map<String, Object> expectedUsers = new HashMap<>();
        when(userService.getUsers(page, size)).thenReturn(expectedUsers);

        // Act
        Map<String, Object> result = userController.getUsers(page, size);

        // Assert
        assertEquals(expectedUsers, result);
        verify(userService).getUsers(page, size);
    }

    @Test
    void testGetUsersNotFoundException() {
        // Arrange
        int page = 1;
        int size = 10;
        when(userService.getUsers(page, size)).thenThrow(new NotFoundElementException("No users found"));

        // Act & Assert
        Exception exception = assertThrows(NotFoundElementException.class, () -> {
            userController.getUsers(page, size);
        });
        assertEquals("Could not retrieve usersNo users found", exception.getMessage());
    }

    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        UserDto expectedUser = new UserDto();
        when(userService.getUserById(userId)).thenReturn(expectedUser);

        // Act
        UserDto result = userController.getUserById(userId);

        // Assert
        assertEquals(expectedUser, result);
        verify(userService).getUserById(userId);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(true);

        // Act
        Boolean result = userController.deleteUser(userId);

        // Assert
        assertTrue(result);
        verify(userService).deleteUser(userId);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        UserInput input = new UserInput();
        UserDto expectedUserDto = new UserDto();
        when(userMapper.convertToDTO(input)).thenReturn(expectedUserDto);
        when(userService.updateUser(expectedUserDto)).thenReturn(expectedUserDto);

        // Act
        UserDto result = userController.updateUser(input);

        // Assert
        assertEquals(expectedUserDto, result);
        verify(userMapper).convertToDTO(input);
        verify(userService).updateUser(expectedUserDto);
    }

    @Test
    void testAddDogBreedInUser() {
        // Arrange
        Long userId = 1L;
        Long dogBreedId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.addDogBreedInUser(userId, dogBreedId)).thenReturn(expectedUserDto);

        // Act
        UserDto result = userController.addDogBreedInUser(userId, dogBreedId);

        // Assert
        assertEquals(expectedUserDto, result);
        verify(userService).addDogBreedInUser(userId, dogBreedId);
    }

    @Test
    void testDeleteDogBreedInUser() {
        // Arrange
        Long userId = 1L;
        Long dogBreedId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.deleteDogBreedInUser(userId, dogBreedId)).thenReturn(expectedUserDto);

        // Act
        UserDto result = userController.deleteDogBreedInUser(userId, dogBreedId);

        // Assert
        assertEquals(expectedUserDto, result);
        verify(userService).deleteDogBreedInUser(userId, dogBreedId);
    }

    @Test
    void testAddCatBreedInUser() {
        // Arrange
        Long userId = 1L;
        Long catBreedId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.addCatBreedInUser(userId, catBreedId)).thenReturn(expectedUserDto);

        // Act
        UserDto result = userController.addCatBreedInUser(userId, catBreedId);

        // Assert
        assertEquals(expectedUserDto, result);
        verify(userService).addCatBreedInUser(userId, catBreedId);
    }

    @Test
    void testDeleteCatBreedInUser() {
        // Arrange
        Long userId = 1L;
        Long catBreedId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.deleteCatBreedInUser(userId, catBreedId)).thenReturn(expectedUserDto);

        // Act
        UserDto result = userController.deleteCatBreedInUser(userId, catBreedId);

        // Assert
        assertEquals(expectedUserDto, result);
        verify(userService).deleteCatBreedInUser(userId, catBreedId);
    }

    @Test
    void testAddReviewInUser() {
        // Arrange
        Long userId = 1L;
        Long reviewId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.addReviewInUser(userId, reviewId)).thenReturn(expectedUserDto);

        // Act
        UserDto result = userController.addReviewInUser(userId, reviewId);

        // Assert
        assertEquals(expectedUserDto, result);
        verify(userService).addReviewInUser(userId, reviewId);
    }

    @Test
    void testDeleteReviewInUser() {
        // Arrange
        Long userId = 1L;
        Long reviewId = 1L;
        UserDto expectedUserDto = new UserDto();
        when(userService.deleteReviewInUser(userId, reviewId)).thenReturn(expectedUserDto);

        // Act
        UserDto result = userController.deleteReviewInUser(userId, reviewId);

        // Assert
        assertEquals(expectedUserDto, result);
        verify(userService).deleteReviewInUser(userId, reviewId);
    }
}
