package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.GroomingGuide;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Repository.GroomingGuideRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroomingGuideServiceImplementation implements GroomingGuideService {
    private final GroomingGuideRepository groomingGuideRepository;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final GenericMapper<GroomingGuide, GroomingGuideDto> groomingGuideMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public GroomingGuideServiceImplementation(
            GroomingGuideRepository groomingGuideRepository,
            GenericMapperFactory mapperFactory,
            DogBreedService dogBreedService,
            CatBreedService catBreedService) {

        this.groomingGuideRepository = groomingGuideRepository;
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.groomingGuideMapper = mapperFactory.createMapper(GroomingGuide.class, GroomingGuideDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }

    @Override
    public Map<String, Object> getAllGroomingGuides(int page, int size) {
        Page<GroomingGuide> groomingGuides = groomingGuideRepository.findAll(PageRequest.of(page, size));
        groomingGuides.forEach(groomingGuide -> {
            groomingGuide.setSuitableCatBreeds(groomingGuide.getSuitableCatBreeds().stream().limit(10).collect(Collectors.toList()));
            groomingGuide.setSuitableDogBreeds(groomingGuide.getSuitableDogBreeds().stream().limit(10).collect(Collectors.toList()));
        });
        Map<String, Object> response = new HashMap<>();
        response.put("groomingGuides", groomingGuides.map(this::convertToDto).getContent());
        response.put("totalPages", groomingGuides.getTotalPages());
        response.put("totalElements", groomingGuides.getTotalElements());
        return response;
    }

    @Override
    public GroomingGuideDto getGroomingGuideById(@NotNull Long id) {
        validateId(id);
        return groomingGuideRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundElementException("Grooming guide Not Found with id: " + id));
    }

    @Override
    public GroomingGuideDto createGroomingGuide(@NotNull GroomingGuideDto groomingGuideDto) {
        validateGroomingGuideDto(groomingGuideDto);
        groomingGuideDto.setCreateDate(LocalDate.now());
        groomingGuideDto.setLastUpdate(LocalDate.now());
        GroomingGuide groomingGuide = convertToEntity(groomingGuideDto);
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public Boolean deleteGroomingGuide(@NotNull Long id) {
        validateId(id);
        GroomingGuide groomingGuide = findGroomingGuideById(id);
        removeBreedsFromGroomingGuide(groomingGuide, id);
        groomingGuideRepository.deleteById(id);
        return true;
    }

    @Override
    public GroomingGuideDto updateGroomingGuide(@NotNull GroomingGuideDto groomingGuideDto) {
        validateId(groomingGuideDto.getId());
        validateGroomingGuideDto(groomingGuideDto);

        GroomingGuide oldGroomingGuide = findGroomingGuideById(groomingGuideDto.getId());
        GroomingGuide newGroomingGuide = convertToEntity(groomingGuideDto);
        newGroomingGuide.setCreateDate(oldGroomingGuide.getCreateDate());
        newGroomingGuide.setLastUpdate(LocalDate.now());
        copyCollections(oldGroomingGuide, newGroomingGuide);
        return convertToDto(groomingGuideRepository.save(newGroomingGuide));
    }

    @Override
    public GroomingGuideDto addSuitableDogBreedToGroomingGuide(@NotNull Long id,@NotNull Long idDogBreed) {
        GroomingGuide groomingGuide = findGroomingGuideById(id);
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!groomingGuide.getSuitableDogBreeds().contains(dogBreed)) {
            groomingGuide.getSuitableDogBreeds().add(dogBreed);
        }
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public GroomingGuideDto addSuitableCatBreedToGroomingGuide(@NotNull Long id,@NotNull Long idCatBreed) {
        validateId(id);
        validateId(idCatBreed);
        GroomingGuide groomingGuide = findGroomingGuideById(id);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!groomingGuide.getSuitableCatBreeds().contains(catBreed)) {
            groomingGuide.getSuitableCatBreeds().add(catBreed);
        }
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public List<DogBreedDto> getGroomingSuitableDogBreeds(@NotNull Long groomingGuideId) {
        validateId(groomingGuideId);
        GroomingGuide groomingGuide = findGroomingGuideById(groomingGuideId);
        return groomingGuide.getSuitableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatBreedDto> getGroomingSuitableCatBreeds(@NotNull Long groomingGuideId) {
        validateId(groomingGuideId);
        GroomingGuide groomingGuide = findGroomingGuideById(groomingGuideId);
        return groomingGuide.getSuitableCatBreeds().stream()
                .map(catBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GroomingGuideDto deleteSuitableCatBreedFromGroomingGuide(@NotNull Long id,@NotNull Long idCatBreed) {
        validateId(id);
        validateId(idCatBreed);
        GroomingGuide groomingGuide = findGroomingGuideById(id);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        groomingGuide.getSuitableCatBreeds().remove(catBreed);
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public GroomingGuideDto deleteSuitableDogBreedFromGroomingGuide(@NotNull Long id,@NotNull Long idDogBreed) {
        validateId(id);
        validateId(idDogBreed);
        GroomingGuide groomingGuide = findGroomingGuideById(id);
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        groomingGuide.getSuitableDogBreeds().remove(dogBreed);
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    private GroomingGuideDto convertToDto(GroomingGuide groomingGuide) {
        return groomingGuideMapper.convertToDTO(groomingGuide);
    }

    private GroomingGuide convertToEntity(GroomingGuideDto groomingGuideDto) {
        return groomingGuideMapper.convertToEntity(groomingGuideDto);
    }

    private void copyCollections(@NotNull GroomingGuide oldCenter, @NotNull GroomingGuide newCenter) {
        newCenter.setSuitableDogBreeds(oldCenter.getSuitableDogBreeds());
        newCenter.setSuitableCatBreeds(oldCenter.getSuitableCatBreeds());
    }

    private void validateGroomingGuideDto(@NotNull GroomingGuideDto dto) {
        if (dto.getSteps() == null || dto.getSteps().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
        if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
        if(dto.getToolsNeeded()==null || dto.getToolsNeeded().trim().isEmpty()){
            throw new BlankInputException("Can't accept spaces in blank");
        }
    }

    private void validateId(Long id) {
        if (id <= 0) {
            throw new InvalidInputException("Invalid ID");
        }
    }

    private GroomingGuide findGroomingGuideById(Long id) {
        return groomingGuideRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Adoption Center not found"));
    }

    private void removeBreedsFromGroomingGuide(@NotNull GroomingGuide groomingGuide, @NotNull Long id) {
        groomingGuide.getSuitableCatBreeds().forEach(catBreed ->
                catBreed.getGroomingGuides().removeIf(center -> center.getId().equals(id))
        );
        groomingGuide.getSuitableDogBreeds().forEach(dogBreed ->
                dogBreed.getGroomingGuides().removeIf(center -> center.getId().equals(id))
        );
    }
}