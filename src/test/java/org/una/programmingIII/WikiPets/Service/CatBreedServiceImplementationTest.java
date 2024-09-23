package org.una.programmingIII.WikiPets.Service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;

import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.time.LocalDate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatBreedServiceImplementationTest {

    @Mock
    private CatBreedRepository catBreedRepository;

    @Mock
    private GenericMapperFactory mapperFactory;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    @InjectMocks
    private CatBreedServiceImplementation catBreedServiceImplementation;

    private CatBreedDto catBreedDto;
    private CatBreed catBreed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Siamese");
        catBreedDto.setOrigin("Thailand");
        catBreedDto.setSize(2);
        catBreedDto.setCoat("Short");
        catBreedDto.setColor("Cream with points");
        catBreedDto.setLifeSpan("12-16 years");
        catBreedDto.setTemperament("Affectionate, Social, Vocal");
        catBreedDto.setDescription("Popular breed known for its striking appearance and vocal nature.");
        catBreedDto.setCreatedDate(LocalDate.ofEpochDay(2020-10-22));
        catBreedDto.setModifiedDate(LocalDate.ofEpochDay(2020-10-22));
        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);
        MockitoAnnotations.openMocks(this);

        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        catBreedServiceImplementation = new CatBreedServiceImplementation(catBreedRepository, mapperFactory);

    }

    @Test
    public void createCatBreedTest() {
        when(catBreedMapper.convertToEntity(Mockito.any(CatBreedDto.class))).thenReturn(catBreed);
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);
        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenReturn(catBreedDto);
        CatBreedDto result = catBreedServiceImplementation.createCatBreed(catBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }

    @Test
    public void updateCatBreedTest() {
        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(catBreed));
        when(catBreedMapper.convertToEntity(Mockito.any(CatBreedDto.class))).thenReturn(catBreed);
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);
        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenReturn(catBreedDto);
        CatBreedDto result = catBreedServiceImplementation.updateCatBreed(catBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }

    @Test
    public void getBreedByIdTest() {
        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenReturn(catBreedDto);
        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(catBreed));

        CatBreedDto result = catBreedServiceImplementation.getBreedById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void deleteCatBreedTest() {
        CatBreed catBreed = new CatBreed();
        catBreed.setId(1L);

        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(catBreed));
        Boolean result = catBreedServiceImplementation.deleteCatBreed(1L);
        assertTrue(result);
        verify(catBreedRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllCatBreedsTest() {
        List<CatBreed> catBreedList = Arrays.asList(new CatBreed(), new CatBreed());
        Page<CatBreed> catBreedPage = new PageImpl<>(catBreedList);

        when(catBreedRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(catBreedPage);

        Map<String, Object> result = catBreedServiceImplementation.getAllCatBreeds(0, 10);

        assertNotNull(result);
        assertEquals(2, ((List<?>) result.get("catBreeds")).size());
        assertEquals(1, result.get("totalPages"));
        assertEquals(2L, result.get("totalElements"));
    }
    @Test
    public void getBreedEntityByIdTest() {
        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(catBreed));

        CatBreed result = catBreedServiceImplementation.getBreedEntityById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }
    @Test
    public void deleteCatBreedNotFoundTest() {
        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundElementException.class, () -> {
            catBreedServiceImplementation.deleteCatBreed(1L);
        });
    }
    @Test
    public void createCatBreedBlankNameTest() {
        CatBreedDto catBreedDto = new CatBreedDto();
        catBreedDto.setName("");

        assertThrows(InvalidInputException.class, () -> {
            catBreedServiceImplementation.createCatBreed(catBreedDto);
        });
    }

    @Test
    public void updateCatBreedInvalidIdTest() {
        CatBreedDto catBreedDto = new CatBreedDto();
        catBreedDto.setId(0L);

        assertThrows(InvalidInputException.class, () -> {
            catBreedServiceImplementation.updateCatBreed(catBreedDto);
        });
    }

    @Test
    public void updateCatBreedBlankNameTest() {
        CatBreedDto catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("");

        assertThrows(BlankInputException.class, () -> {
            catBreedServiceImplementation.updateCatBreed(catBreedDto);
        });
    }

    @Test
    public void updateCatBreedNotFoundTest() {
        CatBreedDto catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Golden Retriever");

        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            catBreedServiceImplementation.updateCatBreed(catBreedDto);
        });
    }

    @Test
    public void deleteCatBreedInvalidIdTest() {
        assertThrows(InvalidInputException.class, () -> {
            catBreedServiceImplementation.deleteCatBreed(0L);
        });
    }

    @Test
    public void getAllCatBreedsLimitListsTest() {
        CatBreed catBreed = new CatBreed();
        catBreed.setAdoptionCenters(Arrays.asList(new AdoptionCenter(), new AdoptionCenter()));
        catBreed.setHealthIssues(Arrays.asList(new HealthIssue(), new HealthIssue()));
        catBreed.setNutritionGuides(Arrays.asList(new NutritionGuide(), new NutritionGuide()));
        catBreed.setUsers(Arrays.asList(new User(), new User()));
        catBreed.setTrainingGuides(Arrays.asList(new TrainingGuide(), new TrainingGuide()));
        catBreed.setBehaviorGuides(Arrays.asList(new BehaviorGuide(), new BehaviorGuide()));
        catBreed.setCareTips(Arrays.asList(new CareTip(), new CareTip()));
        catBreed.setGroomingGuides(Arrays.asList(new GroomingGuide(), new GroomingGuide()));

        Page<CatBreed> catBreedPage = new PageImpl<>(Arrays.asList(catBreed));

        when(catBreedRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(catBreedPage);

        Map<String, Object> result = catBreedServiceImplementation.getAllCatBreeds(0, 10);

        assertNotNull(result);
        assertEquals(1, ((List<?>) result.get("catBreeds")).size());
        assertEquals(1, result.get("totalPages"));
        assertEquals(1L, result.get("totalElements"));
    }
    @Test
    public void getBreedByIdInvalidIdTest() {
        assertThrows(InvalidInputException.class, () -> {
            catBreedServiceImplementation.getBreedById(0L);
        });
    }

    @Test
    public void getBreedByIdNotFoundTest() {
        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundElementException.class, () -> {
            catBreedServiceImplementation.getBreedById(1L);
        });
    }

    @Test
    public void getAllCatBreedsEmptyListTest() {
        Page<CatBreed> catBreedPage = new PageImpl<>(Collections.emptyList());

        when(catBreedRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(catBreedPage);

        Map<String, Object> result = catBreedServiceImplementation.getAllCatBreeds(0, 10);

        assertNotNull(result);
        assertEquals(0, ((List<?>) result.get("catBreeds")).size());
        assertEquals(1, result.get("totalPages"));
        assertEquals(0L, result.get("totalElements"));
    }
    @Test
    public void removeCatBreedReferencesWithNullListsTest() {
        catBreed.setAdoptionCenters(null);
        catBreed.setHealthIssues(null);
        catBreed.setNutritionGuides(null);
        catBreed.setUsers(null);
        catBreed.setTrainingGuides(null);
        catBreed.setBehaviorGuides(null);
        catBreed.setCareTips(null);
        catBreed.setGroomingGuides(null);
        catBreed.setFeedingSchedule(null);

        when(catBreedRepository.findById(1L)).thenReturn(Optional.of(catBreed));

        catBreedServiceImplementation.deleteCatBreed(1L);

        verify(catBreedRepository, times(1)).deleteById(1L);
    }
    @Test
    public void deleteCatBreedWithReferencesTest() {
        AdoptionCenter adoptionCenter = new AdoptionCenter();
        adoptionCenter.setAvailableCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));

        catBreed.setAdoptionCenters(Collections.singletonList(adoptionCenter));

        when(catBreedRepository.findById(1L)).thenReturn(Optional.of(catBreed));

        catBreedServiceImplementation.deleteCatBreed(1L);

        assertTrue(adoptionCenter.getAvailableCatBreeds().isEmpty());
        verify(catBreedRepository, times(1)).deleteById(1L);
    }
    @Test
    public void createCatBreedMappingExceptionTest() {
        when(catBreedMapper.convertToEntity(Mockito.any(CatBreedDto.class))).thenThrow(new RuntimeException("Mapping error"));

        assertThrows(RuntimeException.class, () -> {
            catBreedServiceImplementation.createCatBreed(catBreedDto);
        });
    }
    @Test
    public void createCatBreedSetsDatesCorrectlyTest() {
        when(catBreedMapper.convertToEntity(Mockito.any(CatBreedDto.class))).thenAnswer(invocation -> {
            CatBreedDto dto = invocation.getArgument(0);
            CatBreed breed = new CatBreed();
            breed.setName(dto.getName());
            breed.setCreatedDate(LocalDate.now());
            breed.setModifiedDate(LocalDate.now());
            return breed;
        });
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenAnswer(invocation -> {
            CatBreed breed = invocation.getArgument(0);
            breed.setId(1L);
            return breed;
        });
        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenAnswer(invocation -> {
            CatBreed breed = invocation.getArgument(0);
            CatBreedDto dto = new CatBreedDto();
            dto.setId(breed.getId());
            dto.setName(breed.getName());
            dto.setCreatedDate(breed.getCreatedDate());
            dto.setModifiedDate(breed.getModifiedDate());
            return dto;
        });

        CatBreedDto result = catBreedServiceImplementation.createCatBreed(catBreedDto);

        assertNotNull(result);
        assertEquals(LocalDate.now(), result.getCreatedDate());
        assertEquals(LocalDate.now(), result.getModifiedDate());
    }@Test
    public void removeCatBreedReferencesWithAllListsPopulated() {
        AdoptionCenter adoptionCenter = new AdoptionCenter();
        adoptionCenter.setAvailableCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setAdoptionCenters(Collections.singletonList(adoptionCenter));

        HealthIssue healthIssue = new HealthIssue();
        healthIssue.setSuitableCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setHealthIssues(Collections.singletonList(healthIssue));

        NutritionGuide nutritionGuide = new NutritionGuide();
        nutritionGuide.setRecommendedCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setNutritionGuides(Collections.singletonList(nutritionGuide));


        User user = new User();
        user.setFavoriteCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setUsers(Collections.singletonList(user));

        TrainingGuide trainingGuide = new TrainingGuide();
        trainingGuide.setCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setTrainingGuides(Collections.singletonList(trainingGuide));

        BehaviorGuide behaviorGuide = new BehaviorGuide();
        behaviorGuide.setSuitableCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setBehaviorGuides(Collections.singletonList(behaviorGuide));

        CareTip careTip = new CareTip();
        careTip.setRelevantCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setCareTips(Collections.singletonList(careTip));

        GroomingGuide groomingGuide = new GroomingGuide();
        groomingGuide.setSuitableCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setGroomingGuides(Collections.singletonList(groomingGuide));

        FeedingSchedule feedingSchedule = new FeedingSchedule();
        feedingSchedule.setCatBreeds(new ArrayList<>(Collections.singletonList(catBreed)));
        catBreed.setFeedingSchedule(feedingSchedule);

        when(catBreedRepository.findById(1L)).thenReturn(Optional.of(catBreed));


        catBreedServiceImplementation.deleteCatBreed(1L);

        assertTrue(adoptionCenter.getAvailableCatBreeds().isEmpty());
        assertTrue(healthIssue.getSuitableCatBreeds().isEmpty());
        assertTrue(nutritionGuide.getRecommendedCatBreeds().isEmpty());
        assertTrue(user.getFavoriteCatBreeds().isEmpty());
        assertTrue(trainingGuide.getCatBreeds().isEmpty());
        assertTrue(behaviorGuide.getSuitableCatBreeds().isEmpty());
        assertTrue(careTip.getRelevantCatBreeds().isEmpty());
        assertTrue(groomingGuide.getSuitableCatBreeds().isEmpty());
        assertTrue(feedingSchedule.getCatBreeds().isEmpty());
    }


    @Test
    public void removeCatBreedReferencesWithAllNullLists() {
        catBreed.setAdoptionCenters(null);
        catBreed.setHealthIssues(null);
        catBreed.setNutritionGuides(null);
        catBreed.setUsers(null);
        catBreed.setTrainingGuides(null);
        catBreed.setBehaviorGuides(null);
        catBreed.setCareTips(null);
        catBreed.setGroomingGuides(null);
        catBreed.setFeedingSchedule(null);

        when(catBreedRepository.findById(1L)).thenReturn(Optional.of(catBreed));

        catBreedServiceImplementation.deleteCatBreed(1L);

        verify(catBreedRepository, times(1)).deleteById(1L);
    }
}

