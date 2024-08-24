package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Mapper.NutritionGuideMapper;
import org.una.programmingIII.WikiPets.Model.NutritionGuide;
import org.una.programmingIII.WikiPets.Model.NutritionGuideDto;
import org.una.programmingIII.WikiPets.Repository.NutritionGuideRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NutritionGuideServiceImplementationTest {

    @Mock
    private NutritionGuideRepository nutritionGuideRepository;

    @Mock
    private NutritionGuideMapper nutritionGuideMapper;

    @InjectMocks
    private NutritionGuideServiceImplementation nutritionGuideServiceImplementation;

    @Test
    void getAllNutritionGuidesTest() {
        NutritionGuide nutritionGuide = new NutritionGuide();
        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
        when(nutritionGuideRepository.findAll()).thenReturn(Collections.singletonList(nutritionGuide));

        List<NutritionGuideDto> result = nutritionGuideServiceImplementation.getAllNutritionGuides();

        assertEquals(1, result.size());
        assertEquals(nutritionGuideDto, result.get(0));
    }

    @Test
    void getNutritionGuideByIdTest() {
        NutritionGuide nutritionGuide = new NutritionGuide();
        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.of(nutritionGuide));


        NutritionGuideDto result = nutritionGuideServiceImplementation.getNutritionGuideById(1L);

        assertEquals(nutritionGuideDto, result);
    }

    @Test
    void getNutritionGuideById_NotFoundTest() {
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.getNutritionGuideById(1L));

        assertEquals("Nutrition Guide not found", exception.getMessage());
    }

    @Test
    void getNutritionGuideByTitleTest() {
        NutritionGuide nutritionGuide = new NutritionGuide();
        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
        when(nutritionGuideRepository.findByTitle("title")).thenReturn(nutritionGuide);

        NutritionGuideDto result = nutritionGuideServiceImplementation.getNutritionGuideByTitle("title");

        assertEquals(nutritionGuideDto, result);
    }

    @Test
    void getNutritionGuideByTitle_NotFoundTest() {
        when(nutritionGuideRepository.findByTitle("title")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.getNutritionGuideByTitle("title"));

        assertEquals("Nutrition Guide not found", exception.getMessage());
    }

    @Test
    void createNutritionGuideTest() {
        NutritionGuide nutritionGuide = new NutritionGuide();
        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
        when(nutritionGuideRepository.save(nutritionGuide)).thenReturn(nutritionGuide);

        NutritionGuideDto result = nutritionGuideServiceImplementation.createNutritionGuide(nutritionGuideDto);

        assertEquals(nutritionGuideDto, result);
    }

    @Test
    void deleteNutritionGuideTest() {
        doNothing().when(nutritionGuideRepository).deleteById(1L);

        nutritionGuideServiceImplementation.deleteNutritionGuide(1L);

        verify(nutritionGuideRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateNutritionGuideTest() {
        NutritionGuide nutritionGuide = new NutritionGuide();
        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
        when(nutritionGuideRepository.save(nutritionGuide)).thenReturn(nutritionGuide);


        NutritionGuideDto result = nutritionGuideServiceImplementation.updateNutritionGuide(nutritionGuideDto);

        assertEquals(nutritionGuideDto, result);
    }
}