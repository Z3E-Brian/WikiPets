package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;

import java.util.List;
import java.util.Map;

public interface CatBreedService {

    CatBreedDto getBreedById(Long id);

    CatBreedDto createCatBreed(CatBreedDto catBreedDto);

    Boolean deleteCatBreed(Long id);

    CatBreedDto updateCatBreed(CatBreedDto catBreedDto);

    Map<String, Object> getAllCatBreeds(int page, int size);
}