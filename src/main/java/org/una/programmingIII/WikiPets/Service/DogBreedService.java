package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;

import java.util.List;

public interface DogBreedService {
    DogBreedDto getBreedById(Long id);

    DogBreedDto createDogBreed(DogBreedDto dogBreedDto);

    void deleteDogBreed(Long id);

    DogBreedDto updateDogBreed(DogBreedDto dogBreedDto);

    Page<DogBreedDto> getAllDogBreeds(Pageable pageable);
}