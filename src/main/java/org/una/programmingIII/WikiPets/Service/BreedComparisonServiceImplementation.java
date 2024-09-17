package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedComparisonServiceImplementation implements BreedComparisonService {

    private final DogBreedService dogBreedService;

    private final CatBreedService catBreedService;


    @Autowired
    public BreedComparisonServiceImplementation(DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }

    @Override
    public BreedComparisonResult compareCats(Long idCatBreed1, Long idCatBreed2) {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();

        CatBreedDto catBreed1 = catBreedService.getBreedById(idCatBreed1);
        CatBreedDto catBreed2 = catBreedService.getBreedById(idCatBreed2);

        compareCatBreeds(catBreed1, catBreed2, similarities, differences);
        return new BreedComparisonResult(similarities, differences);

    }

    @Override
    public BreedComparisonResult compareDogs(Long idDogBreed1, Long idDogBreed2) {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();

        DogBreedDto dogBreed1 = dogBreedService.getBreedById(idDogBreed1);
        DogBreedDto dogBreed2 = dogBreedService.getBreedById(idDogBreed2);

        compareDogBreeds(dogBreed1, dogBreed2, similarities, differences);
        return new BreedComparisonResult(similarities, differences);
    }

    @Override
    public BreedComparisonResult compareMixedBreeds(Long idDogBreed, Long idCatBreed) {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();

        DogBreedDto dogBreed = dogBreedService.getBreedById(idDogBreed);
        CatBreedDto catBreedDto = catBreedService.getBreedById(idCatBreed);

        compareMixedBreeds(dogBreed, catBreedDto, similarities, differences);
        return new BreedComparisonResult(similarities, differences);
    }


    private void compareCatBreeds(CatBreedDto catBreed1, CatBreedDto catBreed2, List<String> similarities, List<String> differences) {
        compareFields(catBreed1.getName(), catBreed2.getName(), "Name", similarities, differences);
        compareFields(catBreed1.getOrigin(), catBreed2.getOrigin(), "Origin", similarities, differences);
        compareFields(String.valueOf(catBreed1.getSize()), String.valueOf(catBreed2.getSize()), "Size", similarities, differences);
        compareFields(catBreed1.getCoat(), catBreed2.getCoat(), "Coat", similarities, differences);
        compareFields(catBreed1.getColor(), catBreed2.getColor(), "Color", similarities, differences);
        compareFields(catBreed1.getLifeSpan(), catBreed2.getLifeSpan(), "Life Span", similarities, differences);
        compareFields(catBreed1.getTemperament(), catBreed2.getTemperament(), "Temperament", similarities, differences);
        compareFields(catBreed1.getDescription(), catBreed2.getDescription(), "Description", similarities, differences);
    }


    private void compareDogBreeds(DogBreedDto dogBreed1, DogBreedDto dogBreed2, List<String> similarities, List<String> differences) {
        compareFields(dogBreed1.getName(), dogBreed2.getName(), "Name", similarities, differences);
        compareFields(dogBreed1.getOrigin(), dogBreed2.getOrigin(), "Origin", similarities, differences);
        compareFields(String.valueOf(dogBreed1.getSize()), String.valueOf(dogBreed2.getSize()), "Size", similarities, differences);
        compareFields(dogBreed1.getCoat(), dogBreed2.getCoat(), "Coat", similarities, differences);
        compareFields(dogBreed1.getColor(), dogBreed2.getColor(), "Color", similarities, differences);
        compareFields(dogBreed1.getLifeSpan(), dogBreed2.getLifeSpan(), "Life Span", similarities, differences);
        compareFields(dogBreed1.getTemperament(), dogBreed2.getTemperament(), "Temperament", similarities, differences);
        compareFields(dogBreed1.getDescription(), dogBreed2.getDescription(), "Description", similarities, differences);
    }


    private void compareMixedBreeds(DogBreedDto dogBreed, CatBreedDto catBreed, List<String> similarities, List<String> differences) {
        compareFields(dogBreed.getName(), catBreed.getName(), "Name", similarities, differences);
        compareFields(dogBreed.getOrigin(), catBreed.getOrigin(), "Origin", similarities, differences);
        compareFields(String.valueOf(dogBreed.getSize()), String.valueOf(catBreed.getSize()), "Size", similarities, differences);
        compareFields(dogBreed.getCoat(), catBreed.getCoat(), "Coat", similarities, differences);
        compareFields(dogBreed.getColor(), catBreed.getColor(), "Color", similarities, differences);
        compareFields(dogBreed.getLifeSpan(), catBreed.getLifeSpan(), "Life Span", similarities, differences);
        compareFields(dogBreed.getTemperament(), catBreed.getTemperament(), "Temperament", similarities, differences);
        compareFields(dogBreed.getDescription(), catBreed.getDescription(), "Description", similarities, differences);
    }

    private void compareFields(String field1, String field2, String fieldName, List<String> similarities, List<String> differences) {
        if (field1.equals(field2)) {
            similarities.add(fieldName + ": " + field1);
        } else {
            differences.add(fieldName + ": " + field1 + " vs " + field2);
        }
    }
}
