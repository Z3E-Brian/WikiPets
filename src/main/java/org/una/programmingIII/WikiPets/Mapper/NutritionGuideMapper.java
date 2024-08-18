package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.NutritionGuide;
import org.una.programmingIII.WikiPets.Model.NutritionGuideDto;
@Mapper
public interface NutritionGuideMapper {
    NutritionGuideMapper INSTANCE = Mappers.getMapper(NutritionGuideMapper.class);

    @Mapping(target = "recommendedCatBreeds", source = "recommendedCatBreeds")
    @Mapping(target = "recommendedDogBreeds", source = "recommendedDogBreeds")
    public NutritionGuide toNutritionGuide(NutritionGuideDto nutritionGuideDto);

    @Mapping(target = "recommendedCatBreeds", source = "recommendedCatBreeds")
    @Mapping(target = "recommendedDogBreeds", source = "recommendedDogBreeds")
    public NutritionGuideDto toNutritionGuideDto(NutritionGuide nutritionGuide);
}
