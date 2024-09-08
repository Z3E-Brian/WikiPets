package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @QueryMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public Map<String, Object> getUsers(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDto> userDto = userService.getUsers(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("userDto", userDto.getContent());
        response.put("totalPages", userDto.getTotalPages());
        response.put("totalElements", userDto.getTotalElements());

        return response;
    }

    @QueryMapping
    public UserDto getUserById(@Argument Long id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find user");
        }
    }

    @MutationMapping
    public UserDto createUser(@Argument String name, @Argument String email) {
        try {
            return userService.createUser(new UserDto(name, email));
        } catch (Exception e) {
            throw new CustomException("Could not create user");
        }
    }

    @MutationMapping
    public void deleteUser(@Argument Long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete user");
        }
    }
}
