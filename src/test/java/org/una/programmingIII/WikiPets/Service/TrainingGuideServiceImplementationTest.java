package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.TrainingGuideRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainingGuideServiceTest {

    @Mock
    private TrainingGuideRepository trainingGuideRepository;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<TrainingGuide, TrainingGuideDto> trainingGuideMapper;

    @InjectMocks
    private TrainingGuideService trainingGuideService;

    private TrainingGuide trainingGuide;
    private TrainingGuideDto trainingGuideDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        trainingGuide = new TrainingGuide();
        trainingGuide.setId(1L);
        trainingGuide.setTitle("Basic Training");
        trainingGuide.setContent("A basic guide for training your pet.");

        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreed());
        trainingGuide.setCatBreeds(catBreeds);

        List<DogBreed> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreed());
        trainingGuide.setDogBreeds(dogBreeds);

        trainingGuideDto = new TrainingGuideDto();
        trainingGuideDto.setId(1L);
        trainingGuideDto.setTitle("Basic Training");
        trainingGuideDto.setContent("A basic guide for training your pet.");

        when(mapperFactory.createMapper(TrainingGuide.class, TrainingGuideDto.class)).thenReturn(trainingGuideMapper);
        when(trainingGuideMapper.convertToDTO(trainingGuide)).thenReturn(trainingGuideDto);
        when(trainingGuideMapper.convertToEntity(trainingGuideDto)).thenReturn(trainingGuide);

        trainingGuideService = new TrainingGuideService(trainingGuideRepository, mapperFactory);
    }

    @Test
    public void createTrainingGuideTest() {
        when(trainingGuideRepository.save(any(TrainingGuide.class))).thenReturn(trainingGuide);
        TrainingGuideDto result = trainingGuideService.createTrainingGuide(trainingGuideDto);
        assertEquals(trainingGuideDto.getId(), result.getId());
        assertEquals(trainingGuideDto.getTitle(), result.getTitle());
    }

    @Test
    public void updateTrainingGuideTest() {
        when(trainingGuideRepository.save(any(TrainingGuide.class))).thenReturn(trainingGuide);
        TrainingGuideDto result = trainingGuideService.updateTrainingGuide(trainingGuideDto);
        assertEquals(trainingGuideDto.getId(), result.getId());
        assertEquals(trainingGuideDto.getTitle(), result.getTitle());
    }

    @Test
    public void getGuideByIdTest() {
        when(trainingGuideRepository.findById(1L)).thenReturn(Optional.of(trainingGuide));
        TrainingGuideDto result = trainingGuideService.getTrainingGuideById(1L);
        assertEquals(trainingGuideDto.getId(), result.getId());
        assertEquals(trainingGuideDto.getTitle(), result.getTitle());
    }

    @Test
    public void getGuideByIdNotFoundTest() {
        when(trainingGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> trainingGuideService.getTrainingGuideById(1L));
    }

    @Test
    public void getAllGuidesTest() {
        when(trainingGuideRepository.findAll()).thenReturn(List.of(trainingGuide));
        List<TrainingGuideDto> result = trainingGuideService.getAllTrainingGuides();
        assertEquals(1, result.size());
        assertEquals(trainingGuideDto.getId(), result.get(0).getId());
        assertTrue(result.get(0).getTitle().contains("Basic Training"));
    }

    @Test
    public void deleteTrainingGuideTest() {
        doNothing().when(trainingGuideRepository).deleteById(1L);
        trainingGuideService.deleteTrainingGuide(1L);
        verify(trainingGuideRepository, times(1)).deleteById(1L);
    }
}
