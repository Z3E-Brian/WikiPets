package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.HealthIssueMapper;
import org.una.programmingIII.WikiPets.Model.HealthIssue;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthIssueService {
    private final HealthIssueRepository healthIssueRepository;
    private final HealthIssueMapper healthIssueMapper;

    @Autowired
    public HealthIssueService(HealthIssueRepository healthIssueRepository) {
        this.healthIssueRepository = healthIssueRepository;
        this.healthIssueMapper = HealthIssueMapper.INSTANCE;
    }

    public List<HealthIssueDto> getAllHealthIssues() {
        List<HealthIssue> healthIssues = healthIssueRepository.findAll();
        return healthIssues.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public HealthIssueDto getHealthIssueById(Long id) {
        HealthIssue healthIssue = healthIssueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health Issue Not Found with id: " + id));
        return convertToDto(healthIssue);
    }

    public HealthIssueDto createHealthIssue(HealthIssueDto healthIssueDto) {
        HealthIssue healthIssue = convertToEntity(healthIssueDto);
        HealthIssue savedHealthIssue = healthIssueRepository.save(healthIssue);
        return convertToDto(savedHealthIssue);
    }

    public void deleteHealthIssue(Long id) {
        healthIssueRepository.deleteById(id);
    }

    public HealthIssueDto updateHealthIssue(HealthIssueDto healthIssueDto) {
        HealthIssue healthIssue = convertToEntity(healthIssueDto);
        HealthIssue updatedHealthIssue = healthIssueRepository.save(healthIssue);
        return convertToDto(updatedHealthIssue);
    }

    private HealthIssueDto convertToDto(HealthIssue healthIssue) {
        return healthIssueMapper.toHealthIssueDto(healthIssue);
        
    }

    private HealthIssue convertToEntity(HealthIssueDto healthIssueDto) {
        return healthIssueMapper.toHealthIssue(healthIssueDto);
    }

}
