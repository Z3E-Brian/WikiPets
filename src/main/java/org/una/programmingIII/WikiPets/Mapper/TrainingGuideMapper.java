package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Model.*;

@Mapper
public interface TrainingGuideMapper {
    TrainingGuideMapper INSTANCE = Mappers.getMapper(TrainingGuideMapper.class);

    @Mapping(target = "catsBreedDto", source = "catBreeds")
    @Mapping(target = "dogsBreedDto", source = "dogBreeds")
    TrainingGuideDto toTrainingGuideDto(TrainingGuide trainingGuide);

    @Mapping(target = "catBreeds", source = "catsBreedDto")
    @Mapping(target = "dogBreeds", source = "dogsBreedDto")
    TrainingGuide toTrainingGuide(TrainingGuideDto trainingGuideDto);
}
