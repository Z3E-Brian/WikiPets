package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CareTip;
import org.una.programmingIII.WikiPets.Dto.CareTipDto;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Repository.CareTipRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CareTipServiceImplementation implements CareTipService {
    private final CareTipRepository careTipRepository;
    private final GenericMapper<CareTip, CareTipDto> careTipMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public CareTipServiceImplementation(CareTipRepository careTipRepository, GenericMapperFactory mapperFactory, DogBreedService breedService, CatBreedService catBreedService) {
        this.careTipRepository = careTipRepository;
        this.dogBreedService = breedService;
        this.catBreedService = catBreedService;
        this.careTipMapper = mapperFactory.createMapper(CareTip.class, CareTipDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
    }

    @Override
    public Map<String, Object> getAllCareTips( int page, int size) {
        Page<CareTip> careTips = careTipRepository.findAll(PageRequest.of(page, size));
        careTips.forEach(careTip -> {
            careTip.setRelevantCatBreeds(careTip.getRelevantCatBreeds().stream().limit(10).collect(Collectors.toList()));
            careTip.setRelevantDogBreeds(careTip.getRelevantDogBreeds().stream().limit(10).collect(Collectors.toList()));
        });
        return Map.of("careTips", careTips.map(this::convertToDto).getContent(), "totalPages", careTips.getTotalPages(), "totalElements", careTips.getTotalElements());
    }

    @Override
    public CareTipDto getCareTipById(Long id) {
        return careTipRepository.findById(id).map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Care Tip not found"));
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
        careTipDto.setCreatedDate(LocalDate.now());
        careTipDto.setModifiedDate(LocalDate.now());
        CareTip careTip = careTipMapper.convertToEntity(careTipDto);
        return careTipMapper.convertToDTO(careTipRepository.save(careTip));
    }

    @Override
    public void deleteCareTip(Long id) {
        CareTip careTip = careTipRepository.findById(id).orElseThrow(() -> new RuntimeException("Care Tip not found"));
        careTip.getRelevantCatBreeds().stream().map(catBreed -> catBreed.getCareTips().iterator()).forEach(iterator -> {
            while (iterator.hasNext()) {
                if (iterator.next().equals(careTip)) {
                    iterator.remove();
                }
            }
        });
        careTip.getRelevantDogBreeds().stream().map(dogBreed -> dogBreed.getCareTips().iterator()).forEach(iterator -> {
            while (iterator.hasNext()) {
                if (iterator.next().equals(careTip)) {
                    iterator.remove();
                }
            }
        });
        careTipRepository.deleteById(id);
    }

    @Override
    public CareTipDto updateCareTip(CareTipDto careTipDto) {
        CareTip oldCareTip = careTipRepository.findById(careTipDto.getId()).orElseThrow(() -> new RuntimeException("Care Tip not found"));
        CareTip newCareTip = careTipMapper.convertToEntity(careTipDto);
        newCareTip.setCreatedDate(oldCareTip.getCreatedDate());
        newCareTip.setModifiedDate(LocalDate.now());
        newCareTip.setRelevantCatBreeds(new ArrayList<>());
        newCareTip.setRelevantDogBreeds(new ArrayList<>());

        oldCareTip.getRelevantCatBreeds().stream()
                .filter(catBreed -> !newCareTip.getRelevantCatBreeds().contains(catBreed))
                .forEach(newCareTip.getRelevantCatBreeds()::add);

        oldCareTip.getRelevantDogBreeds().stream()
                .filter(dogBreed -> !newCareTip.getRelevantDogBreeds().contains(dogBreed))
                .forEach(newCareTip.getRelevantDogBreeds()::add);
        return careTipMapper.convertToDTO(careTipRepository.save(newCareTip));
    }

    @Override
    public CareTipDto addDogBreedInCareTip(Long id, Long idDogBreed) {
        CareTip careTip = careTipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Care Tip not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!careTip.getRelevantDogBreeds().contains(dogBreed)) {
            careTip.getRelevantDogBreeds().add(dogBreed);
        }
        return careTipMapper.convertToDTO(careTipRepository.save(careTip));
    }

    @Override
    public CareTipDto addCatBreedInCareTip(Long id, Long idCatBreed) {
        CareTip careTip = careTipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Care Tip not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!careTip.getRelevantCatBreeds().contains(catBreed)) {
            careTip.getRelevantCatBreeds().add(catBreed);
        }
        return careTipMapper.convertToDTO(careTipRepository.save(careTip));
    }

    private CareTipDto convertToDto(CareTip careTip) {
        return careTipMapper.convertToDTO(careTip);
    }
}
