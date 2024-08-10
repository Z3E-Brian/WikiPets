package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        CatBreed siamese = new CatBreed();
        siamese.setId(1L);
        siamese.setName("Siamese");

        DogBreed goldenRetriever = new DogBreed();
        goldenRetriever.setId(1L);
        goldenRetriever.setName("Golden Retriever");

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setFavoriteCatBreeds(Arrays.asList(siamese));
        user.setFavoriteDogBreeds(Arrays.asList(goldenRetriever));

        userDto = new UserDto(1L, "John Doe", "john.doe@example.com", Arrays.asList(new DogBreedDto()), Arrays.asList(new CatBreedDto()));
    }

    @Test
    public void createUserTest() {
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDto result = userService.createUser(userDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals(1, result.getFavoriteCatBreeds().size());
        assertEquals(1, result.getFavoriteDogBreeds().size());
    }

    @Test
    public void updateUserTest() {
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDto result = userService.updateUser(userDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals(1, result.getFavoriteCatBreeds().size());
        assertEquals(1, result.getFavoriteDogBreeds().size());
    }

    @Test
    public void getUserByIdTest() {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));

        UserDto result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1, result.getFavoriteCatBreeds().size());
        assertEquals(1, result.getFavoriteDogBreeds().size());
    }

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<UserDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
        assertEquals(1, result.get(0).getFavoriteCatBreeds().size());
        assertEquals(1, result.get(0).getFavoriteDogBreeds().size());
    }

    @Test
    public void deleteUserTest() {
        userService.deleteUser(1L);
    }
}
