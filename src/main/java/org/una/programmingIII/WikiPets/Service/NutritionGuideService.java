package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;

import java.util.List;

public interface NutritionGuideService {
    List<NutritionGuideDto> getAllNutritionGuides();

    NutritionGuideDto getNutritionGuideById(Long id);

    NutritionGuideDto getNutritionGuideByTitle(String title);

    NutritionGuideDto createNutritionGuide(NutritionGuideDto nutritionGuideDto);

    NutritionGuideDto updateNutritionGuide(NutritionGuideDto nutritionGuideDto);

    void deleteNutritionGuide(Long id);
}
