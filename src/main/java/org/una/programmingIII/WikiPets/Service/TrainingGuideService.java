package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.TrainingGuideDto;

import java.util.List;

public interface TrainingGuideService {
    List<TrainingGuideDto> getAllTrainingGuides();

    TrainingGuideDto getTrainingGuideById(Long id);

    TrainingGuideDto getTrainingGuideByTitle(String title);

    TrainingGuideDto createTrainingGuide(TrainingGuideDto trainingGuideDto);

    TrainingGuideDto updateTrainingGuide(TrainingGuideDto trainingGuideDto);
    List<TrainingGuideDto> searchByTitle(String title);
    List<TrainingGuideDto> getTrainingGuidesByCatBreedId(Long catBreedId);
    List<TrainingGuideDto> getTrainingGuidesByDogBreedId(Long dogBreedId);

    void deleteTrainingGuide(Long id);
}
