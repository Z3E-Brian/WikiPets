package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NutritionGuideTest {
    @Test
    void gettersAndSettersTest() {
        List<DogBreed> dogBreeds = new ArrayList<>();
        List<CatBreed> catBreeds = new ArrayList<>();
        NutritionGuide nutritionGuide = new NutritionGuide();
        nutritionGuide.setId(1L);
        nutritionGuide.setTitle("Title");
        nutritionGuide.setContent("Content");
        nutritionGuide.setRecommendedDogBreeds(dogBreeds);
        nutritionGuide.setRecommendedCatBreeds(catBreeds);
        nutritionGuide.setDietaryRequirements("Dietary");

        assertEquals(1L, nutritionGuide.getId());
        assertEquals("Title", nutritionGuide.getTitle());
        assertEquals("Content", nutritionGuide.getContent());
        assertEquals(dogBreeds, nutritionGuide.getRecommendedDogBreeds());
        assertEquals(catBreeds, nutritionGuide.getRecommendedCatBreeds());
        assertEquals("Dietary", nutritionGuide.getDietaryRequirements());


        nutritionGuide.setTitle("New Title");
        assertEquals("New Title", nutritionGuide.getTitle());
    }

    @Test
    void relationsTest() {
        DogBreed dogBreed = new DogBreed();
        CatBreed catBreed = new CatBreed();
        List<DogBreed> dogBreeds = new ArrayList<>();
        List<CatBreed> catBreeds = new ArrayList<>();
        dogBreeds.add(dogBreed);
        catBreeds.add(catBreed);

        NutritionGuide nutritionGuide = new NutritionGuide();
        nutritionGuide.setRecommendedDogBreeds(dogBreeds);
        nutritionGuide.setRecommendedCatBreeds(catBreeds);

        assertEquals(1, nutritionGuide.getRecommendedDogBreeds().size());
        assertEquals(1, nutritionGuide.getRecommendedCatBreeds().size());
        assertEquals(dogBreed, nutritionGuide.getRecommendedDogBreeds().get(0));
        assertEquals(catBreed, nutritionGuide.getRecommendedCatBreeds().get(0));
    }
}
