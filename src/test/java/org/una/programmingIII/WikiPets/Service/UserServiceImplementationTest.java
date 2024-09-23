package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.Review;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplementationTest {

    @InjectMocks
    private UserServiceImplementation userServiceImplementation;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DogBreedService dogBreedService;

    @Mock
    private CatBreedService catBreedService;

    @Mock
    private ReviewService reviewService;

    @Mock
    private GenericMapper<User, UserDto> userMapper;

    @Mock
    private GenericMapper<Review, ReviewDto> reviewMapper;

    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;

    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;

    @Mock
    private GenericMapper<User, UserInput> userMapperInput;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GenericMapperFactory mapperFactory;

    private User user;
    private UserDto userDto;
    private DogBreed dogBreed;
    private CatBreed catBreed;
    private Review review;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userServiceImplementation = new UserServiceImplementation(
                userRepository,
                mapperFactory,
                passwordEncoder,
                dogBreedService,
                catBreedService,
                reviewService
        );

        review = new Review();

        dogBreed = new DogBreed();
        dogBreed.setId(1L);
        dogBreed.setName("Golden Retriever");
        dogBreed.setOrigin("Scotland");
        dogBreed.setSize(3);
        dogBreed.setCoat("Long");
        dogBreed.setColor("Golden");
        dogBreed.setLifeSpan("10-12 years");
        dogBreed.setTemperament("Intelligent, Friendly, Devoted");
        dogBreed.setDescription("Popular house dog");

        CatBreed catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);
        catBreed.setCoat("Short");
        catBreed.setColor("Cream with Dark Points");
        catBreed.setLifeSpan("15-20 years");
        catBreed.setTemperament("Social, Affectionate, Vocal");
        catBreed.setDescription("Known for their striking appearance and vocal nature");


        user = new User();
        user.setName("Justin");
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("encoded_password");
        user.setFavoriteDogBreeds(new ArrayList<>());
        user.setFavoriteCatBreeds(new ArrayList<>());
        user.setReviews(new ArrayList<>());

        userDto = new UserDto();
        userDto.setName("Justin");
        userDto.setId(1L);
        userDto.setEmail("test@example.com");
        userDto.setFavoriteDogBreeds(new ArrayList<>());
        userDto.setFavoriteCatBreeds(new ArrayList<>());
        userDto.setReviews(new ArrayList<>());

        when(mapperFactory.createMapper(User.class, UserDto.class)).thenReturn(userMapper);
        when(mapperFactory.createMapper(User.class, UserInput.class)).thenReturn(userMapperInput);
        when(mapperFactory.createMapper(Review.class, ReviewDto.class)).thenReturn(reviewMapper);
        when(mapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        userServiceImplementation = new UserServiceImplementation(
                userRepository,
                mapperFactory,
                passwordEncoder,
                dogBreedService,
                catBreedService,
                reviewService
        );

    }


    @Test
    void testFindByEmail() {
        when(userServiceImplementation.findByEmail("test@example.com")).thenReturn(user);
        User result = userServiceImplementation.findByEmail("test@example.com");
        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testAddDogBreedInUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserDto result = userServiceImplementation.addDogBreedInUser(1L, 1L);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testAddCatBreedInUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserDto result = userServiceImplementation.addCatBreedInUser(1L, 1L);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteDogBreedInUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserDto result = userServiceImplementation.deleteDogBreedInUser(1L, 1L);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteCatBreedInUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserDto result = userServiceImplementation.deleteCatBreedInUser(1L, 1L);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testAddReviewInUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(reviewService.getReviewById(anyLong())).thenReturn(new ReviewDto());
        when(reviewMapper.convertToEntity(any(ReviewDto.class))).thenReturn(review);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserDto result = userServiceImplementation.addReviewInUser(1L, 1L);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteReviewInUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(reviewService.getReviewById(anyLong())).thenReturn(new ReviewDto());
        when(reviewMapper.convertToEntity(any(ReviewDto.class))).thenReturn(review);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserDto result = userServiceImplementation.deleteReviewInUser(1L, 1L);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserDto result = userServiceImplementation.getUserById(1L);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void testCreateUser() {
        when(userMapperInput.convertToEntity(any(UserInput.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserInput input = new UserInput();
        input.setEmail("test@example.com");
        input.setPassword("password");
        input.setName("Test User");

        UserDto result = userServiceImplementation.createUser(input);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Boolean result = userServiceImplementation.deleteUser(1L);
        assertTrue(result);
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.convertToEntity(any(UserDto.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToDTO(any(User.class))).thenReturn(userDto);

        UserDto result = userServiceImplementation.updateUser(userDto);
        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(any(User.class));
    }
}
