package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;

import java.util.List;

public interface HealthIssueService {
    public List<HealthIssueDto> getAllHealthIssues();

    public HealthIssueDto getHealthIssueById(Long id);

    public HealthIssueDto createHealthIssue(HealthIssueDto healthIssueDto);

    public void deleteHealthIssue(Long id);

    public HealthIssueDto updateHealthIssue(HealthIssueDto healthIssueDto);
}