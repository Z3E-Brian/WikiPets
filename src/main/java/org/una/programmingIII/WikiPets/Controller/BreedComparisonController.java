package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Model.BreedComparisonResult;
import org.una.programmingIII.WikiPets.Service.BreedComparisonService;

@RequiredArgsConstructor
@Controller
public class BreedComparisonController {
    BreedComparisonService breedComparisonService;

    @Autowired
    public BreedComparisonController(BreedComparisonService breedComparisonService) {
        this.breedComparisonService = breedComparisonService;
    }

    @QueryMapping
    public BreedComparisonResult getCatsBreedComparation(@Argument Long idCatBreed1, @Argument Long idCatBreed2) {
        try {
            return breedComparisonService.compareCats(idCatBreed1, idCatBreed2);
        } catch (Exception e) {
            throw new CustomException("Could not compare cats" + e.getMessage());
        }
    }

    @QueryMapping
    public BreedComparisonResult getDogsBreedComparation(@Argument Long idDogBreed1, @Argument Long idDogBreed2) {
        try {
            return breedComparisonService.compareDogs(idDogBreed1, idDogBreed2);
        } catch (Exception e) {
            throw new CustomException("Could not compare dogs" + e.getMessage());
        }
    }

    @QueryMapping
    public BreedComparisonResult getMixedBreedsComparation(@Argument Long idDogBreed, @Argument Long idCatBreed) {
        try {
            return breedComparisonService.compareMixedBreeds(idDogBreed, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not compared mixed breeds" + e.getMessage());
        }
    }


}
