package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.NutritionGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.NutritionGuideService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class NutritionGuideController {

    private final NutritionGuideService nutritionGuideService;
    private final GenericMapper<NutritionGuideInput, NutritionGuideDto> nutritionGuideMapper;

    @Autowired
    public NutritionGuideController(NutritionGuideService service, GenericMapperFactory mapperFactory) {
        this.nutritionGuideService = service;
        this.nutritionGuideMapper = mapperFactory.createMapper(NutritionGuideInput.class, NutritionGuideDto.class);
    }

    @QueryMapping
    public NutritionGuideDto getNutritionGuideById(@Argument Long id) {
        try {
            return nutritionGuideService.getNutritionGuideById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find nutrition guide" + e.getMessage());
        }
    }

    @QueryMapping
    public NutritionGuideDto getNutritionGuideByTitle(@Argument String title) {
        try {
            return nutritionGuideService.getNutritionGuideByTitle(title);
        } catch (Exception e) {
            throw new CustomException("Could not find nutrition guide" + e.getMessage());
        }
    }

    @QueryMapping
    public Map<String, Object> getNutritionGuides(@Argument int page, @Argument int size) {
        try {
            return nutritionGuideService.getAllNutritionGuides(page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find nutrition guides" + e.getMessage());
        }
    }

    @MutationMapping
    public NutritionGuideDto createNutritionGuide(@Argument NutritionGuideInput input) {
        try {
            NutritionGuideDto nutritionGuideDto = nutritionGuideMapper.convertToDTO(input);
            return nutritionGuideService.createNutritionGuide(nutritionGuideDto);
        } catch (Exception e) {
            throw new CustomException("Could not create nutrition guide" + e.getMessage());
        }
    }

    @MutationMapping
    public NutritionGuideDto updateNutritionGuide(@Argument NutritionGuideInput input) {
        try {
            NutritionGuideDto nutritionGuideDto = nutritionGuideMapper.convertToDTO(input);
            return nutritionGuideService.updateNutritionGuide(nutritionGuideDto);
        } catch (Exception e) {
            throw new CustomException("Could not update nutrition guide" + e.getMessage());
        }
    }

    @MutationMapping
    public boolean deleteNutritionGuide(@Argument Long id) {
        try {
            nutritionGuideService.deleteNutritionGuide(id);
            return true;
        } catch (Exception e) {
            throw new CustomException("Could not delete nutrition guide" + e.getMessage());
        }
    }

    @MutationMapping
    public NutritionGuideDto addRecommendedDogBreedNutritionGuide(@Argument Long IdGuide, @Argument Long idDogBreed) {
        try {
            return nutritionGuideService.addRecommendedDogBreed(IdGuide, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add recommended dog breed" + e.getMessage());
        }
    }

    @MutationMapping
    public NutritionGuideDto addRecommendedCatBreedNutritionGuide(@Argument Long IdGuide, @Argument Long idCatBreed) {
        try {
            return nutritionGuideService.addRecommendedCatBreed(IdGuide, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add recommended cat breed" + e.getMessage());
        }
    }
}
