package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Mapper.DogBreedMapper;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DogBreedServiceTest {

    @Mock
    private DogBreedRepository dogBreedRepository;

    @InjectMocks
    private DogBreedService dogBreedService;

    private DogBreed dogBreed;
    private DogBreedDto dogBreedDto;

    @BeforeEach
    void setUp() {
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
        dogBreedDto = DogBreedMapper.INSTANCE.toDogBreedDto(dogBreed);
        }

    @Test
    public void createDogBreedTest() {
        when(dogBreedRepository.save(Mockito.any(DogBreed.class))).thenReturn(dogBreed);

        DogBreedDto result = dogBreedService.createDogBreed(dogBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Golden Retriever", result.getName());
    }

    @Test
    public void updateDogBreedTest() {
        when(dogBreedRepository.save(Mockito.any(DogBreed.class))).thenReturn(dogBreed);

        DogBreedDto result = dogBreedService.updateDogBreed(dogBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Golden Retriever", result.getName());
    }

    @Test
    public void getBreedByIdTest() {
        when(dogBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(dogBreed));

        DogBreedDto result = dogBreedService.getBreedById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void getAllBreedsTest() {
        when(dogBreedRepository.findAll()).thenReturn(Arrays.asList(dogBreed));

        List<DogBreedDto> result = dogBreedService.getAllBreeds();

        assertNotNull(result);
        assertEquals(1L, result.getFirst().getId());
    }

    @Test
    public void deleteDogBreedTest() {
        dogBreedService.deleteDogBreed(1L);
    }
}
