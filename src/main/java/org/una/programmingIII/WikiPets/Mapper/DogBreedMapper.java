package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.DogBreedDto;

@Mapper
public interface DogBreedMapper {
    DogBreedMapper INSTANCE = Mappers.getMapper(DogBreedMapper.class);
    DogBreed toDogBreed(DogBreedDto dogBreedDto);
    DogBreedDto toDogBreedDto(DogBreed dogBreed);
}
