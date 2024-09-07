package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;

import java.util.List;

public interface CatBreedService {

    CatBreedDto getBreedById(Long id);

    CatBreedDto createCatBreed(CatBreedDto catBreedDto);

    void deleteCatBreed(Long id);

    CatBreedDto updateCatBreed(CatBreedDto catBreedDto);

    Page<CatBreedDto> getAllCatBreeds(Pageable pageable);
}