package org.una.programmingIII.WikiPets.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GroomingGuideTest {

    private GroomingGuide groomingGuide;

    @BeforeEach
    public void setUp() {
        groomingGuide = new GroomingGuide();
        groomingGuide.setId(1L);
        groomingGuide.setContent("Grooming Content");
        groomingGuide.setToolsNeeded("Tools Needed");
        groomingGuide.setSteps("Grooming Steps");
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
    }

    @Test
    public void testSetters() {
        groomingGuide.setContent("New Content");
        groomingGuide.setToolsNeeded("New Tools");
        groomingGuide.setSteps("New Steps");

        assertEquals("New Content", groomingGuide.getContent());
        assertEquals("New Tools", groomingGuide.getToolsNeeded());
        assertEquals("New Steps", groomingGuide.getSteps());
    }
}