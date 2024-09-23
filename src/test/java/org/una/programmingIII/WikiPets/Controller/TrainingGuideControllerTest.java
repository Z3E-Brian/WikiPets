package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Controller.TrainingGuideController;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.TrainingGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Service.TrainingGuideService;

import java.util.HashMap;
import java.util.Map;

public class TrainingGuideControllerTest {

    @InjectMocks
    private TrainingGuideController trainingGuideController;

    @Mock
    private TrainingGuideService trainingGuideService;

    @Mock
    private GenericMapper<TrainingGuideInput, TrainingGuideDto> trainingGuideMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTrainingGuides() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedGuides = new HashMap<>();
        when(trainingGuideService.getAllTrainingGuides(page, size)).thenReturn(expectedGuides);

        // Act
        Map<String, Object> result = trainingGuideController.getTrainingGuides(page, size);

        // Assert
        assertEquals(expectedGuides, result);
        verify(trainingGuideService).getAllTrainingGuides(page, size);
    }

    @Test
    void testGetTrainingGuidesException() {
        // Arrange
        int page = 1;
        int size = 10;
        when(trainingGuideService.getAllTrainingGuides(page, size)).thenThrow(new RuntimeException("Error"));

        // Act & Assert
        Exception exception = assertThrows(CustomException.class, () -> {
            trainingGuideController.getTrainingGuides(page, size);
        });
        assertEquals("Could not retrieve feeding schedulesError", exception.getMessage());
    }

    @Test
    void testGetTrainingGuideById() {
        // Arrange
        Long id = 1L;
        TrainingGuideDto expectedGuide = new TrainingGuideDto();
        when(trainingGuideService.getTrainingGuideById(id)).thenReturn(expectedGuide);

        // Act
        TrainingGuideDto result = trainingGuideController.getTrainingGuideById(id);

        // Assert
        assertEquals(expectedGuide, result);
        verify(trainingGuideService).getTrainingGuideById(id);
    }

    @Test
    void testCreateTrainingGuide() {
        // Arrange
        TrainingGuideInput input = new TrainingGuideInput();
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideMapper.convertToDTO(input)).thenReturn(expectedGuideDto);
        when(trainingGuideService.createTrainingGuide(expectedGuideDto)).thenReturn(expectedGuideDto);

        // Act
        TrainingGuideDto result = trainingGuideController.createTrainingGuide(input);

        // Assert
        assertEquals(expectedGuideDto, result);
        verify(trainingGuideMapper).convertToDTO(input);
        verify(trainingGuideService).createTrainingGuide(expectedGuideDto);
    }

    @Test
    void testUpdateTrainingGuide() {
        // Arrange
        TrainingGuideInput input = new TrainingGuideInput();
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideMapper.convertToDTO(input)).thenReturn(expectedGuideDto);
        when(trainingGuideService.updateTrainingGuide(expectedGuideDto)).thenReturn(expectedGuideDto);

        // Act
        TrainingGuideDto result = trainingGuideController.updateTrainingGuide(input);

        // Assert
        assertEquals(expectedGuideDto, result);
        verify(trainingGuideMapper).convertToDTO(input);
        verify(trainingGuideService).updateTrainingGuide(expectedGuideDto);
    }

    @Test
    void testDeleteTrainingGuide() {
        // Arrange
        Long id = 1L;
        when(trainingGuideService.deleteTrainingGuide(id)).thenReturn(true);

        // Act
        Boolean result = trainingGuideController.deleteTrainingGuide(id);

        // Assert
        assertTrue(result);
        verify(trainingGuideService).deleteTrainingGuide(id);
    }

    @Test
    void testAddDogBreedInTrainingGuide() {
        // Arrange
        Long id = 1L;
        Long dogBreedId = 1L;
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideService.addDogBreedInTrainingGuide(id, dogBreedId)).thenReturn(expectedGuideDto);

        // Act
        TrainingGuideDto result = trainingGuideController.addDogBreedInTrainingGuide(id, dogBreedId);

        // Assert
        assertEquals(expectedGuideDto, result);
        verify(trainingGuideService).addDogBreedInTrainingGuide(id, dogBreedId);
    }

    @Test
    void testDeleteDogBreedInTrainingGuide() {
        // Arrange
        Long id = 1L;
        Long dogBreedId = 1L;
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideService.deleteDogBreedInTrainingGuide(id, dogBreedId)).thenReturn(expectedGuideDto);

        // Act
        TrainingGuideDto result = trainingGuideController.deleteDogBreedInTrainingGuide(id, dogBreedId);

        // Assert
        assertEquals(expectedGuideDto, result);
        verify(trainingGuideService).deleteDogBreedInTrainingGuide(id, dogBreedId);
    }

    @Test
    void testAddCatBreedInTrainingGuide() {
        // Arrange
        Long id = 1L;
        Long catBreedId = 1L;
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideService.addCatBreedInTrainingGuide(id, catBreedId)).thenReturn(expectedGuideDto);

        // Act
        TrainingGuideDto result = trainingGuideController.addCatBreedInTrainingGuide(id, catBreedId);

        // Assert
        assertEquals(expectedGuideDto, result);
        verify(trainingGuideService).addCatBreedInTrainingGuide(id, catBreedId);
    }

    @Test
    void testDeleteCatBreedInTrainingGuide() {
        // Arrange
        Long id = 1L;
        Long catBreedId = 1L;
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideService.deleteCatBreedInTrainingGuide(id, catBreedId)).thenReturn(expectedGuideDto);

        // Act
        TrainingGuideDto result = trainingGuideController.deleteCatBreedInTrainingGuide(id, catBreedId);

        // Assert
        assertEquals(expectedGuideDto, result);
        verify(trainingGuideService).deleteCatBreedInTrainingGuide(id, catBreedId);
    }
}
