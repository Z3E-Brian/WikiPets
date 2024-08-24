package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.UserMapper;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
public interface UserService {
    public List<UserDto> getAllUsers();
    public UserDto getUserById(Long id);
    public UserDto createUser(UserDto userDto);
    public void deleteUser(Long id);
    public UserDto updateUser(UserDto userDto);
}