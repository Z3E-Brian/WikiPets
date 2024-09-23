package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FeedingScheduleServiceImplementationTest {

    @InjectMocks
    private FeedingScheduleServiceImplementation feedingScheduleServiceImplementation;

    @Mock
    private FeedingScheduleRepository feedingScheduleRepository;
    @Mock
    private GenericMapper<FeedingSchedule, FeedingScheduleDto> feedingScheduleMapper;
    @Mock
    private GenericMapperFactory mapperFactory;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private CatBreedService catBreedService;
    @Mock
    private DogBreedService dogBreedService;


    private FeedingSchedule feedingSchedule;
    private FeedingScheduleDto feedingScheduleDto;
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


        feedingSchedule = new FeedingSchedule();
        feedingSchedule.setId(1L);
        feedingSchedule.setFeedingTimes("10");
        feedingSchedule.setAgeGroup("Puppy");
        feedingSchedule.setCatBreeds(new ArrayList<CatBreed>());
        feedingSchedule.setDogBreeds(new ArrayList<DogBreed>());


        feedingScheduleDto = new FeedingScheduleDto();
        feedingScheduleDto.setId(1L);
        feedingScheduleDto.setFeedingTimes("10");
        feedingScheduleDto.setAgeGroup("Puppy");
        feedingScheduleDto.setCatBreeds(new ArrayList<CatBreedDto>());
        feedingScheduleDto.setDogBreeds(new ArrayList<DogBreedDto>());

        when(mapperFactory.createMapper(FeedingSchedule.class, FeedingScheduleDto.class)).thenReturn(feedingScheduleMapper);
        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);
        when(mapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);

        feedingScheduleServiceImplementation = new FeedingScheduleServiceImplementation(feedingScheduleRepository, mapperFactory, dogBreedService, catBreedService);
    }

    @Test
    void testGetFeedingScheduleById_Success() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        when(feedingScheduleMapper.convertToDTO(feedingSchedule)).thenReturn(feedingScheduleDto);

        FeedingScheduleDto result = feedingScheduleServiceImplementation.getFeedingScheduleById(1L);

        assertNotNull(result);
        assertEquals(feedingScheduleDto.getFeedingTimes(), result.getFeedingTimes());
        verify(feedingScheduleRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFeedingScheduleById_NotFound() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundElementException.class, () -> feedingScheduleServiceImplementation.getFeedingScheduleById(1L));
    }

    @Test
    void testCreateFeedingSchedule_Success() {
        when(feedingScheduleMapper.convertToEntity(feedingScheduleDto)).thenReturn(feedingSchedule);
        when(feedingScheduleRepository.save(feedingSchedule)).thenReturn(feedingSchedule);
        when(feedingScheduleMapper.convertToDTO(feedingSchedule)).thenReturn(feedingScheduleDto);

        FeedingScheduleDto result = feedingScheduleServiceImplementation.createFeedingSchedule(feedingScheduleDto);

        assertNotNull(result);
        assertEquals(feedingScheduleDto.getFeedingTimes(), result.getFeedingTimes());
        verify(feedingScheduleRepository, times(1)).save(feedingSchedule);
    }

    @Test
    void testCreateFeedingSchedule_BlankInput() {
        feedingScheduleDto.setFeedingTimes("");

        assertThrows(BlankInputException.class, () -> feedingScheduleServiceImplementation.createFeedingSchedule(feedingScheduleDto));
    }

    @Test
    void testDeleteFeedingSchedule() {
        when(feedingScheduleRepository.existsById(anyLong())).thenReturn(true);
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));

        boolean result = feedingScheduleServiceImplementation.deleteFeedingSchedule(1L);

        assertTrue(result);
    }


    @Test
    void testDeleteFeedingSchedule_NotFound() {
        when(feedingScheduleRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundElementException.class, () -> feedingScheduleServiceImplementation.deleteFeedingSchedule(1L));
    }

    @Test
    void testUpdateFeedingSchedule_Success() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        when(feedingScheduleMapper.convertToEntity(feedingScheduleDto)).thenReturn(feedingSchedule);
        when(feedingScheduleRepository.save(feedingSchedule)).thenReturn(feedingSchedule);
        when(feedingScheduleMapper.convertToDTO(feedingSchedule)).thenReturn(feedingScheduleDto);

        FeedingScheduleDto result = feedingScheduleServiceImplementation.updateFeedingSchedule(feedingScheduleDto);

        assertNotNull(result);
        assertEquals(feedingScheduleDto.getFeedingTimes(), result.getFeedingTimes());
        verify(feedingScheduleRepository, times(1)).save(feedingSchedule);
    }


    @Test
    void testGetAllFeedingSchedules() {
        Page<FeedingSchedule> feedingSchedulePage = new PageImpl<>(List.of(feedingSchedule));
        when(feedingScheduleRepository.findAll(any(PageRequest.class))).thenReturn(feedingSchedulePage);

        Map<String, Object> result = feedingScheduleServiceImplementation.getFeedingSchedules(0, 5);

        assertNotNull(result);
        assertTrue(result.containsKey("feedingSchedules"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));

        verify(feedingScheduleRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testAddCatBreedFeedinSchedule() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(feedingScheduleRepository.save(any(FeedingSchedule.class))).thenReturn(feedingSchedule);
        when(feedingScheduleMapper.convertToDTO(any(FeedingSchedule.class))).thenReturn(feedingScheduleDto);

        FeedingScheduleDto result = feedingScheduleServiceImplementation.addCatBreedInInFeedingSchedule(1L, 1L);

        assertEquals(feedingScheduleDto, result);
        verify(feedingScheduleRepository, times(1)).save(any(FeedingSchedule.class));
    }

    @Test
    void testAddDogBreedTrainingGuide() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(feedingScheduleRepository.save(any(FeedingSchedule.class))).thenReturn(feedingSchedule);
        when(feedingScheduleMapper.convertToDTO(any(FeedingSchedule.class))).thenReturn(feedingScheduleDto);

        FeedingScheduleDto result = feedingScheduleServiceImplementation.addDogBreedInFeedingSchedule(1L, 1L);

        assertEquals(feedingScheduleDto, result);
        verify(feedingScheduleRepository, times(1)).save(any(FeedingSchedule.class));
    }

    @Test
    void testDeleteDogBreedInFeedingSchedule() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(feedingScheduleRepository.save(any(FeedingSchedule.class))).thenReturn(feedingSchedule);
        when(feedingScheduleMapper.convertToDTO(any(FeedingSchedule.class))).thenReturn(feedingScheduleDto);

        FeedingScheduleDto result = feedingScheduleServiceImplementation.deleteDogBreedInFeedingSchedule(1L, 1L);

        assertEquals(feedingScheduleDto, result);
        verify(feedingScheduleRepository, times(1)).save(any(FeedingSchedule.class));
    }

    @Test
    void testDeleteCatBreedInFeedingSchedule() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(feedingScheduleRepository.save(any(FeedingSchedule.class))).thenReturn(feedingSchedule);
        when(feedingScheduleMapper.convertToDTO(any(FeedingSchedule.class))).thenReturn(feedingScheduleDto);

        FeedingScheduleDto result = feedingScheduleServiceImplementation.deleteCatBreedInFeedingSchedule(1L, 1L);

        assertEquals(feedingScheduleDto, result);
        verify(feedingScheduleRepository, times(1)).save(any(FeedingSchedule.class));
    }
}
