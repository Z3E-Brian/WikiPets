package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.CatBreedMapper;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface CatBreedService {
    List<CatBreedDto> getAllBreeds();

    CatBreedDto getBreedById(Long id);

    CatBreedDto createCatBreed(CatBreedDto catBreedDto);

    void deleteCatBreed(Long id);

    CatBreedDto updateCatBreed(CatBreedDto catBreedDto);
}