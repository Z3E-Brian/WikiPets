package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;

import org.springframework.data.domain.Pageable;


public interface HealthIssueService {
    Page<HealthIssueDto> getAllHealthIssues(Pageable pageable);

    HealthIssueDto getHealthIssueById(Long id);

    HealthIssueDto createHealthIssue(HealthIssueDto healthIssueDto);

    void deleteHealthIssue(Long id);

    HealthIssueDto updateHealthIssue(HealthIssueDto healthIssueDto);

    HealthIssueDto addSuitableDogBreed(Long IdIssue, Long dogBreedId);

    HealthIssueDto addSuitableCatBreed(Long IdIssue, Long catBreedId);
}