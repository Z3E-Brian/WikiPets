package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;

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

    BehaviorGuideDto deleteSuitableCatBreedFromBehaviorGuide(Long id, Long idCatBreed);

    BehaviorGuideDto deleteSuitableDogBreedFromBehaviorGuide(Long id, Long idDogBreed);

}
