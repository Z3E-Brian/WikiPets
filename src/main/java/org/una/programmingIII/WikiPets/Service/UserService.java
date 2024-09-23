package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDto getUserById(Long id);

    UserDto createUser(UserInput input);

    Boolean deleteUser(Long id);

    UserDto updateUser(UserDto userDto);

    Map<String, Object> getUsers(int page, int size);

    void updateUser(User user);

    User findByEmail(String email);

    UserDto addDogBreedInUser(Long id, Long idDogBreed);

    UserDto addCatBreedInUser(Long id, Long idCatBreed);

    UserDto deleteDogBreedInUser(Long id, Long idDogBreed);

    UserDto deleteCatBreedInUser(Long id, Long idCatBreed);

    UserDto addReviewInUser(Long id, Long idReview);

    UserDto deleteReviewInUser(Long id, Long idReview);

    void generateFalseUsers(int amount);
}