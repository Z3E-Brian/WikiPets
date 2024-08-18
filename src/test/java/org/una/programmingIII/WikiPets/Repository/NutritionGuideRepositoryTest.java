package org.una.programmingIII.WikiPets.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Model.NutritionGuide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class NutritionGuideRepositoryTest {
    NutritionGuide nutritionGuide;
    @Mock
    private NutritionGuideRepository nutritionGuideRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        nutritionGuide = new NutritionGuide();
        nutritionGuide.setId(1L);
        nutritionGuide.setTitle("Test Title");
        nutritionGuide.setContent("Test Content");
        nutritionGuide.setDietaryRequirements("Test Dietary Requirements");
        nutritionGuideRepository.save(nutritionGuide);
    }
    @Test
    void testFindByTitle() {
        when(nutritionGuideRepository.findByTitle("Test Title")).thenReturn(nutritionGuide);
        NutritionGuide found = nutritionGuideRepository.findByTitle("Test Title");
        assertNotNull(found);
        assertEquals("Test Title", found.getTitle());
    }
}
