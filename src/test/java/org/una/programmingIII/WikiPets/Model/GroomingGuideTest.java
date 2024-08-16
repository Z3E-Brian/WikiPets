package org.una.programmingIII.WikiPets.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class GroomingGuideTest {

    private GroomingGuide groomingGuide;
    private List<DogBreed> dogBreeds;

    @BeforeEach
    public void setUp() {
        dogBreeds = new ArrayList<>();
        groomingGuide = new GroomingGuide();
        groomingGuide.setId(1L);
        groomingGuide.setContent("Grooming Content");
        groomingGuide.setToolsNeeded("Tools Needed");
        groomingGuide.setSteps("Grooming Steps");
        groomingGuide.setSuitableDogBreeds(dogBreeds);
    }

    @Test
    public void testGroomingGuideCreation() {
        assertNotNull(groomingGuide);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, groomingGuide.getId());
        assertEquals("Grooming Content", groomingGuide.getContent());
        assertEquals("Tools Needed", groomingGuide.getToolsNeeded());
        assertEquals("Grooming Steps", groomingGuide.getSteps());
        assertEquals(dogBreeds, groomingGuide.getSuitableDogBreeds());
    }

    @Test
    public void testSetters() {
        List<DogBreed> newDogBreeds = new ArrayList<>();

        groomingGuide.setContent("New Content");
        groomingGuide.setToolsNeeded("New Tools");
        groomingGuide.setSteps("New Steps");
        groomingGuide.setSuitableDogBreeds(newDogBreeds);

        assertEquals("New Content", groomingGuide.getContent());
        assertEquals("New Tools", groomingGuide.getToolsNeeded());
        assertEquals("New Steps", groomingGuide.getSteps());
        assertEquals(newDogBreeds, groomingGuide.getSuitableDogBreeds());
    }

    @Test
    public void testNoArgsConstructor() {
        GroomingGuide guide = new GroomingGuide();
        assertNotNull(guide);
    }
}