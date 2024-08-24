package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;

import java.util.List;


public interface TrainingGuideService {
    List<TrainingGuideDto> getAllTrainingGuides();

    TrainingGuideDto getTrainingGuideById(Long id);

    TrainingGuideDto getTrainingGuideByTitle(String title);

    TrainingGuideDto createTrainingGuide(TrainingGuideDto trainingGuideDto);

    TrainingGuideDto updateTrainingGuide(TrainingGuideDto trainingGuideDto);
    List<TrainingGuideDto> searchByTitle(String title);

    void deleteTrainingGuide(Long id);
}