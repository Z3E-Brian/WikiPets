package org.una.programmingIII.WikiPets.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.DogBreedInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.DogBreedService;

import java.util.HashMap;
import java.util.Map;

public class DogBreedControllerTest {

    @InjectMocks
    private DogBreedController dogBreedController;

    @Mock
    private DogBreedService dogBreedService;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<DogBreedInput, DogBreedDto> dogBreedMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(DogBreedInput.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        dogBreedController = new DogBreedController(dogBreedService, mapperFactory);
    }

    @Test
    public void testGetDogBreeds() {
        Map<String, Object> mockResponse = new HashMap<>();
        when(dogBreedService.getAllDogBreeds(anyInt(), anyInt(),anyInt())).thenReturn(mockResponse);

        Map<String, Object> response = dogBreedController.getDogBreeds(0, 10,10);

        verify(dogBreedService, times(1)).getAllDogBreeds(0, 10,10);
        assertEquals(mockResponse, response);
    }

    @Test
    public void testGetDogBreedsThrowsException() {
        when(dogBreedService.getAllDogBreeds(anyInt(), anyInt(),anyInt())).thenThrow(new RuntimeException("Error"));

        assertThrows(NotFoundElementException.class, () -> {
            dogBreedController.getDogBreeds(0, 10,10);
        });
    }

    @Test
    public void testGetDogBreedById() {
        DogBreedDto mockDto = new DogBreedDto();
        when(dogBreedService.getBreedById(anyLong())).thenReturn(mockDto);

        DogBreedDto response = dogBreedController.getDogBreedById(1L);

        verify(dogBreedService, times(1)).getBreedById(1L);
        assertEquals(mockDto, response);
    }

    @Test
    public void testUpdateDogBreed() {
        DogBreedInput input = new DogBreedInput();
        DogBreedDto mockDto = new DogBreedDto();
        when(dogBreedMapper.convertToDTO(input)).thenReturn(mockDto);
        when(dogBreedService.updateDogBreed(mockDto)).thenReturn(mockDto);

        DogBreedDto response = dogBreedController.updateDogBreed(input);

        verify(dogBreedMapper, times(1)).convertToDTO(input);
        verify(dogBreedService, times(1)).updateDogBreed(mockDto);
        assertEquals(mockDto, response);
    }

    @Test
    public void testCreateDogBreed() {
        DogBreedInput input = new DogBreedInput();
        DogBreedDto mockDto = new DogBreedDto();
        when(dogBreedMapper.convertToDTO(input)).thenReturn(mockDto);
        when(dogBreedService.createDogBreed(mockDto)).thenReturn(mockDto);

        DogBreedDto response = dogBreedController.createDogBreed(input);

        verify(dogBreedMapper, times(1)).convertToDTO(input);
        verify(dogBreedService, times(1)).createDogBreed(mockDto);
        assertEquals(mockDto, response);
    }

    @Test
    public void testDeleteDogBreed() {
        when(dogBreedService.deleteDogBreed(anyLong())).thenReturn(true);

        Boolean response = dogBreedController.deleteDogBreed(1L);

        verify(dogBreedService, times(1)).deleteDogBreed(1L);
        assertTrue(response);
    }
}
