package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Validated
public class BehaviorGuideServiceImplementation implements BehaviorGuideService {
    private final BehaviorGuideRepository behaviorGuideRepository;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final GenericMapper<BehaviorGuide, BehaviorGuideDto> behaviorGuideMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public BehaviorGuideServiceImplementation(
            BehaviorGuideRepository behaviorGuideRepository,
            GenericMapperFactory mapperFactory,
            DogBreedService dogBreedService,
            CatBreedService catBreedService) {

        this.behaviorGuideRepository = behaviorGuideRepository;
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.behaviorGuideMapper = mapperFactory.createMapper(BehaviorGuide.class, BehaviorGuideDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }

    @Override
    public Map<String, Object> getAllBehaviorGuides(int page, int size) {
        Page<BehaviorGuide> behaviorGuides = behaviorGuideRepository.findAll(PageRequest.of(page, size));
        behaviorGuides.forEach(behaviorGuide -> {
            behaviorGuide.setSuitableCatBreeds(behaviorGuide.getSuitableCatBreeds().stream().limit(10).collect(Collectors.toList()));
            behaviorGuide.setSuitableDogBreeds(behaviorGuide.getSuitableDogBreeds().stream().limit(10).collect(Collectors.toList()));
        });
        Map<String, Object> response = new HashMap<>();
        response.put("behaviorGuides", behaviorGuides.map(this::convertToDto).getContent());
        response.put("totalPages", behaviorGuides.getTotalPages());
        response.put("totalElements", behaviorGuides.getTotalElements());
        return response;
    }

    @Override
    public BehaviorGuideDto getBehaviorGuideById(Long id) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Behavior guide Not Found with id: " + id));
        return convertToDto(behaviorGuide);
    }

    @Override
    public BehaviorGuideDto createBehaviorGuide(@Validated BehaviorGuideDto behaviorGuideDto) {
        behaviorGuideDto.setCreateDate(LocalDate.now());
        behaviorGuideDto.setLastUpdate(LocalDate.now());
        BehaviorGuide behaviorGuide = convertToEntity(behaviorGuideDto);
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public Boolean deleteBehaviorGuide(Long id) {
        if (id <= 0) {
            throw new InvalidInputException("Invalid ID");
        }
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Behavior guide Not Found with id: " + id));
        behaviorGuide.getSuitableCatBreeds().forEach(catBreed ->
                catBreed.getHealthIssues().removeIf(behavior -> behavior.getId().equals(id))
        );
        behaviorGuide.getSuitableDogBreeds().forEach(dogBreed ->
                dogBreed.getHealthIssues().removeIf(behavior -> behavior.getId().equals(id))
        );
        behaviorGuideRepository.deleteById(id);
        return true;
    }

    @Override
    public BehaviorGuideDto updateBehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        BehaviorGuide oldBehaviorGuide = behaviorGuideRepository.findById(behaviorGuideDto.getId())
                .orElseThrow(() -> new RuntimeException("Behavior guide Not Found with id: " + behaviorGuideDto.getId()));
        BehaviorGuide newBehaviorGuide = convertToEntity(behaviorGuideDto);
        newBehaviorGuide.setCreateDate(oldBehaviorGuide.getCreateDate());
        newBehaviorGuide.setLastUpdate(LocalDate.now());

        newBehaviorGuide.setSuitableCatBreeds(oldBehaviorGuide.getSuitableCatBreeds() != null ?
                new ArrayList<>(oldBehaviorGuide.getSuitableCatBreeds()) : new ArrayList<>());
        newBehaviorGuide.setSuitableDogBreeds(oldBehaviorGuide.getSuitableDogBreeds() != null ?
                new ArrayList<>(oldBehaviorGuide.getSuitableDogBreeds()) : new ArrayList<>());

        return convertToDto(behaviorGuideRepository.save(newBehaviorGuide));
    }

    @Override
    public BehaviorGuideDto addSuitableDogBreedToBehaviorGuide(Long id, Long idDogBreed) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Behavior guide Not Found with id: " + id));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!behaviorGuide.getSuitableDogBreeds().contains(dogBreed)) {
            behaviorGuide.getSuitableDogBreeds().add(dogBreed);
        }
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public BehaviorGuideDto addSuitableCatBreedToBehaviorGuide(Long id, Long idCatBreed) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Behavior guide Not Found with id: " + id));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!behaviorGuide.getSuitableCatBreeds().contains(catBreed)) {
            behaviorGuide.getSuitableCatBreeds().add(catBreed);
        }
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public List<DogBreedDto> getBehaviorSuitableDogBreeds(Long behaviorGuideId) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(behaviorGuideId)
                .orElseThrow(() -> new RuntimeException("Behavior guide Not Found with id: " + behaviorGuideId));
        return behaviorGuide.getSuitableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatBreedDto> getBehaviorSuitableCatBreeds(Long behaviorGuideId) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(behaviorGuideId)
                .orElseThrow(() -> new RuntimeException("Behavior guide Not Found with id: " + behaviorGuideId));
        return behaviorGuide.getSuitableCatBreeds().stream()
                .map(catBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BehaviorGuideDto removeSuitableCatBreedFromBehaviorGuide(Long id, Long idCatBreed) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Behavior guide Not Found with id: " + id));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (catBreed == null) {
            throw new RuntimeException("Cat breed Not Found with id: " + idCatBreed);
        }
        if (behaviorGuide.getSuitableCatBreeds().contains(catBreed)) {
            behaviorGuide.getSuitableCatBreeds().remove(catBreed);
        } else {
            throw new RuntimeException("Cat breed Not Found in Behavior guide");
        }
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public BehaviorGuideDto removeSuitableDogBreedFromBehaviorGuide(Long id, Long idDogBreed) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Behavior guide Not Found with id: " + id));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (dogBreed == null) {
            throw new RuntimeException("Dog breed Not Found with id: " + idDogBreed);
        }
        if (behaviorGuide.getSuitableDogBreeds().contains(dogBreed)) {
            behaviorGuide.getSuitableDogBreeds().remove(dogBreed);
        } else {
            throw new RuntimeException("Dog breed Not Found in Behavior guide");
        }
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    private BehaviorGuideDto convertToDto(BehaviorGuide behaviorGuide) {
        return behaviorGuideMapper.convertToDTO(behaviorGuide);
    }

    private BehaviorGuide convertToEntity(BehaviorGuideDto behaviorGuideDto) {
        return behaviorGuideMapper.convertToEntity(behaviorGuideDto);
    }
}