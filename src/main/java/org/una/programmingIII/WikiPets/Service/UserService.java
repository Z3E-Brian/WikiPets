package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUsers();

    public UserDto getUserById(Long id);

    public UserDto createUser(UserDto userDto);

    public void deleteUser(Long id);

    public UserDto updateUser(UserDto userDto);

    Page<UserDto> getUsers(Pageable pageable);


}