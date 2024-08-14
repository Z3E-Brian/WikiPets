package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.*;

@Mapper
public interface TrainingGuideMapper {
    TrainingGuideMapper INSTANCE = Mappers.getMapper(TrainingGuideMapper.class);

    @Mapping(target = "catBreeds", source = "catsBreedDto")
    @Mapping(target = "dogBreeds", source = "dogsBreedDto")
    TrainingGuideDto toTrainingGuideDto(TrainingGuide trainingGuide);

    @Mapping(target = "catsBreedDto", source = "catBreeds")
    @Mapping(target = "dogsBreedDto", source = "dogBreeds")
    TrainingGuide toTrainingGuide(TrainingGuideDto trainingGuideDto);
}
