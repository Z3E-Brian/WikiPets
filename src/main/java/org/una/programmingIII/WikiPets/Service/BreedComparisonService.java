package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.BreedComparisonResult;

public interface BreedComparisonService {

    BreedComparisonResult compareCats(Long idCatBreed1, Long idCat2Breed1);

    BreedComparisonResult compareDogs(Long idDogBreed1, Long idDogBreed2);

    BreedComparisonResult compareMixedBreeds(Long idDogBreed, Long idCatBreed);

}
