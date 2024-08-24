package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.HealthIssue;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;

@Mapper
public interface HealthIssueMapper {
HealthIssueMapper INSTANCE = Mappers.getMapper(HealthIssueMapper.class);
    @Mapping(target = "suitableDogBreeds", source = "suitableDogBreeds")
    @Mapping(target = "suitableCatBreeds", source = "suitableCatBreeds")
    HealthIssueDto toHealthIssueDto(HealthIssue healthIssue);

    @Mapping(target = "suitableDogBreeds", source = "suitableDogBreeds")
    @Mapping(target = "suitableCatBreeds", source = "suitableCatBreeds")
    HealthIssue toHealthIssue(HealthIssueDto healthIssueDto);
}
