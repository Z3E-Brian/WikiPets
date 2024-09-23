package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.AdoptionCenterRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdoptionCenterServiceImplementation implements AdoptionCenterService {

    private final AdoptionCenterRepository adoptionCenterRepository;
    private final GenericMapper<AdoptionCenter, AdoptionCenterDto> adoptionCenterMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final DogBreedService dogBreedService;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final CatBreedService catBreedService;

    @Autowired
    public AdoptionCenterServiceImplementation(AdoptionCenterRepository repository,
                                               GenericMapperFactory mapperFactory,
                                               DogBreedService dogbreedService,
                                               CatBreedService catbreedService) {
        this.adoptionCenterRepository = repository;
        this.dogBreedService = dogbreedService;
        this.adoptionCenterMapper = mapperFactory.createMapper(AdoptionCenter.class, AdoptionCenterDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.catBreedService = catbreedService;
    }

    @Override
    public Map<String, Object> getAllAdoptionCenters(int page, int size) {
        Page<AdoptionCenter> adoptionCenterPage = adoptionCenterRepository.findAll(PageRequest.of(page, size));
        return Map.of(
                "adoptionCenters", adoptionCenterPage.map(this::convertToDto).getContent(),
                "totalPages", adoptionCenterPage.getTotalPages(),
                "totalElements", adoptionCenterPage.getTotalElements()
        );
    }

    @Override
    public AdoptionCenterDto getAdoptionCenterById(Long id) {
        return adoptionCenterRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new CustomException("Adoption center with id " + id + " not found"));
    }

    @Override
    public AdoptionCenterDto createAdoptionCenter(AdoptionCenterDto adoptionCenterDto) {
        validateAdoptionCenterDto(adoptionCenterDto);
        adoptionCenterDto.setLastUpdate(LocalDate.now());
        adoptionCenterDto.setCreateDate(LocalDate.now());
        AdoptionCenter adoptionCenter = convertToEntity(adoptionCenterDto);
        return convertToDto(adoptionCenterRepository.save(adoptionCenter));
    }

    @Override
    public Boolean deleteAdoptionCenter(Long id) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Adoption center not found with id: " + id));
        adoptionCenterRepository.deleteById(id);
        return true;
    }

    @Override
    public AdoptionCenterDto updateAdoptionCenter(AdoptionCenterDto adoptionCenterDto) {
        AdoptionCenter oldCenter = adoptionCenterRepository.findById(adoptionCenterDto.getId())
                .orElseThrow(() -> new CustomException("Adoption center not found with id: " + adoptionCenterDto.getId()));

        AdoptionCenter newCenter = convertToEntity(adoptionCenterDto);
        newCenter.setCreateDate(oldCenter.getCreateDate());
        newCenter.setLastUpdate(LocalDate.now());
        copyCollections(oldCenter, newCenter);

        return convertToDto(adoptionCenterRepository.save(newCenter));
    }

    @Override
    public AdoptionCenterDto addDogBreedInAdoptionCenter(Long id, Long idDogBreed) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(id)
                .orElseThrow(() -> new CustomException("Adoption center not found"));

        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!adoptionCenter.getAvailableDogBreeds().contains(dogBreed)) {
            adoptionCenter.getAvailableDogBreeds().add(dogBreed);
        }

        return convertToDto(adoptionCenterRepository.save(adoptionCenter));
    }

    @Override
    public AdoptionCenterDto addCatBreedInAdoptionCenter(Long id, Long idCatBreed) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(id)
                .orElseThrow(() -> new CustomException("Adoption center not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed)); // corrected here
        if (!adoptionCenter.getAvailableCatBreeds().contains(catBreed)) {
            adoptionCenter.getAvailableCatBreeds().add(catBreed);
        }
        return convertToDto(adoptionCenterRepository.save(adoptionCenter));
    }

    @Override
    public List<DogBreedDto> getAvailableDogBreeds(Long adoptionCenterId) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(adoptionCenterId).orElseThrow();
        return adoptionCenter.getAvailableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatBreedDto> getAvailableCatBreeds(Long adoptionCenterId) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(adoptionCenterId).orElseThrow();
        return adoptionCenter.getAvailableCatBreeds().stream()
                .map(catBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    private void copyCollections(AdoptionCenter oldCenter, AdoptionCenter newCenter) {
        newCenter.setAvailableDogBreeds(oldCenter.getAvailableDogBreeds());
        newCenter.setAvailableCatBreeds(oldCenter.getAvailableCatBreeds());
    }

    @Override
    public AdoptionCenterDto removeCatBreedFromAdoptionCenter(Long centerId, Long idCatBreed) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(centerId)
                .orElseThrow(() -> new CustomException("Adoption center with id " + centerId + " not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        adoptionCenter.getAvailableCatBreeds().remove(catBreed);
        return adoptionCenterMapper.convertToDTO(adoptionCenterRepository.save(adoptionCenter));
    }

    @Override
    public AdoptionCenterDto removeDogBreedFromAdoptionCenter(Long centerId, Long dogBreedId) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(centerId)
                .orElseThrow(() -> new CustomException("Adoption center with id " + centerId + " not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(dogBreedId));
        adoptionCenter.getAvailableDogBreeds().remove(dogBreed);
        return adoptionCenterMapper.convertToDTO(adoptionCenterRepository.save(adoptionCenter));
    }

    private AdoptionCenterDto convertToDto(AdoptionCenter adoptionCenter) {
        return adoptionCenterMapper.convertToDTO(adoptionCenter);
    }

    private AdoptionCenter convertToEntity(AdoptionCenterDto adoptionCenterDto) {
        return adoptionCenterMapper.convertToEntity(adoptionCenterDto);
    }

    private void validateAdoptionCenterDto(AdoptionCenterDto dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new CustomException("Title cannot be null or empty");
        }
        if (dto.getLocation() == null || dto.getLocation().trim().isEmpty()) {
            throw new CustomException("Content cannot be null or empty");
        }
    }

}
