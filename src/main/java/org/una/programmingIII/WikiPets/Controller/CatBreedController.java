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
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.CatBreedInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.CatBreedService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class CatBreedController {
    CatBreedService catBreedService;
    private final GenericMapper<CatBreedInput, CatBreedDto> catBreedMapper;

    @Autowired
    CatBreedController(CatBreedService catBreedService, GenericMapperFactory mapperFactory) {
        this.catBreedService = catBreedService;
        this.catBreedMapper = mapperFactory.createMapper(CatBreedInput.class, CatBreedDto.class);
    }

    @QueryMapping
    public Map<String, Object> getCatBreeds(@Argument int page, @Argument int size) {
        try {
            return catBreedService.getAllCatBreeds(page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find cat breeds" + e.getMessage());
        }
    }
    @QueryMapping
    public CatBreedDto getCatBreedById(@Argument Long id) {
        try {
            return catBreedService.getBreedById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find cat breed " +id+". "+ e.getMessage());
        }
    }

    @MutationMapping
    public CatBreedDto updateCatBreed(@Argument CatBreedInput input) {
        try {
            CatBreedDto catBreedDto = catBreedMapper.convertToDTO(input);
            return catBreedService.updateCatBreed(catBreedDto);
        } catch (Exception e) {
            throw new CustomException("Could not update cat breed "+ e.getMessage());
        }
    }
    @MutationMapping
    public CatBreedDto createCatBreed(@Argument CatBreedInput input) {
        try {
            CatBreedDto catBreedDto = catBreedMapper.convertToDTO(input);
            return catBreedService.createCatBreed(catBreedDto);
        } catch (Exception e) {
            throw new CustomException("Could not create cat breed" + e.getMessage());
        }
    }
    @MutationMapping
    public boolean deleteCatBreed(@Argument Long id) {
        try {
            catBreedService.deleteCatBreed(id);
            return true;
        } catch (Exception e) {
            throw new CustomException("Could not delete cat breed "+id+". " + e.getMessage());
        }
    }
}
