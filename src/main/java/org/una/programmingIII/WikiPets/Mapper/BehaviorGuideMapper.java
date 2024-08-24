package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Model.*;

public interface BehaviorGuideMapper {
    BehaviorGuideMapper INSTANCE = Mappers.getMapper(BehaviorGuideMapper.class);
    BehaviorGuideDto toCatBreedDto(BehaviorGuide behaviorGuide);
    BehaviorGuide toBehaviorGuide(BehaviorGuideDto behaviorGuideDto);

}
