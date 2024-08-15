package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GroomingGuideDtoTest {

//    @Test
//    public void testDefaultConstructor() {
//        GroomingGuideDto groomingGuideDto = new GroomingGuideDto();
//
//        assertNull(groomingGuideDto.getId());
//        assertNull(groomingGuideDto.getContent());
//        assertNull(groomingGuideDto.getToolsNeeded());
//        assertNull(groomingGuideDto.getSteps());
//        assertNull(groomingGuideDto.getSuitableDogBreeds());
//        assertNull(groomingGuideDto.getSuitableCatBreeds());
//    }
//
//    @Test
//    public void testAllArgsConstructor() {
//        GroomingGuideDto groomingGuideDto = new GroomingGuideDto(
//                1L,
//                "Content",
//                "Tools",
//                "Steps",
//                Arrays.asList(),
//                Arrays.asList()
//        );
//
//        assertEquals(1L, groomingGuideDto.getId());
//        assertEquals("Content", groomingGuideDto.getContent());
//        assertEquals("Tools", groomingGuideDto.getToolsNeeded());
//        assertEquals("Steps", groomingGuideDto.getSteps());
//        assertEquals(0, groomingGuideDto.getSuitableDogBreeds().size());
//        assertEquals(0, groomingGuideDto.getSuitableCatBreeds().size());
//    }
//
//    @Test
//    public void testConstructorWithGroomingGuide() {
//        GroomingGuide groomingGuide = new GroomingGuide();
//        groomingGuide.setId(1L);
//        groomingGuide.setContent("Content");
//        groomingGuide.setToolsNeeded("Tools");
//        groomingGuide.setSteps("Steps");
//
//        GroomingGuideDto groomingGuideDto = new GroomingGuideDto(groomingGuide);
//
//        assertEquals(1L, groomingGuideDto.getId());
//        assertEquals("Content", groomingGuideDto.getContent());
//        assertEquals("Tools", groomingGuideDto.getToolsNeeded());
//        assertEquals("Steps", groomingGuideDto.getSteps());
//        assertNotNull(groomingGuideDto.getSuitableDogBreeds());
//        assertNotNull(groomingGuideDto.getSuitableCatBreeds());
//    }
//
//    @Test
//    public void testListIntegrity() {
//        GroomingGuideDto groomingGuideDto = new GroomingGuideDto();
//
//        DogBreedDto dogBreed = new DogBreedDto();
//        CatBreedDto catBreed = new CatBreedDto();
//
//        groomingGuideDto.getSuitableDogBreeds().add(dogBreed);
//        groomingGuideDto.getSuitableCatBreeds().add(catBreed);
//
//        assertEquals(1, groomingGuideDto.getSuitableDogBreeds().size());
//        assertEquals(1, groomingGuideDto.getSuitableCatBreeds().size());
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        GroomingGuideDto dto1 = new GroomingGuideDto(1L, "Content", "Tools", "Steps", null, null);
//        GroomingGuideDto dto2 = new GroomingGuideDto(1L, "Content", "Tools", "Steps", null, null);
//
//        assertEquals(dto1, dto2);
//        assertEquals(dto1.hashCode(), dto2.hashCode());
//    }
}