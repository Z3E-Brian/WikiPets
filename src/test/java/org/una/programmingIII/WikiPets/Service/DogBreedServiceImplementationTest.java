package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DogBreedServiceImplementationTest {
    @InjectMocks
    private DogBreedServiceImplementation dogBreedServiceImplementation;
    @Mock
    private DogBreedRepository dogBreedRepository;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private GenericMapperFactory mapperFactory;


    private DogBreed dogBreed;
    private DogBreedDto dogBreedDto;

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
        dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Golden Retriever");
        dogBreedDto.setOrigin("Scotland");
        dogBreedDto.setSize(3);
        dogBreedDto.setCoat("Long");
        when(mapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        dogBreedServiceImplementation = new DogBreedServiceImplementation(dogBreedRepository, mapperFactory);

    }

    @Test
    public void createDogBreedTest() {
        when(dogBreedMapper.convertToEntity(Mockito.any(DogBreedDto.class))).thenReturn(dogBreed);
        when(dogBreedRepository.save(Mockito.any(DogBreed.class))).thenReturn(dogBreed);
        when(dogBreedMapper.convertToDTO(Mockito.any(DogBreed.class))).thenReturn(dogBreedDto);
        DogBreedDto result = dogBreedServiceImplementation.createDogBreed(dogBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Golden Retriever", result.getName());
    }

    @Test
    public void updateDogBreedTest() {
        when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(dogBreed));
        when(dogBreedMapper.convertToEntity(Mockito.any(DogBreedDto.class))).thenReturn(dogBreed);
        when(dogBreedRepository.save(Mockito.any(DogBreed.class))).thenReturn(dogBreed);
        when(dogBreedMapper.convertToDTO(Mockito.any(DogBreed.class))).thenReturn(dogBreedDto);
        DogBreedDto result = dogBreedServiceImplementation.updateDogBreed(dogBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Golden Retriever", result.getName());
    }

    @Test
    public void getBreedByIdTest() {
        when(dogBreedMapper.convertToDTO(Mockito.any(DogBreed.class))).thenReturn(dogBreedDto);
        when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(dogBreed));

        DogBreedDto result = dogBreedServiceImplementation.getBreedById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

@Test
public void deleteDogBreedTest() {
    DogBreed dogBreed = new DogBreed();
    dogBreed.setId(1L);

    when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(dogBreed));
    Boolean result = dogBreedServiceImplementation.deleteDogBreed(1L);
    assertTrue(result);
    verify(dogBreedRepository, times(1)).deleteById(1L);
}

@Test
public void getAllDogBreedsTest() {
    List<DogBreed> dogBreedList = Arrays.asList(new DogBreed(), new DogBreed());
    Page<DogBreed> dogBreedPage = new PageImpl<>(dogBreedList);

    when(dogBreedRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(dogBreedPage);

    Map<String, Object> result = dogBreedServiceImplementation.getAllDogBreeds(0, 10);

    assertNotNull(result);
    assertEquals(2, ((List<?>) result.get("dogBreeds")).size());
    assertEquals(1, result.get("totalPages"));
    assertEquals(2L, result.get("totalElements"));
}
    @Test
    public void getBreedEntityByIdTest() {
        when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(dogBreed));

        DogBreed result = dogBreedServiceImplementation.getBreedEntityById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Golden Retriever", result.getName());
    }
    @Test
    public void deleteDogBreedNotFoundTest() {
        when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundElementException.class, () -> {
            dogBreedServiceImplementation.deleteDogBreed(1L);
        });
    }
    @Test
    public void createDogBreedBlankNameTest() {
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setName("");

        assertThrows(InvalidInputException.class, () -> {
            dogBreedServiceImplementation.createDogBreed(dogBreedDto);
        });
    }

    @Test
    public void updateDogBreedInvalidIdTest() {
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(0L);

        assertThrows(InvalidInputException.class, () -> {
            dogBreedServiceImplementation.updateDogBreed(dogBreedDto);
        });
    }

    @Test
    public void updateDogBreedBlankNameTest() {
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("");

        assertThrows(BlankInputException.class, () -> {
            dogBreedServiceImplementation.updateDogBreed(dogBreedDto);
        });
    }

    @Test
    public void updateDogBreedNotFoundTest() {
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Golden Retriever");

        when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            dogBreedServiceImplementation.updateDogBreed(dogBreedDto);
        });
    }

    @Test
    public void deleteDogBreedInvalidIdTest() {
        assertThrows(InvalidInputException.class, () -> {
            dogBreedServiceImplementation.deleteDogBreed(0L);
        });
    }

    @Test
    public void getAllDogBreedsLimitListsTest() {
        DogBreed dogBreed = new DogBreed();
        dogBreed.setAdoptionCenters(Arrays.asList(new AdoptionCenter(), new AdoptionCenter()));
        dogBreed.setHealthIssues(Arrays.asList(new HealthIssue(), new HealthIssue()));
        dogBreed.setNutritionGuides(Arrays.asList(new NutritionGuide(), new NutritionGuide()));
        dogBreed.setUsers(Arrays.asList(new User(), new User()));
        dogBreed.setTrainingGuides(Arrays.asList(new TrainingGuide(), new TrainingGuide()));
        dogBreed.setBehaviorGuides(Arrays.asList(new BehaviorGuide(), new BehaviorGuide()));
        dogBreed.setCareTips(Arrays.asList(new CareTip(), new CareTip()));
        dogBreed.setGroomingGuides(Arrays.asList(new GroomingGuide(), new GroomingGuide()));

        Page<DogBreed> dogBreedPage = new PageImpl<>(Arrays.asList(dogBreed));

        when(dogBreedRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(dogBreedPage);

        Map<String, Object> result = dogBreedServiceImplementation.getAllDogBreeds(0, 10);

        assertNotNull(result);
        assertEquals(1, ((List<?>) result.get("dogBreeds")).size());
        assertEquals(1, result.get("totalPages"));
        assertEquals(1L, result.get("totalElements"));
    }
    @Test
    public void getBreedByIdInvalidIdTest() {
        assertThrows(InvalidInputException.class, () -> {
            dogBreedServiceImplementation.getBreedById(0L);
        });
    }

    @Test
    public void getBreedByIdNotFoundTest() {
        when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundElementException.class, () -> {
            dogBreedServiceImplementation.getBreedById(1L);
        });
    }

