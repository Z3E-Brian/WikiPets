package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUsers();
    public UserDto getUserById(Long id);
    public UserDto createUser(UserDto userDto);
    public void deleteUser(Long id);
    public UserDto updateUser(UserDto userDto);
}
