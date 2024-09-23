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
import org.una.programmingIII.WikiPets.Dto.CareTipDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.CareTipInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.CareTipService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class CareTipController {
    private final CareTipService careTipService;
    private final GenericMapper<CareTipInput, CareTipDto> careTipMapper;

    @Autowired
    public CareTipController(CareTipService service, GenericMapperFactory mapperFactory) {
        this.careTipService = service;
        this.careTipMapper = mapperFactory.createMapper(CareTipInput.class, CareTipDto.class);
    }

    @QueryMapping
    public Map<String, Object> getAllCareTips(@Argument int page, @Argument int size) {
        try {
            return careTipService.getAllCareTips(page, size);
        } catch (NotFoundElementException e) {
            throw new NotFoundElementException("Could not retrieve care tips" + e.getMessage());
        }
    }

    @QueryMapping
    public CareTipDto getCareTipById(@Argument Long id) {
        return careTipService.getCareTipById(id);
    }

    @QueryMapping
    public CareTipDto getCareTipByTitle(@Argument String title) {
        try {
            return careTipService.getCareTipByTitle(title);
        } catch (Exception e) {
            throw new CustomException("Could not find care tip" + e.getMessage());
        }
    }

    @MutationMapping
    public CareTipDto createCareTip(@Argument CareTipInput input) {
        try {
            CareTipDto careTipDto = convertToDto(input);
            return careTipService.createCareTip(careTipDto);
        } catch (Exception e) {
            throw new CustomException("Could not create care tip" + e.getMessage());
        }
    }

    @MutationMapping
    public CareTipDto updateCareTip(@Argument CareTipInput input) {
        try {
            CareTipDto careTipDto = convertToDto(input);
            return careTipService.updateCareTip(careTipDto);
        } catch (Exception e) {
            throw new CustomException("Could not update care tip" + e.getMessage());
        }
    }

    @MutationMapping
    public Boolean deleteCareTip(@Argument Long id) {
        return careTipService.deleteCareTip(id);
    }

    @MutationMapping
    public CareTipDto addDogBreedInCareTip(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return careTipService.addDogBreedInCareTip(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add dog breed in care tip" + e.getMessage());
        }
    }

    @MutationMapping
    public CareTipDto addCatBreedInCareTip(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return careTipService.addCatBreedInCareTip(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add cat breed in care tip" + e.getMessage());
        }
    }

    private CareTipDto convertToDto(CareTipInput careTipInput) {
        return careTipMapper.convertToDTO(careTipInput);
    }
}
