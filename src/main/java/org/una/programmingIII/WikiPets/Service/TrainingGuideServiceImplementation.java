package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.TrainingGuideRepository;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainingGuideServiceImplementation implements TrainingGuideService {

    private final TrainingGuideRepository trainingGuideRepository;
    private final GenericMapper<TrainingGuide, TrainingGuideDto> trainingGuideMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public TrainingGuideServiceImplementation(TrainingGuideRepository trainingGuideRepository, GenericMapperFactory mapperFactory, DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.trainingGuideRepository = trainingGuideRepository;
        this.trainingGuideMapper = mapperFactory.createMapper(TrainingGuide.class, TrainingGuideDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }


    @Override
    public Map<String, Object> getAllTrainingGuides(int page, int size) {
        Page<TrainingGuide> trainingGuides = trainingGuideRepository.findAll(PageRequest.of(page, size));
        trainingGuides.forEach(trainingGuide -> {
            trainingGuide.setCatBreeds(trainingGuide.getCatBreeds().stream().limit(10).collect(Collectors.toList()));
            trainingGuide.setDogBreeds(trainingGuide.getDogBreeds().stream().limit(10).collect(Collectors.toList()));
        });
        return Map.of("trainingGuides", trainingGuides.map(this::convertToDto).getContent(), "totalPages", trainingGuides.getTotalPages(), "totalElements", trainingGuides.getTotalElements());

    }

    @Override
    public TrainingGuideDto getTrainingGuideById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid Training Guide Schedule ID");
        }
        TrainingGuide trainingGuide = trainingGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Training Guide Not Found with id: " + id));
        return trainingGuideMapper.convertToDTO(trainingGuide);
    }

    @Override
    public TrainingGuideDto createTrainingGuide(@NotNull TrainingGuideDto trainingGuideDto) {
        if (trainingGuideDto.getContent().isBlank() || trainingGuideDto.getTitle().isBlank()) {
            throw new BlankInputException("Can't leave spaces in blank");
        }
        trainingGuideDto.setLastUpdate(LocalDate.now());
        trainingGuideDto.setCreateDate(LocalDate.now());
        TrainingGuide trainingGuide = trainingGuideMapper.convertToEntity(trainingGuideDto);
        TrainingGuide savedTrainingGuide = trainingGuideRepository.save(trainingGuide);
        return trainingGuideMapper.convertToDTO(savedTrainingGuide);
    }

    @Override
    public Boolean deleteTrainingGuide(Long id) {
        if (!trainingGuideRepository.existsById(id)) {
            throw new NotFoundElementException("Training Guide not found with id: " + id);
        }
        TrainingGuide trainingGuide = trainingGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Training Guide not found"));
        trainingGuide.getCatBreeds().forEach(catBreed -> {
            catBreed.getTrainingGuides().removeIf(guide -> guide.getId().equals(id));
        });
        trainingGuide.getDogBreeds().forEach(dogBreed -> {
            dogBreed.getTrainingGuides().removeIf(guide -> guide.getId().equals(id));
        });

        trainingGuideRepository.deleteById(id);
        return true;
    }

    @Override
    public TrainingGuideDto updateTrainingGuide(@NotNull TrainingGuideDto trainingGuideDto) {
        if (trainingGuideDto == null || trainingGuideDto.getId() == null) {
            throw new InvalidInputException("Invalid Training Guide data.");
        }

        if (trainingGuideDto.getContent().isBlank() || trainingGuideDto.getTitle().isBlank()) {
            throw new BlankInputException("Can't leave spaces in blank");
        }

        TrainingGuide oldTrainingGuide = trainingGuideRepository.findById(trainingGuideDto.getId())
                .orElseThrow(() -> new NotFoundElementException("Training Guide with ID: " + trainingGuideDto.getId() + " not found"));

        TrainingGuide newTrainingGuide = trainingGuideMapper.convertToEntity(trainingGuideDto);
        newTrainingGuide.setCreateDate(oldTrainingGuide.getCreateDate());
        newTrainingGuide.setLastUpdate(LocalDate.now());
        newTrainingGuide.setCatBreeds(oldTrainingGuide.getCatBreeds());
        newTrainingGuide.setDogBreeds(oldTrainingGuide.getDogBreeds());
        return trainingGuideMapper.convertToDTO(trainingGuideRepository.save(newTrainingGuide));
    }


    @Override
    public TrainingGuideDto addDogBreedInTrainingGuide(Long id, Long idDogBreed) {
        TrainingGuide trainingGuide = trainingGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("TraningGuide not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!trainingGuide.getDogBreeds().contains(dogBreed)) {
            trainingGuide.getDogBreeds().add(dogBreed);
        }
        return trainingGuideMapper.convertToDTO(trainingGuideRepository.save(trainingGuide));
    }

    @Override
    public TrainingGuideDto addCatBreedInTrainingGuide(Long id, Long idCatBreed) {
        TrainingGuide trainingGuide = trainingGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("TraningGuide not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!trainingGuide.getCatBreeds().contains(catBreed)) {
            trainingGuide.getCatBreeds().add(catBreed);
        }
        return trainingGuideMapper.convertToDTO(trainingGuideRepository.save(trainingGuide));
    }


    @Override
    public TrainingGuideDto deleteDogBreedInTrainingGuide(Long id, Long idDogBreed) {
        TrainingGuide trainingGuide = trainingGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("TrainingGuide not found"));
        DogBreed dogBreed = dogBreedService.getBreedEntityById(idDogBreed);
        trainingGuide.getDogBreeds().remove(dogBreed);
        trainingGuide.setLastUpdate(LocalDate.now());
        return trainingGuideMapper.convertToDTO(trainingGuideRepository.save(trainingGuide));
    }

    @Override
    public TrainingGuideDto deleteCatBreedInTrainingGuide(Long id, Long idCatBreed) {
        TrainingGuide trainingGuide = trainingGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("TrainingGuide not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        trainingGuide.getCatBreeds().remove(catBreed);
        trainingGuide.setLastUpdate(LocalDate.now());
        return trainingGuideMapper.convertToDTO(trainingGuideRepository.save(trainingGuide));
    }

    private TrainingGuideDto convertToDto(TrainingGuide trainingGuide) {
        return trainingGuideMapper.convertToDTO(trainingGuide);
    }


}