package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Input.BehaviorGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.BehaviorGuideService;
import org.una.programmingIII.WikiPets.Exception.CustomException;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BehaviorGuideController {

    private final BehaviorGuideService behaviorGuideService;
    private final GenericMapper<BehaviorGuideInput, BehaviorGuideDto> behaviorGuideMapper;

    @Autowired
    public BehaviorGuideController(BehaviorGuideService behaviorGuideService, GenericMapperFactory mapperFactory) {
        this.behaviorGuideService = behaviorGuideService;
        this.behaviorGuideMapper = mapperFactory.createMapper(BehaviorGuideInput.class, BehaviorGuideDto.class);
    }

    @QueryMapping
    public Map<String, Object> getAllBehaviorGuides(@Argument int page, @Argument int size) {
        try {
            return behaviorGuideService.getAllBehaviorGuides(page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find behavior guides" + e.getMessage());
        }
    }

    @QueryMapping
    public BehaviorGuideDto getBehaviorGuideById(@Argument Long id) {
        try {
            return behaviorGuideService.getBehaviorGuideById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find grooming guide with id " + id + ". " + e.getMessage(), e);
        }
    }

    @QueryMapping
    public List<DogBreedDto> getBehaviorSuitableDogBreeds(@Argument Long id) {
        try {
            return behaviorGuideService.getBehaviorSuitableDogBreeds(id);
        } catch (Exception e) {
            throw new CustomException("Could not find behavior guide" + e.getMessage());
        }
    }

    @QueryMapping
    public List<CatBreedDto> getBehaviorSuitableCatBreeds(@Argument Long id) {
        try {
            return behaviorGuideService.getBehaviorSuitableCatBreeds(id);
        } catch (Exception e) {
            throw new CustomException("Could not find behavior guide" + e.getMessage());
        }
    }

    @MutationMapping
    public BehaviorGuideDto addSuitableDogBreedToBehaviorGuide(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return behaviorGuideService.addSuitableDogBreedToBehaviorGuide(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update behavior guide with id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public BehaviorGuideDto addSuitableCatBreedToBehaviorGuide(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return behaviorGuideService.addSuitableCatBreedToBehaviorGuide(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update behavior guide with id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public BehaviorGuideDto createBehaviorGuide(@Argument BehaviorGuideInput input) {
        try {
            BehaviorGuideDto behaviorGuideDto = convertToDto(input);
            return behaviorGuideService.createBehaviorGuide(behaviorGuideDto);
        } catch (Exception e) {
            throw new CustomException("Could not create behavior guide. " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public BehaviorGuideDto updateBehaviorGuide(@Argument BehaviorGuideInput input) {
        try {
            BehaviorGuideDto behaviorGuideDto = convertToDto(input);
            return behaviorGuideService.updateBehaviorGuide(behaviorGuideDto);
        } catch (Exception e) {
            throw new CustomException("Could not update behavior guide. " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public void deleteBehaviorGuide(@Argument Long id) {
        try {
            behaviorGuideService.deleteBehaviorGuide(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete grooming guide with id " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public BehaviorGuideDto removeSuitableCatBreedFromBehaviorGuide(@Argument Long id, @Argument Long catBreedId) {
        try {
            return behaviorGuideService.removeSuitableCatBreedFromBehaviorGuide(id, catBreedId);
        } catch (Exception e) {
            throw new CustomException("Could not remove cat breed from behavior guide with id " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public BehaviorGuideDto removeSuitableDogBreedFromBehaviorGuide(@Argument Long id, @Argument Long dogBreedId) {
        try {
            return behaviorGuideService.removeSuitableDogBreedFromBehaviorGuide(id, dogBreedId);
        } catch (Exception e) {
            throw new CustomException("Could not remove dog breed from behavior guide with id " + id + ". " + e.getMessage(), e);
        }
    }

    private BehaviorGuideDto convertToDto(BehaviorGuideInput behaviorGuideInput) {
        return behaviorGuideMapper.convertToDTO(behaviorGuideInput);
    }
}
