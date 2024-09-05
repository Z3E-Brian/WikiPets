package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.AdoptionCenterInput;
import org.una.programmingIII.WikiPets.Input.DogBreedInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Service.DogBreedService;

import java.time.LocalDate;
import java.util.List;

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
    public List<DogBreedDto> getDogBreeds() {
        try {
            return dogBreedService.getAllBreeds();
        } catch (Exception e) {
            throw new CustomException("Could not find dog breeds"+e.getMessage());
        }
    }

    @MutationMapping
    public DogBreedDto createDogBreed(@Argument DogBreedInput input) {
        try {
            DogBreedDto dogBreedDto = dogBreedMapper.convertToDTO(input);
            dogBreedDto.setCreatedDate(LocalDate.now());
            dogBreedDto.setModifiedDate(LocalDate.now());
            dogBreedDto.setAdoptionCenters(null);
            dogBreedDto.setHealthIssues(null);
            dogBreedDto.setNutritionGuides(null);
            dogBreedDto.setUsers(null);
            dogBreedDto.setTrainingGuides(null);
            dogBreedDto.setBehaviorGuides(null);
            dogBreedDto.setCareTips(null);
            dogBreedDto.setGroomingGuides(null);
            dogBreedDto.setFeedingSchedules(null);
            dogBreedDto.setImages(null);
            dogBreedDto.setVideos(null);
            dogBreedDto.setReviews(null);
            return dogBreedService.createDogBreed(dogBreedDto);
        } catch (Exception e) {
            throw new CustomException("Could not create dog breed"+e.getMessage());
        }
    }
}
