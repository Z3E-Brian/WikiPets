package org.una.programmingIII.WikiPets.Service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Mapper.CatBreedMapper;
import org.una.programmingIII.WikiPets.Model.CatBreed;

import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatBreedServiceImplementationTest {

    @Mock
    private CatBreedRepository catBreedRepository;

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
        catBreedDto.setCreatedDate(LocalDate.ofEpochDay(2020-10-22));
        catBreedDto.setModifiedDate(LocalDate.ofEpochDay(2020-10-22));
        catBreed = new CatBreed();
        catBreed= CatBreedMapper.INSTANCE.toCatBreed(catBreedDto);


       // catBreedDto = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.");
    }

    @Test
    public void createCatBreedTest() {
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);

        CatBreedDto result = catBreedServiceImplementation.createCatBreed(catBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }

    @Test
    public void updateCatBreedTest() {
        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);

        CatBreedDto result = catBreedServiceImplementation.updateCatBreed(catBreedDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }

    @Test
    public void getBreedByIdTest() {
        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(catBreed));

        CatBreedDto result = catBreedServiceImplementation.getBreedById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void getAllBreedsTest() {
        when(catBreedRepository.findAll()).thenReturn(Arrays.asList(catBreed));

        List<CatBreedDto> result = catBreedServiceImplementation.getAllBreeds();

        assertNotNull(result);
        assertEquals(1L, result.getFirst().getId());
    }

    @Test
    public void deleteCatBreedTest() {
        catBreedServiceImplementation.deleteCatBreed(1L);
    }
}

