package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NutritionGuideDtoTest {
    @Test
    void gettersAndSettersTest() {
        List<DogBreedDto> dogBreedIds = new ArrayList<>();
        List<CatBreedDto> catBreedIds = new ArrayList<>();
        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
        nutritionGuideDto.setId(1L);
        nutritionGuideDto.setTitle("Title");
        nutritionGuideDto.setContent("Content");
        nutritionGuideDto.setRecommendedDogBreeds(dogBreedIds);
        nutritionGuideDto.setRecommendedCatBreeds(catBreedIds);
        nutritionGuideDto.setDietaryRequirements("Dietary");

        assertEquals(1L, nutritionGuideDto.getId());
        assertEquals("Title", nutritionGuideDto.getTitle());
        assertEquals("Content", nutritionGuideDto.getContent());
        assertEquals(dogBreedIds, nutritionGuideDto.getRecommendedDogBreeds());
        assertEquals(catBreedIds, nutritionGuideDto.getRecommendedCatBreeds());
        assertEquals("Dietary", nutritionGuideDto.getDietaryRequirements());

        nutritionGuideDto.setTitle("New Title");
        assertEquals("New Title", nutritionGuideDto.getTitle());
    }
}
