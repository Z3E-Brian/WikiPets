package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.UserService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final GenericMapper<UserInput, UserDto> userMapper;

    @Autowired
    UserController(UserService userService, GenericMapperFactory mapperFactory) {
        this.userService = userService;
        this.userMapper = mapperFactory.createMapper(UserInput.class, UserDto.class);
    }

    @QueryMapping
    public Map<String, Object> getUsers(@Argument int page, @Argument int size) {
        try {
            return userService.getUsers(page, size);
        } catch (NotFoundElementException e) {
            throw new NotFoundElementException("Could not retrieve users" + e.getMessage());
        }
    }

    @QueryMapping
    public UserDto getUserById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        return userService.deleteUser(id);
    }

    @MutationMapping
    public UserDto updateUser(@Argument UserInput input) {
        UserDto userDto = convertToDto(input);
        return userService.updateUser(userDto);
    }

    @MutationMapping
    public UserDto addDogBreedInUser(@Argument Long id, @Argument Long idDogBreed) {
        return userService.addDogBreedInUser(id, idDogBreed);
    }

    @MutationMapping
    public UserDto addCatBreedInUser(@Argument Long id, @Argument Long idCatBreed) {
        return userService.addCatBreedInUser(id, idCatBreed);
    }

    @MutationMapping
    public UserDto deleteDogBreedInUser(@Argument Long id, @Argument Long idDogBreed) {
        return userService.deleteDogBreedInUser(id, idDogBreed);
    }

    @MutationMapping
    public UserDto deleteCatBreedInUser(@Argument Long id, @Argument Long idCatBreed) {
        return userService.deleteCatBreedInUser(id, idCatBreed);
    }

    @MutationMapping
    public UserDto addReviewInUser(@Argument Long id, @Argument Long idReview) {
        return userService.addReviewInUser(id, idReview);
    }

    @MutationMapping
    public UserDto deleteReviewInUser(@Argument Long id, @Argument Long idReview) {
        return userService.deleteReviewInUser(id, idReview);
    }

    private UserDto convertToDto(UserInput userInput) {
        return userMapper.convertToDTO(userInput);
    }

}
