package org.una.programmingIII.WikiPets.Service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.CatBreedDto;
import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatBreedServiceTest {

    @Mock
    private CatBreedRepository catBreedRepository;

    @InjectMocks
    private CatBreedService catBreedService;

    private CatBreed catBreed;
    private CatBreedDto catBreedDto;

    @BeforeEach
    void setUp() {
        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);
        catBreed.setCoat("Short");
        catBreed.setColor("Cream with points");
        catBreed.setLifeSpan("12-16 years");
        catBreed.setTemperament("Affectionate, Social, Vocal");
        catBreed.setDescription("Popular breed known for its striking appearance and vocal nature.");
       // catBreedDto = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.");
    }

    @Test
    public void createCatBreedTest() {
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);

        CatBreedDto result = catBreedService.createCatBreed(catBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }

    @Test
    public void updateCatBreedTest() {
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);

        CatBreedDto result = catBreedService.updateCatBreed(catBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }

    @Test
    public void getBreedByIdTest() {
        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(catBreed));

        CatBreedDto result = catBreedService.getBreedById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void getAllBreedsTest() {
        when(catBreedRepository.findAll()).thenReturn(Arrays.asList(catBreed));

        List<CatBreedDto> result = catBreedService.getAllBreeds();

        assertNotNull(result);
        assertEquals(1L, result.getFirst().getId());
    }

    @Test
    public void deleteCatBreedTest() {
        catBreedService.deleteCatBreed(1L);
    }
}

