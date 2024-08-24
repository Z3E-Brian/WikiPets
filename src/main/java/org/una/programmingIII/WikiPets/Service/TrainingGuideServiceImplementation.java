package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.TrainingGuideMapper;
import org.una.programmingIII.WikiPets.Model.TrainingGuide;
import org.una.programmingIII.WikiPets.Model.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Repository.TrainingGuideRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingGuideServiceImplementation implements TrainingGuideService {
    private final TrainingGuideRepository trainingGuideRepository;
    private final TrainingGuideMapper trainingGuideMapper;

    @Autowired
    public TrainingGuideServiceImplementation(TrainingGuideRepository trainingGuideRepository) {
        this.trainingGuideRepository = trainingGuideRepository;
        this.trainingGuideMapper = TrainingGuideMapper.INSTANCE;
    }

    @Override
    public List<TrainingGuideDto> getAllTrainingGuides() {
        List<TrainingGuide> trainingGuides = trainingGuideRepository.findAll();
        return trainingGuides.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TrainingGuideDto getTrainingGuideById(Long id) {
        TrainingGuide trainingGuide = trainingGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training Guide Not Found with id: " + id));
        return convertToDto(trainingGuide);
    }

    @Override
    public TrainingGuideDto createTrainingGuide(TrainingGuideDto trainingGuideDto) {
        TrainingGuide trainingGuide = convertToEntity(trainingGuideDto);
        TrainingGuide savedTrainingGuide = trainingGuideRepository.save(trainingGuide);
        return convertToDto(savedTrainingGuide);
    }

    @Override
    public void deleteTrainingGuide(Long id) {
        trainingGuideRepository.deleteById(id);
    }

    @Override
    public TrainingGuideDto updateTrainingGuide(TrainingGuideDto trainingGuideDto) {
        TrainingGuide trainingGuide = convertToEntity(trainingGuideDto);
        TrainingGuide updatedTrainingGuide = trainingGuideRepository.save(trainingGuide);
        return convertToDto(updatedTrainingGuide);
    }

    @Override
    public TrainingGuideDto getTrainingGuideByTitle(String title) {
        TrainingGuide trainingGuide = trainingGuideRepository.findByTitle(title);
        return convertToDto(trainingGuide);
    }

    @Override
    public List<TrainingGuideDto> searchByTitle(String title) {
        List<TrainingGuide> trainingGuides = trainingGuideRepository.findByTitleContainingIgnoreCase(title);
        return trainingGuides.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TrainingGuideDto> getTrainingGuidesByCatBreedId(Long catBreedId) {
        List<TrainingGuide> trainingGuides = trainingGuideRepository.findByCatBreedsId(catBreedId);
        return trainingGuides.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TrainingGuideDto> getTrainingGuidesByDogBreedId(Long dogBreedId) {
        List<TrainingGuide> trainingGuides = trainingGuideRepository.findByDogBreedsId(dogBreedId);
        return trainingGuides.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TrainingGuideDto convertToDto(TrainingGuide trainingGuide) {
        return TrainingGuideMapper.INSTANCE.toTrainingGuideDto(trainingGuide);
    }

    private TrainingGuide convertToEntity(TrainingGuideDto trainingGuideDto) {
        return TrainingGuideMapper.INSTANCE.toTrainingGuide(trainingGuideDto);
    }
}
