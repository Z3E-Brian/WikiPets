package org.una.programmingIII.WikiPets.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Model.TrainingGuide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TrainingGuideRepositoryTest {

    @Mock
    private TrainingGuideRepository trainingGuideRepository;

    private TrainingGuide trainingGuide;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingGuide = new TrainingGuide();
        trainingGuide.setId(1L);
        trainingGuide.setTitle("Basic Training");
        trainingGuide.setContent("A basic guide for training your pet.");
    }

    @Test
    public void findByTitleTest() {
        when(trainingGuideRepository.findByTitle("Basic Training")).thenReturn(trainingGuide);
        TrainingGuide result = trainingGuideRepository.findByTitle("Basic Training");

        assertEquals(1L, result.getId());
        assertEquals("Basic Training", result.getTitle());
    }
}
