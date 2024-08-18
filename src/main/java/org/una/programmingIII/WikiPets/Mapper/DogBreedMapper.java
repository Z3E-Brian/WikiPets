package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.DogBreedDto;
@Mapper
public interface DogBreedMapper {
    DogBreedMapper INSTANCE = Mappers.getMapper(DogBreedMapper.class);
//    @Mapping(target = "adoptionCenters", source = "adoptionCenters")
//    @Mapping(target = "healthIssues", source = "healthIssues")
//    @Mapping(target = "nutritionGuides", source = "nutritionGuides")
//    @Mapping(target = "users", source = "users")
//    @Mapping(target = "trainingGuides", source = "trainingGuides")
//    @Mapping(target = "behaviorGuides", source = "behaviorGuides")
//    @Mapping(target = "careTips", source = "careTips")
//    @Mapping(target = "groomingGuides", source = "groomingGuides")
//    @Mapping(target = "feedingSchedules", source = "feedingSchedules")
//    @Mapping(target = "images", source = "images")
//    @Mapping(target = "videos", source = "videos")
//    @Mapping(target = "reviews", source = "reviews")
    DogBreed toDogBreed(DogBreedDto dogBreedDto);
//    @Mapping(target = "adoptionCenters", source = "adoptionCenters")
//    @Mapping(target = "healthIssues", source = "healthIssues")
//    @Mapping(target = "nutritionGuides", source = "nutritionGuides")
//    @Mapping(target = "users", source = "users")
//    @Mapping(target = "trainingGuides", source = "trainingGuides")
//    @Mapping(target = "behaviorGuides", source = "behaviorGuides")
//    @Mapping(target = "careTips", source = "careTips")
//    @Mapping(target = "groomingGuides", source = "groomingGuides")
//    @Mapping(target = "feedingSchedules", source = "feedingSchedules")
//    @Mapping(target = "images", source = "images")
//    @Mapping(target = "videos", source = "videos")
//    @Mapping(target = "reviews", source = "reviews")
    DogBreedDto toDogBreedDto(DogBreed dogBreed);
}
