package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Mapper.TrainingGuideMapper;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.TrainingGuideRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingGuideServiceImplementationTest {

    @Mock
    private TrainingGuideRepository trainingGuideRepository;

    @InjectMocks
    private TrainingGuideServiceImplementation trainingGuideServiceImplementation;

    private TrainingGuide trainingGuide;
    private TrainingGuideDto trainingGuideDto;

    @BeforeEach
    void setUp() {
        trainingGuide = new TrainingGuide();
        trainingGuide.setId(1L);
        trainingGuide.setTitle("Basic Training");
        trainingGuide.setContent("A basic guide for training your pet.");
        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreed());
        List<DogBreed> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreed());
        trainingGuide.setCatBreeds(catBreeds);
        trainingGuide.setDogBreeds(dogBreeds);
        trainingGuideDto = (TrainingGuideMapper.INSTANCE.toTrainingGuideDto(trainingGuide));
    }

    @Test
    public void createTrainingGuideTest() {
        when(trainingGuideRepository.save(Mockito.any(TrainingGuide.class))).thenReturn(trainingGuide);

        TrainingGuideDto result = trainingGuideServiceImplementation.createTrainingGuide(trainingGuideDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Basic Training", result.getTitle());
    }

    @Test
    public void updateTrainingGuideTest() {
        when(trainingGuideRepository.save(Mockito.any(TrainingGuide.class))).thenReturn(trainingGuide);

        TrainingGuideDto result = trainingGuideServiceImplementation.updateTrainingGuide(trainingGuideDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Basic Training", result.getTitle());
    }

    @Test
    public void getGuideByIdTest() {
        when(trainingGuideRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(trainingGuide));

        TrainingGuideDto result = trainingGuideServiceImplementation.getTrainingGuideById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void getGuideByIdNotFoundTest() {
        when(trainingGuideRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> trainingGuideServiceImplementation.getTrainingGuideById(1L));
    }

    @Test
    public void getAllGuidesTest() {
        when(trainingGuideRepository.findAll()).thenReturn(Arrays.asList(trainingGuide));

        List<TrainingGuideDto> result = trainingGuideServiceImplementation.getAllTrainingGuides();

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void deleteTrainingGuideTest() {
        trainingGuideServiceImplementation.deleteTrainingGuide(1L);

        verify(trainingGuideRepository, times(1)).deleteById(1L);
    }

    @Test
    public void searchByTitleTest() {
        when(trainingGuideRepository.findByTitleContainingIgnoreCase(Mockito.anyString())).thenReturn(Arrays.asList(trainingGuide));

        List<TrainingGuideDto> result = trainingGuideServiceImplementation.searchByTitle("Basic");

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void getTrainingGuidesByCatBreedIdTest() {
        when(trainingGuideRepository.findByCatBreedsId(Mockito.anyLong())).thenReturn(Arrays.asList(trainingGuide));

        List<TrainingGuideDto> result = trainingGuideServiceImplementation.getTrainingGuidesByCatBreedId(1L);

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void getTrainingGuidesByDogBreedIdTest() {
        when(trainingGuideRepository.findByDogBreedsId(Mockito.anyLong())).thenReturn(Arrays.asList(trainingGuide));

        List<TrainingGuideDto> result = trainingGuideServiceImplementation.getTrainingGuidesByDogBreedId(1L);

        assertNotNull(result);
        assertEquals(1L, result.get(0).getId());
    }
}
