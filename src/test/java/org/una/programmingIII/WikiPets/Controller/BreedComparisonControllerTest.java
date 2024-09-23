package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Model.BreedComparisonResult;
import org.una.programmingIII.WikiPets.Service.BreedComparisonService;

public class BreedComparisonControllerTest {

    @InjectMocks
    private BreedComparisonController breedComparisonController;

    @Mock
    private BreedComparisonService breedComparisonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCatsBreedComparation() {
        Long idCatBreed1 = 1L;
        Long idCatBreed2 = 2L;
        BreedComparisonResult expectedComparisonResult = new BreedComparisonResult();
        when(breedComparisonService.compareCats(idCatBreed1, idCatBreed2)).thenReturn(expectedComparisonResult);

        BreedComparisonResult result = breedComparisonController.getCatsBreedComparation(idCatBreed1, idCatBreed2);

        assertEquals(expectedComparisonResult, result);
        verify(breedComparisonService).compareCats(idCatBreed1, idCatBreed2);
    }

    @Test
    void testGetDogsBreedComparation() {
        Long idDogBreed1 = 1L;
        Long idDogBreed2 = 2L;
        BreedComparisonResult expectedComparisonResult = new BreedComparisonResult();
        when(breedComparisonService.compareDogs(idDogBreed1, idDogBreed2)).thenReturn(expectedComparisonResult);

        BreedComparisonResult result = breedComparisonController.getDogsBreedComparation(idDogBreed1, idDogBreed2);

        assertEquals(expectedComparisonResult, result);
        verify(breedComparisonService).compareDogs(idDogBreed1, idDogBreed2);
    }

    @Test
    void testGetMixedBreedsComparation() {
        Long idDogBreed = 1L;
        Long idCatBreed = 2L;
        BreedComparisonResult expectedComparisonResult = new BreedComparisonResult();
        when(breedComparisonService.compareMixedBreeds(idDogBreed, idCatBreed)).thenReturn(expectedComparisonResult);

        BreedComparisonResult result = breedComparisonController.getMixedBreedsComparation(idDogBreed, idCatBreed);

        assertEquals(expectedComparisonResult, result);
        verify(breedComparisonService).compareMixedBreeds(idDogBreed, idCatBreed);
    }
}
