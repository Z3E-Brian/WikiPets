package org.una.programmingIII.WikiPets.Service;

import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedComparisonService {

    public BreedComparisonResult compareBreeds(DogBreed dogBreed, CatBreed catBreed) {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();

        compareFields(dogBreed.getName(), catBreed.getName(), "Name", similarities, differences);
        compareFields(dogBreed.getOrigin(), catBreed.getOrigin(), "Origin", similarities, differences);
        compareFields(String.valueOf(dogBreed.getSize()), String.valueOf(catBreed.getSize()), "Size", similarities, differences);
        compareFields(dogBreed.getCoat(), catBreed.getCoat(), "Coat", similarities, differences);
        compareFields(dogBreed.getColor(), catBreed.getColor(), "Color", similarities, differences);
        compareFields(dogBreed.getLifeSpan(), catBreed.getLifeSpan(), "Life Span", similarities, differences);
        compareFields(dogBreed.getTemperament(), catBreed.getTemperament(), "Temperament", similarities, differences);
        compareFields(dogBreed.getDescription(), catBreed.getDescription(), "Description", similarities, differences);

        return new BreedComparisonResult(similarities, differences);
    }

    private void compareFields(String field1, String field2, String fieldName, List<String> similarities, List<String> differences) {
        if (field1.equals(field2)) {
            similarities.add(fieldName + ": " + field1);
        } else {
            differences.add(fieldName + ": " + field1 + " vs " + field2);
        }
    }

    public static class BreedComparisonResult {
        private List<String> similarities;
        private List<String> differences;

        public BreedComparisonResult(List<String> similarities, List<String> differences) {
            this.similarities = similarities;
            this.differences = differences;
        }

        public List<String> getSimilarities() {
            return similarities;
        }

        public void setSimilarities(List<String> similarities) {
            this.similarities = similarities;
        }

        public List<String> getDifferences() {
            return differences;
        }

        public void setDifferences(List<String> differences) {
            this.differences = differences;
        }
    }
}




