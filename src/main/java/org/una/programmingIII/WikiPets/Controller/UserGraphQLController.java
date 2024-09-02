package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Service.UserServiceImplementation;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserGraphQLController {
    private final UserServiceImplementation userService;

    @QueryMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @MutationMapping
    public UserDto createUser(@Argument String name, @Argument String email) {

        return userService.createUser(new UserDto(name,email));
    }

    @MutationMapping
    public boolean deleteUser(@Argument Long id) {
         userService.deleteUser(id);
         return true;
    }
}
