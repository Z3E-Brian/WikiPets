package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Dto.CareTipDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CareTip;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.HealthIssue;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HealthIssueServiceImplementationTest {
    @Mock
    private HealthIssueRepository healthIssueRepository;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<HealthIssue, HealthIssueDto> healthIssueMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private DogBreedService breedService;
    @Mock
    private CatBreedService catBreedService;
    @InjectMocks
    private HealthIssueServiceImplementation healthIssueServiceImplementation;
    HealthIssue healthIssue;
    HealthIssueDto healthIssueDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        healthIssue = new HealthIssue();
        healthIssue.setId(1L);
        healthIssue.setName("Test");
        healthIssue.setDescription("Test");
        healthIssue.setSymptoms("Test");
        healthIssue.setCreatedDate(LocalDate.now());
        healthIssue.setModifiedDate(LocalDate.now());
        healthIssueDto = new HealthIssueDto();
        healthIssueDto.setId(1L);
        healthIssueDto.setName("Test");
        healthIssueDto.setDescription("Test");
        healthIssueDto.setSymptoms("Test");
        healthIssueDto.setCreatedDate(LocalDate.now());
        healthIssueDto.setModifiedDate(LocalDate.now());
        when(mapperFactory.createMapper(HealthIssue.class, HealthIssueDto.class)).thenReturn(healthIssueMapper);
        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);
        when(mapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        healthIssueServiceImplementation = new HealthIssueServiceImplementation(healthIssueRepository, mapperFactory, breedService, catBreedService);
    }

    @Test
    public void getHealthIssueByIdTest() {
        HealthIssue healthIssue = new HealthIssue();
        when(healthIssueRepository.findById(anyLong())).thenReturn(Optional.of(healthIssue));
        when(healthIssueMapper.convertToDTO(any(HealthIssue.class))).thenReturn(new HealthIssueDto());

        HealthIssueDto healthIssueDto = healthIssueServiceImplementation.getHealthIssueById(1L);

        assertNotNull(healthIssueDto);
        verify(healthIssueRepository, times(1)).findById(anyLong());
    }


    @Test
    void getHealthIssueByIdNotFoundTest() {

        when(healthIssueRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> healthIssueServiceImplementation.getHealthIssueById(1L));
        assertEquals("Health Issue Not Found with id: 1", exception.getMessage());
        verify(healthIssueRepository, times(1)).findById(1L);
    }

    @Test
    public void createHealthIssueTest() {
        when(healthIssueMapper.convertToEntity(healthIssueDto)).thenReturn(healthIssue);
        when(healthIssueRepository.save(healthIssue)).thenReturn(healthIssue);
        when(healthIssueMapper.convertToDTO(healthIssue)).thenReturn(healthIssueDto);

        HealthIssueDto result = healthIssueServiceImplementation.createHealthIssue(healthIssueDto);

        assertNotNull(result);
        verify(healthIssueRepository, times(1)).save(any(HealthIssue.class));
    }

    @Test
    public void deleteHealthIssueTest() {
        when(healthIssueRepository.findById(1L)).thenReturn(Optional.of(healthIssue));

        boolean result = healthIssueServiceImplementation.deleteHealthIssue(1L);
        assertTrue(result);
        verify(healthIssueRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateHealthIssueTest() {
        when(healthIssueRepository.findById(1L)).thenReturn(Optional.of(healthIssue));
        when(healthIssueMapper.convertToDTO(any(HealthIssue.class))).thenReturn(healthIssueDto);
        when(healthIssueRepository.save(any(HealthIssue.class))).thenReturn(healthIssue);
        when(healthIssueMapper.convertToEntity(any(HealthIssueDto.class))).thenReturn(healthIssue);

        HealthIssueDto result = healthIssueServiceImplementation.updateHealthIssue(healthIssueDto);

        assertNotNull(result);
        verify(healthIssueRepository, times(1)).save(any(HealthIssue.class));
    }

    @Test
    void addSuitableDogBreedInHealthIssueTest() {
        DogBreed dogBreed = new DogBreed();
        dogBreed.setId(1L);
        when(healthIssueRepository.findById(1L)).thenReturn(Optional.of(healthIssue));
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(healthIssueMapper.convertToDTO(healthIssue)).thenReturn(healthIssueDto);
        when(healthIssueRepository.save(healthIssue)).thenReturn(healthIssue);

        HealthIssueDto result = healthIssueServiceImplementation.addSuitableDogBreed(1L, 1L);

        assertNotNull(result);
        verify(healthIssueRepository, times(1)).findById(1L);
        verify(breedService, times(1)).getBreedById(1L);
        verify(healthIssueRepository, times(1)).save(healthIssue);
    }

    @Test
    void addSuitableCatBreedInHealthIssueTest() {
        CatBreed catBreed = new CatBreed();
        catBreed.setId(1L);
        when(healthIssueRepository.findById(1L)).thenReturn(Optional.of(healthIssue));
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(healthIssueMapper.convertToDTO(healthIssue)).thenReturn(healthIssueDto);
        when(healthIssueRepository.save(healthIssue)).thenReturn(healthIssue);

        HealthIssueDto result = healthIssueServiceImplementation.addSuitableCatBreed(1L, 1L);

        assertNotNull(result);
        verify(healthIssueRepository, times(1)).findById(1L);
        verify(catBreedService, times(1)).getBreedById(1L);
        verify(healthIssueRepository, times(1)).save(healthIssue);
    }

    @Test
    void getAllHealthIssuesTest() {
        List<HealthIssue> healthIssueList = new ArrayList<>();
        healthIssue.setSuitableCatBreeds(Arrays.asList(new CatBreed(), new CatBreed()));
        healthIssue.setSuitableDogBreeds(Arrays.asList(new DogBreed(), new DogBreed()));
        healthIssueList.add(healthIssue);
        Page<HealthIssue> healthIssues = new PageImpl<>(healthIssueList);
        when(healthIssueRepository.findAll(any(PageRequest.class))).thenReturn(healthIssues);
        Map<String, Object> result = healthIssueServiceImplementation.getAllHealthIssues(0, 1);

        assertNotNull(result);
        assertEquals(1, ((List<?>) result.get("healthIssues")).size());
        verify(healthIssueRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testGetHealthIssueByIdNotFound() {
        when(healthIssueRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            healthIssueServiceImplementation.getHealthIssueById(1L);
        });

        verify(healthIssueRepository, times(1)).findById(anyLong());
    }

    @Test
    void createHealthIssueBlankInputTest() {
        HealthIssueDto healthIssueDto1 = new HealthIssueDto();
        healthIssueDto1.setDescription("");
        healthIssueDto1.setName("");

        assertThrows(BlankInputException.class, () -> {
            healthIssueServiceImplementation.createHealthIssue(healthIssueDto1);
        });
    }

    @Test
    void deleteHealthIssueTests() {
        CatBreed catBreed = new CatBreed();
        catBreed.setHealthIssues(new ArrayList<>());
        DogBreed dogBreed = new DogBreed();
        dogBreed.setHealthIssues(new ArrayList<>());

        HealthIssue healthIssue = new HealthIssue();
        healthIssue.setSuitableCatBreeds(new ArrayList<>(List.of(catBreed)));
        healthIssue.setSuitableDogBreeds(new ArrayList<>(List.of(dogBreed)));

        when(healthIssueRepository.findById(anyLong())).thenReturn(Optional.of(healthIssue));

        Boolean result = healthIssueServiceImplementation.deleteHealthIssue(1L);

        assertTrue(result);
        verify(healthIssueRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void updateHealthIssueInvalidIdTest() {
        healthIssueDto.setId(0L);

        assertThrows(InvalidInputException.class, () -> healthIssueServiceImplementation.updateHealthIssue(healthIssueDto));
    }

    @Test
    void updateHealthIssueBlankFieldsTest() {
        healthIssueDto.setId(1L);
        healthIssueDto.setName("");
        healthIssueDto.setDescription("");

        assertThrows(BlankInputException.class, () -> healthIssueServiceImplementation.updateHealthIssue(healthIssueDto));
    }

    @Test
    void addCatBreedInHealthIssueNotFoundTest() {
        when(healthIssueRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> healthIssueServiceImplementation.addSuitableCatBreed(1L, 1L));

        assertEquals("Health Issue Not Found with id: 1", exception.getMessage());
        verify(healthIssueRepository, times(1)).findById(1L);
    }

    @Test
    void addDogBreedInHealthIssueNotFoundTest() {
        when(healthIssueRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> healthIssueServiceImplementation.addSuitableDogBreed(1L, 1L));

        assertEquals("Health Issue Not Found with id: 1", exception.getMessage());
        verify(healthIssueRepository, times(1)).findById(1L);
    }

    @Test
    void createHealthIssueSetsDatesCorrectlyTest() {
        when(healthIssueMapper.convertToEntity(healthIssueDto)).thenReturn(healthIssue);
        when(healthIssueRepository.save(healthIssue)).thenReturn(healthIssue);
        when(healthIssueMapper.convertToDTO(healthIssue)).thenReturn(healthIssueDto);

        HealthIssueDto result = healthIssueServiceImplementation.createHealthIssue(healthIssueDto);

        assertNotNull(result);
        assertEquals(LocalDate.now(), healthIssue.getCreatedDate());
        assertEquals(LocalDate.now(), healthIssue.getModifiedDate());
        verify(healthIssueRepository, times(1)).save(healthIssue);
    }

    @Test
    void deleteHealthIssueNotFoundTest() {
        when(healthIssueRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundElementException.class, () -> healthIssueServiceImplementation.deleteHealthIssue(1L));

        assertEquals("Health Issue Not Found with id: 1", exception.getMessage());
        verify(healthIssueRepository, times(1)).findById(1L);
    }

    @Test
    void getAllHealthIssuesMultiplePagesTest() {
        HealthIssue healthIssue1 = new HealthIssue();
        healthIssue1.setSuitableCatBreeds(new ArrayList<>());
        healthIssue1.setSuitableDogBreeds(new ArrayList<>());

        HealthIssue healthIssue2 = new HealthIssue();
        healthIssue2.setSuitableCatBreeds(new ArrayList<>());
        healthIssue2.setSuitableDogBreeds(new ArrayList<>());

        List<HealthIssue> healthIssueList = Arrays.asList(healthIssue1, healthIssue2);
        Page<HealthIssue> page = new PageImpl<>(healthIssueList);

        when(healthIssueRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Map<String, Object> result = healthIssueServiceImplementation.getAllHealthIssues(0, 10);

        assertEquals(2, ((List<?>) result.get("healthIssues")).size());
        verify(healthIssueRepository, times(1)).findAll(any(PageRequest.class));
    }
}
