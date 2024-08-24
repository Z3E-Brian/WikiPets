package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.CareTipMapper;
import org.una.programmingIII.WikiPets.Model.CareTip;
import org.una.programmingIII.WikiPets.Model.CareTipDto;
import org.una.programmingIII.WikiPets.Repository.CareTipRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareTipServiceImplementation implements CareTipService {
    private final CareTipRepository careTipRepository;
    private final CareTipMapper careTipMapper = CareTipMapper.INSTANCE;

    @Autowired
    public CareTipServiceImplementation(CareTipRepository careTipRepository) {
        this.careTipRepository = careTipRepository;
    }
    @Override
    public List<CareTipDto> getAllCareTips() {
        return careTipRepository.findAll().stream()
                .map(careTipMapper::toCareTipDto)
                .collect(Collectors.toList());
    }
    @Override
    public CareTipDto getCareTipById(Long id) {
        CareTip careTip = careTipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Care Tip not found"));
        return careTipMapper.toCareTipDto(careTip);
    }
    @Override
    public CareTipDto getCareTipByTitle(String title) {
        CareTip careTip = careTipRepository.findByTitle(title);
        if (careTip == null) {
            throw new RuntimeException("Care Tip not found");
        }
        return careTipMapper.toCareTipDto(careTip);
    }
    @Override
    public CareTipDto createCareTip(CareTipDto careTipDto) {
        CareTip careTip = careTipMapper.toCareTip(careTipDto);
        return careTipMapper.toCareTipDto(careTipRepository.save(careTip));
    }
    @Override
    public void deleteCareTip(Long id) {
        careTipRepository.deleteById(id);
    }
    @Override
    public CareTipDto updateCareTip(CareTipDto careTipDto) {
        CareTip careTip = careTipMapper.toCareTip(careTipDto);
        return careTipMapper.toCareTipDto(careTipRepository.save(careTip));
    }
}
