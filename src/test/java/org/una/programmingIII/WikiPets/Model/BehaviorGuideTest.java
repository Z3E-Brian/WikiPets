package org.una.programmingIII.WikiPets.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BehaviorGuideTest {

    private BehaviorGuide behaviorGuide;

    @BeforeEach
    public void setUp() {
        BehaviorGuideDto behaviorGuideDto = new BehaviorGuideDto();
        behaviorGuideDto.setId(1L);
        behaviorGuideDto.setTitle("Guide Title");
        behaviorGuideDto.setContent("Guide Content");
        behaviorGuideDto.setSolutions("Guide Solutions");

        behaviorGuide = new BehaviorGuide(behaviorGuideDto);
    }

    @Test
    public void testBehaviorGuideCreation() {
        assertNotNull(behaviorGuide);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, behaviorGuide.getId());
        assertEquals("Guide Title", behaviorGuide.getTitle());
        assertEquals("Guide Content", behaviorGuide.getContent());
        assertEquals("Guide Solutions", behaviorGuide.getSolutions());
    }

    @Test
    public void testSetters() {
        behaviorGuide.setTitle("New Title");
        behaviorGuide.setContent("New Content");
        behaviorGuide.setSolutions("New Solutions");

        assertEquals("New Title", behaviorGuide.getTitle());
        assertEquals("New Content", behaviorGuide.getContent());
        assertEquals("New Solutions", behaviorGuide.getSolutions());
    }

    @Test
    public void testConstructorWithDto() {
        assertEquals(1L, behaviorGuide.getId());
        assertEquals("Guide Title", behaviorGuide.getTitle());
        assertEquals("Guide Content", behaviorGuide.getContent());
        assertEquals("Guide Solutions", behaviorGuide.getSolutions());
    }
}