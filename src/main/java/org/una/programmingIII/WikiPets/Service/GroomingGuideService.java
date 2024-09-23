package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;

import java.util.List;
import java.util.Map;

public interface GroomingGuideService {

    GroomingGuideDto getGroomingGuideById(Long id);

    GroomingGuideDto updateGroomingGuide(GroomingGuideDto groomingGuideDto);

    GroomingGuideDto addSuitableDogBreedToGroomingGuide(Long id, Long idDogBreed);

    GroomingGuideDto addSuitableCatBreedToGroomingGuide(Long id, Long idDogBreed);

    GroomingGuideDto createGroomingGuide(GroomingGuideDto groomingGuideDto);

    Map<String, Object> getAllGroomingGuides(int page, int size);

    List<DogBreedDto> getGroomingSuitableDogBreeds(Long groomingGuideId);

    List<CatBreedDto> getGroomingSuitableCatBreeds(Long groomingGuideId);

    GroomingGuideDto deleteSuitableCatBreedFromGroomingGuide(Long id, Long idCatBreed);

    GroomingGuideDto deleteSuitableDogBreedFromGroomingGuide(Long id, Long idDogBreed);

    Boolean deleteGroomingGuide(Long id);
}
