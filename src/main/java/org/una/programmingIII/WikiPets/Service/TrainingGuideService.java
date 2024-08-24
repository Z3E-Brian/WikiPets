package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Review;
import org.una.programmingIII.WikiPets.Model.TrainingGuide;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Repository.TrainingGuideRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface TrainingGuideService {
    List<TrainingGuideDto> getAllTrainingGuides();

    TrainingGuideDto getTrainingGuideById(Long id);

    TrainingGuideDto getTrainingGuideByTitle(String title);

    TrainingGuideDto createTrainingGuide(TrainingGuideDto trainingGuideDto);

    TrainingGuideDto updateTrainingGuide(TrainingGuideDto trainingGuideDto);
    List<TrainingGuideDto> searchByTitle(String title);

    void deleteTrainingGuide(Long id);
}