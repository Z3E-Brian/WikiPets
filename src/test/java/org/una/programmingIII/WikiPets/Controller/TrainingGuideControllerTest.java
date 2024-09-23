package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.TrainingGuideInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.TrainingGuideService;

import java.util.HashMap;
import java.util.Map;

public class TrainingGuideControllerTest {

    @InjectMocks
    private TrainingGuideController trainingGuideController;

    @Mock
    private TrainingGuideService trainingGuideService;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<TrainingGuideInput, TrainingGuideDto> trainingGuideMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(TrainingGuideInput.class, TrainingGuideDto.class)).thenReturn(trainingGuideMapper);
        trainingGuideController = new TrainingGuideController(trainingGuideService, mapperFactory);
    }

    @Test
    void testTrainingGuideControllerConstructor() {
        assertNotNull(trainingGuideController);
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
        int page = 1;
        int size = 10;
        when(trainingGuideService.getAllTrainingGuides(page, size)).thenThrow(new NotFoundElementException(""));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            trainingGuideController.getTrainingGuides(page, size);
        });
        assertEquals("Could not retrieve feeding schedules", exception.getMessage());
    }

    @Test
    void testGetTrainingGuideById() {
        Long id = 1L;
        TrainingGuideDto expectedGuide = new TrainingGuideDto();
        when(trainingGuideService.getTrainingGuideById(id)).thenReturn(expectedGuide);

        TrainingGuideDto result = trainingGuideController.getTrainingGuideById(id);

        assertEquals(expectedGuide, result);
        verify(trainingGuideService).getTrainingGuideById(id);
    }

    @Test
    void testCreateTrainingGuide() {
        TrainingGuideInput input = new TrainingGuideInput();
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideMapper.convertToDTO(input)).thenReturn(expectedGuideDto);
        when(trainingGuideService.createTrainingGuide(expectedGuideDto)).thenReturn(expectedGuideDto);

        TrainingGuideDto result = trainingGuideController.createTrainingGuide(input);

        assertEquals(expectedGuideDto, result);
        verify(trainingGuideMapper).convertToDTO(input);
        verify(trainingGuideService).createTrainingGuide(expectedGuideDto);
    }

    @Test
    void testUpdateTrainingGuide() {
        TrainingGuideInput input = new TrainingGuideInput();
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideMapper.convertToDTO(input)).thenReturn(expectedGuideDto);
        when(trainingGuideService.updateTrainingGuide(expectedGuideDto)).thenReturn(expectedGuideDto);

        TrainingGuideDto result = trainingGuideController.updateTrainingGuide(input);

        assertEquals(expectedGuideDto, result);
        verify(trainingGuideMapper).convertToDTO(input);
        verify(trainingGuideService).updateTrainingGuide(expectedGuideDto);
    }

    @Test
    void testDeleteTrainingGuide() {
        Long id = 1L;
        when(trainingGuideService.deleteTrainingGuide(id)).thenReturn(true);

        Boolean result = trainingGuideController.deleteTrainingGuide(id);

        assertTrue(result);
        verify(trainingGuideService).deleteTrainingGuide(id);
    }

    @Test
    void testAddDogBreedInTrainingGuide() {
        Long id = 1L;
        Long dogBreedId = 1L;
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideService.addDogBreedInTrainingGuide(id, dogBreedId)).thenReturn(expectedGuideDto);

        TrainingGuideDto result = trainingGuideController.addDogBreedInTrainingGuide(id, dogBreedId);

        assertEquals(expectedGuideDto, result);
        verify(trainingGuideService).addDogBreedInTrainingGuide(id, dogBreedId);
    }

    @Test
    void testDeleteDogBreedInTrainingGuide() {
        Long id = 1L;
        Long dogBreedId = 1L;
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideService.deleteDogBreedInTrainingGuide(id, dogBreedId)).thenReturn(expectedGuideDto);

        TrainingGuideDto result = trainingGuideController.deleteDogBreedInTrainingGuide(id, dogBreedId);
        assertEquals(expectedGuideDto, result);
        verify(trainingGuideService).deleteDogBreedInTrainingGuide(id, dogBreedId);
    }

    @Test
    void testAddCatBreedInTrainingGuide() {
        Long id = 1L;
        Long catBreedId = 1L;
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideService.addCatBreedInTrainingGuide(id, catBreedId)).thenReturn(expectedGuideDto);

        TrainingGuideDto result = trainingGuideController.addCatBreedInTrainingGuide(id, catBreedId);

        assertEquals(expectedGuideDto, result);
        verify(trainingGuideService).addCatBreedInTrainingGuide(id, catBreedId);
    }

    @Test
    void testDeleteCatBreedInTrainingGuide() {
        Long id = 1L;
        Long catBreedId = 1L;
        TrainingGuideDto expectedGuideDto = new TrainingGuideDto();
        when(trainingGuideService.deleteCatBreedInTrainingGuide(id, catBreedId)).thenReturn(expectedGuideDto);

        TrainingGuideDto result = trainingGuideController.deleteCatBreedInTrainingGuide(id, catBreedId);
        assertEquals(expectedGuideDto, result);
        verify(trainingGuideService).deleteCatBreedInTrainingGuide(id, catBreedId);
    }
}
