package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;

import java.util.List;
import java.util.Map;

public interface NutritionGuideService {
    Map<String, Object> getAllNutritionGuides(int page, int size);

    NutritionGuideDto getNutritionGuideById(Long id);

    NutritionGuideDto getNutritionGuideByTitle(String title);

    NutritionGuideDto addRecommendedDogBreed(Long IdGuide, Long dogBreedId);

    NutritionGuideDto addRecommendedCatBreed(Long IdGuide, Long catBreedId);

    NutritionGuideDto createNutritionGuide(NutritionGuideDto nutritionGuideDto);

    NutritionGuideDto updateNutritionGuide(NutritionGuideDto nutritionGuideDto);

    Boolean deleteNutritionGuide(Long id);
}
