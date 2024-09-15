package org.una.programmingIII.WikiPets.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.HealthIssue;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public Page<HealthIssueDto> getAllHealthIssues(Pageable pageable) {
        Page<HealthIssue> healthIssues = healthIssueRepository.findAll(pageable);
        healthIssues.forEach(healthIssue -> {
            healthIssue.setSuitableCatBreeds(healthIssue.getSuitableCatBreeds().stream().limit(10).collect(Collectors.toList()));
            healthIssue.setSuitableDogBreeds(healthIssue.getSuitableDogBreeds().stream().limit(10).collect(Collectors.toList()));});
        return healthIssues.map(this::convertToDto);
    }

    @Override
    public HealthIssueDto getHealthIssueById(Long id) {
        HealthIssue healthIssue = healthIssueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health Issue Not Found with id: " + id));
        return convertToDto(healthIssue);
    }

    @Override
    public HealthIssueDto createHealthIssue(HealthIssueDto healthIssueDto) {
        healthIssueDto.setCreatedDate(LocalDate.now());
        healthIssueDto.setModifiedDate(LocalDate.now());
        HealthIssue healthIssue = convertToEntity(healthIssueDto);
        return convertToDto(healthIssueRepository.save(healthIssue));
    }

    @Override
    public void deleteHealthIssue(Long id) {
        HealthIssue healthIssue = healthIssueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health Issue Not Found with id: " + id));
        healthIssue.getSuitableCatBreeds().forEach(catBreed ->
                catBreed.getHealthIssues().removeIf(issue -> issue.getId().equals(id))
        );
        healthIssue.getSuitableDogBreeds().forEach(dogBreed ->
                dogBreed.getHealthIssues().removeIf(issue -> issue.getId().equals(id))
        );
        healthIssueRepository.deleteById(id);
    }

    @Override
    public HealthIssueDto updateHealthIssue(HealthIssueDto healthIssueDto) {
        HealthIssue oldHealthIssue = healthIssueRepository.findById(healthIssueDto.getId())
                .orElseThrow(() -> new RuntimeException("Health Issue Not Found with id: " + healthIssueDto.getId()));
        HealthIssue newHealthIssue = convertToEntity(healthIssueDto);
        newHealthIssue.setCreatedDate(oldHealthIssue.getCreatedDate());
        newHealthIssue.setModifiedDate(LocalDate.now());

        newHealthIssue.setSuitableCatBreeds(oldHealthIssue.getSuitableCatBreeds() != null ?
                new ArrayList<>(oldHealthIssue.getSuitableCatBreeds()) : new ArrayList<>());
        newHealthIssue.setSuitableDogBreeds(oldHealthIssue.getSuitableDogBreeds() != null ?
                new ArrayList<>(oldHealthIssue.getSuitableDogBreeds()) : new ArrayList<>());

        return convertToDto(healthIssueRepository.save(newHealthIssue));
    }

    @Override
    public HealthIssueDto addSuitableDogBreed(Long IdIssue, Long dogBreedId) {
        HealthIssue healthIssue = healthIssueRepository.findById(IdIssue)
                .orElseThrow(() -> new RuntimeException("Health Issue Not Found with id: " + IdIssue));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(dogBreedId));
        if (!healthIssue.getSuitableDogBreeds().contains(dogBreed)) {
            healthIssue.getSuitableDogBreeds().add(dogBreed);
        }
        return convertToDto(healthIssueRepository.save(healthIssue));
    }

    @Override
    public HealthIssueDto addSuitableCatBreed(Long IdIssue, Long catBreedId) {
        HealthIssue healthIssue = healthIssueRepository.findById(IdIssue)
                .orElseThrow(() -> new RuntimeException("Health Issue Not Found with id: " + IdIssue));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(catBreedId));
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
