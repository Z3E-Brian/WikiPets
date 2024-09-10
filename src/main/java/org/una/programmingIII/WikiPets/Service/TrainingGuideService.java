package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;

import java.util.List;


public interface TrainingGuideService {
    List<TrainingGuideDto> getAllTrainingGuides();

    Page<TrainingGuideDto> getAllTrainingGuides(Pageable pageable);

    TrainingGuideDto getTrainingGuideById(Long id);

    TrainingGuideDto getTrainingGuideByTitle(String title);

    TrainingGuideDto createTrainingGuide(TrainingGuideDto trainingGuideDto);

    TrainingGuideDto updateTrainingGuide(TrainingGuideDto trainingGuideDto);

    List<TrainingGuideDto> searchByTitle(String title);

    TrainingGuideDto addDogBreedInTrainingGuide(Long id, Long idDogBreed);

    TrainingGuideDto deleteDogBreedInTrainingGuide(Long id, Long idDogBreed);

    void deleteTrainingGuide(Long id);
}