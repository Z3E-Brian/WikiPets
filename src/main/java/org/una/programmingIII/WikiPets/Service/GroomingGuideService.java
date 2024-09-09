package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;

import java.util.List;

public interface GroomingGuideService {

    GroomingGuideDto getGroomingGuideById(Long id);

    List<GroomingGuideDto> getAllGroomingGuides();

    GroomingGuideDto updateGroomingGuide(GroomingGuideDto groomingGuideDto);

    GroomingGuideDto addSuitableDogBreedToGroomingGuide(Long id, Long idDogBreed);

    GroomingGuideDto addSuitableCatBreedToGroomingGuide(Long id, Long idDogBreed);

    GroomingGuideDto createGroomingGuide(GroomingGuideDto groomingGuideDto);

    Page<GroomingGuideDto> getAllGroomingGuides(Pageable pageable);

    List<DogBreedDto> getSuitableDogBreeds(Long GroomingGuideId);

    void deleteGroomingGuide(Long id);
}
