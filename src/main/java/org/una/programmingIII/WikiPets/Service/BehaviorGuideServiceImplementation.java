package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
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
    public BehaviorGuideDto getBehaviorGuideById(@NotNull Long id) {
        validateId(id);
        return behaviorGuideRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundElementException("Behavior guide Not Found with id: " + id));
    }

    @Override
    public BehaviorGuideDto createBehaviorGuide(@NotNull BehaviorGuideDto behaviorGuideDto) {
        validateBehaviorGuideDto(behaviorGuideDto);
        behaviorGuideDto.setCreateDate(LocalDate.now());
        behaviorGuideDto.setLastUpdate(LocalDate.now());
        BehaviorGuide behaviorGuide = convertToEntity(behaviorGuideDto);
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public Boolean deleteBehaviorGuide(@NotNull Long id) {
        validateId(id);
        BehaviorGuide behaviorGuide = findBehaviorGuideById(id);
        removeBreedsFromBehaviorGuide(behaviorGuide, id);
        behaviorGuideRepository.deleteById(id);
        return true;
    }

    @Override
    public BehaviorGuideDto updateBehaviorGuide(@NotNull BehaviorGuideDto behaviorGuideDto) {
        validateId(behaviorGuideDto.getId());
        validateBehaviorGuideDto(behaviorGuideDto);

        BehaviorGuide oldBehaviorGuide = findBehaviorGuideById(behaviorGuideDto.getId());
        BehaviorGuide newBehaviorGuide = convertToEntity(behaviorGuideDto);
        newBehaviorGuide.setCreateDate(oldBehaviorGuide.getCreateDate());
        newBehaviorGuide.setLastUpdate(LocalDate.now());
        copyCollections(oldBehaviorGuide, newBehaviorGuide);
        return convertToDto(behaviorGuideRepository.save(newBehaviorGuide));
    }

    @Override
    public BehaviorGuideDto addSuitableDogBreedToBehaviorGuide(@NotNull Long id,@NotNull Long idDogBreed) {
        BehaviorGuide behaviorGuide = findBehaviorGuideById(id);
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!behaviorGuide.getSuitableDogBreeds().contains(dogBreed)) {
            behaviorGuide.getSuitableDogBreeds().add(dogBreed);
        }
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public BehaviorGuideDto addSuitableCatBreedToBehaviorGuide(@NotNull Long id,@NotNull Long idCatBreed) {
        validateId(id);
        validateId(idCatBreed);
        BehaviorGuide behaviorGuide = findBehaviorGuideById(id);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!behaviorGuide.getSuitableCatBreeds().contains(catBreed)) {
            behaviorGuide.getSuitableCatBreeds().add(catBreed);
        }
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public List<DogBreedDto> getBehaviorSuitableDogBreeds(@NotNull Long behaviorGuideId) {
        validateId(behaviorGuideId);
        BehaviorGuide behaviorGuide = findBehaviorGuideById(behaviorGuideId);
        return behaviorGuide.getSuitableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatBreedDto> getBehaviorSuitableCatBreeds(@NotNull Long behaviorGuideId) {
        validateId(behaviorGuideId);
        BehaviorGuide behaviorGuide = findBehaviorGuideById(behaviorGuideId);
        return behaviorGuide.getSuitableCatBreeds().stream()
                .map(catBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BehaviorGuideDto deleteSuitableCatBreedFromBehaviorGuide(@NotNull Long id,@NotNull Long idCatBreed) {
        validateId(id);
        validateId(idCatBreed);
        BehaviorGuide behaviorGuide = findBehaviorGuideById(id);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        behaviorGuide.getSuitableCatBreeds().remove(catBreed);
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public BehaviorGuideDto deleteSuitableDogBreedFromBehaviorGuide(@NotNull Long id,@NotNull Long idDogBreed) {
        validateId(id);
        validateId(idDogBreed);
        BehaviorGuide behaviorGuide = findBehaviorGuideById(id);
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        behaviorGuide.getSuitableDogBreeds().remove(dogBreed);
        return convertToDto(behaviorGuideRepository.save(behaviorGuide));
    }

    private BehaviorGuideDto convertToDto(BehaviorGuide behaviorGuide) {
        return behaviorGuideMapper.convertToDTO(behaviorGuide);
    }

    private BehaviorGuide convertToEntity(BehaviorGuideDto behaviorGuideDto) {
        return behaviorGuideMapper.convertToEntity(behaviorGuideDto);
    }

    private void copyCollections(@NotNull BehaviorGuide oldCenter, @NotNull BehaviorGuide newCenter) {
        newCenter.setSuitableDogBreeds(oldCenter.getSuitableDogBreeds());
        newCenter.setSuitableCatBreeds(oldCenter.getSuitableCatBreeds());
    }

    private void validateBehaviorGuideDto(@NotNull BehaviorGuideDto dto) {
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
        if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
        if(dto.getSolutions()==null || dto.getSolutions().trim().isEmpty()){
            throw new BlankInputException("Can't accept spaces in blank");
        }
        if(dto.getBehavioralIssues()==null || dto.getBehavioralIssues().trim().isEmpty()){
            throw new BlankInputException("Can't accept spaces in blank");
        }
    }

    private void validateId(Long id) {
        if (id <= 0) {
            throw new InvalidInputException("Invalid ID");
        }
    }

    private BehaviorGuide findBehaviorGuideById(Long id) {
        return behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Adoption Center not found"));
    }

    private void removeBreedsFromBehaviorGuide(@NotNull BehaviorGuide behaviorGuide, @NotNull Long id) {
        behaviorGuide.getSuitableCatBreeds().forEach(catBreed ->
                catBreed.getBehaviorGuides().removeIf(center -> center.getId().equals(id))
        );
        behaviorGuide.getSuitableDogBreeds().forEach(dogBreed ->
                dogBreed.getBehaviorGuides().removeIf(center -> center.getId().equals(id))
        );
    }
}