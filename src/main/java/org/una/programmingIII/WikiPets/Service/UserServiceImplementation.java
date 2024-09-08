package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Review;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final GenericMapper<User, UserDto> userMapper;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, GenericMapperFactory mapperFactory) {
        this.userRepository = userRepository;
        this.userMapper = mapperFactory.createMapper(User.class, UserDto.class);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this.userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found with id: " + id));
        return userMapper.convertToDTO(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.convertToDTO(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        User updatedUser = userRepository.save(user);
        return userMapper.convertToDTO(updatedUser);
    }

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {
        Page<User> user = userRepository.findAll(pageable);
        return user.map(userMapper::convertToDTO);
    }
}