package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;

public interface AdoptionCenterMapper {
    AdoptionCenterMapper INSTANCE = Mappers.getMapper(AdoptionCenterMapper.class);
    @Mapping(target = "availableDogBreeds", source = "availableDogBreeds")
    @Mapping(target = "availableCatBreeds", source = "availableCatBreeds")
    AdoptionCenterDto toAdoptionCenterDto(AdoptionCenter adoptionCenterGuide);
    @Mapping(target = "availableDogBreeds", source = "availableDogBreeds")
    @Mapping(target = "availableCatBreeds", source = "availableCatBreeds")
    AdoptionCenter toAdoptionCenter(AdoptionCenterDto behaviorGuideDto);
}
