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
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.DogBreedInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.DogBreedService;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class DogBreedController {
    DogBreedService dogBreedService;
    private final GenericMapper<DogBreedInput, DogBreedDto> dogBreedMapper;

    @Autowired
    DogBreedController(DogBreedService dogBreedService, GenericMapperFactory mapperFactory) {
        this.dogBreedService = dogBreedService;
        this.dogBreedMapper = mapperFactory.createMapper(DogBreedInput.class, DogBreedDto.class);
    }

    @QueryMapping
    public Map<String, Object> getDogBreeds(@Argument int page, @Argument int size) {
        try {
            return dogBreedService.getAllDogBreeds(page, size);
        } catch (Exception e) {
            throw new NotFoundElementException("Could not find dog breeds" + e.getMessage());
        }
    }

    @QueryMapping
    public DogBreedDto getDogBreedById(@Argument Long id) {
        return dogBreedService.getBreedById(id);
    }

    @MutationMapping
    public DogBreedDto updateDogBreed(@Argument DogBreedInput input) {
        DogBreedDto dogBreedDto = dogBreedMapper.convertToDTO(input);
        return dogBreedService.updateDogBreed(dogBreedDto);
    }

    @MutationMapping
    public DogBreedDto createDogBreed(@Argument DogBreedInput input) {
        DogBreedDto dogBreedDto = dogBreedMapper.convertToDTO(input);
        return dogBreedService.createDogBreed(dogBreedDto);
    }

    @MutationMapping
    public Boolean deleteDogBreed(@Argument Long id) {
        return dogBreedService.deleteDogBreed(id);
    }
}
