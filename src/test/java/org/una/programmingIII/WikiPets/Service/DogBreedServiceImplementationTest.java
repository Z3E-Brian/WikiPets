package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DogBreedServiceImplementationTest {

    @Mock
    private DogBreedRepository dogBreedRepository;

    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private GenericMapperFactory mapperFactory;

    @InjectMocks
    private DogBreedServiceImplementation dogBreedServiceImplementation;

    private DogBreed dogBreed;
    private DogBreedDto dogBreedDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
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
        dogBreedServiceImplementation = new DogBreedServiceImplementation(dogBreedRepository, mapperFactory);
        }

    @Test
    public void createDogBreedTest() {
        when(dogBreedMapper.convertToEntity(Mockito.any(DogBreedDto.class))).thenReturn(dogBreed);
        when(dogBreedMapper.convertToDTO(Mockito.any(DogBreed.class))).thenReturn(dogBreedDto);
        when(dogBreedRepository.save(Mockito.any(DogBreed.class))).thenReturn(dogBreed);

        DogBreedDto result = dogBreedServiceImplementation.createDogBreed(dogBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Golden Retriever", result.getName());
    }

    @Test
    public void updateDogBreedTest() {
        when(dogBreedMapper.convertToEntity(Mockito.any(DogBreedDto.class))).thenReturn(dogBreed);
        when(dogBreedMapper.convertToDTO(Mockito.any(DogBreed.class))).thenReturn(dogBreedDto);
        when(dogBreedRepository.save(Mockito.any(DogBreed.class))).thenReturn(dogBreed);

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
        dogBreedServiceImplementation.deleteDogBreed(1L);
    }
}
