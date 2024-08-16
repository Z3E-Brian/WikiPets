package org.una.programmingIII.WikiPets.Service;

import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.BreedComparisonResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedComparisonService {

    public BreedComparisonResult compareBreeds(Object breed1, Object breed2) {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();

        if (breed1 instanceof CatBreed && breed2 instanceof CatBreed) {
            CatBreed catBreed1 = (CatBreed) breed1;
            CatBreed catBreed2 = (CatBreed) breed2;
            compareCatBreeds(catBreed1, catBreed2, similarities, differences);
        } else if (breed1 instanceof DogBreed && breed2 instanceof DogBreed) {
            DogBreed dogBreed1 = (DogBreed) breed1;
            DogBreed dogBreed2 = (DogBreed) breed2;
            compareDogBreeds(dogBreed1, dogBreed2, similarities, differences);
        } else if (breed1 instanceof DogBreed && breed2 instanceof CatBreed) {
            DogBreed dogBreed = (DogBreed) breed1;
            CatBreed catBreed = (CatBreed) breed2;
            compareMixedBreeds(dogBreed, catBreed, similarities, differences);
        } else if (breed1 instanceof CatBreed && breed2 instanceof DogBreed) {
            CatBreed catBreed = (CatBreed) breed1;
            DogBreed dogBreed = (DogBreed) breed2;
            compareMixedBreeds(dogBreed, catBreed, similarities, differences);
        }

        return new BreedComparisonResult(similarities, differences);
    }

    public void compareCatBreeds(CatBreed catBreed1, CatBreed catBreed2, List<String> similarities, List<String> differences) {
        compareFields(catBreed1.getName(), catBreed2.getName(), "Name", similarities, differences);
        compareFields(catBreed1.getOrigin(), catBreed2.getOrigin(), "Origin", similarities, differences);
        compareFields(String.valueOf(catBreed1.getSize()), String.valueOf(catBreed2.getSize()), "Size", similarities, differences);
        compareFields(catBreed1.getCoat(), catBreed2.getCoat(), "Coat", similarities, differences);
        compareFields(catBreed1.getColor(), catBreed2.getColor(), "Color", similarities, differences);
        compareFields(catBreed1.getLifeSpan(), catBreed2.getLifeSpan(), "Life Span", similarities, differences);
        compareFields(catBreed1.getTemperament(), catBreed2.getTemperament(), "Temperament", similarities, differences);
        compareFields(catBreed1.getDescription(), catBreed2.getDescription(), "Description", similarities, differences);
    }

    public void compareDogBreeds(DogBreed dogBreed1, DogBreed dogBreed2, List<String> similarities, List<String> differences) {
        compareFields(dogBreed1.getName(), dogBreed2.getName(), "Name", similarities, differences);
        compareFields(dogBreed1.getOrigin(), dogBreed2.getOrigin(), "Origin", similarities, differences);
        compareFields(String.valueOf(dogBreed1.getSize()), String.valueOf(dogBreed2.getSize()), "Size", similarities, differences);
        compareFields(dogBreed1.getCoat(), dogBreed2.getCoat(), "Coat", similarities, differences);
        compareFields(dogBreed1.getColor(), dogBreed2.getColor(), "Color", similarities, differences);
        compareFields(dogBreed1.getLifeSpan(), dogBreed2.getLifeSpan(), "Life Span", similarities, differences);
        compareFields(dogBreed1.getTemperament(), dogBreed2.getTemperament(), "Temperament", similarities, differences);
        compareFields(dogBreed1.getDescription(), dogBreed2.getDescription(), "Description", similarities, differences);
    }

    public void compareMixedBreeds(DogBreed dogBreed, CatBreed catBreed, List<String> similarities, List<String> differences) {
        compareFields(dogBreed.getName(), catBreed.getName(), "Name", similarities, differences);
        compareFields(dogBreed.getOrigin(), catBreed.getOrigin(), "Origin", similarities, differences);
        compareFields(String.valueOf(dogBreed.getSize()), String.valueOf(catBreed.getSize()), "Size", similarities, differences);
        compareFields(dogBreed.getCoat(), catBreed.getCoat(), "Coat", similarities, differences);
        compareFields(dogBreed.getColor(), catBreed.getColor(), "Color", similarities, differences);
        compareFields(dogBreed.getLifeSpan(), catBreed.getLifeSpan(), "Life Span", similarities, differences);
        compareFields(dogBreed.getTemperament(), catBreed.getTemperament(), "Temperament", similarities, differences);
        compareFields(dogBreed.getDescription(), catBreed.getDescription(), "Description", similarities, differences);
    }

    public void compareFields(String field1, String field2, String fieldName, List<String> similarities, List<String> differences) {
        if (field1.equals(field2)) {
            similarities.add(fieldName + ": " + field1);
        } else {
            differences.add(fieldName + ": " + field1 + " vs " + field2);
        }
    }
}
