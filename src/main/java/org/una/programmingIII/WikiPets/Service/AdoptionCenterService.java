package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;

import java.util.List;
import java.util.Map;

public interface AdoptionCenterService {
    AdoptionCenterDto getAdoptionCenterById(Long id);

    AdoptionCenterDto updateAdoptionCenter(AdoptionCenterDto adoptionCenterDto);

    AdoptionCenterDto addDogBreedInAdoptionCenter(Long id, Long idDogBreed);

    AdoptionCenterDto addCatBreedInAdoptionCenter(Long id, Long idDogBreed);

    AdoptionCenterDto createAdoptionCenter(AdoptionCenterDto adoptionCenterDto);

    AdoptionCenterDto removeCatBreedFromAdoptionCenter(Long centerId, Long catBreedId);

    AdoptionCenterDto removeDogBreedFromAdoptionCenter(Long centerId, Long dogBreedId);

    void deleteAdoptionCenter(Long id);

    List<DogBreedDto> getAvailableDogBreeds(Long adoptionCenterId);

    List<CatBreedDto> getAvailableCatBreeds(Long adoptionCenterId);

    Map<String, Object> getAllAdoptionCenters(int page, int size);
}
