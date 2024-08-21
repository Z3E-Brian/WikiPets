package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.TrainingGuideRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingGuideServiceTest {

    @Mock
    private TrainingGuideRepository trainingGuideRepository;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<TrainingGuide, TrainingGuideDto> traningGuideMapper;

    @InjectMocks
    private TrainingGuideService trainingGuideService;

    private TrainingGuide trainingGuide;
    private TrainingGuideDto trainingGuideDto;

    @BeforeEach
    void setUp() {
        trainingGuide = new TrainingGuide();
        trainingGuideDto = new TrainingGuideDto();
        trainingGuide.setId(1L);
        trainingGuide.setTitle("Basic Training");
        trainingGuide.setContent("A basic guide for training your pet.");
        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreed());
        List<DogBreed> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreed());
        trainingGuide.setCatBreeds(catBreeds);
        trainingGuide.setDogBreeds(dogBreeds);

        when(mapperFactory.createMapper(TrainingGuide.class, TrainingGuideDto.class)).thenReturn(traningGuideMapper);
        when(traningGuideMapper.convertToDTO(trainingGuide)).thenReturn(trainingGuideDto);
        when(traningGuideMapper.convertToEntity(trainingGuideDto)).thenReturn(trainingGuide);
        trainingGuideService = new TrainingGuideService(trainingGuideRepository, mapperFactory);
        trainingGuideDto = traningGuideMapper.convertToDTO(trainingGuide);
    }

    @Test
    public void createTrainingGuideTest() {
        when(trainingGuideRepository.findAll()).thenReturn(List.of(trainingGuide));
        List<TrainingGuideDto> result = trainingGuideService.getAllTrainingGuides();
        assertEquals(1, result.size());
        assertEquals(trainingGuideDto.getId(), result.get(0).getId());
        assertTrue(result.get(0).getTitle().contains("Basic Training"));
    }

    @Test
    public void updateTrainingGuideTest() {
        when(trainingGuideRepository.save(Mockito.any(TrainingGuide.class))).thenReturn(trainingGuide);

        TrainingGuideDto result = trainingGuideService.updateTrainingGuide(trainingGuideDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Basic Training", result.getTitle());
    }

    @Test
    public void getGuideByIdTest() {
        when(trainingGuideRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(trainingGuide));

        TrainingGuideDto result = trainingGuideService.getTrainingGuideById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void getGuideByIdNotFoundTest() {
        when(trainingGuideRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> trainingGuideService.getTrainingGuideById(1L));
    }

    @Test
    public void getAllGuidesTest() {
        when(trainingGuideRepository.findAll()).thenReturn(Arrays.asList(trainingGuide));

        List<TrainingGuideDto> result = trainingGuideService.getAllTrainingGuides();

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void deleteTrainingGuideTest() {
        trainingGuideService.deleteTrainingGuide(1L);
        verify(trainingGuideRepository, times(1)).deleteById(1L);
    }

    @Test
    public void searchByTitleTest() {
        when(trainingGuideRepository.findByTitleContainingIgnoreCase(Mockito.anyString())).thenReturn(Arrays.asList(trainingGuide));

        List<TrainingGuideDto> result = trainingGuideService.searchByTitle("Basic");

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void getTrainingGuidesByCatBreedIdTest() {
        when(trainingGuideRepository.findByCatBreedsId(Mockito.anyLong())).thenReturn(Arrays.asList(trainingGuide));

        List<TrainingGuideDto> result = trainingGuideService.getTrainingGuidesByCatBreedId(1L);

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void getTrainingGuidesByDogBreedIdTest() {
        when(trainingGuideRepository.findByDogBreedsId(Mockito.anyLong())).thenReturn(Arrays.asList(trainingGuide));

        List<TrainingGuideDto> result = trainingGuideService.getTrainingGuidesByDogBreedId(1L);

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
    }
}
