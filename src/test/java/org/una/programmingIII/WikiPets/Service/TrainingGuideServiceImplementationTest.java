package org.una.programmingIII.WikiPets.Service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Input.TrainingGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.TrainingGuideRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TrainingGuideServiceImplementationTest {
    @InjectMocks
    private TrainingGuideServiceImplementation trainingGuideServiceImplementation;
    @Mock
    private TrainingGuideRepository trainingGuideRepository;
    @Mock
    private GenericMapper<TrainingGuide, TrainingGuideDto> trainingGuideMapper;
    @Mock
    private GenericMapper<TrainingGuide, TrainingGuideInput> trainingGuideInputMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private DogBreedService dogBreedService;
    @Mock
    private CatBreedService catBreedService;
    @Mock
    private GenericMapperFactory mapperFactory;

    private TrainingGuide trainingGuide;
    private TrainingGuideDto trainingGuideDto;
    private CatBreed catBreed;
    private DogBreed dogBreed;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dogBreed = new DogBreed();
        dogBreed.setId(1L);
        dogBreed.setName("Golden Retriever");
        dogBreed.setOrigin("Scotland");
        dogBreed.setSize(3);
        dogBreed.setCoat("Long");
        dogBreed.setColor("Golden");
        dogBreed.setLifeSpan("10-12 years");
        dogBreed.setTemperament("Intelligent, Friendly, Devoted");
        dogBreed.setDescription("Popular house dog");

        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);
        catBreed.setCoat("Short");
        catBreed.setColor("Cream with Dark Points");
        catBreed.setLifeSpan("15-20 years");
        catBreed.setTemperament("Social, Affectionate, Vocal");
        catBreed.setDescription("Known for their striking appearance and vocal nature");

        trainingGuide = new TrainingGuide();
        trainingGuide.setId(1L);
        trainingGuide.setContent("Content");
        trainingGuide.setTitle("Title");
        trainingGuide.setDogBreeds(new ArrayList<>());
        trainingGuide.setCatBreeds(new ArrayList<>());

        trainingGuideDto = new TrainingGuideDto();
        trainingGuideDto.setId(1L);
        trainingGuideDto.setContent("ContentDto");
        trainingGuideDto.setTitle("TitleDto");
        trainingGuideDto.setDogBreeds(new ArrayList<>());
        trainingGuideDto.setCatBreeds(new ArrayList<>());


        when(mapperFactory.createMapper(TrainingGuide.class, TrainingGuideDto.class)).thenReturn(trainingGuideMapper);
        when(mapperFactory.createMapper(TrainingGuide.class, TrainingGuideInput.class)).thenReturn(trainingGuideInputMapper);
        when(mapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        trainingGuideServiceImplementation = new TrainingGuideServiceImplementation(trainingGuideRepository, mapperFactory, dogBreedService, catBreedService);


    }


    @Test
    void testGetTrainingGuideById() {
        when(trainingGuideRepository.findById(anyLong())).thenReturn(Optional.of(trainingGuide));
        when(trainingGuideMapper.convertToDTO(trainingGuide)).thenReturn(trainingGuideDto);

        TrainingGuideDto result = trainingGuideServiceImplementation.getTrainingGuideById(1L);

        assertNotNull(result);
        assertEquals(trainingGuideDto.getTitle(), result.getTitle());
    }

    @Test
    void testCreateTrainingGuide() {
        when(trainingGuideMapper.convertToEntity(trainingGuideDto)).thenReturn(trainingGuide);
        when(trainingGuideRepository.save(trainingGuide)).thenReturn(trainingGuide);
        when(trainingGuideMapper.convertToDTO(trainingGuide)).thenReturn(trainingGuideDto);

        TrainingGuideDto result = trainingGuideServiceImplementation.createTrainingGuide(trainingGuideDto);

        assertNotNull(result);
        assertEquals(trainingGuideDto.getTitle(), result.getTitle());
        verify(trainingGuideRepository, times(1)).save(trainingGuide);
    }

    @Test
    void testDeleteTrainingGuide() {
        when(trainingGuideRepository.existsById(anyLong())).thenReturn(true);
        when(trainingGuideRepository.findById(anyLong())).thenReturn(Optional.of(trainingGuide));

        boolean result = trainingGuideServiceImplementation.deleteTrainingGuide(1L);

        assertTrue(result);
        verify(trainingGuideRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateTrainingGuide() {
        when(trainingGuideRepository.findById(anyLong())).thenReturn(Optional.of(trainingGuide));
        when(trainingGuideMapper.convertToEntity(trainingGuideDto)).thenReturn(trainingGuide);
        when(trainingGuideRepository.save(trainingGuide)).thenReturn(trainingGuide);
        when(trainingGuideMapper.convertToDTO(trainingGuide)).thenReturn(trainingGuideDto);

        TrainingGuideDto result = trainingGuideServiceImplementation.updateTrainingGuide(trainingGuideDto);

        assertNotNull(result);
        assertEquals(trainingGuideDto.getTitle(), result.getTitle());
        verify(trainingGuideRepository, times(1)).save(trainingGuide);
    }

    @Test
    void testAddCatBreedTrainingGuide() {
        when(trainingGuideRepository.findById(anyLong())).thenReturn(Optional.of(trainingGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(trainingGuideRepository.save(any(TrainingGuide.class))).thenReturn(trainingGuide);
        when(trainingGuideMapper.convertToDTO(any(TrainingGuide.class))).thenReturn(trainingGuideDto);

        TrainingGuideDto result = trainingGuideServiceImplementation.addCatBreedInTrainingGuide(1L, 1L);

        assertEquals(trainingGuideDto, result);
        verify(trainingGuideRepository, times(1)).save(any(TrainingGuide.class));
    }

    @Test
    void testAddDogBreedTrainingGuide() {
        when(trainingGuideRepository.findById(anyLong())).thenReturn(Optional.of(trainingGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(trainingGuideRepository.save(any(TrainingGuide.class))).thenReturn(trainingGuide);
        when(trainingGuideMapper.convertToDTO(any(TrainingGuide.class))).thenReturn(trainingGuideDto);

        TrainingGuideDto result = trainingGuideServiceImplementation.addDogBreedInTrainingGuide(1L, 1L);

        assertEquals(trainingGuideDto, result);
        verify(trainingGuideRepository, times(1)).save(any(TrainingGuide.class));
    }

    @Test
    void testDeleteDogBreedInUser() {
        when(trainingGuideRepository.findById(anyLong())).thenReturn(Optional.of(trainingGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(trainingGuideRepository.save(any(TrainingGuide.class))).thenReturn(trainingGuide);
        when(trainingGuideMapper.convertToDTO(any(TrainingGuide.class))).thenReturn(trainingGuideDto);

        TrainingGuideDto result = trainingGuideServiceImplementation.deleteDogBreedInTrainingGuide(1L, 1L);

        assertEquals(trainingGuideDto, result);
        verify(trainingGuideRepository, times(1)).save(any(TrainingGuide.class));
    }

    @Test
    void testDeleteCatBreedInUser() {
        when(trainingGuideRepository.findById(anyLong())).thenReturn(Optional.of(trainingGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(trainingGuideRepository.save(any(TrainingGuide.class))).thenReturn(trainingGuide);
        when(trainingGuideMapper.convertToDTO(any(TrainingGuide.class))).thenReturn(trainingGuideDto);

        TrainingGuideDto result = trainingGuideServiceImplementation.deleteCatBreedInTrainingGuide(1L, 1L);

        assertEquals(trainingGuideDto, result);
        verify(trainingGuideRepository, times(1)).save(any(TrainingGuide.class));
    }

    @Test
    void testGetTrainingGuides() {

        List<TrainingGuide> trainingGuideList = List.of(trainingGuide);
        Page<TrainingGuide> trainingGuidePage = new PageImpl<>(trainingGuideList);

        when(trainingGuideRepository.findAll(any(PageRequest.class))).thenReturn(trainingGuidePage);

        Map<String, Object> result = trainingGuideServiceImplementation.getAllTrainingGuides(0, 2);

        assertNotNull(result);
        assertTrue(result.containsKey("trainingGuides"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));

        assertEquals(1, ((List<?>) result.get("trainingGuides")).size());
        assertEquals(trainingGuidePage.getTotalPages(), result.get("totalPages"));
        assertEquals(trainingGuidePage.getTotalElements(), result.get("totalElements"));

        verify(trainingGuideRepository, times(1)).findAll(any(PageRequest.class));
    }


}
