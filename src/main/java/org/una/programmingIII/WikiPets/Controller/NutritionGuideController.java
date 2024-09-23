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
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
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
        return nutritionGuideService.getNutritionGuideById(id);
    }

    @QueryMapping
    public NutritionGuideDto getNutritionGuideByTitle(@Argument String title) {
        return nutritionGuideService.getNutritionGuideByTitle(title);
    }

    @QueryMapping
    public Map<String, Object> getNutritionGuides(@Argument int page, @Argument int size) {
        try {
            return nutritionGuideService.getAllNutritionGuides(page, size);
        } catch (NotFoundElementException e) {
            throw new NotFoundElementException("Could not find nutrition guides" + e.getMessage());
        }
    }

    @MutationMapping
    public NutritionGuideDto createNutritionGuide(@Argument NutritionGuideInput input) {
        NutritionGuideDto nutritionGuideDto = nutritionGuideMapper.convertToDTO(input);
        return nutritionGuideService.createNutritionGuide(nutritionGuideDto);
    }

    @MutationMapping
    public NutritionGuideDto updateNutritionGuide(@Argument NutritionGuideInput input) {
        NutritionGuideDto nutritionGuideDto = nutritionGuideMapper.convertToDTO(input);
        return nutritionGuideService.updateNutritionGuide(nutritionGuideDto);
    }

    @MutationMapping
    public Boolean deleteNutritionGuide(@Argument Long id) {
        return nutritionGuideService.deleteNutritionGuide(id);
    }

    @MutationMapping
    public NutritionGuideDto addRecommendedDogBreedNutritionGuide(@Argument Long IdGuide, @Argument Long idDogBreed) {
        return nutritionGuideService.addRecommendedDogBreed(IdGuide, idDogBreed);
    }

    @MutationMapping
    public NutritionGuideDto addRecommendedCatBreedNutritionGuide(@Argument Long IdGuide, @Argument Long idCatBreed) {
            return nutritionGuideService.addRecommendedCatBreed(IdGuide, idCatBreed);
    }
}
