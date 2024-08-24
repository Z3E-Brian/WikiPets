package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.CareTipDto;

import java.util.List;

public interface CareTipService {
    List<CareTipDto> getAllCareTips();

    CareTipDto getCareTipById(Long id);

    CareTipDto getCareTipByTitle(String title);

    CareTipDto createCareTip(CareTipDto careTipDto);

    void deleteCareTip(Long id);

    CareTipDto updateCareTip(CareTipDto careTipDto);
}
