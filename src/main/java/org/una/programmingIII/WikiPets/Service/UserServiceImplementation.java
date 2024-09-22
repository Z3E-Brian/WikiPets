package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final DogBreedService dogBreedService;

    private final CatBreedService catBreedService;

    private final ReviewService reviewService;

    private final GenericMapper<User, UserDto> userMapper;

    private final GenericMapper<Review, ReviewDto> reviewMapper;

    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;

    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;

    private final GenericMapper<User, UserInput> userMapperInput;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, GenericMapperFactory mapperFactory, PasswordEncoder passwordEncoder, DogBreedService dogBreedService, CatBreedService catBreedService, ReviewService reviewService) {
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
        this.reviewService = reviewService;
        this.userRepository = userRepository;
        this.userMapper = mapperFactory.createMapper(User.class, UserDto.class);
        this.reviewMapper = mapperFactory.createMapper(Review.class, ReviewDto.class);
        this.userMapperInput = mapperFactory.createMapper(User.class, UserInput.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto addDogBreedInUser(Long id, Long idDogBreed) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("User not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!user.getFavoriteDogBreeds().contains(dogBreed)) {
            user.getFavoriteDogBreeds().add(dogBreed);
        }
        return userMapper.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDto addCatBreedInUser(Long id, Long idCatBreed) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("User not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!user.getFavoriteCatBreeds().contains(catBreed)) {
            user.getFavoriteCatBreeds().add(catBreed);
        }
        return userMapper.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDto deleteDogBreedInUser(Long id, Long idDogBreed) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("User not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        user.getFavoriteDogBreeds().remove(dogBreed);
        return userMapper.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDto deleteCatBreedInUser(Long id, Long idCatBreed) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("User not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        user.getFavoriteCatBreeds().remove(catBreed);
        return userMapper.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDto addReviewInUser(Long id, Long idReview) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("User not found"));
        Review review = reviewMapper.convertToEntity(reviewService.getReviewById(idReview));
        if (!user.getReviews().contains(review)) {
            user.getReviews().add(review);
        }
        return userMapper.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDto deleteReviewInUser(Long id, Long idReview) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("User not found"));
        Review review = reviewMapper.convertToEntity(reviewService.getReviewById(idReview));
        user.getReviews().remove(review);
        return userMapper.convertToDTO(userRepository.save(user));

    }

    @Override
    public UserDto getUserById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid user ID");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundElementException("User Not Found with id: " + id));
        return userMapper.convertToDTO(user);
    }

    @Override
    public UserDto createUser(UserInput input) {
        if (input.getEmail().isBlank() || input.getPassword().isBlank() || input.getName().isBlank()) {
            throw new BlankInputException("Can't accept space(s) in blank");
        }
        User user = userMapperInput.convertToEntity(input);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setCreateDate(LocalDate.now());
        user.setLastUpdate(LocalDate.now());
        User savedUser = userRepository.save(user);
        return userMapper.convertToDTO(savedUser);
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundElementException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDto updateUser(@NotNull UserDto userDto) {
        if (userDto.getEmail().isBlank() || userDto.getName().isBlank()) {
            throw new BlankInputException("Can't accept space(s) in blank");
        }
        User oldUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NotFoundElementException("User with the ID: " + userDto.getId() + " not found"));

        User newUser = userMapper.convertToEntity(userDto);
        newUser.setCreateDate(oldUser.getCreateDate());
        newUser.setLastUpdate(LocalDate.now());

        List<Review> oldReviews = oldUser.getReviews();
        List<ReviewDto> oldReviewsDto = reviewMapper.convertToDTOList(oldReviews);
        List<ReviewDto> newReviewsDto = userDto.getReviews();

        for (ReviewDto reviewDto : oldReviewsDto) {
            if (!(newReviewsDto.contains(reviewDto))) {
                newUser.getReviews().add(reviewMapper.convertToEntity(reviewDto));
            }
        }
        userRepository.save(newUser);
        return userMapper.convertToDTO(newUser);
    }

    @Override
    public Map<String, Object> getUsers(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        users.forEach(user -> {
            user.setFavoriteCatBreeds(user.getFavoriteCatBreeds().stream().limit(10).collect(Collectors.toList()));
            user.setFavoriteDogBreeds(user.getFavoriteDogBreeds().stream().limit(10).collect(Collectors.toList()));
            user.setReviews(user.getReviews().stream().limit(10).collect(Collectors.toList()));
        });
        return Map.of("users", users.map(this::convertToDto).getContent(), "totalPages", users.getTotalPages(), "totalElements", users.getTotalElements());
    }


    private UserDto convertToDto(User user) {
        return userMapper.convertToDTO(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

}