package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    List<DogBreedDto> getSuitableDogBreeds(Long GroomingGuideId);

    List<CatBreedDto> getSuitableCatBreeds(Long GroomingGuideId);

    GroomingGuideDto removeSuitableCatBreedFromGroomingGuide(Long id, Long idCatBreed);

    GroomingGuideDto removeSuitableDogBreedFromGroomingGuide(Long id, Long idDogBreed);

    void deleteGroomingGuide(Long id);
}
