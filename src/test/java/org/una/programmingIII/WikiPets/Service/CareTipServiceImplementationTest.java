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
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CareTip;
import org.una.programmingIII.WikiPets.Dto.CareTipDto;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Repository.CareTipRepository;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CareTipServiceImplementationTest {

    @Mock
    private CareTipRepository careTipRepository;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<CareTip, CareTipDto> careTipMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private DogBreedService breedService;
    @Mock
    private CatBreedService catBreedService;
    @InjectMocks
    private CareTipServiceImplementation careTipService;

    CareTip careTip;
    CareTipDto careTipDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        careTip = new CareTip();
        careTip.setId(1L);
        careTip.setTitle("Test Title");
        careTip.setContent("Test Content");
        careTipDto = new CareTipDto();
        careTipDto.setId(1L);
        careTipDto.setTitle("Test Title");
        careTipDto.setContent("Test Content");

        when(mapperFactory.createMapper(CareTip.class, CareTipDto.class)).thenReturn(careTipMapper);
        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);
        when(mapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        careTipService = new CareTipServiceImplementation(careTipRepository, mapperFactory, breedService, catBreedService);
    }


    @Test
    void getCareTipByIdTest() {
        CareTip careTip = new CareTip();
        CareTipDto careTipDto = new CareTipDto();

        when(careTipRepository.findById(1L)).thenReturn(Optional.of(careTip));
        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);

        CareTipDto result = careTipService.getCareTipById(1L);

        assertNotNull(result);
        verify(careTipRepository, times(1)).findById(1L);
    }

    @Test
    void getCareTipByIdNotFoundTest() {
        when(careTipRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> careTipService.getCareTipById(1L));

        assertEquals("Care Tip not found", exception.getMessage());
        verify(careTipRepository, times(1)).findById(1L);
    }

    @Test
    void getCareTipByTitleTest() {
        CareTip careTip = new CareTip();
        CareTipDto careTipDto = new CareTipDto();

        when(careTipRepository.findByTitle("Test Title")).thenReturn(careTip);
        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);

        CareTipDto result = careTipService.getCareTipByTitle("Test Title");

        assertNotNull(result);
        verify(careTipRepository, times(1)).findByTitle("Test Title");
    }

    @Test
    void getCareTipByTitleNotFoundTest() {
        when(careTipRepository.findByTitle("Nonexistent Title")).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> careTipService.getCareTipByTitle("Nonexistent Title"));

        assertEquals("Care Tip not found", exception.getMessage());
        verify(careTipRepository, times(1)).findByTitle("Nonexistent Title");
    }

    @Test
    void createCareTipTest() {
        when(careTipMapper.convertToEntity(careTipDto)).thenReturn(careTip);
        when(careTipRepository.save(careTip)).thenReturn(careTip);
        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);

        CareTipDto result = careTipService.createCareTip(careTipDto);

        assertNotNull(result);
        verify(careTipRepository, times(1)).save(careTip);
    }

    @Test
    void deleteCareTipTest() {
        when(careTipRepository.findById(1L)).thenReturn(Optional.of(careTip));
        boolean result = careTipService.deleteCareTip(1L);

        assertTrue(result);
        verify(careTipRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateCareTipTest() {
        when(careTipRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(careTip));
        when(careTipMapper.convertToEntity(careTipDto)).thenReturn(careTip);
        when(careTipRepository.save(careTip)).thenReturn(careTip);
        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);

        CareTipDto result = careTipService.updateCareTip(careTipDto);

        assertNotNull(result);
        verify(careTipRepository, times(1)).save(careTip);
    }

    @Test
    void addCatBreedInCareTipTest() {
        CatBreed catBreed = new CatBreed();
        catBreed.setId(1L);
        when(careTipRepository.findById(1L)).thenReturn(Optional.of(careTip));
        when(catBreedService.getBreedById(1L)).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(careTipRepository.save(any(CareTip.class))).thenReturn(careTip);
        when(careTipMapper.convertToDTO(any(CareTip.class))).thenReturn(new CareTipDto());


        CareTipDto result = careTipService.addCatBreedInCareTip(1L, 1L);

        assertNotNull(result);
        verify(careTipRepository, times(1)).findById(1L);
        verify(catBreedService, times(1)).getBreedById(1L);
        verify(careTipRepository, times(1)).save(careTip);
    }
    @Test
    void addDogBreedInCareTipTest() {
        DogBreed dogBreed = new DogBreed();
        dogBreed.setId(1L);
        when(careTipRepository.findById(1L)).thenReturn(Optional.of(careTip));
        when(breedService.getBreedById(1L)).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(careTipRepository.save(any(CareTip.class))).thenReturn(careTip);
        when(careTipMapper.convertToDTO(any(CareTip.class))).thenReturn(new CareTipDto());
    CareTipDto result = careTipService.addDogBreedInCareTip(1L, 1L);

        assertNotNull(result);
        verify(careTipRepository, times(1)).findById(1L);
        verify(breedService, times(1)).getBreedById(1L);
        verify(careTipRepository, times(1)).save(careTip);
    }
    @Test
    void getAllCareTipsTest() {
        List<CareTip> careTipList = new ArrayList<>();
        CareTip careTip = new CareTip();
        careTip.setRelevantCatBreeds(Arrays.asList(new CatBreed(), new CatBreed()));
        careTip.setRelevantDogBreeds(Arrays.asList(new DogBreed(), new DogBreed()));

        careTipList.add(careTip);
        Page<CareTip> page = new PageImpl<>(careTipList);

        when(careTipRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Map<String, Object> result = careTipService.getAllCareTips(0, 10);

        assertEquals(1, ((List<?>) result.get("careTips")).size());
        verify(careTipRepository, times(1)).findAll(any(PageRequest.class));
    }
    @Test
    void testGetCareTipByIdNotFound() {
        when(careTipRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            careTipService.getCareTipById(1L);
        });

        verify(careTipRepository, times(1)).findById(anyLong());
    }
    @Test
    void createCareTipBlankInputTest() {
        CareTipDto careTipDto = new CareTipDto();
        careTipDto.setContent("");
        careTipDto.setTitle("");

        assertThrows(BlankInputException.class, () -> {
            careTipService.createCareTip(careTipDto);
        });
    }
    @Test
    void deleteCareTipTests() {
        CareTip careTip = new CareTip();
        careTip.setRelevantCatBreeds(new ArrayList<>(List.of(new CatBreed())));
        careTip.setRelevantDogBreeds(new ArrayList<>(List.of(new DogBreed())));

        when(careTipRepository.findById(anyLong())).thenReturn(Optional.of(careTip));

        Boolean result = careTipService.deleteCareTip(1L);

        assertTrue(result);
        verify(careTipRepository, times(1)).deleteById(anyLong());
    }
    @Test
    void updateCareTipInvalidIdTest() {
        careTipDto.setId(0L);

        assertThrows(InvalidInputException.class, () -> careTipService.updateCareTip(careTipDto));
    }

    @Test
    void updateCareTipBlankFieldsTest() {
        careTipDto.setId(1L);
        careTipDto.setTitle("");
        careTipDto.setContent("");

        assertThrows(BlankInputException.class, () -> careTipService.updateCareTip(careTipDto));
    }
    @Test
    void addCatBreedInCareTipNotFoundTest() {
        when(careTipRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> careTipService.addCatBreedInCareTip(1L, 1L));

        assertEquals("Care Tip not found", exception.getMessage());
        verify(careTipRepository, times(1)).findById(1L);
    }
    @Test
    void addDogBreedInCareTipNotFoundTest() {
        when(careTipRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> careTipService.addDogBreedInCareTip(1L, 1L));

        assertEquals("Care Tip not found", exception.getMessage());
        verify(careTipRepository, times(1)).findById(1L);
    }
    @Test
    void createCareTipSetsDatesCorrectlyTest() {
        when(careTipMapper.convertToEntity(careTipDto)).thenReturn(careTip);
        when(careTipRepository.save(careTip)).thenReturn(careTip);
        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);

        CareTipDto result = careTipService.createCareTip(careTipDto);

        assertNotNull(result);
        assertEquals(LocalDate.now(), careTip.getCreatedDate());
        assertEquals(LocalDate.now(), careTip.getModifiedDate());
        verify(careTipRepository, times(1)).save(careTip);
    }
    @Test
    void deleteCareTipNotFoundTest() {
        when(careTipRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundElementException.class, () -> careTipService.deleteCareTip(1L));

        assertEquals("Care Tip not found", exception.getMessage());
        verify(careTipRepository, times(1)).findById(1L);
    }
    @Test
    void getAllCareTipsMultiplePagesTest() {
        List<CareTip> careTipList = Arrays.asList(new CareTip(), new CareTip());
        Page<CareTip> page = new PageImpl<>(careTipList);

        when(careTipRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Map<String, Object> result = careTipService.getAllCareTips(0, 10);

        assertEquals(2, ((List<?>) result.get("careTips")).size());
        verify(careTipRepository, times(1)).findAll(any(PageRequest.class));
    }

}