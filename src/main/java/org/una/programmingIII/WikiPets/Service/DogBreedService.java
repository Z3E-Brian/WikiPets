package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Model.DogBreed;

import java.util.List;
import java.util.Map;

public interface DogBreedService {
    DogBreedDto getBreedById(Long id);

    DogBreed getBreedEntityById(Long id);

    DogBreedDto createDogBreed(DogBreedDto dogBreedDto);

    void deleteDogBreed(Long id);

    DogBreedDto updateDogBreed(DogBreedDto dogBreedDto);

    Map<String, Object> getAllDogBreeds( int page, int size);
}