package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;

import java.util.List;
import java.util.Map;


public interface BehaviorGuideService {
    Map<String, Object> getAllBehaviorGuides(int page,  int size);

    BehaviorGuideDto getBehaviorGuideById(Long id);

    BehaviorGuideDto createBehaviorGuide(BehaviorGuideDto behaviorGuideDto);

    Boolean deleteBehaviorGuide(Long id);

    BehaviorGuideDto updateBehaviorGuide(BehaviorGuideDto behaviorGuideDto);

    BehaviorGuideDto addSuitableDogBreedToBehaviorGuide(Long id, Long idDogBreed);

    BehaviorGuideDto addSuitableCatBreedToBehaviorGuide(Long id, Long idDogBreed);

    List<DogBreedDto> getBehaviorSuitableDogBreeds(Long BehaviorGuideId);

    List<CatBreedDto> getBehaviorSuitableCatBreeds(Long BehaviorGuideId);

    BehaviorGuideDto removeSuitableCatBreedFromBehaviorGuide(Long id, Long idCatBreed);

    BehaviorGuideDto removeSuitableDogBreedFromBehaviorGuide(Long id, Long idDogBreed);

}
