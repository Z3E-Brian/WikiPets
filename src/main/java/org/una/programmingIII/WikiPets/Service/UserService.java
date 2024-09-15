package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Model.User;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUsers();

    public UserDto getUserById(Long id);

    public UserDto createUser(UserInput input);

    public void deleteUser(Long id);

    public UserDto updateUser(UserDto userDto);

    Page<UserDto> getUsers(Pageable pageable);

    void updateUser(User user);

    User findByEmail(String email);
}