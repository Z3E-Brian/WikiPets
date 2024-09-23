package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.GroomingGuide;
import org.una.programmingIII.WikiPets.Repository.GroomingGuideRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Validated
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
    public GroomingGuideDto getGroomingGuideById(Long id) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + id));
        return convertToDto(groomingGuide);
    }

    @Override
    public GroomingGuideDto createGroomingGuide(@Validated GroomingGuideDto groomingGuideDto) {
        groomingGuideDto.setCreateDate(LocalDate.now());
        groomingGuideDto.setLastUpdate(LocalDate.now());
        GroomingGuide groomingGuide = convertToEntity(groomingGuideDto);
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public void deleteGroomingGuide(Long id) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + id));
        groomingGuide.getSuitableCatBreeds().forEach(catBreed ->
                catBreed.getHealthIssues().removeIf(grooming -> grooming.getId().equals(id))
        );
        groomingGuide.getSuitableDogBreeds().forEach(dogBreed ->
                dogBreed.getHealthIssues().removeIf(grooming -> grooming.getId().equals(id))
        );
        groomingGuideRepository.deleteById(id);
    }

    @Override
    public GroomingGuideDto updateGroomingGuide(GroomingGuideDto groomingGuideDto) {
        GroomingGuide oldGroomingGuide = groomingGuideRepository.findById(groomingGuideDto.getId())
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + groomingGuideDto.getId()));
        GroomingGuide newGroomingGuide = convertToEntity(groomingGuideDto);
        newGroomingGuide.setCreateDate(oldGroomingGuide.getCreateDate());
        newGroomingGuide.setLastUpdate(LocalDate.now());

        newGroomingGuide.setSuitableCatBreeds(oldGroomingGuide.getSuitableCatBreeds() != null ?
                new ArrayList<>(oldGroomingGuide.getSuitableCatBreeds()) : new ArrayList<>());
        newGroomingGuide.setSuitableDogBreeds(oldGroomingGuide.getSuitableDogBreeds() != null ?
                new ArrayList<>(oldGroomingGuide.getSuitableDogBreeds()) : new ArrayList<>());

        return convertToDto(groomingGuideRepository.save(newGroomingGuide));
    }

    @Override
    public GroomingGuideDto addSuitableDogBreedToGroomingGuide(Long id, Long idDogBreed) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + id));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!groomingGuide.getSuitableDogBreeds().contains(dogBreed)) {
            groomingGuide.getSuitableDogBreeds().add(dogBreed);
        }
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public GroomingGuideDto addSuitableCatBreedToGroomingGuide(Long id, Long idCatBreed) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + id));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!groomingGuide.getSuitableCatBreeds().contains(catBreed)) {
            groomingGuide.getSuitableCatBreeds().add(catBreed);
        }
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public List<DogBreedDto> getSuitableDogBreeds(Long groomingGuideId) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(groomingGuideId)
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + groomingGuideId));
        return groomingGuide.getSuitableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatBreedDto> getSuitableCatBreeds(Long groomingGuideId) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(groomingGuideId)
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + groomingGuideId));
        return groomingGuide.getSuitableCatBreeds().stream()
                .map(catBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GroomingGuideDto removeSuitableCatBreedFromGroomingGuide(Long id, Long idCatBreed) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + id));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (catBreed == null) {
            throw new RuntimeException("Cat breed Not Found with id: " + idCatBreed);
        }
        if (groomingGuide.getSuitableCatBreeds().contains(catBreed)) {
            groomingGuide.getSuitableCatBreeds().remove(catBreed);
        } else {
            throw new RuntimeException("Cat breed Not Found in Grooming guide");
        }
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public GroomingGuideDto removeSuitableDogBreedFromGroomingGuide(Long id, Long idDogBreed) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grooming guide Not Found with id: " + id));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (dogBreed == null) {
            throw new RuntimeException("Dog breed Not Found with id: " + idDogBreed);
        }
        if (groomingGuide.getSuitableDogBreeds().contains(dogBreed)) {
            groomingGuide.getSuitableDogBreeds().remove(dogBreed);
        } else {
            throw new RuntimeException("Dog breed Not Found in Grooming guide");
        }
        return convertToDto(groomingGuideRepository.save(groomingGuide));
    }

    private GroomingGuideDto convertToDto(GroomingGuide groomingGuide) {
        return groomingGuideMapper.convertToDTO(groomingGuide);
    }

    private GroomingGuide convertToEntity(GroomingGuideDto groomingGuideDto) {
        return groomingGuideMapper.convertToEntity(groomingGuideDto);
    }
}