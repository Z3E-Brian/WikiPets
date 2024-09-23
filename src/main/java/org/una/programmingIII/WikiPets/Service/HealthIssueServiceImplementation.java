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
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.HealthIssue;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HealthIssueServiceImplementation implements HealthIssueService {
    private final HealthIssueRepository healthIssueRepository;
    private final GenericMapper<HealthIssue, HealthIssueDto> healthIssueMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public HealthIssueServiceImplementation(HealthIssueRepository healthIssueRepository, GenericMapperFactory mapperFactory, DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.healthIssueRepository = healthIssueRepository;
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
        this.healthIssueMapper = mapperFactory.createMapper(HealthIssue.class, HealthIssueDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
    }

    @Override
    public Map<String, Object> getAllHealthIssues(int page, int size) {
        Page<HealthIssue> healthIssues = healthIssueRepository.findAll(PageRequest.of(page, size));
        healthIssues.forEach(healthIssue -> {
            healthIssue.setSuitableCatBreeds(healthIssue.getSuitableCatBreeds().stream().limit(10).collect(Collectors.toList()));
            healthIssue.setSuitableDogBreeds(healthIssue.getSuitableDogBreeds().stream().limit(10).collect(Collectors.toList()));
        });
        Map<String, Object> response = new HashMap<>();
        response.put("healthIssues", healthIssues.map(this::convertToDto).getContent());
        response.put("totalPages", healthIssues.getTotalPages());
        response.put("totalElements", healthIssues.getTotalElements());
        return response;
    }

    @Override
    public HealthIssueDto getHealthIssueById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid HealthIssue Schedule ID");
        }
        HealthIssue healthIssue = healthIssueRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Health Issue Not Found with id: " + id));
        return convertToDto(healthIssue);
    }

    @Override
    public HealthIssueDto createHealthIssue(HealthIssueDto healthIssueDto) {
        if (healthIssueDto.getName().isBlank() || healthIssueDto.getDescription().isBlank()) {
            throw new BlankInputException("Can't have important spaces in blank");
        }
        HealthIssue healthIssue = convertToEntity(healthIssueDto);
        healthIssue.setCreatedDate(LocalDate.now());
        healthIssue.setModifiedDate(LocalDate.now());
        return convertToDto(healthIssueRepository.save(healthIssue));
    }

    @Override
    public Boolean deleteHealthIssue(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid HealthIssue Schedule ID");
        }
        HealthIssue healthIssue = healthIssueRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Health Issue Not Found with id: " + id));
        if (healthIssue.getSuitableCatBreeds() != null) {
            healthIssue.getSuitableCatBreeds().forEach(catBreed ->
                    catBreed.getHealthIssues().removeIf(issue -> issue.getId().equals(id))
            );
        }
        if (healthIssue.getSuitableDogBreeds() != null) {
            healthIssue.getSuitableDogBreeds().forEach(dogBreed ->
                    dogBreed.getHealthIssues().removeIf(issue -> issue.getId().equals(id))
            );
        }
        healthIssueRepository.deleteById(id);
        return true;
    }

    @Override
    public HealthIssueDto updateHealthIssue(HealthIssueDto healthIssueDto) {
        if (healthIssueDto.getId() == null || healthIssueDto.getId() <= 0) {
            throw new InvalidInputException("Invalid HealthIssue Schedule ID");
        }

        if (healthIssueDto.getName().isBlank() || healthIssueDto.getDescription().isBlank()) {
            throw new BlankInputException("Can't have important spaces in blank");
        }
        HealthIssue oldHealthIssue = healthIssueRepository.findById(healthIssueDto.getId())
                .orElseThrow(() -> new RuntimeException("Health Issue Not Found with id: " + healthIssueDto.getId()));
        HealthIssue newHealthIssue = convertToEntity(healthIssueDto);
        newHealthIssue.setCreatedDate(oldHealthIssue.getCreatedDate());
        newHealthIssue.setModifiedDate(LocalDate.now());
        newHealthIssue.setSuitableCatBreeds(new ArrayList<>());
        newHealthIssue.setSuitableDogBreeds(new ArrayList<>());
        oldHealthIssue.getSuitableCatBreeds().stream()
                .filter(catBreed -> !newHealthIssue.getSuitableCatBreeds().contains(catBreed))
                .forEach(newHealthIssue.getSuitableCatBreeds()::add);
        oldHealthIssue.getSuitableDogBreeds().stream()
                .filter(dogBreed -> !newHealthIssue.getSuitableDogBreeds().contains(dogBreed))
                .forEach(newHealthIssue.getSuitableDogBreeds()::add);
        return convertToDto(healthIssueRepository.save(newHealthIssue));
    }

    @Override
    public HealthIssueDto addSuitableDogBreed(Long IdIssue, Long dogBreedId) {
        HealthIssue healthIssue = healthIssueRepository.findById(IdIssue)
                .orElseThrow(() -> new NotFoundElementException("Health Issue Not Found with id: " + IdIssue));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(dogBreedId));
        if (healthIssue.getSuitableDogBreeds() == null) {
            healthIssue.setSuitableDogBreeds(new ArrayList<>());
        }
        if (!healthIssue.getSuitableDogBreeds().contains(dogBreed)) {
            healthIssue.getSuitableDogBreeds().add(dogBreed);
        }
        return convertToDto(healthIssueRepository.save(healthIssue));
    }

    @Override
    public HealthIssueDto addSuitableCatBreed(Long IdIssue, Long catBreedId) {
        HealthIssue healthIssue = healthIssueRepository.findById(IdIssue)
                .orElseThrow(() -> new NotFoundElementException("Health Issue Not Found with id: " + IdIssue));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(catBreedId));
        if (healthIssue.getSuitableCatBreeds() == null) {
            healthIssue.setSuitableCatBreeds(new ArrayList<>());
        }
        if (!healthIssue.getSuitableCatBreeds().contains(catBreed)) {
            healthIssue.getSuitableCatBreeds().add(catBreed);
        }
        return convertToDto(healthIssueRepository.save(healthIssue));
    }

    private HealthIssueDto convertToDto(HealthIssue healthIssue) {
        return healthIssueMapper.convertToDTO(healthIssue);

    }

    private HealthIssue convertToEntity(HealthIssueDto healthIssueDto) {
        return healthIssueMapper.convertToEntity(healthIssueDto);
    }

}
