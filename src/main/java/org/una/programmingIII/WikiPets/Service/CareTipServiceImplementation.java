package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CareTip;
import org.una.programmingIII.WikiPets.Dto.CareTipDto;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Repository.CareTipRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
    public Map<String, Object> getAllCareTips(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CareTip> careTipPage = careTipRepository.findAll(pageRequest);

        careTipPage.forEach(careTip -> {
            if (careTip.getRelevantCatBreeds() == null) {
                careTip.setRelevantCatBreeds(new ArrayList<>());
            }
            if (careTip.getRelevantDogBreeds() == null) {
                careTip.setRelevantDogBreeds(new ArrayList<>());
            }
        });

        Map<String, Object> response = new HashMap<>();
        response.put("careTips", careTipPage.getContent());
        response.put("currentPage", careTipPage.getNumber());
        response.put("totalItems", careTipPage.getTotalElements());
        response.put("totalPages", careTipPage.getTotalPages());

        return response;
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
            throw new NotFoundElementException("Care Tip not found");
        }
        return careTipMapper.convertToDTO(careTip);
    }

    @Override
    public CareTipDto createCareTip(CareTipDto careTipDto) {
        if (careTipDto.getTitle().isBlank() || careTipDto.getContent().isBlank()) {
            throw new BlankInputException("Cant' leave spaces in blank");
        }
        CareTip careTip = careTipMapper.convertToEntity(careTipDto);
        careTip.setCreatedDate(LocalDate.now());
        careTip.setModifiedDate(LocalDate.now());
        careTip = careTipRepository.save(careTip);
        return careTipMapper.convertToDTO(careTip);
    }

    @Override
    public Boolean deleteCareTip(Long careTipId) {
        CareTip careTip = careTipRepository.findById(careTipId)
                .orElseThrow(() -> new NotFoundElementException("Care Tip not found"));

        if (careTip.getRelevantCatBreeds() != null) {
            careTip.getRelevantCatBreeds().forEach(catBreed -> {
                if (catBreed.getCareTips() == null) {
                    catBreed.setCareTips(new ArrayList<>());
                }
                catBreed.getCareTips().removeIf(ct -> ct.getId().equals(careTipId));
            });
        }

        if (careTip.getRelevantDogBreeds() != null) {
            careTip.getRelevantDogBreeds().forEach(dogBreed -> {
                if (dogBreed.getCareTips() == null) {
                    dogBreed.setCareTips(new ArrayList<>());
                }
                dogBreed.getCareTips().removeIf(ct -> ct.getId().equals(careTipId));
            });
        }

        careTipRepository.deleteById(careTipId);
        return true;
    }

    @Override
    public CareTipDto updateCareTip(CareTipDto careTipDto) {
        if (careTipDto.getId() <= 0) {
            throw new InvalidInputException("Invalid careTip ID");
        }

        if (careTipDto.getTitle().isBlank() || careTipDto.getContent().isBlank()) {
            throw new BlankInputException("Cant' leave spaces in blank");
        }
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
                .orElseThrow(() -> new NotFoundElementException("Care Tip not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
 if (careTip.getRelevantDogBreeds() == null) {
            careTip.setRelevantDogBreeds(new ArrayList<>());
        }
 if (!careTip.getRelevantDogBreeds().contains(dogBreed)) {
            careTip.getRelevantDogBreeds().add(dogBreed);
        }
        return careTipMapper.convertToDTO(careTipRepository.save(careTip));
    }

    @Override
    public CareTipDto addCatBreedInCareTip(Long id, Long idCatBreed) {
        CareTip careTip = careTipRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Care Tip not found"));
        CatBreedDto catBreedDto = catBreedService.getBreedById(idCatBreed);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedDto);

        if (careTip.getRelevantCatBreeds() == null) {
            careTip.setRelevantCatBreeds(new ArrayList<>());
        }

        if (!careTip.getRelevantCatBreeds().contains(catBreed)) {
            careTip.getRelevantCatBreeds().add(catBreed);
        }

        careTipRepository.save(careTip);
        return careTipMapper.convertToDTO(careTip);
    }

    private CareTipDto convertToDto(CareTip careTip) {
        return careTipMapper.convertToDTO(careTip);
    }
}
