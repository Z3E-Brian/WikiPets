package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Model.BreedComparisonResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BreedComparisonServiceImplementationTest {

    @Mock
    private DogBreedService dogBreedService;

    @Mock
    private CatBreedService catBreedService;

    @InjectMocks
    private BreedComparisonServiceImplementation breedComparisonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        breedComparisonService = new BreedComparisonServiceImplementation(dogBreedService, catBreedService);
    }

    @Test
    void testCompareCatsWithSimilarAttributes() {
        Long idCatBreed1 = 1L;
        Long idCatBreed2 = 2L;

        CatBreedDto catBreed1 = new CatBreedDto();
        catBreed1.setName("Siamese");
        catBreed1.setOrigin("Thailand");
        catBreed1.setSize(10);
        catBreed1.setCoat("Short");
        catBreed1.setColor("Cream");
        catBreed1.setLifeSpan("15 years");
        catBreed1.setTemperament("Active");
        catBreed1.setDescription("Friendly");

        CatBreedDto catBreed2 = new CatBreedDto();
        catBreed2.setName("Siamese");
        catBreed2.setOrigin("Thailand");
        catBreed2.setSize(10);
        catBreed2.setCoat("Short");
        catBreed2.setColor("Cream");
        catBreed2.setLifeSpan("15 years");
        catBreed2.setTemperament("Active");
        catBreed2.setDescription("Friendly");

        when(catBreedService.getBreedById(idCatBreed1)).thenReturn(catBreed1);
        when(catBreedService.getBreedById(idCatBreed2)).thenReturn(catBreed2);
        BreedComparisonResult result = breedComparisonService.compareCats(idCatBreed1, idCatBreed2);
        assertEquals(8, result.getSimilarities().size());
        assertEquals(0, result.getDifferences().size());
    }

    @Test
    void testCompareDogsWithDifferentAttributes() {
        Long idDogBreed1 = 1L;
        Long idDogBreed2 = 2L;

        DogBreedDto dogBreed1 = new DogBreedDto();
        dogBreed1.setName("Labrador");
        dogBreed1.setOrigin("Canada");
        dogBreed1.setSize(50);
        dogBreed1.setCoat("Short");
        dogBreed1.setColor("Yellow");
        dogBreed1.setLifeSpan("12 years");
        dogBreed1.setTemperament("Friendly");
        dogBreed1.setDescription("Gentle");

        DogBreedDto dogBreed2 = new DogBreedDto();
        dogBreed2.setName("Poodle");
        dogBreed2.setOrigin("Germany");
        dogBreed2.setSize(40);
        dogBreed2.setCoat("Curly");
        dogBreed2.setColor("White");
        dogBreed2.setLifeSpan("14 years");
        dogBreed2.setTemperament("Intelligent");
        dogBreed2.setDescription("Energetic");

        when(dogBreedService.getBreedById(idDogBreed1)).thenReturn(dogBreed1);
        when(dogBreedService.getBreedById(idDogBreed2)).thenReturn(dogBreed2);

        BreedComparisonResult result = breedComparisonService.compareDogs(idDogBreed1, idDogBreed2);

        assertEquals(0, result.getSimilarities().size());
        assertEquals(8, result.getDifferences().size());
    }

    @Test
    void testCompareMixedBreeds() {
        Long idDogBreed = 1L;
        Long idCatBreed = 2L;

        DogBreedDto dogBreed = new DogBreedDto();
        dogBreed.setName("Bulldog");
        dogBreed.setOrigin("England");
        dogBreed.setSize(20);
        dogBreed.setCoat("Short");
        dogBreed.setColor("Brown");
        dogBreed.setLifeSpan("10 years");
        dogBreed.setTemperament("Loyal");
        dogBreed.setDescription("Muscular");

        CatBreedDto catBreed = new CatBreedDto();
        catBreed.setName("Sphynx");
        catBreed.setOrigin("Canada");
        catBreed.setSize(10);
        catBreed.setCoat("Hairless");
        catBreed.setColor("Pink");
        catBreed.setLifeSpan("12 years");
        catBreed.setTemperament("Playful");
        catBreed.setDescription("Unique");

        when(dogBreedService.getBreedById(idDogBreed)).thenReturn(dogBreed);
        when(catBreedService.getBreedById(idCatBreed)).thenReturn(catBreed);
        BreedComparisonResult result = breedComparisonService.compareMixedBreeds(idDogBreed, idCatBreed);

        assertEquals(0, result.getSimilarities().size());
        assertEquals(8, result.getDifferences().size());
    }
}
