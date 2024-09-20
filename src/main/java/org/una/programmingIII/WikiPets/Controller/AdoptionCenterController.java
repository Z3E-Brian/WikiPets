package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Input.AdoptionCenterInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;

import org.una.programmingIII.WikiPets.Service.AdoptionCenterService;
import org.una.programmingIII.WikiPets.Exception.CustomException;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AdoptionCenterController {
    private final AdoptionCenterService adoptionCenterService;
    private final GenericMapper<AdoptionCenterInput, AdoptionCenterDto> adoptionCenterMapper;

    @Autowired
    AdoptionCenterController(AdoptionCenterService adoptionCenterService, GenericMapperFactory mapperFactory) {
        this.adoptionCenterService = adoptionCenterService;
        this.adoptionCenterMapper = mapperFactory.createMapper(AdoptionCenterInput.class, AdoptionCenterDto.class);
    }

    @QueryMapping
    public Map<String, Object> getAdoptionCenters(@Argument int page, @Argument int size) {
        try {
            return adoptionCenterService.getAllAdoptionCenters(page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find adoption centers" + e.getMessage());
        }
    }

    @QueryMapping
    public AdoptionCenterDto getAdoptionCenterById(@Argument Long id) {
        try {
            return adoptionCenterService.getAdoptionCenterById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find adoption center " + id + ". " + e.getMessage());
        }
    }

    @QueryMapping
    public List<DogBreedDto> getAvailableDogBreeds(@Argument Long id) {
        try {
            return adoptionCenterService.getAvailableDogBreeds(id);
        } catch (Exception e) {
            throw new CustomException("Could not find available dog breeds" + e.getMessage());
        }
    }
@QueryMapping
    public List<CatBreedDto> getAvailableCatBreeds(@Argument Long id) {
        try {
            return adoptionCenterService.getAvailableCatBreeds(id);
        } catch (Exception e) {
            throw new CustomException("Could not find available dog breeds" + e.getMessage());
        }
    }


    @MutationMapping
    public AdoptionCenterDto createAdoptionCenter(@Argument AdoptionCenterInput input) {
        try {
            AdoptionCenterDto adoptionCenterDto = adoptionCenterMapper.convertToDTO(input);
            return adoptionCenterService.createAdoptionCenter(adoptionCenterDto);
        } catch (Exception e) {
            throw new CustomException("Could not create adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public AdoptionCenterDto updateAdoptionCenter(@Argument AdoptionCenterInput input) {
        try {
            AdoptionCenterDto adoptionCenterDto = adoptionCenterMapper.convertToDTO(input);
            return adoptionCenterService.updateAdoptionCenter(adoptionCenterDto);
        } catch (Exception e) {
            throw new CustomException("Could not update adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public boolean deleteAdoptionCenter(@Argument Long id) {
        try {
            adoptionCenterService.deleteAdoptionCenter(id);
            return true;
        } catch (Exception e) {
            throw new CustomException("Could not delete adoption center" + id + ". " + e.getMessage());
        }
    }

    @MutationMapping
    public AdoptionCenterDto addDogBreedInAdoptionCenter(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return adoptionCenterService.addDogBreedInAdoptionCenter(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update adoption center with id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public AdoptionCenterDto addCatBreedInAdoptionCenter(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return adoptionCenterService.addCatBreedInAdoptionCenter(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update adoption center with id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public AdoptionCenterDto removeCatBreedFromAdoptionCenter(@Argument Long id, @Argument Long catBreedId) {
        try {
            return adoptionCenterService.removeCatBreedFromAdoptionCenter(id, catBreedId);
        } catch (Exception e) {
            throw new CustomException("Could not remove cat breed from adoption center with id " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public AdoptionCenterDto removeDogBreedFromAdoptionCenter(@Argument Long id, @Argument Long dogBreedId) {
        try {
            return adoptionCenterService.removeDogBreedFromAdoptionCenter(id, dogBreedId);
        } catch (Exception e) {
            throw new CustomException("Could not remove dog breed from adoption center with id " + id + ". " + e.getMessage(), e);
        }
    }
}
