package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.AdoptionCenterInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.AdoptionCenterService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdoptionCenterControllerTest {

    @InjectMocks
    private AdoptionCenterController adoptionCenterController;

    @Mock
    private AdoptionCenterService adoptionCenterService;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<AdoptionCenterInput, AdoptionCenterDto> adoptionCenterMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(AdoptionCenterInput.class, AdoptionCenterDto.class)).thenReturn(adoptionCenterMapper);
        adoptionCenterController = new AdoptionCenterController(adoptionCenterService, mapperFactory);
    }

    @Test
    void testGetAdoptionCenters() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedCenters = new HashMap<>();
        when(adoptionCenterService.getAllAdoptionCenters(page, size)).thenReturn(expectedCenters);

        Map<String, Object> result = adoptionCenterController.getAdoptionCenters(page, size);

        assertEquals(expectedCenters, result);
        verify(adoptionCenterService).getAllAdoptionCenters(page, size);
    }

    @Test
    void testGetAdoptionCentersException() {
        int page = 1;
        int size = 10;
        when(adoptionCenterService.getAllAdoptionCenters(page, size)).thenThrow(new NotFoundElementException(""));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            adoptionCenterController.getAdoptionCenters(page, size);
        });
        assertEquals("Could not retrieve adoption centers", exception.getMessage());
    }

    @Test
    void testGetAdoptionCenterById() {
        Long id = 1L;
        AdoptionCenterDto expectedCenter = new AdoptionCenterDto();
        when(adoptionCenterService.getAdoptionCenterById(id)).thenReturn(expectedCenter);

        AdoptionCenterDto result = adoptionCenterController.getAdoptionCenterById(id);

        assertEquals(expectedCenter, result);
        verify(adoptionCenterService).getAdoptionCenterById(id);
    }

    @Test
    void testCreateAdoptionCenter() {
        AdoptionCenterInput input = new AdoptionCenterInput();
        AdoptionCenterDto expectedCenterDto = new AdoptionCenterDto();
        when(adoptionCenterMapper.convertToDTO(input)).thenReturn(expectedCenterDto);
        when(adoptionCenterService.createAdoptionCenter(expectedCenterDto)).thenReturn(expectedCenterDto);

        AdoptionCenterDto result = adoptionCenterController.createAdoptionCenter(input);

        assertEquals(expectedCenterDto, result);
        verify(adoptionCenterMapper).convertToDTO(input);
        verify(adoptionCenterService).createAdoptionCenter(expectedCenterDto);
    }

    @Test
    void testUpdateAdoptionCenter() {
        AdoptionCenterInput input = new AdoptionCenterInput();
        AdoptionCenterDto expectedCenterDto = new AdoptionCenterDto();
        when(adoptionCenterMapper.convertToDTO(input)).thenReturn(expectedCenterDto);
        when(adoptionCenterService.updateAdoptionCenter(expectedCenterDto)).thenReturn(expectedCenterDto);

        AdoptionCenterDto result = adoptionCenterController.updateAdoptionCenter(input);

        assertEquals(expectedCenterDto, result);
        verify(adoptionCenterMapper).convertToDTO(input);
        verify(adoptionCenterService).updateAdoptionCenter(expectedCenterDto);
    }

    @Test
    void testDeleteAdoptionCenter() {
        Long id = 1L;
        when(adoptionCenterService.deleteAdoptionCenter(id)).thenReturn(true);

        Boolean result = adoptionCenterController.deleteAdoptionCenter(id);

        assertTrue(result);
        verify(adoptionCenterService).deleteAdoptionCenter(id);
    }

    @Test
    void testAdoptionCenterControllerConstructor() {
        assertNotNull(adoptionCenterController);
    }

    @Test
    void testGetAvailableDogBreeds() {
        Long id = 1L;
        List<DogBreedDto> expectedBreeds = new ArrayList<>();
        when(adoptionCenterService.getAvailableDogBreeds(id)).thenReturn(expectedBreeds);

        List<DogBreedDto> result = adoptionCenterController.getAvailableDogBreeds(id);

        assertEquals(expectedBreeds, result);
        verify(adoptionCenterService).getAvailableDogBreeds(id);
    }

    @Test
    void testGetAvailableCatBreeds() {
        Long id = 1L;
        List<CatBreedDto> expectedBreeds = new ArrayList<>();
        when(adoptionCenterService.getAvailableCatBreeds(id)).thenReturn(expectedBreeds);

        List<CatBreedDto> result = adoptionCenterController.getAvailableCatBreeds(id);

        assertEquals(expectedBreeds, result);
        verify(adoptionCenterService).getAvailableCatBreeds(id);
    }

    @Test
    void testAddDogBreedInAdoptionCenter() {
        Long id = 1L;
        Long dogBreedId = 2L;
        AdoptionCenterDto expectedCenterDto = new AdoptionCenterDto();
        when(adoptionCenterService.addDogBreedInAdoptionCenter(id, dogBreedId)).thenReturn(expectedCenterDto);

        AdoptionCenterDto result = adoptionCenterController.addDogBreedInAdoptionCenter(id, dogBreedId);

        assertEquals(expectedCenterDto, result);
        verify(adoptionCenterService).addDogBreedInAdoptionCenter(id, dogBreedId);
    }

    @Test
    void testAddCatBreedInAdoptionCenter() {
        Long id = 1L;
        Long catBreedId = 3L;
        AdoptionCenterDto expectedCenterDto = new AdoptionCenterDto();
        when(adoptionCenterService.addCatBreedInAdoptionCenter(id, catBreedId)).thenReturn(expectedCenterDto);

        AdoptionCenterDto result = adoptionCenterController.addCatBreedInAdoptionCenter(id, catBreedId);

        assertEquals(expectedCenterDto, result);
        verify(adoptionCenterService).addCatBreedInAdoptionCenter(id, catBreedId);
    }

    @Test
    void testDeleteCatBreedFromAdoptionCenter() {
        Long id = 1L;
        Long catBreedId = 3L;
        AdoptionCenterDto expectedCenterDto = new AdoptionCenterDto();
        when(adoptionCenterService.deleteCatBreedFromAdoptionCenter(id, catBreedId)).thenReturn(expectedCenterDto);

        AdoptionCenterDto result = adoptionCenterController.deleteCatBreedFromAdoptionCenter(id, catBreedId);

        assertEquals(expectedCenterDto, result);
        verify(adoptionCenterService).deleteCatBreedFromAdoptionCenter(id, catBreedId);
    }

    @Test
    void testDeleteDogBreedFromAdoptionCenter() {
        Long id = 1L;
        Long dogBreedId = 2L;
        AdoptionCenterDto expectedCenterDto = new AdoptionCenterDto();
        when(adoptionCenterService.deleteDogBreedFromAdoptionCenter(id, dogBreedId)).thenReturn(expectedCenterDto);

        AdoptionCenterDto result = adoptionCenterController.deleteDogBreedFromAdoptionCenter(id, dogBreedId);

        assertEquals(expectedCenterDto, result);
        verify(adoptionCenterService).deleteDogBreedFromAdoptionCenter(id, dogBreedId);
    }

    @Test
    void testAdoptionCenterControllerControllerConstructor() {
        assertNotNull(adoptionCenterController);
    }
}

