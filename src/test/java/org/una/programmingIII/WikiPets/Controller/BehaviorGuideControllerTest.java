package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.BehaviorGuideInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.BehaviorGuideService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BehaviorGuideControllerTest {

    @InjectMocks
    private BehaviorGuideController behaviorGuideController;

    @Mock
    private BehaviorGuideService behaviorGuideService;

    @Mock
    private GenericMapperFactory mapperFactory;


    @Mock
    private GenericMapper<BehaviorGuideInput, BehaviorGuideDto> behaviorGuideMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(BehaviorGuideInput.class, BehaviorGuideDto.class)).thenReturn(behaviorGuideMapper);
        behaviorGuideController = new BehaviorGuideController(behaviorGuideService, mapperFactory);
    }

    @Test
    void testGetBehaviorGuides() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedGuides = new HashMap<>();
        when(behaviorGuideService.getAllBehaviorGuides(page, size)).thenReturn(expectedGuides);

        Map<String, Object> result = behaviorGuideController.getAllBehaviorGuides(page, size);

        assertEquals(expectedGuides, result);
        verify(behaviorGuideService).getAllBehaviorGuides(page, size);
    }

    @Test
    void testGetBehaviorGuidesException() {
        int page = 1;
        int size = 10;
        when(behaviorGuideService.getAllBehaviorGuides(page, size)).thenThrow(new NotFoundElementException(""));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            behaviorGuideController.getAllBehaviorGuides(page, size);
        });
        assertEquals("Could not retrieve behavior guides", exception.getMessage());
    }

    @Test
    void testGetBehaviorGuideById() {
        Long id = 1L;
        BehaviorGuideDto expectedGuide = new BehaviorGuideDto();
        when(behaviorGuideService.getBehaviorGuideById(id)).thenReturn(expectedGuide);

        BehaviorGuideDto result = behaviorGuideController.getBehaviorGuideById(id);

        assertEquals(expectedGuide, result);
        verify(behaviorGuideService).getBehaviorGuideById(id);
    }

    @Test
    void testCreateBehaviorGuide() {
        BehaviorGuideInput input = new BehaviorGuideInput();
        BehaviorGuideDto expectedGuideDto = new BehaviorGuideDto();
        when(behaviorGuideMapper.convertToDTO(input)).thenReturn(expectedGuideDto);
        when(behaviorGuideService.createBehaviorGuide(expectedGuideDto)).thenReturn(expectedGuideDto);

        BehaviorGuideDto result = behaviorGuideController.createBehaviorGuide(input);

        assertEquals(expectedGuideDto, result);
        verify(behaviorGuideMapper).convertToDTO(input);
        verify(behaviorGuideService).createBehaviorGuide(expectedGuideDto);
    }

    @Test
    void testUpdateBehaviorGuide() {

        BehaviorGuideInput input = new BehaviorGuideInput();
        BehaviorGuideDto expectedGuideDto = new BehaviorGuideDto();
        when(behaviorGuideMapper.convertToDTO(input)).thenReturn(expectedGuideDto);
        when(behaviorGuideService.updateBehaviorGuide(expectedGuideDto)).thenReturn(expectedGuideDto);


        BehaviorGuideDto result = behaviorGuideController.updateBehaviorGuide(input);


        assertEquals(expectedGuideDto, result);
        verify(behaviorGuideMapper).convertToDTO(input);
        verify(behaviorGuideService).updateBehaviorGuide(expectedGuideDto);
    }

    @Test
    void testDeleteBehaviorGuide() {

        Long id = 1L;
        when(behaviorGuideService.deleteBehaviorGuide(id)).thenReturn(true);


        Boolean result = behaviorGuideController.deleteBehaviorGuide(id);

        assertTrue(result);
        verify(behaviorGuideService).deleteBehaviorGuide(id);
    }

    @Test
    void testBehaviorGuideControllerConstructor() {
        behaviorGuideController = new BehaviorGuideController(behaviorGuideService, mapperFactory);
        assertNotNull(behaviorGuideController);
    }

    @Test
    void testGetBehaviorSuitableDogBreeds() {
        Long id = 1L;
        List<DogBreedDto> expectedDogBreeds = List.of(new DogBreedDto());
        when(behaviorGuideService.getBehaviorSuitableDogBreeds(id)).thenReturn(expectedDogBreeds);

        List<DogBreedDto> result = behaviorGuideController.getBehaviorSuitableDogBreeds(id);

        assertEquals(expectedDogBreeds, result);
        verify(behaviorGuideService).getBehaviorSuitableDogBreeds(id);
    }

    @Test
    void testGetBehaviorSuitableCatBreeds() {
        Long id = 1L;
        List<CatBreedDto> expectedCatBreeds = List.of(new CatBreedDto());
        when(behaviorGuideService.getBehaviorSuitableCatBreeds(id)).thenReturn(expectedCatBreeds);

        List<CatBreedDto> result = behaviorGuideController.getBehaviorSuitableCatBreeds(id);

        assertEquals(expectedCatBreeds, result);
        verify(behaviorGuideService).getBehaviorSuitableCatBreeds(id);
    }

    @Test
    void testAddSuitableDogBreedToBehaviorGuide() {
        Long id = 1L;
        Long idDogBreed = 2L;
        BehaviorGuideDto expectedGuideDto = new BehaviorGuideDto();
        when(behaviorGuideService.addSuitableDogBreedToBehaviorGuide(id, idDogBreed)).thenReturn(expectedGuideDto);

        BehaviorGuideDto result = behaviorGuideController.addSuitableDogBreedToBehaviorGuide(id, idDogBreed);

        assertEquals(expectedGuideDto, result);
        verify(behaviorGuideService).addSuitableDogBreedToBehaviorGuide(id, idDogBreed);
    }

    @Test
    void testAddSuitableCatBreedToBehaviorGuide() {
        Long id = 1L;
        Long idCatBreed = 2L;
        BehaviorGuideDto expectedGuideDto = new BehaviorGuideDto();
        when(behaviorGuideService.addSuitableCatBreedToBehaviorGuide(id, idCatBreed)).thenReturn(expectedGuideDto);

        BehaviorGuideDto result = behaviorGuideController.addSuitableCatBreedToBehaviorGuide(id, idCatBreed);

        assertEquals(expectedGuideDto, result);
        verify(behaviorGuideService).addSuitableCatBreedToBehaviorGuide(id, idCatBreed);
    }

    @Test
    void testDeleteSuitableCatBreedFromBehaviorGuide() {
        Long id = 1L;
        Long catBreedId = 2L;
        BehaviorGuideDto expectedGuideDto = new BehaviorGuideDto();
        when(behaviorGuideService.deleteSuitableCatBreedFromBehaviorGuide(id, catBreedId)).thenReturn(expectedGuideDto);

        BehaviorGuideDto result = behaviorGuideController.deleteSuitableCatBreedFromBehaviorGuide(id, catBreedId);

        assertEquals(expectedGuideDto, result);
        verify(behaviorGuideService).deleteSuitableCatBreedFromBehaviorGuide(id, catBreedId);
    }

    @Test
    void testDeleteSuitableDogBreedFromBehaviorGuide() {
        Long id = 1L;
        Long dogBreedId = 2L;
        BehaviorGuideDto expectedGuideDto = new BehaviorGuideDto();
        when(behaviorGuideService.deleteSuitableDogBreedFromBehaviorGuide(id, dogBreedId)).thenReturn(expectedGuideDto);

        BehaviorGuideDto result = behaviorGuideController.deleteSuitableDogBreedFromBehaviorGuide(id, dogBreedId);

        assertEquals(expectedGuideDto, result);
        verify(behaviorGuideService).deleteSuitableDogBreedFromBehaviorGuide(id, dogBreedId);
    }


}
