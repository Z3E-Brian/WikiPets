package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CareTip;
import org.una.programmingIII.WikiPets.Model.CareTipDto;
import org.una.programmingIII.WikiPets.Repository.CareTipRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareTipServiceImplementation implements CareTipService {
    private final CareTipRepository careTipRepository;
    private final GenericMapper<CareTip,CareTipDto> careTipMapper;

    @Autowired
    public CareTipServiceImplementation(CareTipRepository careTipRepository, GenericMapperFactory mapperFactory) {
        this.careTipRepository = careTipRepository;
        this.careTipMapper = mapperFactory.createMapper(CareTip.class, CareTipDto.class);
    }
    @Override
    public List<CareTipDto> getAllCareTips() {
        return careTipRepository.findAll().stream()
                .map(careTipMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public CareTipDto getCareTipById(Long id) {
        CareTip careTip = careTipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Care Tip not found"));
        return careTipMapper.convertToDTO(careTip);
    }
    @Override
    public CareTipDto getCareTipByTitle(String title) {
        CareTip careTip = careTipRepository.findByTitle(title);
        if (careTip == null) {
            throw new RuntimeException("Care Tip not found");
        }
        return careTipMapper.convertToDTO(careTip);
    }
    @Override
    public CareTipDto createCareTip(CareTipDto careTipDto) {
        CareTip careTip = careTipMapper.convertToEntity(careTipDto);
        return careTipMapper.convertToDTO(careTipRepository.save(careTip));
    }
    @Override
    public void deleteCareTip(Long id) {
        careTipRepository.deleteById(id);
    }
    @Override
    public CareTipDto updateCareTip(CareTipDto careTipDto) {
        CareTip careTip = careTipMapper.convertToEntity(careTipDto);
        return careTipMapper.convertToDTO(careTipRepository.save(careTip));
    }
}
