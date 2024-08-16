package org.una.programmingIII.WikiPets.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BehaviorGuideTest {

    private BehaviorGuide behaviorGuide;
    private List<DogBreed> dogBreeds;
    private List<CatBreed> catBreeds;

    @BeforeEach
    public void setUp() {
        dogBreeds = new ArrayList<>();
        catBreeds = new ArrayList<>();
        behaviorGuide = new BehaviorGuide();
        behaviorGuide.setId(1L);
        behaviorGuide.setTitle("Behavior Guide Title");
        behaviorGuide.setContent("Behavior Guide Content");
        behaviorGuide.setBehavioralIssues("Behavioral Issues");
        behaviorGuide.setSolutions("Solutions");
        behaviorGuide.setSuitableDogBreeds(dogBreeds);
        behaviorGuide.setSuitableCatBreeds(catBreeds);
        behaviorGuide.setCreateDate(LocalDate.now());
    }

    @Test
    public void testBehaviorGuideCreation() {
        assertNotNull(behaviorGuide);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, behaviorGuide.getId());
        assertEquals("Behavior Guide Title", behaviorGuide.getTitle());
        assertEquals("Behavior Guide Content", behaviorGuide.getContent());
        assertEquals("Behavioral Issues", behaviorGuide.getBehavioralIssues());
        assertEquals("Solutions", behaviorGuide.getSolutions());
        assertEquals(dogBreeds, behaviorGuide.getSuitableDogBreeds());
        assertEquals(catBreeds, behaviorGuide.getSuitableCatBreeds());
        assertEquals(LocalDate.now(), behaviorGuide.getCreateDate());
    }

    @Test
    public void testSetters() {
        List<DogBreed> newDogBreeds = new ArrayList<>();
        List<CatBreed> newCatBreeds = new ArrayList<>();
        LocalDate newDate = LocalDate.of(2022, 1, 1);

        behaviorGuide.setTitle("New Title");
        behaviorGuide.setContent("New Content");
        behaviorGuide.setBehavioralIssues("New Issues");
        behaviorGuide.setSolutions("New Solutions");
        behaviorGuide.setSuitableDogBreeds(newDogBreeds);
        behaviorGuide.setSuitableCatBreeds(newCatBreeds);
        behaviorGuide.setCreateDate(newDate);

        assertEquals("New Title", behaviorGuide.getTitle());
        assertEquals("New Content", behaviorGuide.getContent());
        assertEquals("New Issues", behaviorGuide.getBehavioralIssues());
        assertEquals("New Solutions", behaviorGuide.getSolutions());
        assertEquals(newDogBreeds, behaviorGuide.getSuitableDogBreeds());
        assertEquals(newCatBreeds, behaviorGuide.getSuitableCatBreeds());
        assertEquals(newDate, behaviorGuide.getCreateDate());
    }
}