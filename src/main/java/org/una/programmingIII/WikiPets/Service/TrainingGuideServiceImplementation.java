package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
import org.una.programmingIII.WikiPets.Model.TrainingGuide;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Repository.TrainingGuideRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainingGuideServiceImplementation implements TrainingGuideService {

    private final TrainingGuideRepository trainingGuideRepository;
    private final GenericMapper<TrainingGuide, TrainingGuideDto> trainingGuideMapper;

    @Autowired
    public TrainingGuideServiceImplementation(TrainingGuideRepository trainingGuideRepository, GenericMapperFactory mapperFactory) {
        this.trainingGuideRepository = trainingGuideRepository;
        this.trainingGuideMapper = mapperFactory.createMapper(TrainingGuide.class, TrainingGuideDto.class);
    }

    @Override
    public List<TrainingGuideDto> getAllTrainingGuides() {
        List<TrainingGuide> trainingGuides = trainingGuideRepository.findAll();
        return trainingGuides.stream().map(this.trainingGuideMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Page<TrainingGuideDto> getAllTrainingGuides(Pageable pageable) {
        Page<TrainingGuide> trainingGuides = trainingGuideRepository.findAll(pageable);
        return trainingGuides.map(trainingGuideMapper::convertToDTO);
    }

    @Override
    public TrainingGuideDto getTrainingGuideById(Long id) {
        TrainingGuide trainingGuide = trainingGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training Guide Not Found with id: " + id));
        return trainingGuideMapper.convertToDTO(trainingGuide);
    }

    @Override
    public TrainingGuideDto getTrainingGuideByTitle(String title) {
        TrainingGuide trainingGuide = trainingGuideRepository.findByTitle(title);
        return trainingGuideMapper.convertToDTO(trainingGuide);
    }

    @Override
    public TrainingGuideDto createTrainingGuide(TrainingGuideDto trainingGuideDto) {
        TrainingGuide trainingGuide = trainingGuideMapper.convertToEntity(trainingGuideDto);
        TrainingGuide savedTrainingGuide = trainingGuideRepository.save(trainingGuide);
        return trainingGuideMapper.convertToDTO(savedTrainingGuide);
    }

    @Override
    public void deleteTrainingGuide(Long id) {
        trainingGuideRepository.deleteById(id);
    }

    @Override
    public TrainingGuideDto updateTrainingGuide(TrainingGuideDto trainingGuideDto) {
        TrainingGuide trainingGuide = trainingGuideMapper.convertToEntity(trainingGuideDto);
        TrainingGuide updatedTrainingGuide = trainingGuideRepository.save(trainingGuide);
        return trainingGuideMapper.convertToDTO(updatedTrainingGuide);
    }

    @Override
    public List<TrainingGuideDto> searchByTitle(String title) {
        List<TrainingGuide> trainingGuides = trainingGuideRepository.findByTitleContainingIgnoreCase(title);
        return trainingGuides.stream().map(this.trainingGuideMapper::convertToDTO).collect(Collectors.toList());
    }


}