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
import org.una.programmingIII.WikiPets.Repository.GroomingGuideRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GroomingGuideServiceImplementationTest {

    @Mock
    private GroomingGuideRepository groomingGuideRepository;
    @Mock
    private GenericMapperFactory genericMapperFactory;
    @Mock
    private GenericMapper<GroomingGuide, GroomingGuideDto> groomingGuideMapper;
    @Mock
    private DogBreedService dogBreedService;
    @Mock
    private CatBreedService catBreedService;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;

    @InjectMocks
    private GroomingGuideServiceImplementation groomingGuideServiceImplementation;

    private GroomingGuide groomingGuide;
    private GroomingGuideDto groomingGuideDto;
    private DogBreed dogBreed;
    private CatBreed catBreed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        groomingGuideServiceImplementation = new GroomingGuideServiceImplementation(
                groomingGuideRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService
        );
        ArrayList<GroomingGuide> groomingGuides = new ArrayList<>();

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
        ArrayList<DogBreed> dogList = new ArrayList<>();
        dogList.add(dogBreed);
        ArrayList<CatBreed> catList = new ArrayList<>();
        catList.add(catBreed);


        groomingGuide = new GroomingGuide();
        groomingGuide.setId(1L);
        groomingGuide.setContent("content 1");
        groomingGuide.setSteps("groomingGuide 1");
        groomingGuide.setToolsNeeded("toolsNeeded 1");


        groomingGuide.setSuitableDogBreeds(dogList);
        groomingGuide.setSuitableCatBreeds(catList);

        groomingGuideDto = new GroomingGuideDto();
        groomingGuideDto.setId(1L);
        groomingGuideDto.setContent("content 1");
        groomingGuideDto.setSteps("groomingGuide 1");
        groomingGuideDto.setToolsNeeded("toolsNeeded 1");


        catBreed.setGroomingGuides(groomingGuides);
        dogBreed.setGroomingGuides(groomingGuides);
        groomingGuideDto.setSuitableCatBreeds(new ArrayList<>());
        groomingGuideDto.setSuitableDogBreeds(new ArrayList<>());

        when(genericMapperFactory.createMapper(GroomingGuide.class, GroomingGuideDto.class)).thenReturn(groomingGuideMapper);
        when(genericMapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        when(genericMapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        groomingGuideServiceImplementation = new GroomingGuideServiceImplementation(
                groomingGuideRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService);
    }

    @Test
    void getGroomingGuideById_Success() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.getGroomingGuideById(1L);

        assertNotNull(result);
        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).findById(anyLong());
    }

    @Test
    void getGroomingGuideById_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.getGroomingGuideById(null));
    }

    @Test
    void getGroomingGuideById_NotFound() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> groomingGuideServiceImplementation.getGroomingGuideById(1L));
    }

    @Test
    void createGroomingGuide_Success() {
        when(groomingGuideMapper.convertToEntity(groomingGuideDto)).thenReturn(groomingGuide);
        when(groomingGuideRepository.save(groomingGuide)).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(groomingGuide)).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.createGroomingGuide(groomingGuideDto);

        assertNotNull(result);
        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void createGroomingGuide_BlankInput() {
        groomingGuideDto.setContent("");
        assertThrows(BlankInputException.class, () -> groomingGuideServiceImplementation.createGroomingGuide(groomingGuideDto));
    }

    @Test
    void deleteGroomingGuide() {
        when(groomingGuideRepository.existsById(anyLong())).thenReturn(true);
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));

        boolean result = groomingGuideServiceImplementation.deleteGroomingGuide(1L);

        assertTrue(result);
    }

    @Test
    void deleteGroomingGuide_NotFound() {
        when(groomingGuideRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundElementException.class, () -> groomingGuideServiceImplementation.deleteGroomingGuide(1L));
    }

    @Test
    void updateGroomingGuide_Success() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(groomingGuideMapper.convertToEntity(groomingGuideDto)).thenReturn(groomingGuide);
        when(groomingGuideRepository.save(groomingGuide)).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(groomingGuide)).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.updateGroomingGuide(groomingGuideDto);

        assertNotNull(result);
        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(groomingGuide);
    }

    @Test
    void updateGroomingGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.updateGroomingGuide(null));
    }

    @Test
    void deleteGroomingGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.deleteGroomingGuide(null));
    }

    @Test
    void deleteCatBreedFromGroomingGuide_InvalidInput() {
        assertThrows(InvalidInputException.class, () -> groomingGuideServiceImplementation.deleteSuitableCatBreedFromGroomingGuide(0L, 0L));
    }

    @Test
    void updateGroomingGuide_InvalidInput() {
        groomingGuideDto.setId(0L);
        assertThrows(RuntimeException.class, () -> groomingGuideServiceImplementation.updateGroomingGuide(groomingGuideDto));
    }

    @Test
    void updateGroomingGuide_BlankInput() {
        groomingGuideDto.setContent("");
        assertThrows(BlankInputException.class, () -> groomingGuideServiceImplementation.updateGroomingGuide(groomingGuideDto));
    }

    @Test
    void updateGroomingGuide_NotFound() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> groomingGuideServiceImplementation.updateGroomingGuide(groomingGuideDto));
    }

    @Test
    void getAllGroomingGuides() {
        Page<GroomingGuide> groomingGuidePage = new PageImpl<>(List.of(groomingGuide));
        when(groomingGuideRepository.findAll(any(PageRequest.class))).thenReturn(groomingGuidePage);

        Map<String, Object> result = groomingGuideServiceImplementation.getAllGroomingGuides(0, 5);

        assertNotNull(result);
        assertTrue(result.containsKey("groomingGuides"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));

        verify(groomingGuideRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void addDogBreedInGroomingGuide() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.addSuitableDogBreedToGroomingGuide(1L, 1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void addCatBreedInGroomingGuide() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.addSuitableCatBreedToGroomingGuide(1L, 1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void deleteDogBreedInGroomingGuide() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.deleteSuitableDogBreedFromGroomingGuide(1L, 1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void deleteCatBreedInGroomingGuide() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.deleteSuitableCatBreedFromGroomingGuide(1L, 1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void getAvailableDogBreeds() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(dogBreedMapper.convertToDTO(any(DogBreed.class))).thenReturn(new DogBreedDto());

        groomingGuide.getSuitableDogBreeds().add(dogBreed);
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Golden Retriever");

        when(dogBreedMapper.convertToDTO(any(DogBreed.class))).thenReturn(dogBreedDto);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        DogBreedDto result = groomingGuideServiceImplementation.getGroomingSuitableDogBreeds(1L).getFirst();
        assertEquals(dogBreedDto, result);
        verify(groomingGuideRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAvailableCatBreeds() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(catBreedMapper.convertToDTO(any(CatBreed.class))).thenReturn(new CatBreedDto());

        groomingGuide.getSuitableCatBreeds().add(catBreed);
        CatBreedDto catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Siamese");

        when(catBreedMapper.convertToDTO(any(CatBreed.class))).thenReturn(catBreedDto);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        CatBreedDto result = groomingGuideServiceImplementation.getGroomingSuitableCatBreeds(1L).getFirst();
        assertEquals(catBreedDto, result);
        verify(groomingGuideRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAvailableDogBreeds_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.getGroomingSuitableDogBreeds(null));
    }

    @Test
    void getAvailableCatBreeds_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.getGroomingSuitableCatBreeds(null));
    }

    @Test
    void getAvailableDogBreeds_NotFound() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> groomingGuideServiceImplementation.getGroomingSuitableDogBreeds(1L));
    }

    @Test
    void getAvailableCatBreeds_NotFound() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> groomingGuideServiceImplementation.getGroomingSuitableCatBreeds(1L));
    }

    @Test
    void addDogBreedToGroomingGuide() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.addSuitableDogBreedToGroomingGuide(1L, 1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void addCatBreedToGroomingGuide() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.addSuitableCatBreedToGroomingGuide(1L, 1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void addDogBreedToGroomingGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.addSuitableDogBreedToGroomingGuide(null, null));
    }

    @Test
    void addCatBreedToGroomingGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.addSuitableCatBreedToGroomingGuide(null, null));
    }

    @Test
    void deleteCatBreedFromGroomingGuide() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.deleteSuitableCatBreedFromGroomingGuide(1L, 1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void deleteDogBreedFromGroomingGuide() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.deleteSuitableDogBreedFromGroomingGuide(1L, 1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).save(any(GroomingGuide.class));
    }

    @Test
    void deleteDogBreedFromGroomingGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.deleteSuitableDogBreedFromGroomingGuide(null, null));
    }

    @Test
    void deleteCatBreedFromGroomingGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.deleteSuitableCatBreedFromGroomingGuide(null, null));
    }

    @Test
    void addDogBreedInGroomingGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.addSuitableDogBreedToGroomingGuide(null, null));
    }

    @Test
    void addCatBreedInGroomingGuide_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> groomingGuideServiceImplementation.addSuitableCatBreedToGroomingGuide(null, null));
    }

    @Test
    void createGroomingGuide_BlankContent() {
        groomingGuideDto.setContent("");
        assertThrows(BlankInputException.class, () -> groomingGuideServiceImplementation.createGroomingGuide(groomingGuideDto));
    }

    @Test
    void createGroomingGuide_BlankTools() {
        groomingGuideDto.setToolsNeeded("");
        assertThrows(BlankInputException.class, () -> groomingGuideServiceImplementation.createGroomingGuide(groomingGuideDto));
    }

    @Test
    void deleteGroomingGuide_ValidId() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        doNothing().when(groomingGuideRepository).deleteById(anyLong());

        Boolean result = groomingGuideServiceImplementation.deleteGroomingGuide(1L);

        assertTrue(result);
        verify(groomingGuideRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getGroomingGuideById_ValidId() {
        when(groomingGuideRepository.findById(anyLong())).thenReturn(Optional.of(groomingGuide));
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        GroomingGuideDto result = groomingGuideServiceImplementation.getGroomingGuideById(1L);

        assertEquals(groomingGuideDto, result);
        verify(groomingGuideRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllGroomingGuides_ValidPage() {
        Page<GroomingGuide> page = new PageImpl<>(List.of(groomingGuide));
        when(groomingGuideRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(groomingGuideMapper.convertToDTO(any(GroomingGuide.class))).thenReturn(groomingGuideDto);

        Map<String, Object> result = groomingGuideServiceImplementation.getAllGroomingGuides(0, 10);

        assertTrue(result.containsKey("groomingGuides"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));
        verify(groomingGuideRepository, times(1)).findAll(any(PageRequest.class));
    }
}