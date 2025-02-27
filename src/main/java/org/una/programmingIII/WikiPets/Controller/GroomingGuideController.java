package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.GroomingGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.GroomingGuideService;
import org.una.programmingIII.WikiPets.Exception.CustomException;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class GroomingGuideController {

    private final GroomingGuideService groomingGuideService;
    private final GenericMapper<GroomingGuideInput, GroomingGuideDto> groomingGuideMapper;

    @Autowired
    public GroomingGuideController(GroomingGuideService groomingGuideService, GenericMapperFactory mapperFactory) {
        this.groomingGuideService = groomingGuideService;
        this.groomingGuideMapper = mapperFactory.createMapper(GroomingGuideInput.class, GroomingGuideDto.class);
    }

    @QueryMapping
    public Map<String, Object> getAllGroomingGuides(@Argument int page, @Argument int size) {
        try {
            return groomingGuideService.getAllGroomingGuides(page, size);
        } catch (Exception e) {
            throw new NotFoundElementException("Could not retrieve grooming guides");
        }
    }

    @QueryMapping
    public GroomingGuideDto getGroomingGuideById(@Argument Long id) {
        try {
            return groomingGuideService.getGroomingGuideById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find grooming guide with id " + id + ". " + e.getMessage(), e);
        }
    }

    @QueryMapping
    public List<DogBreedDto> getGroomingSuitableDogBreeds(@Argument Long id) {
        try {
            return groomingGuideService.getGroomingSuitableDogBreeds(id);
        } catch (Exception e) {
            throw new CustomException("Could not find grooming guide" + e.getMessage());
        }
    }

    @QueryMapping
    public List<CatBreedDto> getGroomingSuitableCatBreeds(@Argument Long id) {
        try {
            return groomingGuideService.getGroomingSuitableCatBreeds(id);
        } catch (Exception e) {
            throw new CustomException("Could not find grooming guide" + e.getMessage());
        }
    }

    @MutationMapping
    public GroomingGuideDto addSuitableDogBreedToGroomingGuide(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return groomingGuideService.addSuitableDogBreedToGroomingGuide(id, idDogBreed);
        } catch (Exception e) {
            throw new NotFoundElementException("Could not update grooming guide with id: " + id + ". " + e.getMessage());

        }
    }

    @MutationMapping
    public GroomingGuideDto addSuitableCatBreedToGroomingGuide(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return groomingGuideService.addSuitableCatBreedToGroomingGuide(id, idCatBreed);
        } catch (Exception e) {
            throw new NotFoundElementException("Could not update grooming guide with id: " + id + ". " + e.getMessage());
        }
    }

    @MutationMapping
    public GroomingGuideDto createGroomingGuide(@Argument GroomingGuideInput input) {
        try {
            GroomingGuideDto groomingGuideDto = convertToDto(input);
            return groomingGuideService.createGroomingGuide(groomingGuideDto);
        } catch (Exception e) {
            throw new CustomException("Could not create grooming guide. " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public GroomingGuideDto updateGroomingGuide(@Argument GroomingGuideInput input) {
        try {
            GroomingGuideDto groomingGuideDto = convertToDto(input);
            return groomingGuideService.updateGroomingGuide(groomingGuideDto);
        } catch (Exception e) {
            throw new CustomException("Could not update grooming guide. " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public Boolean deleteGroomingGuide(@Argument Long id) {
        return groomingGuideService.deleteGroomingGuide(id);
    }

    @MutationMapping
    public GroomingGuideDto deleteSuitableCatBreedFromGroomingGuide(@Argument Long id, @Argument Long catBreedId) {
        try {
            return groomingGuideService.deleteSuitableCatBreedFromGroomingGuide(id, catBreedId);
        } catch (Exception e) {
            throw new NotFoundElementException("Could not remove cat breed from grooming guide with id " + id + ". " + e.getMessage());
        }
    }

    @MutationMapping
    public GroomingGuideDto deleteSuitableDogBreedFromGroomingGuide(@Argument Long id, @Argument Long dogBreedId) {
        try {
            return groomingGuideService.deleteSuitableDogBreedFromGroomingGuide(id, dogBreedId);
        } catch (Exception e) {
            throw new NotFoundElementException("Could not remove dog breed from grooming guide with id " + id + ". " + e.getMessage());
        }
    }

    private GroomingGuideDto convertToDto(GroomingGuideInput groomingGuideInput) {
        return groomingGuideMapper.convertToDTO(groomingGuideInput);
    }
}
