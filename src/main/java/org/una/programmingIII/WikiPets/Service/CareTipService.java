package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.una.programmingIII.WikiPets.Dto.CareTipDto;

import java.util.List;
import java.util.Map;

public interface CareTipService {

    CareTipDto getCareTipById(Long id);

    CareTipDto getCareTipByTitle(String title);

    CareTipDto createCareTip(CareTipDto careTipDto);

    void deleteCareTip(Long id);

    CareTipDto updateCareTip(CareTipDto careTipDto);

    Map<String, Object> getAllCareTips( int page,  int size);

    CareTipDto addDogBreedInCareTip(Long id, Long idDogBreed);

    CareTipDto addCatBreedInCareTip(Long id, Long idCatBreed);
}
