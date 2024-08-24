package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.GroomingGuide;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;

public interface GroomingGuideMapper {
    GroomingGuideMapper INSTANCE = Mappers.getMapper(GroomingGuideMapper.class);
    @Mapping(target = "availableDogBreeds", source = "availableDogBreeds")
    @Mapping(target = "availableCatBreeds", source = "availableCatBreeds")
    GroomingGuideDto toGroomingGuideDto(GroomingGuide groomingGuide);
    @Mapping(target = "availableDogBreeds", source = "availableDogBreeds")
    @Mapping(target = "availableCatBreeds", source = "availableCatBreeds")
    GroomingGuide toGroomingGuide(GroomingGuideDto groomingGuideDto);
}