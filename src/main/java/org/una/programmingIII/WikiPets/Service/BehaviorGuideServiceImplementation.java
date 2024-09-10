package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;

import java.time.LocalDate;
import java.util.List;
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
    public BehaviorGuideServiceImplementation(BehaviorGuideRepository behaviorGuideRepository, GenericMapperFactory mapperFactory, DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.behaviorGuideRepository = behaviorGuideRepository;
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);

        this.behaviorGuideMapper = mapperFactory.createMapper(BehaviorGuide.class, BehaviorGuideDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }

    @Override
    public BehaviorGuideDto getBehaviorGuideById(Long id) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new CustomException("Grooming guide with id " + id + " not found"));
        return behaviorGuideMapper.convertToDTO(behaviorGuide);
    }

    @Override
    public List<BehaviorGuideDto> getAllBehaviorGuides() {
        List<BehaviorGuide> behaviorGuides = behaviorGuideRepository.findAll();
        return behaviorGuides.stream()
                .map(behaviorGuideMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BehaviorGuideDto createBehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        behaviorGuideDto.setCreateDate(LocalDate.now());
        behaviorGuideDto.setLastUpdate(LocalDate.now());
        BehaviorGuide behaviorGuide = behaviorGuideMapper.convertToEntity(behaviorGuideDto);
        return behaviorGuideMapper.convertToDTO(behaviorGuideRepository.save(behaviorGuide));
    }
    @Override
    public BehaviorGuideDto addSuitableDogBreedToBehaviorGuide(Long id, Long idDogBreed) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new CustomException("Adoption center not found"));

        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!behaviorGuide.getSuitableDogBreeds().contains(dogBreed)) {
            behaviorGuide.getSuitableDogBreeds().add(dogBreed);
        }

        return behaviorGuideMapper.convertToDTO(behaviorGuideRepository.save(behaviorGuide));
    }
    @Override
    public BehaviorGuideDto addSuitableCatBreedToBehaviorGuide(Long id, Long idCatBreed) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id)
                .orElseThrow(() -> new CustomException("Adoption center not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!behaviorGuide.getSuitableCatBreeds().contains(catBreed)) {
            behaviorGuide.getSuitableCatBreeds().add(catBreed);
        }
        return behaviorGuideMapper.convertToDTO(behaviorGuideRepository.save(behaviorGuide));
    }

    @Override
    public List<DogBreedDto> getBehaviorSuitableDogBreeds(Long behaviorGuideId) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(behaviorGuideId).orElseThrow();
        return behaviorGuide.getSuitableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BehaviorGuideDto updateBehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        BehaviorGuide oldGuide = behaviorGuideRepository.findById(behaviorGuideDto.getId())
                .orElseThrow(() -> new CustomException("Grooming guide with id " + behaviorGuideDto.getId() + " not found"));

        BehaviorGuide newGuide = behaviorGuideMapper.convertToEntity(behaviorGuideDto);
        newGuide.setCreateDate(oldGuide.getCreateDate());
        newGuide.setLastUpdate(LocalDate.now());

        return behaviorGuideMapper.convertToDTO(behaviorGuideRepository.save(newGuide));
    }

    @Override
    public void deleteBehaviorGuide(Long id) {
        behaviorGuideRepository.deleteById(id);
    }

    @Override
    public Page<BehaviorGuideDto> getAllBehaviorGuides(Pageable pageable) {
        Page<BehaviorGuide> behaviorGuides = behaviorGuideRepository.findAll(pageable);
        return behaviorGuides.map(behaviorGuideMapper::convertToDTO);
    }
}
