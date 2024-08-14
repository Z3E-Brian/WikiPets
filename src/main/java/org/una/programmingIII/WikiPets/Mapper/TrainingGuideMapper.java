package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.*;

@Mapper
public interface TrainingGuideMapper {
    TrainingGuideMapper INSTANCE = Mappers.getMapper(TrainingGuideMapper.class);

    TrainingGuideDto toTrainingGuideDto(TrainingGuide trainingGuide);

    TrainingGuide toTrainingGuide(TrainingGuideDto trainingGuideDto);
}

