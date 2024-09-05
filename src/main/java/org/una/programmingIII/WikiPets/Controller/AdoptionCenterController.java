package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Input.AdoptionCenterInput;
import org.una.programmingIII.WikiPets.Input.DogBreedInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;

import org.una.programmingIII.WikiPets.Service.AdoptionCenterService;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Service.DogBreedService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdoptionCenterController {
    private final AdoptionCenterService adoptionCenterService;
    private final DogBreedService dogBreedService;
    private final GenericMapper<AdoptionCenterInput, AdoptionCenterDto> adoptionCenterMapper;
    private final GenericMapper<DogBreedInput, DogBreedDto> dogBreedMapper;

    @Autowired
    AdoptionCenterController(AdoptionCenterService adoptionCenterService, GenericMapperFactory mapperFactory, DogBreedService dogBreedService) {
        this.adoptionCenterService = adoptionCenterService;
        this.dogBreedService = dogBreedService;
        this.adoptionCenterMapper = mapperFactory.createMapper(AdoptionCenterInput.class, AdoptionCenterDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreedInput.class, DogBreedDto.class);
    }

    @QueryMapping
    public List<AdoptionCenterDto> getAdoptionCenters() {
        try {
            return adoptionCenterService.getAllAdoptionCenters();
        } catch (Exception e) {
            throw new CustomException("Could not find adoption centers" + e.getMessage());
        }
    }

    @QueryMapping
    public AdoptionCenterDto getAdoptionCenterById(@Argument Long id) {
        try {
            return adoptionCenterService.getAdoptionCenterById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find adoption center" + e.getMessage());
        }
    }

    @QueryMapping
    public List<DogBreedDto> getAvailableDogBreeds(@Argument Long id) {
        try {
            return adoptionCenterService.getAvailableDogBreeds(id);
        } catch (Exception e) {
            throw new CustomException("Could not find adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public AdoptionCenterDto createAdoptionCenter(@Argument AdoptionCenterInput input) {
        try {
            AdoptionCenterDto adoptionCenterDto = convertToDto(input);
            return adoptionCenterService.createAdoptionCenter(adoptionCenterDto);
        } catch (Exception e) {
            throw new CustomException("Could not create adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public AdoptionCenterDto updateAdoptionCenter(@Argument AdoptionCenterInput input) {
        try {
            AdoptionCenterDto adoptionCenterDto = convertToDto(input);
            return adoptionCenterService.updateAdoptionCenter(adoptionCenterDto);
        } catch (Exception e) {
            throw new CustomException("Could not update adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public void deleteAdoptionCenter(@Argument Long id) {
        try {
            adoptionCenterService.deleteAdoptionCenter(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete adoption center");
        }
    }

@MutationMapping
public AdoptionCenterDto addDogBreedInAdoptionCenter(@Argument Long id, @Argument Long idDogBreed) {
    try {
        return adoptionCenterService.addDogBreedInAdoptionCenter(id, idDogBreed);
    } catch (Exception e) {
        throw new CustomException("Could not update adoption center" + e.getMessage());
    }
}
    private AdoptionCenterDto convertToDto(AdoptionCenterInput adoptionCenter) {
        return adoptionCenterMapper.convertToDTO(adoptionCenter);
    }
}