@Test
public void getAllDogBreedsEmptyListTest() {
    Page<DogBreed> dogBreedPage = new PageImpl<>(Collections.emptyList());

    when(dogBreedRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(dogBreedPage);

    Map<String, Object> result = dogBreedServiceImplementation.getAllDogBreeds(0, 10);

    assertNotNull(result);
    assertEquals(0, ((List<?>) result.get("dogBreeds")).size());
    assertEquals(1, result.get("totalPages"));
    assertEquals(0L, result.get("totalElements"));
}
@Test
public void removeDogBreedReferencesWithNullListsTest() {
    dogBreed.setAdoptionCenters(null);
    dogBreed.setHealthIssues(null);
    dogBreed.setNutritionGuides(null);
    dogBreed.setUsers(null);
    dogBreed.setTrainingGuides(null);
    dogBreed.setBehaviorGuides(null);
    dogBreed.setCareTips(null);
    dogBreed.setGroomingGuides(null);
    dogBreed.setFeedingSchedule(null);

    when(dogBreedRepository.findById(1L)).thenReturn(Optional.of(dogBreed));

    dogBreedServiceImplementation.deleteDogBreed(1L);

    verify(dogBreedRepository, times(1)).deleteById(1L);
}
@Test
public void deleteDogBreedWithReferencesTest() {
    AdoptionCenter adoptionCenter = new AdoptionCenter();
    adoptionCenter.setAvailableDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));

    dogBreed.setAdoptionCenters(Collections.singletonList(adoptionCenter));

    when(dogBreedRepository.findById(1L)).thenReturn(Optional.of(dogBreed));

    dogBreedServiceImplementation.deleteDogBreed(1L);

    assertTrue(adoptionCenter.getAvailableDogBreeds().isEmpty());
    verify(dogBreedRepository, times(1)).deleteById(1L);
}
    @Test
    public void createDogBreedMappingExceptionTest() {
        when(dogBreedMapper.convertToEntity(Mockito.any(DogBreedDto.class))).thenThrow(new RuntimeException("Mapping error"));

        assertThrows(RuntimeException.class, () -> {
            dogBreedServiceImplementation.createDogBreed(dogBreedDto);
        });
    }
   @Test
