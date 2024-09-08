package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;

import java.util.List;

public interface NutritionGuideService {
    Page<NutritionGuideDto> getAllNutritionGuides(Pageable pageable);

    NutritionGuideDto getNutritionGuideById(Long id);

    NutritionGuideDto getNutritionGuideByTitle(String title);

    NutritionGuideDto addRecommendedDogBreed(Long IdGuide, Long dogBreedId);

    NutritionGuideDto addRecommendedCatBreed(Long IdGuide, Long catBreedId);

    NutritionGuideDto createNutritionGuide(NutritionGuideDto nutritionGuideDto);

    NutritionGuideDto updateNutritionGuide(NutritionGuideDto nutritionGuideDto);

    void deleteNutritionGuide(Long id);
}
