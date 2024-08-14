package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.UserMapper;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Model.UserDto;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = UserMapper.INSTANCE;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found with id: " + id));
        return convertToDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    private UserDto convertToDto(User user) {
        return UserMapper.INSTANCE.toCatBreedDto(user);
    }

    private User convertToEntity(UserDto userDto) {
        return UserMapper.INSTANCE.toCatBreed(userDto);
    }
}
