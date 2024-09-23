package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;

import java.util.List;
import java.util.Map;


public interface TrainingGuideService {

    Map<String, Object> getAllTrainingGuides(int page, int size);

    TrainingGuideDto getTrainingGuideById(Long id);

    TrainingGuideDto createTrainingGuide(TrainingGuideDto trainingGuideDto);

    TrainingGuideDto updateTrainingGuide(TrainingGuideDto trainingGuideDto);

    TrainingGuideDto addDogBreedInTrainingGuide(Long id, Long idDogBreed);

    TrainingGuideDto addCatBreedInTrainingGuide(Long id, Long idCatBreed);

    TrainingGuideDto deleteDogBreedInTrainingGuide(Long id, Long idDogBreed);

    TrainingGuideDto deleteCatBreedInTrainingGuide(Long id, Long idCatBreed);

    Boolean deleteTrainingGuide(Long id);
}
