//package org.una.programmingIII.WikiPets.Controller;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
//import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
//import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
//import org.una.programmingIII.WikiPets.Dto.UserDto;
//import org.una.programmingIII.WikiPets.Input.GroomingGuideInput;
//import org.una.programmingIII.WikiPets.Input.UserInput;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
//import org.una.programmingIII.WikiPets.Service.GroomingGuideService;
//
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
//
//public class GroomingGuideControllerTest {
//
//    @InjectMocks
//    private GroomingGuideController groomingGuideController;
//
//    @Mock
//    private GroomingGuideService groomingGuideService;
//    @Mock
//    private GenericMapperFactory mapperFactory;
//    @Mock
//    private GenericMapper<GroomingGuideInput, GroomingGuideDto> groomingGuideMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        when(mapperFactory.createMapper(GroomingGuideInput.class, GroomingGuideDto.class)).thenReturn(groomingGuideMapper);
//        groomingGuideController = new GroomingGuideController(groomingGuideService, mapperFactory);
//    }
//
//    @Test
//    void testGroomingGuideControllerConstructor() {
//        assertNotNull(groomingGuideController);
//    }
//
//    @Test
//    void testGetAllGroomingGuides() {
//        int page = 1;
//        int size = 10;
//        Map<String, Object> expectedGuides = new HashMap<>();
//        when(groomingGuideService.getAllGroomingGuides(page, size)).thenReturn(expectedGuides);
//
//        Map<String, Object> result = groomingGuideController.getAllGroomingGuides(page, size);
//
//        assertEquals(expectedGuides, result);
//        verify(groomingGuideService).getAllGroomingGuides(page, size);
//    }
//
//    @Test
//    void testGetGroomingGuideById() {
//        Long id = 1L;
//        GroomingGuideDto expectedGroomingGuide = new GroomingGuideDto();
//        when(groomingGuideService.getGroomingGuideById(id)).thenReturn(expectedGroomingGuide);
//
//        GroomingGuideDto result = groomingGuideController.getGroomingGuideById(id);
//
//        assertEquals(expectedGroomingGuide, result);
//        verify(groomingGuideService).getGroomingGuideById(id);
//    }
//
//    @Test
//    void testGetGroomingSuitableDogBreeds() {
//        Long id = 1L;
//        List<DogBreedDto> expectedBreeds = List.of(new DogBreedDto());
//        when(groomingGuideService.getSuitableDogBreeds(id)).thenReturn(expectedBreeds);
//
//        List<DogBreedDto> result = groomingGuideController.getGroomingSuitableDogBreeds(id);
//
//        assertEquals(expectedBreeds, result);
//        verify(groomingGuideService).getSuitableDogBreeds(id);
//    }
//
//    @Test
//    void testGetGroomingSuitableCatBreeds() {
//        Long id = 1L;
//        List<CatBreedDto> expectedBreeds = List.of(new CatBreedDto());
//        when(groomingGuideService.getAllGroomingGuides(id)).thenReturn(expectedBreeds);
//
//        List<CatBreedDto> result = groomingGuideController.getGroomingSuitableCatBreeds(id);
//
//        assertEquals(expectedBreeds, result);
//        verify(groomingGuideService).getSuitableCatBreeds(id);
//    }
//
//    @Test
//    void testAddSuitableDogBreedToGroomingGuide() {
//        Long guideId = 1L;
//        Long dogBreedId = 1L;
//        GroomingGuideDto expectedGuide = new GroomingGuideDto();
//        when(groomingGuideService.addSuitableDogBreedToGroomingGuide(guideId, dogBreedId)).thenReturn(expectedGuide);
//
//        GroomingGuideDto result = groomingGuideController.addSuitableDogBreedToGroomingGuide(guideId, dogBreedId);
//
//        assertEquals(expectedGuide, result);
//        verify(groomingGuideService).addSuitableDogBreedToGroomingGuide(guideId, dogBreedId);
//    }
//
//    @Test
//    void testAddSuitableCatBreedToGroomingGuide() {
//        Long guideId = 1L;
//        Long catBreedId = 1L;
//        GroomingGuideDto expectedGuide = new GroomingGuideDto();
//        when(groomingGuideService.addSuitableCatBreedToGroomingGuide(guideId, catBreedId)).thenReturn(expectedGuide);
//
//        GroomingGuideDto result = groomingGuideController.addSuitableCatBreedToGroomingGuide(guideId, catBreedId);
//
//        assertEquals(expectedGuide, result);
//        verify(groomingGuideService).addSuitableCatBreedToGroomingGuide(guideId, catBreedId);
//    }
//
//    @Test
//    void testCreateGroomingGuide() {
//        GroomingGuideInput input = new GroomingGuideInput();
//        GroomingGuideDto expectedGuide = new GroomingGuideDto();
//        when(groomingGuideMapper.convertToDTO(input)).thenReturn(expectedGuide);
//        when(groomingGuideService.createGroomingGuide(expectedGuide)).thenReturn(expectedGuide);
//
//        GroomingGuideDto result = groomingGuideController.createGroomingGuide(input);
//
//        assertEquals(expectedGuide, result);
//        verify(groomingGuideMapper).convertToDTO(input);
//        verify(groomingGuideService).createGroomingGuide(expectedGuide);
//    }
//
//    @Test
//    void testUpdateGroomingGuide() {
//        GroomingGuideInput input = new GroomingGuideInput();
//        GroomingGuideDto expectedGuide = new GroomingGuideDto();
//        when(groomingGuideMapper.convertToDTO(input)).thenReturn(expectedGuide);
//        when(groomingGuideService.updateGroomingGuide(expectedGuide)).thenReturn(expectedGuide);
//
//        GroomingGuideDto result = groomingGuideController.updateGroomingGuide(input);
//
//        assertEquals(expectedGuide, result);
//        verify(groomingGuideMapper).convertToDTO(input);
//        verify(groomingGuideService).updateGroomingGuide(expectedGuide);
//    }
//
//    @Test
//    void testDeleteGroomingGuide() {
//        Long id = 1L;
//
//        groomingGuideController.deleteGroomingGuide(id);
//
//        verify(groomingGuideService).deleteGroomingGuide(id);
//    }
//
//    @Test
//    void testRemoveSuitableCatBreedFromGroomingGuide() {
//        Long guideId = 1L;
//        Long catBreedId = 1L;
//        GroomingGuideDto expectedGuide = new GroomingGuideDto();
//        when(groomingGuideService.removeSuitableCatBreedFromGroomingGuide(guideId, catBreedId)).thenReturn(expectedGuide);
//
//        GroomingGuideDto result = groomingGuideController.removeSuitableCatBreedFromGroomingGuide(guideId, catBreedId);
//
//        assertEquals(expectedGuide, result);
//        verify(groomingGuideService).removeSuitableCatBreedFromGroomingGuide(guideId, catBreedId);
//    }
//
//    @Test
//    void testRemoveSuitableDogBreedFromGroomingGuide() {
//        Long guideId = 1L;
//        Long dogBreedId = 1L;
//        GroomingGuideDto expectedGuide = new GroomingGuideDto();
//        when(groomingGuideService.removeSuitableDogBreedFromGroomingGuide(guideId, dogBreedId)).thenReturn(expectedGuide);
//
//        GroomingGuideDto result = groomingGuideController.removeSuitableDogBreedFromGroomingGuide(guideId, dogBreedId);
//
//        assertEquals(expectedGuide, result);
//        verify(groomingGuideService).removeSuitableDogBreedFromGroomingGuide(guideId, dogBreedId);
//    }
//
//    @Test
//    void testGetAllGroomingGuidesException() {
//        int page = 1;
//        int size = 10;
//        when(groomingGuideService.getAllGroomingGuides(page, size)).thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.getAllGroomingGuides(page, size);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not find grooming guides"));
//    }
//
//    @Test
//    void testGetGroomingGuideByIdException() {
//        Long id = 1L;
//        when(groomingGuideService.getGroomingGuideById(id)).thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.getGroomingGuideById(id);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not find grooming guide with id"));
//    }
//    @Test
//    void testGetGroomingSuitableDogBreedsException() {
//        Long id = 1L;
//        when(groomingGuideService.getSuitableDogBreeds(id)).thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.getGroomingSuitableDogBreeds(id);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not find grooming guide"));
//    }
//
//    @Test
//    void testGetGroomingSuitableCatBreedsException() {
//        Long id = 1L;
//        when(groomingGuideService.getSuitableCatBreeds(id)).thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.getGroomingSuitableCatBreeds(id);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not find grooming guide"));
//    }
//
//    @Test
//    void testAddSuitableDogBreedToGroomingGuideException() {
//        Long guideId = 1L;
//        Long dogBreedId = 1L;
//        when(groomingGuideService.addSuitableDogBreedToGroomingGuide(guideId, dogBreedId))
//                .thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.addSuitableDogBreedToGroomingGuide(guideId, dogBreedId);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not update grooming guide"));
//    }
//
//    @Test
//    void testAddSuitableCatBreedToGroomingGuideException() {
//        Long guideId = 1L;
//        Long catBreedId = 1L;
//        when(groomingGuideService.addSuitableCatBreedToGroomingGuide(guideId, catBreedId))
//                .thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.addSuitableCatBreedToGroomingGuide(guideId, catBreedId);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not update grooming guide"));
//    }
//
//    @Test
//    void testCreateGroomingGuideException() {
//        GroomingGuideInput input = new GroomingGuideInput();
//        when(groomingGuideMapper.convertToDTO(input)).thenReturn(new GroomingGuideDto());
//        when(groomingGuideService.createGroomingGuide(any())).thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.createGroomingGuide(input);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not create grooming guide"));
//    }
//
//
//    @Test
//    void testUpdateGroomingGuideException() {
//        GroomingGuideInput input = new GroomingGuideInput();
//        when(groomingGuideMapper.convertToDTO(input)).thenReturn(new GroomingGuideDto());
//        when(groomingGuideService.updateGroomingGuide(any())).thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.updateGroomingGuide(input);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not update grooming guide"));
//    }
//
//    @Test
//    void testRemoveSuitableCatBreedFromGroomingGuideException() {
//        Long guideId = 1L;
//        Long catBreedId = 1L;
//        when(groomingGuideService.removeSuitableCatBreedFromGroomingGuide(guideId, catBreedId))
//                .thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.removeSuitableCatBreedFromGroomingGuide(guideId, catBreedId);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not remove cat breed from grooming guide"));
//    }
//
//    @Test
//    void testRemoveSuitableDogBreedFromGroomingGuideException() {
//        Long guideId = 1L;
//        Long dogBreedId = 1L;
//        when(groomingGuideService.removeSuitableDogBreedFromGroomingGuide(guideId, dogBreedId))
//                .thenThrow(new RuntimeException("Service error"));
//
//        Exception exception = assertThrows(CustomException.class, () -> {
//            groomingGuideController.removeSuitableDogBreedFromGroomingGuide(guideId, dogBreedId);
//        });
//
//        assertTrue(exception.getMessage().contains("Could not remove dog breed from grooming guide"));
//    }
//
//
//
//
//
//
//
//
//
//}
