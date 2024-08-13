package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Model.UserDto;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
//        return new UserDto(
//                user.getId(),
//                user.getName(),
//                user.getEmail(),
//                user.getFavoriteDogBreedsDto(),
//                user.getFavoriteCatBreedsDto(),
//                user.getVersion()
//        );
        return null;
    }

    private User convertToEntity(UserDto userDto) {
//        return new User(
//                userDto.getId(),
//                userDto.getName(),
//                userDto.getEmail(),
//                userDto.getFavoriteCatBreedsEntity(),
//                userDto.getFavoriteDogBreedsEntity(),
//                userDto.getVersion()
//        );
        return null;
    }
}
