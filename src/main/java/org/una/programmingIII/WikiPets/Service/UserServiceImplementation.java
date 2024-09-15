package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Review;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final GenericMapper<User, UserDto> userMapper;

    private final GenericMapper<Review, ReviewDto> reviewMapper;

    private final GenericMapper<User, UserInput>  userMapperInput;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, GenericMapperFactory mapperFactory, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = mapperFactory.createMapper(User.class, UserDto.class);
        this.reviewMapper = mapperFactory.createMapper(Review.class, ReviewDto.class);
        this.userMapperInput = mapperFactory.createMapper(User.class, UserInput.class);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this.userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found with id: " + id));
        return userMapper.convertToDTO(user);
    }

    @Override
    public UserDto createUser(UserInput input) {
        User user = userMapperInput.convertToEntity(input);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setCreateDate(LocalDate.now());
        user.setLastUpdate(LocalDate.now());
        User savedUser = userRepository.save(user);
        return userMapper.convertToDTO(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(@NotNull UserDto userDto) {
        User oldUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new CustomException("User with the ID: " + userDto.getId() + " not found"));

        User newUser = userMapper.convertToEntity(userDto);
        newUser.setCreateDate(oldUser.getCreateDate());
        newUser.setLastUpdate(LocalDate.now());

        List<Review> oldReviews = oldUser.getReviews();
        List<ReviewDto> oldReviewsDto = reviewMapper.convertToDTOList(oldReviews);
        List<ReviewDto> newReviewsDto = userDto.getReviewsDto();

        for (ReviewDto reviewDto : oldReviewsDto) {
            if (!(newReviewsDto.contains(reviewDto))) {
                newUser.getReviews().add(reviewMapper.convertToEntity(reviewDto));
            }
        }

        //check other entitites relation
        userRepository.save(newUser);
        return userMapper.convertToDTO(newUser);
    }

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {
        Page<User> user = userRepository.findAll(pageable);
        return user.map(userMapper::convertToDTO);
    }


    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

}