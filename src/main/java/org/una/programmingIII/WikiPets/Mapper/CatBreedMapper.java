package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.CatBreedDto;

@Mapper
public interface CatBreedMapper {
    CatBreedMapper INSTANCE = Mappers.getMapper(CatBreedMapper.class);
    CatBreedDto toCatBreedDto(CatBreed catBreed);
    CatBreed toCatBreed(CatBreedDto catBreedDto);
}
