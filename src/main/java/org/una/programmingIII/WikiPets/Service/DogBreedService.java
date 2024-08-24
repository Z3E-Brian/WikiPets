package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.DogBreedMapper;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;

import java.util.List;
import java.util.stream.Collectors;


public interface DogBreedService {
    List<DogBreedDto> getAllBreeds();

    DogBreedDto getBreedById(Long id);

    DogBreedDto createDogBreed(DogBreedDto dogBreedDto);

    void deleteDogBreed(Long id);

    DogBreedDto updateDogBreed(DogBreedDto dogBreedDto);
}