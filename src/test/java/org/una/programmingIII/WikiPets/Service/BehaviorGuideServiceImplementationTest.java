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
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BehaviorGuideServiceImplementationTest {

    @Mock
    private BehaviorGuideRepository behaviorGuideRepository;
    @Mock
    private GenericMapperFactory genericMapperFactory;
    @Mock
    private GenericMapper<BehaviorGuide, BehaviorGuideDto> behaviorGuideMapper;
    @Mock
    private DogBreedService dogBreedService;
    @Mock
    private CatBreedService catBreedService;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;

    @InjectMocks
    private BehaviorGuideServiceImplementation behaviorGuideServiceImplementation;

    private BehaviorGuide behaviorGuide;
    private BehaviorGuideDto behaviorGuideDto;
    private DogBreed dogBreed;
    private CatBreed catBreed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        behaviorGuideServiceImplementation = new BehaviorGuideServiceImplementation(
                behaviorGuideRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService
        );
        ArrayList<BehaviorGuide> behaviorGuides = new ArrayList<>();

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
        ArrayList<DogBreed> dogList = new ArrayList<DogBreed>();
        dogList.add(dogBreed);
        ArrayList<CatBreed> catList = new ArrayList<CatBreed>();
        catList.add(catBreed);


        behaviorGuide = new BehaviorGuide();
        behaviorGuide.setId(1L);
        behaviorGuide.setTitle("behaviorGuide 1");
        behaviorGuide.setBehavioralIssues("behavioralIssues 1");
        behaviorGuide.setContent("content 1");
        behaviorGuide.setSolutions("solutions 1");


        behaviorGuide.setSuitableDogBreeds(dogList);
        behaviorGuide.setSuitableCatBreeds(catList);

        behaviorGuideDto = new BehaviorGuideDto();
        behaviorGuideDto.setId(1L);
        behaviorGuideDto.setTitle("behaviorGuide 1");
        behaviorGuideDto.setBehavioralIssues("behavioralIssues 1");
        behaviorGuideDto.setContent("content 1");
        behaviorGuideDto.setSolutions("solutions 1");


        catBreed.setBehaviorGuides(behaviorGuides);
        dogBreed.setBehaviorGuides(behaviorGuides);
        behaviorGuideDto.setSuitableCatBreeds(new ArrayList<CatBreedDto>());
        behaviorGuideDto.setSuitableDogBreeds(new ArrayList<DogBreedDto>());

        when(genericMapperFactory.createMapper(BehaviorGuide.class, BehaviorGuideDto.class)).thenReturn(behaviorGuideMapper);
        when(genericMapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        when(genericMapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        behaviorGuideServiceImplementation = new BehaviorGuideServiceImplementation(
                behaviorGuideRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService);
    }

    @Test
    void getBehaviorGuideById_Success() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.getBehaviorGuideById(1L);

        assertNotNull(result);
        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).findById(anyLong());
    }

    @Test
    void getBehaviorGuideById_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.getBehaviorGuideById(null));
    }

    @Test
    void getBehaviorGuideById_NotFound() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> behaviorGuideServiceImplementation.getBehaviorGuideById(1L));
    }

    @Test
    void createBehaviorGuide_Success() {
        when(behaviorGuideMapper.convertToEntity(behaviorGuideDto)).thenReturn(behaviorGuide);
        when(behaviorGuideRepository.save(behaviorGuide)).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(behaviorGuide)).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.createBehaviorGuide(behaviorGuideDto);

        assertNotNull(result);
        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void createBehaviorGuide_BlankInput() {
        behaviorGuideDto.setTitle("");
        assertThrows(BlankInputException.class, () -> behaviorGuideServiceImplementation.createBehaviorGuide(behaviorGuideDto));
    }

    @Test
    void deleteBehaviorGuide() {
        when(behaviorGuideRepository.existsById(anyLong())).thenReturn(true);
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));

        boolean result = behaviorGuideServiceImplementation.deleteBehaviorGuide(1L);

        assertTrue(result);
    }

    @Test
    void deleteBehaviorGuide_NotFound() {
        when(behaviorGuideRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundElementException.class, () -> behaviorGuideServiceImplementation.deleteBehaviorGuide(1L));
    }

    @Test
    void updateBehaviorGuide_Success() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(behaviorGuideMapper.convertToEntity(behaviorGuideDto)).thenReturn(behaviorGuide);
        when(behaviorGuideRepository.save(behaviorGuide)).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(behaviorGuide)).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.updateBehaviorGuide(behaviorGuideDto);

        assertNotNull(result);
        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(behaviorGuide);
    }

    @Test
    void updateBehaviorGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.updateBehaviorGuide(null));
    }

    @Test
    void deleteBehaviorGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.deleteBehaviorGuide(null));
    }

    @Test
    void deleteCatBreedFromBehaviorGuide_InvalidInput() {
        assertThrows(InvalidInputException.class, () -> behaviorGuideServiceImplementation.deleteSuitableCatBreedFromBehaviorGuide(0L, 0L));
    }

    @Test
    void updateBehaviorGuide_InvalidInput() {
        behaviorGuideDto.setId(0L);
        assertThrows(RuntimeException.class, () -> behaviorGuideServiceImplementation.updateBehaviorGuide(behaviorGuideDto));
    }

    @Test
    void updateBehaviorGuide_BlankInput() {
        behaviorGuideDto.setTitle("");
        assertThrows(BlankInputException.class, () -> behaviorGuideServiceImplementation.updateBehaviorGuide(behaviorGuideDto));
    }

    @Test
    void updateBehaviorGuide_NotFound() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> behaviorGuideServiceImplementation.updateBehaviorGuide(behaviorGuideDto));
    }

    @Test
    void getAllBehaviorGuides() {
        Page<BehaviorGuide> behaviorGuidePage = new PageImpl<>(List.of(behaviorGuide));
        when(behaviorGuideRepository.findAll(any(PageRequest.class))).thenReturn(behaviorGuidePage);

        Map<String, Object> result = behaviorGuideServiceImplementation.getAllBehaviorGuides(0, 5);

        assertNotNull(result);
        assertTrue(result.containsKey("behaviorGuides"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));

        verify(behaviorGuideRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void addDogBreedInBehaviorGuide() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.addSuitableDogBreedToBehaviorGuide(1L, 1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void addCatBreedInBehaviorGuide() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.addSuitableCatBreedToBehaviorGuide(1L, 1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void deleteDogBreedInBehaviorGuide() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.deleteSuitableDogBreedFromBehaviorGuide(1L, 1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void deleteCatBreedInBehaviorGuide() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.deleteSuitableCatBreedFromBehaviorGuide(1L, 1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void getAvailableDogBreeds() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(dogBreedMapper.convertToDTO(any(DogBreed.class))).thenReturn(new DogBreedDto());

        behaviorGuide.getSuitableDogBreeds().add(dogBreed);
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Golden Retriever");

        when(dogBreedMapper.convertToDTO(any(DogBreed.class))).thenReturn(dogBreedDto);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        DogBreedDto result = behaviorGuideServiceImplementation.getBehaviorSuitableDogBreeds(1L).getFirst();
        assertEquals(dogBreedDto, result);
        verify(behaviorGuideRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAvailableCatBreeds() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(catBreedMapper.convertToDTO(any(CatBreed.class))).thenReturn(new CatBreedDto());

        behaviorGuide.getSuitableCatBreeds().add(catBreed);
        CatBreedDto catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Siamese");

        when(catBreedMapper.convertToDTO(any(CatBreed.class))).thenReturn(catBreedDto);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        CatBreedDto result = behaviorGuideServiceImplementation.getBehaviorSuitableCatBreeds(1L).getFirst();
        assertEquals(catBreedDto, result);
        verify(behaviorGuideRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAvailableDogBreeds_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.getBehaviorSuitableDogBreeds(null));
    }

    @Test
    void getAvailableCatBreeds_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.getBehaviorSuitableCatBreeds(null));
    }

    @Test
    void getAvailableDogBreeds_NotFound() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> behaviorGuideServiceImplementation.getBehaviorSuitableDogBreeds(1L));
    }

    @Test
    void getAvailableCatBreeds_NotFound() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> behaviorGuideServiceImplementation.getBehaviorSuitableCatBreeds(1L));
    }

    @Test
    void addDogBreedToBehaviorGuide() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.addSuitableDogBreedToBehaviorGuide(1L, 1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void addCatBreedToBehaviorGuide() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.addSuitableCatBreedToBehaviorGuide(1L, 1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void addDogBreedToBehaviorGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.addSuitableDogBreedToBehaviorGuide(null, null));
    }

    @Test
    void addCatBreedToBehaviorGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.addSuitableCatBreedToBehaviorGuide(null, null));
    }

    @Test
    void deleteCatBreedFromBehaviorGuide() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.deleteSuitableCatBreedFromBehaviorGuide(1L, 1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void deleteDogBreedFromBehaviorGuide() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.deleteSuitableDogBreedFromBehaviorGuide(1L, 1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    void deleteDogBreedFromBehaviorGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.deleteSuitableDogBreedFromBehaviorGuide(null, null));
    }

    @Test
    void deleteCatBreedFromBehaviorGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.deleteSuitableCatBreedFromBehaviorGuide(null, null));
    }

    @Test
    void addDogBreedInBehaviorGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.addSuitableDogBreedToBehaviorGuide(null, null));
    }

    @Test
    void addCatBreedInBehaviorGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> behaviorGuideServiceImplementation.addSuitableCatBreedToBehaviorGuide(null, null));
    }

    @Test
    void createBehaviorGuide_BlankName() {
    behaviorGuideDto.setTitle("");
        assertThrows(BlankInputException.class, () -> behaviorGuideServiceImplementation.createBehaviorGuide(behaviorGuideDto));
    }

    @Test
    void createBehaviorGuide_BlankLocation() {
        behaviorGuideDto.setBehavioralIssues("");
        assertThrows(BlankInputException.class, () -> behaviorGuideServiceImplementation.createBehaviorGuide(behaviorGuideDto));
    }

    @Test
    void deleteBehaviorGuide_ValidId() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        doNothing().when(behaviorGuideRepository).deleteById(anyLong());

        Boolean result = behaviorGuideServiceImplementation.deleteBehaviorGuide(1L);

        assertTrue(result);
        verify(behaviorGuideRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getBehaviorGuideById_ValidId() {
        when(behaviorGuideRepository.findById(anyLong())).thenReturn(Optional.of(behaviorGuide));
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto result = behaviorGuideServiceImplementation.getBehaviorGuideById(1L);

        assertEquals(behaviorGuideDto, result);
        verify(behaviorGuideRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllBehaviorGuides_ValidPage() {
        Page<BehaviorGuide> page = new PageImpl<>(List.of(behaviorGuide));
        when(behaviorGuideRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(behaviorGuideMapper.convertToDTO(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        Map<String, Object> result = behaviorGuideServiceImplementation.getAllBehaviorGuides(0, 10);

        assertTrue(result.containsKey("behaviorGuides"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));
        verify(behaviorGuideRepository, times(1)).findAll(any(PageRequest.class));
    }
}