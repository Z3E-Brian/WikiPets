package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;

import java.util.List;

public interface BehaviorGuideService {

    BehaviorGuideDto getBehaviorGuideById(Long id);

    List<BehaviorGuideDto> getAllBehaviorGuides();

    BehaviorGuideDto updateBehaviorGuide(BehaviorGuideDto behaviorGuideDto);

    BehaviorGuideDto addSuitableDogBreedToBehaviorGuide(Long id, Long idDogBreed);

    BehaviorGuideDto addSuitableCatBreedToBehaviorGuide(Long id, Long idDogBreed);

    BehaviorGuideDto createBehaviorGuide(BehaviorGuideDto behaviorGuideDto);

    Page<BehaviorGuideDto> getAllBehaviorGuides(Pageable pageable);

    List<DogBreedDto> getBehaviorSuitableDogBreeds(Long BehaviorGuideId);

    void deleteBehaviorGuide(Long id);
}
