package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.CareTipDto;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.ReviewInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final GenericMapper<UserInput, UserDto> userMapper;
    private ReviewController reviewController;


    @Autowired
    UserController(UserService userService, GenericMapperFactory mapperFactory) {
        this.userService = userService;
        this.userMapper = mapperFactory.createMapper(UserInput.class, UserDto.class);
    }

    @QueryMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public Map<String, Object> getUsers(@Argument int page, @Argument int size) {
        try {
            return userService.getUsers(page, size);
        } catch (Exception e) {
            throw new CustomException("Could not retrieve users" + e.getMessage());
        }
    }

    @QueryMapping
    public UserDto getUserById(@Argument Long id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find user");
        }
    }

    /*@MutationMapping
    public UserDto createUser(@Argument UserInput input) {
        try {
            return userService.createUser(input);
        } catch (Exception e) {
            throw new CustomException("Could not create user");
        }
    }*/

    @MutationMapping
    public void deleteUser(@Argument Long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete user");
        }
    }

    @MutationMapping
    public UserDto updateUser(@Argument UserInput input) {
        try {
            UserDto userDto = convertToDto(input);
            return userService.updateUser(userDto);
        } catch (Exception e) {
            throw new CustomException("Could not update user: " + e.getMessage());
        }
    }

    @MutationMapping
    public UserDto addDogBreedInUser(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return userService.addDogBreedInUser(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add dog breed in user" + e.getMessage());
        }
    }

    @MutationMapping
    public UserDto addCatBreedInUser(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return userService.addCatBreedInUser(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add cat breed in user" + e.getMessage());
        }
    }

    @MutationMapping
    public UserDto deleteDogBreedInUser(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return userService.deleteDogBreedInUser(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add dog breed in user" + e.getMessage());
        }
    }

    @MutationMapping
    public UserDto deleteCatBreedInUser(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return userService.deleteCatBreedInUser(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add cat breed in user" + e.getMessage());
        }
    }

    @MutationMapping
    public UserDto addReviewInUser(@Argument Long id, @Argument Long idReview) {
        try {
            return userService.addReviewInUser(id, idReview);
        } catch (Exception e) {
            throw new CustomException("Could not add cat breed in user" + e.getMessage());
        }
    }

    @MutationMapping
    public UserDto deleteReviewInUser(@Argument Long id, @Argument Long idReview) {
        try {
            return userService.deleteReviewInUser(id, idReview);
        } catch (Exception e) {
            throw new CustomException("Could not add cat breed in user" + e.getMessage());
        }
    }

    private UserDto convertToDto(UserInput userInput) {
        return userMapper.convertToDTO(userInput);
    }

}
