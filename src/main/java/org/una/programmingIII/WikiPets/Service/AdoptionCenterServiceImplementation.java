package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
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
    public AdoptionCenterDto getAdoptionCenterById(@NotNull Long id) {
        validateId(id);
        return adoptionCenterRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundElementException("Adoption Center Not Found with id: " + id));
    }

    @Override
    public AdoptionCenterDto createAdoptionCenter(@NotNull AdoptionCenterDto adoptionCenterDto) {
        validateAdoptionCenterDto(adoptionCenterDto);
        adoptionCenterDto.setLastUpdate(LocalDate.now());
        adoptionCenterDto.setCreateDate(LocalDate.now());
        AdoptionCenter adoptionCenter = convertToEntity(adoptionCenterDto);
        return convertToDto(adoptionCenterRepository.save(adoptionCenter));
    }

    @Override
    public Boolean deleteAdoptionCenter(@NotNull Long id) {
        validateId(id);
        AdoptionCenter adoptionCenter = findAdoptionCenterById(id);
        removeBreedsFromAdoptionCenter(adoptionCenter, id);
        adoptionCenterRepository.deleteById(id);
        return true;
    }

    @Override
    public AdoptionCenterDto updateAdoptionCenter(@NotNull AdoptionCenterDto adoptionCenterDto) {
        validateId(adoptionCenterDto.getId());
        validateAdoptionCenterDto(adoptionCenterDto);

        AdoptionCenter oldCenter = findAdoptionCenterById(adoptionCenterDto.getId());
        AdoptionCenter newCenter = convertToEntity(adoptionCenterDto);
        newCenter.setCreateDate(oldCenter.getCreateDate());
        newCenter.setLastUpdate(LocalDate.now());
        copyCollections(oldCenter, newCenter);
        return convertToDto(adoptionCenterRepository.save(newCenter));
    }

    @Override
    public AdoptionCenterDto addDogBreedInAdoptionCenter(@NotNull Long id,@NotNull Long idDogBreed) {
        AdoptionCenter adoptionCenter = findAdoptionCenterById(id);
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!adoptionCenter.getAvailableDogBreeds().contains(dogBreed)) {
            adoptionCenter.getAvailableDogBreeds().add(dogBreed);
        }
        return convertToDto(adoptionCenterRepository.save(adoptionCenter));
    }

    @Override
    public AdoptionCenterDto addCatBreedInAdoptionCenter(@NotNull Long id, @NotNull Long idCatBreed) {
        validateId(id);
        validateId(idCatBreed);
        AdoptionCenter adoptionCenter = findAdoptionCenterById(id);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!adoptionCenter.getAvailableCatBreeds().contains(catBreed)) {
            adoptionCenter.getAvailableCatBreeds().add(catBreed);
        }
        return convertToDto(adoptionCenterRepository.save(adoptionCenter));
    }

    @Override
    public List<DogBreedDto> getAvailableDogBreeds(@NotNull Long adoptionCenterId) {
        validateId(adoptionCenterId);
        AdoptionCenter adoptionCenter = findAdoptionCenterById(adoptionCenterId);
        return adoptionCenter.getAvailableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatBreedDto> getAvailableCatBreeds(@NotNull Long adoptionCenterId) {
        validateId(adoptionCenterId);
        AdoptionCenter adoptionCenter = findAdoptionCenterById(adoptionCenterId);
        return adoptionCenter.getAvailableCatBreeds().stream()
                .map(catBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdoptionCenterDto deleteCatBreedFromAdoptionCenter(@NotNull Long centerId, @NotNull Long idCatBreed) {
        validateId(centerId);
        validateId(idCatBreed);
        AdoptionCenter adoptionCenter = findAdoptionCenterById(centerId);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        adoptionCenter.getAvailableCatBreeds().remove(catBreed);
        return convertToDto(adoptionCenterRepository.save(adoptionCenter));
    }

    @Override
    public AdoptionCenterDto deleteDogBreedFromAdoptionCenter(@NotNull Long centerId, @NotNull Long dogBreedId) {
        validateId(centerId);
        validateId(dogBreedId);
        AdoptionCenter adoptionCenter = findAdoptionCenterById(centerId);
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(dogBreedId));
        adoptionCenter.getAvailableDogBreeds().remove(dogBreed);
        adoptionCenter.setLastUpdate(LocalDate.now());
        return convertToDto(adoptionCenterRepository.save(adoptionCenter));
    }

    private AdoptionCenterDto convertToDto(AdoptionCenter adoptionCenter) {
        return adoptionCenterMapper.convertToDTO(adoptionCenter);
    }

    private AdoptionCenter convertToEntity(AdoptionCenterDto adoptionCenterDto) {
        return adoptionCenterMapper.convertToEntity(adoptionCenterDto);
    }

    private void copyCollections(@NotNull AdoptionCenter oldCenter, @NotNull AdoptionCenter newCenter) {
        newCenter.setAvailableDogBreeds(oldCenter.getAvailableDogBreeds());
        newCenter.setAvailableCatBreeds(oldCenter.getAvailableCatBreeds());
    }

    private void validateAdoptionCenterDto(@NotNull AdoptionCenterDto dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
        if (dto.getLocation() == null || dto.getLocation().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
    }

    private void validateId(Long id) {
        if (id <= 0) {
            throw new InvalidInputException("Invalid ID");
        }
    }

    private AdoptionCenter findAdoptionCenterById(Long id) {
        return adoptionCenterRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Adoption Center not found"));
    }

    private void removeBreedsFromAdoptionCenter(@NotNull AdoptionCenter adoptionCenter, @NotNull Long id) {
        adoptionCenter.getAvailableCatBreeds().forEach(catBreed ->
                catBreed.getAdoptionCenters().removeIf(center -> center.getId().equals(id))
        );
        adoptionCenter.getAvailableDogBreeds().forEach(dogBreed ->
                dogBreed.getAdoptionCenters().removeIf(center -> center.getId().equals(id))
        );
    }
}