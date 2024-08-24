package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.CatBreedDto;

import java.util.List;

public interface CatBreedService {
    List<CatBreedDto> getAllBreeds();

    CatBreedDto getBreedById(Long id);

    CatBreedDto createCatBreed(CatBreedDto catBreedDto);

    void deleteCatBreed(Long id);

    CatBreedDto updateCatBreed(CatBreedDto catBreedDto);
}
