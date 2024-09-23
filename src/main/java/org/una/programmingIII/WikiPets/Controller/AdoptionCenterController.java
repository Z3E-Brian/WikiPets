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
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
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
        } catch (NotFoundElementException e) {
            throw new NotFoundElementException("Could not retrieve adoption centers" + e.getMessage());
        }
    }

    @QueryMapping
    public AdoptionCenterDto getAdoptionCenterById(@Argument Long id) {
        return adoptionCenterService.getAdoptionCenterById(id);
    }

    @QueryMapping
    public List<DogBreedDto> getAvailableDogBreeds(@Argument Long id) {
        return adoptionCenterService.getAvailableDogBreeds(id);
    }

    @QueryMapping
    public List<CatBreedDto> getAvailableCatBreeds(@Argument Long id) {
        return adoptionCenterService.getAvailableCatBreeds(id);
    }


    @MutationMapping
    public AdoptionCenterDto createAdoptionCenter(@Argument AdoptionCenterInput input) {
        AdoptionCenterDto adoptionCenterDto = adoptionCenterMapper.convertToDTO(input);
        return adoptionCenterService.createAdoptionCenter(adoptionCenterDto);

    }

    @MutationMapping
    public AdoptionCenterDto updateAdoptionCenter(@Argument AdoptionCenterInput input) {
        AdoptionCenterDto adoptionCenterDto = adoptionCenterMapper.convertToDTO(input);
        return adoptionCenterService.updateAdoptionCenter(adoptionCenterDto);
    }

    @MutationMapping
    public boolean deleteAdoptionCenter(@Argument Long id) {
        return adoptionCenterService.deleteAdoptionCenter(id);
    }

    @MutationMapping
    public AdoptionCenterDto addDogBreedInAdoptionCenter(@Argument Long id, @Argument Long idDogBreed) {
        return adoptionCenterService.addDogBreedInAdoptionCenter(id, idDogBreed);
    }

    @MutationMapping
    public AdoptionCenterDto addCatBreedInAdoptionCenter(@Argument Long id, @Argument Long idCatBreed) {
        return adoptionCenterService.addCatBreedInAdoptionCenter(id, idCatBreed);
    }

    @MutationMapping
    public AdoptionCenterDto deleteCatBreedFromAdoptionCenter(@Argument Long id, @Argument Long catBreedId) {
        return adoptionCenterService.deleteCatBreedFromAdoptionCenter(id, catBreedId);
    }

    @MutationMapping
    public AdoptionCenterDto deleteDogBreedFromAdoptionCenter(@Argument Long id, @Argument Long dogBreedId) {
        return adoptionCenterService.deleteDogBreedFromAdoptionCenter(id, dogBreedId);
    }
}
