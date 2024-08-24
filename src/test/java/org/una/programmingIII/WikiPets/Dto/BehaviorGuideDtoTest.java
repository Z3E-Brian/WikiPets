package org.una.programmingIII.WikiPets.Dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BehaviorGuideDtoTest {

    private BehaviorGuideDto behaviorGuideDto;
    private List<DogBreedDto> dogBreeds;
    private List<CatBreedDto> catBreeds;

    @BeforeEach
    public void setUp() {
        dogBreeds = new ArrayList<>();
        catBreeds = new ArrayList<>();
        behaviorGuideDto = new BehaviorGuideDto();
        behaviorGuideDto.setId(1L);
        behaviorGuideDto.setTitle("Behavior Guide Title");
        behaviorGuideDto.setContent("Behavior Guide Content");
        behaviorGuideDto.setBehavioralIssues("Behavioral Issues");
        behaviorGuideDto.setSolutions("Solutions");
        behaviorGuideDto.setSuitableDogBreeds(dogBreeds);
        behaviorGuideDto.setSuitableCatBreeds(catBreeds);
        behaviorGuideDto.setCreateDate(LocalDate.now());
        behaviorGuideDto.setLastUpdate(LocalDate.now());
    }

    @Test
    public void testBehaviorGuideDtoCreation() {
        assertNotNull(behaviorGuideDto);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, behaviorGuideDto.getId());
        assertEquals("Behavior Guide Title", behaviorGuideDto.getTitle());
        assertEquals("Behavior Guide Content", behaviorGuideDto.getContent());
        assertEquals("Behavioral Issues", behaviorGuideDto.getBehavioralIssues());
        assertEquals("Solutions", behaviorGuideDto.getSolutions());
        assertEquals(dogBreeds, behaviorGuideDto.getSuitableDogBreeds());
        assertEquals(catBreeds, behaviorGuideDto.getSuitableCatBreeds());
        assertEquals(LocalDate.now(), behaviorGuideDto.getCreateDate());
        assertEquals(LocalDate.now(), behaviorGuideDto.getLastUpdate());
    }

    @Test
    public void testSetters() {
        List<DogBreedDto> newDogBreeds = new ArrayList<>();
        List<CatBreedDto> newCatBreeds = new ArrayList<>();
        LocalDate newDate = LocalDate.of(2022, 1, 1);

        behaviorGuideDto.setTitle("New Title");
        behaviorGuideDto.setContent("New Content");
        behaviorGuideDto.setBehavioralIssues("New Issues");
        behaviorGuideDto.setSolutions("New Solutions");
        behaviorGuideDto.setSuitableDogBreeds(newDogBreeds);
        behaviorGuideDto.setSuitableCatBreeds(newCatBreeds);
        behaviorGuideDto.setCreateDate(newDate);
        behaviorGuideDto.setLastUpdate(newDate);

        assertEquals("New Title", behaviorGuideDto.getTitle());
        assertEquals("New Content", behaviorGuideDto.getContent());
        assertEquals("New Issues", behaviorGuideDto.getBehavioralIssues());
        assertEquals("New Solutions", behaviorGuideDto.getSolutions());
        assertEquals(newDogBreeds, behaviorGuideDto.getSuitableDogBreeds());
        assertEquals(newCatBreeds, behaviorGuideDto.getSuitableCatBreeds());
        assertEquals(newDate, behaviorGuideDto.getCreateDate());
        assertEquals(newDate, behaviorGuideDto.getLastUpdate());
    }

    @Test
    public void testNoArgsConstructor() {
        BehaviorGuideDto dto = new BehaviorGuideDto();
        assertNull(dto.getSuitableDogBreeds());
        assertNull(dto.getSuitableCatBreeds());
    }
}