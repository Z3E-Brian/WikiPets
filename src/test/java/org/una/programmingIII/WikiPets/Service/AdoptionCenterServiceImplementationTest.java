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
import org.una.programmingIII.WikiPets.Repository.AdoptionCenterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdoptionCenterServiceImplementationTest {

    @Mock
    private AdoptionCenterRepository adoptionCenterRepository;
    @Mock
    private GenericMapperFactory genericMapperFactory;
    @Mock
    private GenericMapper<AdoptionCenter, AdoptionCenterDto> adoptionCenterMapper;
    @Mock
    private DogBreedService dogBreedService;
    @Mock
    private CatBreedService catBreedService;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;

    @InjectMocks
    private AdoptionCenterServiceImplementation adoptionCenterServiceImplementation;

    private AdoptionCenter adoptionCenter;
    private AdoptionCenterDto adoptionCenterDto;
    private DogBreed dogBreed;
    private CatBreed catBreed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adoptionCenterServiceImplementation = new AdoptionCenterServiceImplementation(
                adoptionCenterRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService
        );
        ArrayList<AdoptionCenter> adoptionCenters = new ArrayList<>();

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


        adoptionCenter = new AdoptionCenter();
        adoptionCenter.setId(1L);
        adoptionCenter.setName("Adoption Center 1");
        adoptionCenter.setLocation("100m Norte de la Municipalidad de San José");
        adoptionCenter.setAvailableCatBreeds(new ArrayList<CatBreed>());
        adoptionCenter.setAvailableDogBreeds(dogList);
        adoptionCenter.setAvailableCatBreeds(catList);

        adoptionCenterDto = new AdoptionCenterDto();
        adoptionCenterDto.setId(1L);
        adoptionCenterDto.setName("Adoption Center 1");
        adoptionCenterDto.setLocation("100m Norte de la Municipalidad de San José");


        catBreed.setAdoptionCenters(adoptionCenters);
        dogBreed.setAdoptionCenters(adoptionCenters);
        adoptionCenterDto.setAvailableCatBreeds(new ArrayList<CatBreedDto>());
        adoptionCenterDto.setAvailableDogBreeds(new ArrayList<DogBreedDto>());

        when(genericMapperFactory.createMapper(AdoptionCenter.class, AdoptionCenterDto.class)).thenReturn(adoptionCenterMapper);
        when(genericMapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        when(genericMapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        adoptionCenterServiceImplementation = new AdoptionCenterServiceImplementation(
                adoptionCenterRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService);
    }

    @Test
    void getAdoptionCenterById_Success() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.getAdoptionCenterById(1L);

        assertNotNull(result);
        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAdoptionCenterById_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.getAdoptionCenterById(null));
    }

    @Test
    void getAdoptionCenterById_NotFound() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> adoptionCenterServiceImplementation.getAdoptionCenterById(1L));
    }

    @Test
    void createAdoptionCenter_Success() {
        when(adoptionCenterMapper.convertToEntity(adoptionCenterDto)).thenReturn(adoptionCenter);
        when(adoptionCenterRepository.save(adoptionCenter)).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(adoptionCenter)).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.createAdoptionCenter(adoptionCenterDto);

        assertNotNull(result);
        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).save(any(AdoptionCenter.class));
    }

    @Test
    void createAdoptionCenter_BlankInput() {
        adoptionCenterDto.setName("");
        assertThrows(BlankInputException.class, () -> adoptionCenterServiceImplementation.createAdoptionCenter(adoptionCenterDto));
    }

    @Test
    void deleteAdoptionCenter() {
        when(adoptionCenterRepository.existsById(anyLong())).thenReturn(true);
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));

        boolean result = adoptionCenterServiceImplementation.deleteAdoptionCenter(1L);

        assertTrue(result);
    }

    @Test
    void deleteAdoptionCenter_NotFound() {
        when(adoptionCenterRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundElementException.class, () -> adoptionCenterServiceImplementation.deleteAdoptionCenter(1L));
    }

    @Test
    void updateAdoptionCenter_Success() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(adoptionCenterMapper.convertToEntity(adoptionCenterDto)).thenReturn(adoptionCenter);
        when(adoptionCenterRepository.save(adoptionCenter)).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(adoptionCenter)).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.updateAdoptionCenter(adoptionCenterDto);

        assertNotNull(result);
        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).save(adoptionCenter);
    }

    @Test
    void updateAdoptionCenter_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.updateAdoptionCenter(null));
    }

    @Test
    void deleteAdoptionCenter_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.deleteAdoptionCenter(null));
    }

    @Test
    void deleteCatBreedFromAdoptionCenter_InvalidInput() {
        assertThrows(InvalidInputException.class, () -> adoptionCenterServiceImplementation.deleteCatBreedFromAdoptionCenter(0L, 0L));
    }

    @Test
    void updateAdoptionCenter_InvalidInput() {
        adoptionCenterDto.setId(0L);
        assertThrows(RuntimeException.class, () -> adoptionCenterServiceImplementation.updateAdoptionCenter(adoptionCenterDto));
    }

    @Test
    void updateAdoptionCenter_BlankInput() {
        adoptionCenterDto.setName("");
        assertThrows(BlankInputException.class, () -> adoptionCenterServiceImplementation.updateAdoptionCenter(adoptionCenterDto));
    }

    @Test
    void updateAdoptionCenter_NotFound() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> adoptionCenterServiceImplementation.updateAdoptionCenter(adoptionCenterDto));
    }

    @Test
    void getAllAdoptionCenters() {
        Page<AdoptionCenter> adoptionCenterPage = new PageImpl<>(List.of(adoptionCenter));
        when(adoptionCenterRepository.findAll(any(PageRequest.class))).thenReturn(adoptionCenterPage);

        Map<String, Object> result = adoptionCenterServiceImplementation.getAllAdoptionCenters(0, 5);

        assertNotNull(result);
        assertTrue(result.containsKey("adoptionCenters"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));

        verify(adoptionCenterRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void addDogBreedInAdoptionCenter() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.addDogBreedInAdoptionCenter(1L, 1L);

        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).save(any(AdoptionCenter.class));
    }

    @Test
    void addCatBreedInAdoptionCenter() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.addCatBreedInAdoptionCenter(1L, 1L);

        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).save(any(AdoptionCenter.class));
    }

    @Test
    void deleteDogBreedInAdoptionCenter() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.deleteDogBreedFromAdoptionCenter(1L, 1L);

        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).save(any(AdoptionCenter.class));
    }

    @Test
    void deleteCatBreedInAdoptionCenter() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.deleteCatBreedFromAdoptionCenter(1L, 1L);

        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).save(any(AdoptionCenter.class));
    }

    @Test
    void getAvailableDogBreeds() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(dogBreedMapper.convertToDTO(any(DogBreed.class))).thenReturn(new DogBreedDto());

        adoptionCenter.getAvailableDogBreeds().add(dogBreed);
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Golden Retriever");

        when(dogBreedMapper.convertToDTO(any(DogBreed.class))).thenReturn(dogBreedDto);
        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        DogBreedDto result = adoptionCenterServiceImplementation.getAvailableDogBreeds(1L).get(0);
        assertEquals(dogBreedDto, result);
        verify(adoptionCenterRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAvailableCatBreeds() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(catBreedMapper.convertToDTO(any(CatBreed.class))).thenReturn(new CatBreedDto());

        adoptionCenter.getAvailableCatBreeds().add(catBreed);
        CatBreedDto catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Siamese");

        when(catBreedMapper.convertToDTO(any(CatBreed.class))).thenReturn(catBreedDto);
        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        CatBreedDto result = adoptionCenterServiceImplementation.getAvailableCatBreeds(1L).get(0);
        assertEquals(catBreedDto, result);
        verify(adoptionCenterRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAvailableDogBreeds_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.getAvailableDogBreeds(null));
    }

    @Test
    void getAvailableCatBreeds_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.getAvailableCatBreeds(null));
    }

    @Test
    void getAvailableDogBreeds_NotFound() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> adoptionCenterServiceImplementation.getAvailableDogBreeds(1L));
    }

    @Test
    void getAvailableCatBreeds_NotFound() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> adoptionCenterServiceImplementation.getAvailableCatBreeds(1L));
    }

    @Test
    void deleteCatBreedFromAdoptionCenter() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.deleteCatBreedFromAdoptionCenter(1L, 1L);

        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).save(any(AdoptionCenter.class));
    }

    @Test
    void deleteDogBreedFromAdoptionCenter() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.deleteDogBreedFromAdoptionCenter(1L, 1L);

        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).save(any(AdoptionCenter.class));
    }

    @Test
    void deleteDogBreedFromAdoptionCenter_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.deleteDogBreedFromAdoptionCenter(null, null));
    }

    @Test
    void deleteCatBreedFromAdoptionCenter_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.deleteCatBreedFromAdoptionCenter(null, null));
    }

    @Test
    void addDogBreedInAdoptionCenter_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.addDogBreedInAdoptionCenter(null, null));
    }

    @Test
    void addCatBreedInAdoptionCenter_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> adoptionCenterServiceImplementation.addCatBreedInAdoptionCenter(null, null));
    }

    @Test
    void createAdoptionCenter_BlankName() {
        adoptionCenterDto.setName(" ");
        assertThrows(BlankInputException.class, () -> adoptionCenterServiceImplementation.createAdoptionCenter(adoptionCenterDto));
    }

    @Test
    void createAdoptionCenter_BlankLocation() {
        adoptionCenterDto.setLocation(" ");
        assertThrows(BlankInputException.class, () -> adoptionCenterServiceImplementation.createAdoptionCenter(adoptionCenterDto));
    }

    @Test
    void deleteAdoptionCenter_ValidId() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        doNothing().when(adoptionCenterRepository).deleteById(anyLong());

        Boolean result = adoptionCenterServiceImplementation.deleteAdoptionCenter(1L);

        assertTrue(result);
        verify(adoptionCenterRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAdoptionCenterById_ValidId() {
        when(adoptionCenterRepository.findById(anyLong())).thenReturn(Optional.of(adoptionCenter));
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        AdoptionCenterDto result = adoptionCenterServiceImplementation.getAdoptionCenterById(1L);

        assertEquals(adoptionCenterDto, result);
        verify(adoptionCenterRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllAdoptionCenters_ValidPage() {
        Page<AdoptionCenter> page = new PageImpl<>(List.of(adoptionCenter));
        when(adoptionCenterRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(adoptionCenterMapper.convertToDTO(any(AdoptionCenter.class))).thenReturn(adoptionCenterDto);

        Map<String, Object> result = adoptionCenterServiceImplementation.getAllAdoptionCenters(0, 10);

        assertTrue(result.containsKey("adoptionCenters"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));
        verify(adoptionCenterRepository, times(1)).findAll(any(PageRequest.class));
    }
}