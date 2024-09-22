package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.NutritionGuide;
import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;
import org.una.programmingIII.WikiPets.Repository.NutritionGuideRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NutritionGuideServiceImplementation implements NutritionGuideService {
    private final NutritionGuideRepository nutritionGuideRepository;
    private final GenericMapper<NutritionGuide, NutritionGuideDto> nutritionGuideMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public NutritionGuideServiceImplementation(NutritionGuideRepository nutritionGuideRepository, GenericMapperFactory mapperFactory, DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.nutritionGuideRepository = nutritionGuideRepository;
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
        this.nutritionGuideMapper = mapperFactory.createMapper(NutritionGuide.class, NutritionGuideDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
    }

    @Override
    public Map<String, Object> getAllNutritionGuides(int page, int size) {
        Page<NutritionGuide> nutritionGuides = nutritionGuideRepository.findAll(PageRequest.of(page, size));
        nutritionGuides.forEach(nutritionGuide -> {
            nutritionGuide.setRecommendedCatBreeds(nutritionGuide.getRecommendedCatBreeds().stream().limit(10).collect(Collectors.toList()));
            nutritionGuide.setRecommendedDogBreeds(nutritionGuide.getRecommendedDogBreeds().stream().limit(10).collect(Collectors.toList()));
        });
        return Map.of("nutritionGuides", nutritionGuides.map(nutritionGuideMapper::convertToDTO).getContent(), "totalPages", nutritionGuides.getTotalPages(), "totalElements", nutritionGuides.getTotalElements());
    }

    @Override
    public NutritionGuideDto getNutritionGuideById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid Nutrition Guide Schedule ID");
        }
        NutritionGuide nutritionGuide = nutritionGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Nutrition Guide not found"));
        return nutritionGuideMapper.convertToDTO(nutritionGuide);
    }

    @Override
    public NutritionGuideDto getNutritionGuideByTitle(String title) {
        NutritionGuide nutritionGuide = nutritionGuideRepository.findByTitle(title);
        if (nutritionGuide == null) {
            throw new NotFoundElementException("Nutrition Guide not found");
        }
        return nutritionGuideMapper.convertToDTO(nutritionGuide);
    }

    @Override
    public NutritionGuideDto createNutritionGuide(NutritionGuideDto nutritionGuideDto) {
        if (nutritionGuideDto == null) {
            throw new InvalidInputException("Nutrition Guide cannot be null");
        }

        if (nutritionGuideDto.getContent().isBlank() || nutritionGuideDto.getTitle().isBlank()) {
            throw new BlankInputException("Nutrition Guide cannot be have spaces in blank");
        }
        nutritionGuideDto.setCreatedDate(LocalDate.now());
        nutritionGuideDto.setModifiedDate(LocalDate.now());
        NutritionGuide nutritionGuide = nutritionGuideMapper.convertToEntity(nutritionGuideDto);
        return nutritionGuideMapper.convertToDTO(nutritionGuideRepository.save(nutritionGuide));
    }

    @Override
    public Boolean deleteNutritionGuide(Long id) {
        NutritionGuide nutritionGuide = nutritionGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Nutrition Guide not found"));
        nutritionGuide.getRecommendedCatBreeds().forEach(catBreed -> {
            catBreed.getNutritionGuides().removeIf(guide -> guide.getId().equals(id));
        });
        nutritionGuide.getRecommendedDogBreeds().forEach(dogBreed -> {
            dogBreed.getNutritionGuides().removeIf(guide -> guide.getId().equals(id));
        });

        nutritionGuideRepository.deleteById(id);
        return true;
    }

    @Override
    public NutritionGuideDto updateNutritionGuide(NutritionGuideDto nutritionGuideDto) {

        if (nutritionGuideDto.getId() <= 0) {
            throw new InvalidInputException("Invalid careTip ID");
        }

        if (nutritionGuideDto.getTitle().isBlank() || nutritionGuideDto.getContent().isBlank()) {
            throw new BlankInputException("Cant' leave spaces in blank");
        }
        NutritionGuide oldNutritionGuide = nutritionGuideRepository.findById(nutritionGuideDto.getId())
                .orElseThrow(() -> new NotFoundElementException("Nutrition Guide not found"));
        NutritionGuide newNutritionGuide = nutritionGuideMapper.convertToEntity(nutritionGuideDto);
        newNutritionGuide.setCreatedDate(oldNutritionGuide.getCreatedDate());
        newNutritionGuide.setModifiedDate(LocalDate.now());

        newNutritionGuide.setRecommendedCatBreeds(oldNutritionGuide.getRecommendedCatBreeds() != null ?
                new ArrayList<>(oldNutritionGuide.getRecommendedCatBreeds()) : new ArrayList<>());

        newNutritionGuide.setRecommendedDogBreeds(oldNutritionGuide.getRecommendedDogBreeds() != null ?
                new ArrayList<>(oldNutritionGuide.getRecommendedDogBreeds()) : new ArrayList<>());
        return nutritionGuideMapper.convertToDTO(nutritionGuideRepository.save(newNutritionGuide));
    }

    @Override
    public NutritionGuideDto addRecommendedDogBreed(Long IdGuide, Long dogBreedId) {
        if (IdGuide <= 0) {
            throw new InvalidInputException("Invalid careTip ID");
        }
        NutritionGuide nutritionGuide = nutritionGuideRepository.findById(IdGuide)
                .orElseThrow(() -> new NotFoundElementException("Nutrition Guide not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(dogBreedId));
        if (!nutritionGuide.getRecommendedDogBreeds().contains(dogBreed)) {
            nutritionGuide.getRecommendedDogBreeds().add(dogBreed);
        }
        return nutritionGuideMapper.convertToDTO(nutritionGuideRepository.save(nutritionGuide));
    }

    @Override
    public NutritionGuideDto addRecommendedCatBreed(Long IdGuide, Long catBreedId) {
        if (IdGuide <= 0) {
            throw new InvalidInputException("Invalid careTip ID");
        }
        NutritionGuide nutritionGuide = nutritionGuideRepository.findById(IdGuide)
                .orElseThrow(() -> new RuntimeException("Nutrition Guide not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(catBreedId));
        if (!nutritionGuide.getRecommendedCatBreeds().contains(catBreed)) {
            nutritionGuide.getRecommendedCatBreeds().add(catBreed);
        }
        return nutritionGuideMapper.convertToDTO(nutritionGuideRepository.save(nutritionGuide));
    }
}
