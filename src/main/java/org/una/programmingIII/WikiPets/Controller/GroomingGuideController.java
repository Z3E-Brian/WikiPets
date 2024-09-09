package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Input.GroomingGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.GroomingGuideService;
import org.una.programmingIII.WikiPets.Exception.CustomException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class GroomingGuideController {
    private final GroomingGuideService groomingGuideService;
    private final GenericMapper<GroomingGuideInput, GroomingGuideDto> groomingGuideMapper;

    @Autowired
    GroomingGuideController(GroomingGuideService groomingGuideService, GenericMapperFactory mapperFactory) {
        this.groomingGuideService = groomingGuideService;
        this.groomingGuideMapper = mapperFactory.createMapper(GroomingGuideInput.class, GroomingGuideDto.class);
    }

    @QueryMapping
    public Map<String, Object> getGroomingGuides(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GroomingGuideDto> groomingGuidePage = groomingGuideService.getAllGroomingGuides(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("groomingGuides", groomingGuidePage.getContent());
        response.put("totalPages", groomingGuidePage.getTotalPages());
        response.put("totalElements", groomingGuidePage.getTotalElements());

        return response;
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
    public List<DogBreedDto> getSuitableDogBreeds(@Argument Long id) {
        try {
            return groomingGuideService.getSuitableDogBreeds(id);
        } catch (Exception e) {
            throw new CustomException("Could not find adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public GroomingGuideDto addSuitableDogBreedToGroomingGuide(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return groomingGuideService.addSuitableDogBreedToGroomingGuide(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update adoption center with id: " + id + ". " + e.getMessage(), e);
        }
    }
    @MutationMapping
    public GroomingGuideDto addSuitableCatBreedToGroomingGuide(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return groomingGuideService.addSuitableCatBreedToGroomingGuide(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update adoption center with id: " + id + ". " + e.getMessage(), e);
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
    public void deleteGroomingGuide(@Argument Long id) {
        try {
            groomingGuideService.deleteGroomingGuide(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete grooming guide with id " + id + ". " + e.getMessage(), e);
        }
    }

    private GroomingGuideDto convertToDto(GroomingGuideInput groomingGuideInput) {
        return groomingGuideMapper.convertToDTO(groomingGuideInput);
    }
}
