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
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
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
        try {
            return healthIssueService.getAllHealthIssues(page, size);
        } catch (Exception e) {
            throw new NotFoundElementException("Could not retrieve health issues" + e.getMessage());
        }
    }

    @QueryMapping
    public HealthIssueDto getHealthIssueById(@Argument Long id) {
        if (id <= 0) {
            throw new InvalidInputException("Invalid Health Issue id. ");
        }
        return healthIssueService.getHealthIssueById(id);
    }

    @MutationMapping
    public HealthIssueDto createHealthIssue(@Argument HealthIssueInput input) {
        HealthIssueDto healthIssueDto = healthIssueMapper.convertToDTO(input);
        return healthIssueService.createHealthIssue(healthIssueDto);
    }

    @MutationMapping
    public HealthIssueDto updateHealthIssue(@Argument HealthIssueInput input) {
        HealthIssueDto healthIssueDto = healthIssueMapper.convertToDTO(input);
        return healthIssueService.updateHealthIssue(healthIssueDto);
    }

    @MutationMapping
    public boolean deleteHealthIssue(@Argument Long id) {
           return healthIssueService.deleteHealthIssue(id);
    }

    @MutationMapping
    public HealthIssueDto addDogBreedInHealthIssue(@Argument Long id, @Argument Long idDogBreed) {
            return healthIssueService.addSuitableDogBreed(id, idDogBreed);
    }

    @MutationMapping
    public HealthIssueDto addCatBreedInHealthIssue(@Argument Long id, @Argument Long idCatBreed) {
            return healthIssueService.addSuitableCatBreed(id, idCatBreed);
    }
}
