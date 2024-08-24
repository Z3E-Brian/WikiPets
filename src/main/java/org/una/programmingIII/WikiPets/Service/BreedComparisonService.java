package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.BreedComparisonResult;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;

import java.util.List;

public interface BreedComparisonService {
    BreedComparisonResult compareBreeds(Object breed1, Object breed2);

    void compareCatBreeds(CatBreed catBreed1, CatBreed catBreed2, List<String> similarities, List<String> differences);

    void compareDogBreeds(DogBreed dogBreed1, DogBreed dogBreed2, List<String> similarities, List<String> differences);

    void compareMixedBreeds(DogBreed dogBreed, CatBreed catBreed, List<String> similarities, List<String> differences);

    void compareFields(String field1, String field2, String fieldName, List<String> similarities, List<String> differences);
}
