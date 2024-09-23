package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;

import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface HealthIssueService {
    Map<String, Object> getAllHealthIssues(  int page,  int size);

    HealthIssueDto getHealthIssueById(Long id);

    HealthIssueDto createHealthIssue(HealthIssueDto healthIssueDto);

    Boolean deleteHealthIssue(Long id);

    HealthIssueDto updateHealthIssue(HealthIssueDto healthIssueDto);

    HealthIssueDto addSuitableDogBreed(Long IdIssue, Long dogBreedId);

    HealthIssueDto addSuitableCatBreed(Long IdIssue, Long catBreedId);
}