public void createDogBreedSetsDatesCorrectlyTest() {
    when(dogBreedMapper.convertToEntity(Mockito.any(DogBreedDto.class))).thenAnswer(invocation -> {
        DogBreedDto dto = invocation.getArgument(0);
        DogBreed breed = new DogBreed();
        breed.setName(dto.getName());
        breed.setCreatedDate(LocalDate.now());
        breed.setModifiedDate(LocalDate.now());
        return breed;
    });
    when(dogBreedRepository.save(Mockito.any(DogBreed.class))).thenAnswer(invocation -> {
        DogBreed breed = invocation.getArgument(0);
        breed.setId(1L);
        return breed;
    });
    when(dogBreedMapper.convertToDTO(Mockito.any(DogBreed.class))).thenAnswer(invocation -> {
        DogBreed breed = invocation.getArgument(0);
        DogBreedDto dto = new DogBreedDto();
        dto.setId(breed.getId());
        dto.setName(breed.getName());
        dto.setCreatedDate(breed.getCreatedDate());
        dto.setModifiedDate(breed.getModifiedDate());
        return dto;
    });

    DogBreedDto result = dogBreedServiceImplementation.createDogBreed(dogBreedDto);

    assertNotNull(result);
    assertEquals(LocalDate.now(), result.getCreatedDate());
    assertEquals(LocalDate.now(), result.getModifiedDate());
}@Test
    public void removeDogBreedReferencesWithAllListsPopulated() {
        AdoptionCenter adoptionCenter = new AdoptionCenter();
        adoptionCenter.setAvailableDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setAdoptionCenters(Collections.singletonList(adoptionCenter));

        HealthIssue healthIssue = new HealthIssue();
        healthIssue.setSuitableDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setHealthIssues(Collections.singletonList(healthIssue));

        NutritionGuide nutritionGuide = new NutritionGuide();
        nutritionGuide.setRecommendedDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setNutritionGuides(Collections.singletonList(nutritionGuide));


        User user = new User();
        user.setFavoriteDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setUsers(Collections.singletonList(user));

        TrainingGuide trainingGuide = new TrainingGuide();
        trainingGuide.setDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setTrainingGuides(Collections.singletonList(trainingGuide));

        BehaviorGuide behaviorGuide = new BehaviorGuide();
        behaviorGuide.setSuitableDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setBehaviorGuides(Collections.singletonList(behaviorGuide));

        CareTip careTip = new CareTip();
        careTip.setRelevantDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setCareTips(Collections.singletonList(careTip));

        GroomingGuide groomingGuide = new GroomingGuide();
        groomingGuide.setSuitableDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setGroomingGuides(Collections.singletonList(groomingGuide));

        FeedingSchedule feedingSchedule = new FeedingSchedule();
        feedingSchedule.setDogBreeds(new ArrayList<>(Collections.singletonList(dogBreed)));
        dogBreed.setFeedingSchedule(feedingSchedule);

        when(dogBreedRepository.findById(1L)).thenReturn(Optional.of(dogBreed));


        dogBreedServiceImplementation.deleteDogBreed(1L);

        assertTrue(adoptionCenter.getAvailableDogBreeds().isEmpty());
        assertTrue(healthIssue.getSuitableDogBreeds().isEmpty());
        assertTrue(nutritionGuide.getRecommendedDogBreeds().isEmpty());
        assertTrue(user.getFavoriteDogBreeds().isEmpty());
        assertTrue(trainingGuide.getDogBreeds().isEmpty());
        assertTrue(behaviorGuide.getSuitableDogBreeds().isEmpty());
        assertTrue(careTip.getRelevantDogBreeds().isEmpty());
        assertTrue(groomingGuide.getSuitableDogBreeds().isEmpty());
        assertTrue(feedingSchedule.getDogBreeds().isEmpty());
    }


    @Test
public void removeDogBreedReferencesWithAllNullLists() {
    dogBreed.setAdoptionCenters(null);
    dogBreed.setHealthIssues(null);
    dogBreed.setNutritionGuides(null);
    dogBreed.setUsers(null);
    dogBreed.setTrainingGuides(null);
    dogBreed.setBehaviorGuides(null);
    dogBreed.setCareTips(null);
    dogBreed.setGroomingGuides(null);
    dogBreed.setFeedingSchedule(null);

    when(dogBreedRepository.findById(1L)).thenReturn(Optional.of(dogBreed));

    dogBreedServiceImplementation.deleteDogBreed(1L);

    verify(dogBreedRepository, times(1)).deleteById(1L);
}

}
