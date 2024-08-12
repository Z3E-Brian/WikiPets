package org.una.programmingIII.WikiPets.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GroomingGuideDtoTest {

    private GroomingGuideDto groomingGuideDto;
    private GroomingGuide groomingGuide;

    @BeforeEach
    public void setUp() {
        groomingGuide = new GroomingGuide();
        groomingGuide.setId(1L);
        groomingGuide.setContent("Grooming Content");
        groomingGuide.setToolsNeeded("Tools Needed");
        groomingGuide.setSteps("Grooming Steps");

        groomingGuideDto = new GroomingGuideDto(groomingGuide);
    }

    @Test
    public void testGroomingGuideDtoCreation() {
        assertNotNull(groomingGuideDto);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, groomingGuideDto.getId());
        assertEquals("Grooming Content", groomingGuideDto.getContent());
        assertEquals("Tools Needed", groomingGuideDto.getToolsNeeded());
        assertEquals("Grooming Steps", groomingGuideDto.getSteps());
    }

    @Test
    public void testSetters() {
        groomingGuideDto.setContent("New Content");
        groomingGuideDto.setToolsNeeded("New Tools");
        groomingGuideDto.setSteps("New Steps");

        assertEquals("New Content", groomingGuideDto.getContent());
        assertEquals("New Tools", groomingGuideDto.getToolsNeeded());
        assertEquals("New Steps", groomingGuideDto.getSteps());
    }

    @Test
    public void testConstructorWithGroomingGuide() {
        assertEquals(1L, groomingGuideDto.getId());
        assertEquals("Grooming Content", groomingGuideDto.getContent());
        assertEquals("Tools Needed", groomingGuideDto.getToolsNeeded());
        assertEquals("Grooming Steps", groomingGuideDto.getSteps());
    }

    @Test
    public void testEqualsAndHashCode() {
        GroomingGuideDto anotherGroomingGuideDto = new GroomingGuideDto(groomingGuide);
        assertEquals(groomingGuideDto, anotherGroomingGuideDto);
        assertEquals(groomingGuideDto.hashCode(), anotherGroomingGuideDto.hashCode());
    }
}