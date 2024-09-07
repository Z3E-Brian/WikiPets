package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.HealthIssueInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.HealthIssueService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class HealthIssueController {
    private final HealthIssueService healthIssueService;
    private final GenericMapper<HealthIssueInput, HealthIssueDto> healthIssueMapper;

    @Autowired
    public HealthIssueController(HealthIssueService service, GenericMapperFactory mapperFactory) {
        this.healthIssueService = service;
        this.healthIssueMapper = mapperFactory.createMapper(HealthIssueInput.class, HealthIssueDto.class);
    }

    @QueryMapping
    public Map<String, Object> getHealthIssues(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HealthIssueDto> healthIssuePage = healthIssueService.getAllHealthIssues(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("healthIssues", healthIssuePage.getContent());
        response.put("totalPages", healthIssuePage.getTotalPages());
        response.put("totalElements", healthIssuePage.getTotalElements());

        return response;
    }

    @QueryMapping
    public HealthIssueDto getHealthIssueById(@Argument Long id) {
        try {
            return healthIssueService.getHealthIssueById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find health issue" + e.getMessage());
        }
    }

    @MutationMapping
    public HealthIssueDto createHealthIssue(@Argument HealthIssueInput input) {
        try {
            HealthIssueDto healthIssueDto = healthIssueMapper.convertToDTO(input);
            return healthIssueService.createHealthIssue(healthIssueDto);
        } catch (Exception e) {
            throw new CustomException("Could not create health issue" + e.getMessage());
        }
    }

    @MutationMapping
    public HealthIssueDto updateHealthIssue(@Argument HealthIssueInput input) {
        try {
            HealthIssueDto healthIssueDto = healthIssueMapper.convertToDTO(input);
            return healthIssueService.updateHealthIssue(healthIssueDto);
        } catch (Exception e) {
            throw new CustomException("Could not update health issue" + e.getMessage());
        }
    }

    @MutationMapping
    public boolean deleteHealthIssue(@Argument Long id) {
        try {
            healthIssueService.deleteHealthIssue(id);
            return true;
        } catch (Exception e) {
            throw new CustomException("Could not delete health issue" + e.getMessage());
        }
    }

    @MutationMapping
    public HealthIssueDto addDogBreedInHealthIssue(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return healthIssueService.addSuitableDogBreed(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add dog breed to health issue" + e.getMessage());
        }
    }

    @MutationMapping
    public HealthIssueDto addCatBreedInHealthIssue(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return healthIssueService.addSuitableCatBreed(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add cat breed to health issue" + e.getMessage());
        }
    }
}
