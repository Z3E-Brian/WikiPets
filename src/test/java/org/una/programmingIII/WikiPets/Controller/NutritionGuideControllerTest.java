package org.una.programmingIII.WikiPets.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.NutritionGuideInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.NutritionGuideService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NutritionGuideControllerTest {

    @InjectMocks
    private NutritionGuideController nutritionGuideController;

    @Mock
    private NutritionGuideService nutritionGuideService;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<NutritionGuideInput, NutritionGuideDto> nutritionGuideMapper;

    private NutritionGuideDto nutritionGuideDto;
    private NutritionGuideInput nutritionGuideInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        nutritionGuideDto = new NutritionGuideDto();
        nutritionGuideInput = new NutritionGuideInput();
        when(mapperFactory.createMapper(NutritionGuideInput.class, NutritionGuideDto.class)).thenReturn(nutritionGuideMapper);
        nutritionGuideController = new NutritionGuideController(nutritionGuideService, mapperFactory);
    }

    @Test
    void testNutritionGuideControllerConstructor() {
        assertNotNull(nutritionGuideController);
    }

    @Test
    void testGetNutritionGuideByIdSuccess() {
        when(nutritionGuideService.getNutritionGuideById(1L)).thenReturn(nutritionGuideDto);

        NutritionGuideDto result = nutritionGuideController.getNutritionGuideById(1L);

        assertNotNull(result);
        verify(nutritionGuideService, times(1)).getNutritionGuideById(1L);
    }

    @Test
    void testGetNutritionGuideByIdNotFound() {
        when(nutritionGuideService.getNutritionGuideById(1L)).thenThrow(new NotFoundElementException("Nutrition Guide not found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            nutritionGuideController.getNutritionGuideById(1L);
        });

        assertEquals("Nutrition Guide not found", exception.getMessage());
    }

    @Test
    void testGetNutritionGuideByTitleSuccess() {
        when(nutritionGuideService.getNutritionGuideByTitle("Healthy Diet")).thenReturn(nutritionGuideDto);

        NutritionGuideDto result = nutritionGuideController.getNutritionGuideByTitle("Healthy Diet");

        assertNotNull(result);
        verify(nutritionGuideService, times(1)).getNutritionGuideByTitle("Healthy Diet");
    }

    @Test
    void testGetNutritionGuidesSuccess() {
        Map<String, Object> guidesMap = new HashMap<>();
        when(nutritionGuideService.getAllNutritionGuides(0, 10)).thenReturn(guidesMap);

        Map<String, Object> result = nutritionGuideController.getNutritionGuides(0, 10);

        assertNotNull(result);
        verify(nutritionGuideService, times(1)).getAllNutritionGuides(0, 10);
    }

    @Test
    void testGetNutritionGuidesNotFound() {
        when(nutritionGuideService.getAllNutritionGuides(0, 10)).thenThrow(new NotFoundElementException("Not found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            nutritionGuideController.getNutritionGuides(0, 10);
        });

        assertEquals("Could not find nutrition guidesNot found", exception.getMessage());
    }

    @Test
    void testCreateNutritionGuideSuccess() {
        when(nutritionGuideMapper.convertToDTO(any(NutritionGuideInput.class))).thenReturn(nutritionGuideDto);
        when(nutritionGuideService.createNutritionGuide(any(NutritionGuideDto.class))).thenReturn(nutritionGuideDto);

        NutritionGuideDto result = nutritionGuideController.createNutritionGuide(nutritionGuideInput);

        assertNotNull(result);
        verify(nutritionGuideMapper, times(1)).convertToDTO(nutritionGuideInput);
        verify(nutritionGuideService, times(1)).createNutritionGuide(nutritionGuideDto);
    }

    @Test
    void testUpdateNutritionGuideSuccess() {
        when(nutritionGuideMapper.convertToDTO(any(NutritionGuideInput.class))).thenReturn(nutritionGuideDto);
        when(nutritionGuideService.updateNutritionGuide(any(NutritionGuideDto.class))).thenReturn(nutritionGuideDto);

        NutritionGuideDto result = nutritionGuideController.updateNutritionGuide(nutritionGuideInput);

        assertNotNull(result);
        verify(nutritionGuideMapper, times(1)).convertToDTO(nutritionGuideInput);
        verify(nutritionGuideService, times(1)).updateNutritionGuide(nutritionGuideDto);
    }

    @Test
    void testDeleteNutritionGuideSuccess() {
        when(nutritionGuideService.deleteNutritionGuide(1L)).thenReturn(true);

        Boolean result = nutritionGuideController.deleteNutritionGuide(1L);

        assertTrue(result);
        verify(nutritionGuideService, times(1)).deleteNutritionGuide(1L);
    }

    @Test
    void testAddRecommendedDogBreedNutritionGuide() {
        when(nutritionGuideService.addRecommendedDogBreed(1L, 2L)).thenReturn(nutritionGuideDto);

        NutritionGuideDto result = nutritionGuideController.addRecommendedDogBreedNutritionGuide(1L, 2L);

        assertNotNull(result);
        verify(nutritionGuideService, times(1)).addRecommendedDogBreed(1L, 2L);
    }

    @Test
    void testAddRecommendedCatBreedNutritionGuide() {
        when(nutritionGuideService.addRecommendedCatBreed(1L, 2L)).thenReturn(nutritionGuideDto);

        NutritionGuideDto result = nutritionGuideController.addRecommendedCatBreedNutritionGuide(1L, 2L);

        assertNotNull(result);
        verify(nutritionGuideService, times(1)).addRecommendedCatBreed(1L, 2L);
    }
}
