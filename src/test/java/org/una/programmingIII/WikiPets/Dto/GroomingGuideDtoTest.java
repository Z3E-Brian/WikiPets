package org.una.programmingIII.WikiPets.Dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GroomingGuideDtoTest {

    private GroomingGuideDto groomingGuideDto;
    private List<DogBreedDto> dogBreeds;
    private List<CatBreedDto> catBreeds;

    @BeforeEach
    public void setUp() {
        dogBreeds = new ArrayList<>();
        catBreeds = new ArrayList<>();
        groomingGuideDto = new GroomingGuideDto();
        groomingGuideDto.setId(1L);
        groomingGuideDto.setContent("Grooming Guide Content");
        groomingGuideDto.setToolsNeeded("Brush, Shampoo");
        groomingGuideDto.setSteps("Step 1: Brush the dog, Step 2: Wash with shampoo");
        groomingGuideDto.setSuitableDogBreeds(dogBreeds);
        groomingGuideDto.setSuitableCatBreeds(catBreeds);
        groomingGuideDto.setCreateDate(LocalDate.now());
        groomingGuideDto.setLastUpdate(LocalDate.now());
    }

    @Test
    public void testGroomingGuideDtoCreation() {
        assertNotNull(groomingGuideDto);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, groomingGuideDto.getId());
        assertEquals("Grooming Guide Content", groomingGuideDto.getContent());
        assertEquals("Brush, Shampoo", groomingGuideDto.getToolsNeeded());
        assertEquals("Step 1: Brush the dog, Step 2: Wash with shampoo", groomingGuideDto.getSteps());
        assertEquals(dogBreeds, groomingGuideDto.getSuitableDogBreeds());
        assertEquals(catBreeds, groomingGuideDto.getSuitableCatBreeds());
        assertEquals(LocalDate.now(), groomingGuideDto.getCreateDate());
        assertEquals(LocalDate.now(), groomingGuideDto.getLastUpdate());
    }

    @Test
    public void testSetters() {
        List<DogBreedDto> newDogBreeds = new ArrayList<>();
        List<CatBreedDto> newCatBreeds = new ArrayList<>();
        LocalDate newDate = LocalDate.of(2022, 1, 1);

        groomingGuideDto.setContent("New Content");
        groomingGuideDto.setToolsNeeded("New Tools");
        groomingGuideDto.setSteps("New Steps");
        groomingGuideDto.setSuitableDogBreeds(newDogBreeds);
        groomingGuideDto.setSuitableCatBreeds(newCatBreeds);
        groomingGuideDto.setCreateDate(newDate);
        groomingGuideDto.setLastUpdate(newDate);

        assertEquals("New Content", groomingGuideDto.getContent());
        assertEquals("New Tools", groomingGuideDto.getToolsNeeded());
        assertEquals("New Steps", groomingGuideDto.getSteps());
        assertEquals(newDogBreeds, groomingGuideDto.getSuitableDogBreeds());
        assertEquals(newCatBreeds, groomingGuideDto.getSuitableCatBreeds());
        assertEquals(newDate, groomingGuideDto.getCreateDate());
        assertEquals(newDate, groomingGuideDto.getLastUpdate());
    }

    @Test
    public void testNoArgsConstructor() {
        GroomingGuideDto dto = new GroomingGuideDto();
        assertNull(dto.getSuitableDogBreeds());
        assertNull(dto.getSuitableCatBreeds());
    }
}
