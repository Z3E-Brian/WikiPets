package org.una.programmingIII.WikiPets.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.GroomingGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.GroomingGuideService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroomingGuideControllerTest {

    @InjectMocks
    private GroomingGuideController groomingGuideController;

    @Mock
    private GroomingGuideService groomingGuideService;

    @Mock
    private GenericMapper<GroomingGuideInput, GroomingGuideDto> groomingGuideMapper;

    @Mock
    private GenericMapperFactory mapperFactory;

    private GroomingGuideDto groomingGuideDto;
    private GroomingGuideInput groomingGuideInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        groomingGuideDto = new GroomingGuideDto();
        groomingGuideInput = new GroomingGuideInput();
        when(mapperFactory.createMapper(GroomingGuideInput.class, GroomingGuideDto.class)).thenReturn(groomingGuideMapper);
    }

    @Test
    void testGroomingGuideControllerConstructor() {
        assertNotNull(groomingGuideController);
    }

    @Test
    void testGetAllGroomingGuidesSuccess() {
        Map<String, Object> groomingGuidesMap = new HashMap<>();
        when(groomingGuideService.getAllGroomingGuides(0, 10)).thenReturn(groomingGuidesMap);

        Map<String, Object> result = groomingGuideController.getAllGroomingGuides(0, 10);

        assertNotNull(result);
        verify(groomingGuideService, times(1)).getAllGroomingGuides(0, 10);
    }

    @Test
    void testGetAllGroomingGuidesNotFound() {
        when(groomingGuideService.getAllGroomingGuides(0, 10)).thenThrow(new RuntimeException("No grooming guides found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            groomingGuideController.getAllGroomingGuides(0, 10);
        });

        assertEquals("Could not retrieve grooming guides", exception.getMessage());
    }

    @Test
    void testGetGroomingGuideByIdSuccess() {
        when(groomingGuideService.getGroomingGuideById(1L)).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideController.getGroomingGuideById(1L);

        assertNotNull(result);
        verify(groomingGuideService, times(1)).getGroomingGuideById(1L);
    }

    @Test
    void testGetGroomingGuideByIdNotFound() {
        when(groomingGuideService.getGroomingGuideById(1L)).thenThrow(new RuntimeException("Grooming guide not found"));

        CustomException exception = assertThrows(CustomException.class, () -> {
            groomingGuideController.getGroomingGuideById(1L);
        });

        assertEquals("Could not find grooming guide with id 1. Grooming guide not found", exception.getMessage());
    }

    @Test
    void testCreateGroomingGuideSuccess() {
        when(groomingGuideMapper.convertToDTO(any(GroomingGuideInput.class))).thenReturn(groomingGuideDto);
        when(groomingGuideService.createGroomingGuide(any(GroomingGuideDto.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideController.createGroomingGuide(groomingGuideInput);

        assertNotNull(result);
        verify(groomingGuideMapper, times(1)).convertToDTO(groomingGuideInput);
        verify(groomingGuideService, times(1)).createGroomingGuide(groomingGuideDto);
    }

    @Test
    void testCreateGroomingGuideFailure() {
        when(groomingGuideMapper.convertToDTO(any(GroomingGuideInput.class))).thenReturn(groomingGuideDto);
        when(groomingGuideService.createGroomingGuide(any(GroomingGuideDto.class))).thenThrow(new RuntimeException("Failed to create grooming guide"));

        CustomException exception = assertThrows(CustomException.class, () -> {
            groomingGuideController.createGroomingGuide(groomingGuideInput);
        });

        assertEquals("Could not create grooming guide. Failed to create grooming guide", exception.getMessage());
    }

    @Test
    void testUpdateGroomingGuideSuccess() {
        when(groomingGuideMapper.convertToDTO(any(GroomingGuideInput.class))).thenReturn(groomingGuideDto);
        when(groomingGuideService.updateGroomingGuide(any(GroomingGuideDto.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideController.updateGroomingGuide(groomingGuideInput);

        assertNotNull(result);
        verify(groomingGuideMapper, times(1)).convertToDTO(groomingGuideInput);
        verify(groomingGuideService, times(1)).updateGroomingGuide(groomingGuideDto);
    }

    @Test
    void testUpdateGroomingGuideFailure() {
        when(groomingGuideMapper.convertToDTO(any(GroomingGuideInput.class))).thenReturn(groomingGuideDto);
        when(groomingGuideService.updateGroomingGuide(any(GroomingGuideDto.class))).thenThrow(new RuntimeException("Failed to update grooming guide"));

        CustomException exception = assertThrows(CustomException.class, () -> {
            groomingGuideController.updateGroomingGuide(groomingGuideInput);
        });

        assertEquals("Could not update grooming guide. Failed to update grooming guide", exception.getMessage());
    }

    @Test
    void testDeleteGroomingGuideSuccess() {
        when(groomingGuideService.deleteGroomingGuide(1L)).thenReturn(true);

        boolean result = groomingGuideController.deleteGroomingGuide(1L);

        assertTrue(result);
        verify(groomingGuideService, times(1)).deleteGroomingGuide(1L);
    }

    @Test
    void testAddSuitableDogBreedToGroomingGuideSuccess() {
        when(groomingGuideService.addSuitableDogBreedToGroomingGuide(1L, 2L)).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideController.addSuitableDogBreedToGroomingGuide(1L, 2L);

        assertNotNull(result);
        verify(groomingGuideService, times(1)).addSuitableDogBreedToGroomingGuide(1L, 2L);
    }

    @Test
    void testAddSuitableDogBreedToGroomingGuideFailure() {
        when(groomingGuideService.addSuitableDogBreedToGroomingGuide(1L, 2L)).thenThrow(new NotFoundElementException("Dog breed not found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            groomingGuideController.addSuitableDogBreedToGroomingGuide(1L, 2L);
        });

        assertEquals("Could not update grooming guide with id: 1. Dog breed not found", exception.getMessage());
    }

    @Test
    void testAddSuitableCatBreedToGroomingGuideSuccess() {
        when(groomingGuideService.addSuitableCatBreedToGroomingGuide(1L, 2L)).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideController.addSuitableCatBreedToGroomingGuide(1L, 2L);

        assertNotNull(result);
        verify(groomingGuideService, times(1)).addSuitableCatBreedToGroomingGuide(1L, 2L);
    }

    @Test
    void testAddSuitableCatBreedToGroomingGuideFailure() {
        when(groomingGuideService.addSuitableCatBreedToGroomingGuide(1L, 2L)).thenThrow(new NotFoundElementException("Cat breed not found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            groomingGuideController.addSuitableCatBreedToGroomingGuide(1L, 2L);
        });

        assertEquals("Could not update grooming guide with id: 1. Cat breed not found", exception.getMessage());
    }

    @Test
    void testDeleteSuitableCatBreedFromGroomingGuideSuccess() {
        when(groomingGuideService.deleteSuitableCatBreedFromGroomingGuide(1L, 2L)).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideController.deleteSuitableCatBreedFromGroomingGuide(1L, 2L);

        assertNotNull(result);
        verify(groomingGuideService, times(1)).deleteSuitableCatBreedFromGroomingGuide(1L, 2L);
    }

    @Test
    void testDeleteSuitableCatBreedFromGroomingGuideFailure() {
        when(groomingGuideService.deleteSuitableCatBreedFromGroomingGuide(1L, 2L)).thenThrow(new NotFoundElementException("Cat breed not found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            groomingGuideController.deleteSuitableCatBreedFromGroomingGuide(1L, 2L);
        });

        assertEquals("Could not remove cat breed from grooming guide with id 1. Cat breed not found", exception.getMessage());
    }

    @Test
    void testDeleteSuitableDogBreedFromGroomingGuideSuccess() {
        when(groomingGuideService.deleteSuitableDogBreedFromGroomingGuide(1L, 2L)).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideController.deleteSuitableDogBreedFromGroomingGuide(1L, 2L);

        assertNotNull(result);
        verify(groomingGuideService, times(1)).deleteSuitableDogBreedFromGroomingGuide(1L, 2L);
    }

    @Test
    void testDeleteSuitableDogBreedFromGroomingGuideFailure() {
        when(groomingGuideService.deleteSuitableDogBreedFromGroomingGuide(1L, 2L)).thenThrow(new NotFoundElementException("Dog breed not found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            groomingGuideController.deleteSuitableDogBreedFromGroomingGuide(1L, 2L);
        });

        assertEquals("Could not remove dog breed from grooming guide with id 1. Dog breed not found", exception.getMessage());
    }
}
