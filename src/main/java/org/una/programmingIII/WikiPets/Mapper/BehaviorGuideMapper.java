package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
import org.una.programmingIII.WikiPets.Model.BehaviorGuideDto;

@Mapper(componentModel = "spring")
public interface BehaviorGuideMapper {
    BehaviorGuideMapper INSTANCE = Mappers.getMapper(BehaviorGuideMapper.class);
    @Mapping(target = "suitableDogBreeds", source = "suitableDogBreeds")
    @Mapping(target = "suitableCatBreeds", source = "suitableCatBreeds")
    BehaviorGuideDto BehaviorGuideDto(BehaviorGuide behaviorGuide);

    @Mapping(target = "suitableDogBreeds", source = "suitableDogBreeds")
    @Mapping(target = "suitableCatBreeds", source = "suitableCatBreeds")
    BehaviorGuide toBehaviorGuide(BehaviorGuideDto behaviorGuideDto);
}