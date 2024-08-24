package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.HealthIssueMapper;
import org.una.programmingIII.WikiPets.Model.HealthIssue;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;

import java.util.List;
import java.util.stream.Collectors;
public interface HealthIssueService {
    public List<HealthIssueDto> getAllHealthIssues();

    public HealthIssueDto getHealthIssueById(Long id);

    public HealthIssueDto createHealthIssue(HealthIssueDto healthIssueDto);

    public void deleteHealthIssue(Long id);

    public HealthIssueDto updateHealthIssue(HealthIssueDto healthIssueDto);
}