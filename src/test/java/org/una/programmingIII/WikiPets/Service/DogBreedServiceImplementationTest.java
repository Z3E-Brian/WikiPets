package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    /*
    @Test
    public void deleteDogBreedTest() {
        when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Boolean result = dogBreedServiceImplementation.deleteDogBreed(1L);
        assertTrue(result);
        verify(dogBreedRepository, times(1)).deleteById(1L);
    }

@Test
public void getAllDogBreedsTest() {
    Page<DogBreed> dogBreedPage = new PageImpl<>(Collections.singletonList(dogBreed));
    when(dogBreedMapper.convertToDTO(Mockito.any(DogBreed.class))).thenReturn(dogBreedDto);
    when(dogBreedRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(dogBreedPage);

    Map<String, Object> result = dogBreedServiceImplementation.getAllDogBreeds(0, 10);

    assertNotNull(result);
    assertEquals(1, ((List<?>) result.get("dogBreeds")).size());
    assertEquals(1, result.get("totalPages"));
    assertEquals(1L, result.get("totalElements"));
}*/
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
}
