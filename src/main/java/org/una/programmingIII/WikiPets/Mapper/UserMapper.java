package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.*;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toCatBreedDto(User user);
    User toCatBreed(UserDto userDto);
}

