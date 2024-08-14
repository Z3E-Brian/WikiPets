package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.*;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "favoriteCatBreeds", source = "favoriteCatBreedsDto")
    @Mapping(target = "favoriteDogBreeds", source = "favoriteDogBreedsDto")
    UserDto toUserDto(User user);
    @Mapping(target = "favoriteCatBreedsDto", source = "favoriteCatBreed")
    @Mapping(target = "favoriteDogBreedsDto", source = "favoriteDogBreeds")
    User toUser(UserDto userDto);
}