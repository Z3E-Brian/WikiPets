package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.CareTipDto;

import java.util.List;

public interface CareTipService {

    CareTipDto getCareTipById(Long id);

    CareTipDto getCareTipByTitle(String title);

    CareTipDto createCareTip(CareTipDto careTipDto);

    void deleteCareTip(Long id);

    CareTipDto updateCareTip(CareTipDto careTipDto);

    Page<CareTipDto> getAllCareTips(Pageable pageable);

    CareTipDto addDogBreedInCareTip(Long id, Long idDogBreed);

    CareTipDto addCatBreedInCareTip(Long id, Long idCatBreed);
}
