package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.DogBreedDto;

import java.util.List;

public interface DogBreedService {
    List<DogBreedDto> getAllBreeds();

    DogBreedDto getBreedById(Long id);

    DogBreedDto createDogBreed(DogBreedDto dogBreedDto);

    void deleteDogBreed(Long id);

    DogBreedDto updateDogBreed(DogBreedDto dogBreedDto);
}
