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
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;

import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        catBreedDto.setCreatedDate(LocalDate.parse("2020-10-22"));
        catBreedDto.setModifiedDate(LocalDate.parse("2020-10-22"));

        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);

        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        catBreedServiceImplementation = new CatBreedServiceImplementation(catBreedRepository, mapperFactory);
    }

    @Test
    public void createCatBreedTest() {
        when(catBreedMapper.convertToEntity(Mockito.any(CatBreedDto.class))).thenReturn(catBreed);
        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenReturn(catBreedDto);
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);

        CatBreedDto result = catBreedServiceImplementation.createCatBreed(catBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }


    @Test
    public void updateCatBreedTest() {
        when(catBreedMapper.convertToEntity(Mockito.any(CatBreedDto.class))).thenReturn(catBreed);
        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenReturn(catBreedDto);
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);

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
    public void getAllCatBreedsReturnsCorrectPage() {
        Page<CatBreed> catBreedPage = new PageImpl<>(List.of(catBreed));
        when(catBreedRepository.findAll(any(Pageable.class))).thenReturn(catBreedPage);
        when(catBreedMapper.convertToDTO(any(CatBreed.class))).thenReturn(catBreedDto);

        Map<String, Object> result = catBreedServiceImplementation.getAllCatBreeds(0, 10);

        assertNotNull(result);
        assertEquals(1, ((List<?>) result.get("catBreeds")).size());
        assertEquals(1, result.get("totalPages"));
        assertEquals(1L, result.get("totalElements"));
    }

    @Test
    public void getAllCatBreedsReturnsEmptyPage() {
        Page<CatBreed> catBreedPage = new PageImpl<>(List.of());
        when(catBreedRepository.findAll(any(Pageable.class))).thenReturn(catBreedPage);

        Map<String, Object> result = catBreedServiceImplementation.getAllCatBreeds(0, 10);

        assertNotNull(result);
        assertTrue(((List<?>) result.get("catBreeds")).isEmpty());
        assertEquals(0, result.get("totalPages"));
        assertEquals(0L, result.get("totalElements"));
    }

    @Test
    public void getAllCatBreedsLimitsLists() {
        CatBreed catBreedWithLongLists = new CatBreed();
        catBreedWithLongLists.setAdoptionCenters(Arrays.asList(new AdoptionCenter[20]));
        catBreedWithLongLists.setHealthIssues(Arrays.asList(new HealthIssue[20]));
        catBreedWithLongLists.setNutritionGuides(Arrays.asList(new NutritionGuide[20]));
        catBreedWithLongLists.setUsers(Arrays.asList(new User[20]));
        catBreedWithLongLists.setTrainingGuides(Arrays.asList(new TrainingGuide[20]));
        catBreedWithLongLists.setBehaviorGuides(Arrays.asList(new BehaviorGuide[20]));
        catBreedWithLongLists.setCareTips(Arrays.asList(new CareTip[20]));
        catBreedWithLongLists.setGroomingGuides(Arrays.asList(new GroomingGuide[20]));

        Page<CatBreed> catBreedPage = new PageImpl<>(List.of(catBreedWithLongLists));
        when(catBreedRepository.findAll(any(Pageable.class))).thenReturn(catBreedPage);
        when(catBreedMapper.convertToDTO(any(CatBreed.class))).thenReturn(catBreedDto);

        Map<String, Object> result = catBreedServiceImplementation.getAllCatBreeds(0, 10);

        assertNotNull(result);
        CatBreedDto resultDto = ((List<CatBreedDto>) result.get("catBreeds")).get(0);
        assertEquals(10, resultDto.getAdoptionCenters().size());
        assertEquals(10, resultDto.getHealthIssues().size());
        assertEquals(10, resultDto.getNutritionGuides().size());
        assertEquals(10, resultDto.getUsers().size());
        assertEquals(10, resultDto.getTrainingGuides().size());
        assertEquals(10, resultDto.getBehaviorGuides().size());
        assertEquals(10, resultDto.getCareTips().size());
        assertEquals(10, resultDto.getGroomingGuides().size());
    }

    @Test
    public void deleteCatBreedTest() {
        doNothing().when(catBreedRepository).deleteById(1L);
        catBreedServiceImplementation.deleteCatBreed(1L);
        verify(catBreedRepository, times(1)).deleteById(1L);
    }
}

