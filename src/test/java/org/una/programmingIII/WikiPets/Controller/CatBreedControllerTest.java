package org.una.programmingIII.WikiPets.Controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.CatBreedInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.CatBreedService;

import java.util.HashMap;
import java.util.Map;

public class CatBreedControllerTest {

    @InjectMocks
    private CatBreedController catBreedController;

    @Mock
    private CatBreedService catBreedService;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<CatBreedInput, CatBreedDto> catBreedMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(CatBreedInput.class, CatBreedDto.class)).thenReturn(catBreedMapper);
        catBreedController = new CatBreedController(catBreedService, mapperFactory);
    }

    @Test
    public void testGetCatBreeds() {
        Map<String, Object> mockResponse = new HashMap<>();
        when(catBreedService.getAllCatBreeds(anyInt(), anyInt(),anyInt())).thenReturn(mockResponse);

        Map<String, Object> response = catBreedController.getCatBreeds(0, 10,10);

        verify(catBreedService, times(1)).getAllCatBreeds(0, 10,10);
        assertEquals(mockResponse, response);
    }

    @Test
    public void testGetCatBreedsThrowsException() {
        when(catBreedService.getAllCatBreeds(anyInt(), anyInt(),anyInt())).thenThrow(new RuntimeException("Error"));

        assertThrows(NotFoundElementException.class, () -> {
            catBreedController.getCatBreeds(0, 10,10);
        });
    }

    @Test
    public void testGetCatBreedById() {
        CatBreedDto mockDto = new CatBreedDto();
        when(catBreedService.getBreedById(anyLong())).thenReturn(mockDto);

        CatBreedDto response = catBreedController.getCatBreedById(1L);

        verify(catBreedService, times(1)).getBreedById(1L);
        assertEquals(mockDto, response);
    }

    @Test
    public void testUpdateCatBreed() {
        CatBreedInput input = new CatBreedInput();
        CatBreedDto mockDto = new CatBreedDto();
        when(catBreedMapper.convertToDTO(input)).thenReturn(mockDto);
        when(catBreedService.updateCatBreed(mockDto)).thenReturn(mockDto);

        CatBreedDto response = catBreedController.updateCatBreed(input);

        verify(catBreedMapper, times(1)).convertToDTO(input);
        verify(catBreedService, times(1)).updateCatBreed(mockDto);
        assertEquals(mockDto, response);
    }

    @Test
    public void testCreateCatBreed() {
        CatBreedInput input = new CatBreedInput();
        CatBreedDto mockDto = new CatBreedDto();
        when(catBreedMapper.convertToDTO(input)).thenReturn(mockDto);
        when(catBreedService.createCatBreed(mockDto)).thenReturn(mockDto);

        CatBreedDto response = catBreedController.createCatBreed(input);

        verify(catBreedMapper, times(1)).convertToDTO(input);
        verify(catBreedService, times(1)).createCatBreed(mockDto);
        assertEquals(mockDto, response);
    }

    @Test
    public void testDeleteCatBreed() {
        when(catBreedService.deleteCatBreed(anyLong())).thenReturn(true);

        Boolean response = catBreedController.deleteCatBreed(1L);

        verify(catBreedService, times(1)).deleteCatBreed(1L);
        assertTrue(response);
    }
}
