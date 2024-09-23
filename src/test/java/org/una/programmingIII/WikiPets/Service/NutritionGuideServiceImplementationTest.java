package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.NutritionGuide;
import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;
import org.una.programmingIII.WikiPets.Repository.NutritionGuideRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NutritionGuideServiceImplementationTest {

    @Mock
    private NutritionGuideRepository nutritionGuideRepository;

    @Mock
    private GenericMapper<NutritionGuide, NutritionGuideDto> nutritionGuideMapper;

    @Mock
    private GenericMapperFactory mapperFactory;

    @InjectMocks
    private NutritionGuideServiceImplementation nutritionGuideServiceImplementation;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    @Mock
    private DogBreedService dogBreedService;
    @Mock
    private CatBreedService catBreedService;
    NutritionGuide nutritionGuide;
    NutritionGuideDto nutritionGuideDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        nutritionGuide = new NutritionGuide();
        nutritionGuide.setId(1L);
        nutritionGuide.setTitle("title");
        nutritionGuide.setContent("content");
        nutritionGuide.setCreatedDate(LocalDate.now());
        nutritionGuide.setModifiedDate(LocalDate.now());
        nutritionGuideDto = new NutritionGuideDto();
        nutritionGuideDto.setId(1L);
        nutritionGuideDto.setTitle("title");
        nutritionGuideDto.setContent("content");
        nutritionGuideDto.setCreatedDate(LocalDate.now());
        nutritionGuideDto.setModifiedDate(LocalDate.now());
        when(mapperFactory.createMapper(NutritionGuide.class, NutritionGuideDto.class)).thenReturn(nutritionGuideMapper);
        when(mapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        nutritionGuideServiceImplementation = new NutritionGuideServiceImplementation(nutritionGuideRepository, mapperFactory, dogBreedService, catBreedService);

    }

    @Test
    void getNutritionGuideByIdTest() {
        when(nutritionGuideMapper.convertToDTO(any())).thenReturn(new NutritionGuideDto());
        NutritionGuide nutritionGuide = new NutritionGuide();
        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.of(nutritionGuide));


        NutritionGuideDto result = nutritionGuideServiceImplementation.getNutritionGuideById(1L);

        assertEquals(nutritionGuideDto, result);
    }

    @Test
    void getNutritionGuideById_NotFoundTest() {
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.getNutritionGuideById(1L));

        assertEquals("Nutrition Guide not found", exception.getMessage());
    }

    @Test
    void getNutritionGuideByTitleTest() {
        when(nutritionGuideMapper.convertToDTO(any())).thenReturn(new NutritionGuideDto());
        NutritionGuide nutritionGuide = new NutritionGuide();
        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
        when(nutritionGuideRepository.findByTitle("title")).thenReturn(nutritionGuide);

        NutritionGuideDto result = nutritionGuideServiceImplementation.getNutritionGuideByTitle("title");

        assertEquals(nutritionGuideDto, result);
    }

    @Test
    void getNutritionGuideByTitle_NotFoundTest() {
        when(nutritionGuideRepository.findByTitle("title")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.getNutritionGuideByTitle("title"));

        assertEquals("Nutrition Guide not found", exception.getMessage());
    }

    @Test
    void createNutritionGuideTest() {
        when(nutritionGuideMapper.convertToEntity(any())).thenReturn(nutritionGuide);
        doReturn(nutritionGuide).when(nutritionGuideRepository).save(any(NutritionGuide.class));
        when(nutritionGuideMapper.convertToDTO(any())).thenReturn(nutritionGuideDto);

        NutritionGuideDto result = nutritionGuideServiceImplementation.createNutritionGuide(nutritionGuideDto);

        assertNotNull(result);
        verify(nutritionGuideRepository, times(1)).save(any(NutritionGuide.class));
    }

    @Test
    void deleteNutritionGuideTest() {
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.of(nutritionGuide));

        boolean result = nutritionGuideServiceImplementation.deleteNutritionGuide(1L);
        assertTrue(result);
        verify(nutritionGuideRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateNutritionGuideTest() {
        NutritionGuide existingNutritionGuide = new NutritionGuide();
        existingNutritionGuide.setId(1L);
        existingNutritionGuide.setCreatedDate(LocalDate.now().minusDays(1));
        existingNutritionGuide.setModifiedDate(LocalDate.now().minusDays(1));

        NutritionGuide updatedNutritionGuide = new NutritionGuide();
        updatedNutritionGuide.setId(1L);
        updatedNutritionGuide.setCreatedDate(existingNutritionGuide.getCreatedDate());
        updatedNutritionGuide.setModifiedDate(LocalDate.now());

        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.of(existingNutritionGuide));
        when(nutritionGuideMapper.convertToEntity(any())).thenReturn(updatedNutritionGuide);
        when(nutritionGuideRepository.save(any(NutritionGuide.class))).thenReturn(updatedNutritionGuide);
        when(nutritionGuideMapper.convertToDTO(any())).thenReturn(nutritionGuideDto);

        NutritionGuideDto result = nutritionGuideServiceImplementation.updateNutritionGuide(nutritionGuideDto);

        assertNotNull(result);
        assertEquals(nutritionGuideDto, result);
        verify(nutritionGuideRepository, times(1)).findById(1L);
        verify(nutritionGuideRepository, times(1)).save(any(NutritionGuide.class));
    }

    @Test
    void addRecommendedCatBreedsTest() {
        CatBreed catBreed = new CatBreed();
        catBreed.setId(1L);
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.of(nutritionGuide));
        when(catBreedService.getBreedById(1L)).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(nutritionGuideRepository.save(any(NutritionGuide.class))).thenReturn(nutritionGuide);
        when(nutritionGuideMapper.convertToDTO(any(NutritionGuide.class))).thenReturn(new NutritionGuideDto());
        NutritionGuideDto result = nutritionGuideServiceImplementation.addRecommendedCatBreed(1L, 1L);

        assertNotNull(result);
        verify(nutritionGuideRepository, times(1)).findById(1L);
        verify(catBreedService, times(1)).getBreedById(1L);
        verify(nutritionGuideRepository, times(1)).save(any(NutritionGuide.class));
    }

    @Test
    void addRecommendedDogBreedsTest() {
        DogBreed dogBreed = new DogBreed();
        dogBreed.setId(1L);
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.of(nutritionGuide));
        when(dogBreedService.getBreedById(1L)).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(nutritionGuideRepository.save(any(NutritionGuide.class))).thenReturn(nutritionGuide);
        when(nutritionGuideMapper.convertToDTO(any(NutritionGuide.class))).thenReturn(new NutritionGuideDto());
        NutritionGuideDto result = nutritionGuideServiceImplementation.addRecommendedDogBreed(1L, 1L);

        assertNotNull(result);
        verify(nutritionGuideRepository, times(1)).findById(1L);
        verify(dogBreedService, times(1)).getBreedById(1L);
        verify(nutritionGuideRepository, times(1)).save(any(NutritionGuide.class));
    }

    @Test
    void getAllNutritionGuidesTest() {
        List<NutritionGuide> nutritionGuideList = new ArrayList<>();
        nutritionGuide.setRecommendedDogBreeds(Arrays.asList(new DogBreed(), new DogBreed()));
        nutritionGuide.setRecommendedCatBreeds(Arrays.asList(new CatBreed(), new CatBreed()));

        nutritionGuideList.add(nutritionGuide);
        Page<NutritionGuide> nutritionGuidePage = new PageImpl<>(nutritionGuideList);
        when(nutritionGuideRepository.findAll(any(PageRequest.class))).thenReturn(nutritionGuidePage);

        Map<String, Object> result = nutritionGuideServiceImplementation.getAllNutritionGuides(0,1);

        assertEquals(1,((List<?>)result.get("nutritionGuides")).size());
        verify(nutritionGuideRepository, times(1)).findAll(any(PageRequest.class));
    }
    @Test
    void createNutritionGuide_NullInputTest() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> nutritionGuideServiceImplementation.createNutritionGuide(null));
        assertEquals("Nutrition Guide cannot be null", exception.getMessage());
    }

    @Test
    void createNutritionGuide_BlankInputTest() {
        NutritionGuideDto blankNutritionGuideDto = new NutritionGuideDto();
        blankNutritionGuideDto.setTitle("");
        blankNutritionGuideDto.setContent("");
        BlankInputException exception = assertThrows(BlankInputException.class, () -> nutritionGuideServiceImplementation.createNutritionGuide(blankNutritionGuideDto));
        assertEquals("Nutrition Guide cannot be have spaces in blank", exception.getMessage());
    }
    @Test
    void updateNutritionGuide_InvalidIdTest() {
        nutritionGuideDto.setId(-1L);
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> nutritionGuideServiceImplementation.updateNutritionGuide(nutritionGuideDto));
        assertEquals("Invalid careTip ID", exception.getMessage());
    }

    @Test
    void updateNutritionGuide_NotFoundTest() {
        nutritionGuideDto.setId(0L);
        assertThrows(InvalidInputException.class, () -> nutritionGuideServiceImplementation.updateNutritionGuide(nutritionGuideDto));
    }
    @Test
    void updateNutritionGuide_BlankInputTest() {
        nutritionGuideDto.setTitle("");
        nutritionGuideDto.setContent("");
        BlankInputException exception = assertThrows(BlankInputException.class, () -> nutritionGuideServiceImplementation.updateNutritionGuide(nutritionGuideDto));
        assertEquals("Cant' leave spaces in blank", exception.getMessage());
    }
    @Test
    void addRecommendedCatBreed_InvalidIdTest() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> nutritionGuideServiceImplementation.addRecommendedCatBreed(-1L, 1L));
        assertEquals("Invalid careTip ID", exception.getMessage());
    }
    @Test
    void addRecommendedDogBreed_InvalidIdTest() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> nutritionGuideServiceImplementation.addRecommendedDogBreed(-1L, 1L));
        assertEquals("Invalid careTip ID", exception.getMessage());
    }
@Test
    void addRecommendedCatBreed_NotFoundTest() {
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.addRecommendedCatBreed(1L, 1L));
        assertEquals("Nutrition Guide not found", exception.getMessage());
        verify(nutritionGuideRepository, times(1)).findById(1L);
    }
@Test
    void addRecommendedDogBreed_NotFoundTest() {
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.addRecommendedDogBreed(1L, 1L));
        assertEquals("Nutrition Guide not found", exception.getMessage());
        verify(nutritionGuideRepository, times(1)).findById(1L);
    }
@Test
    void deleteNutritionGuide_NotFoundTest() {
        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.deleteNutritionGuide(1L));
        assertEquals("Nutrition Guide not found", exception.getMessage());
        verify(nutritionGuideRepository, times(1)).findById(1L);
    }
}