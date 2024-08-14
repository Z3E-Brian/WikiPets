package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.HealthIssueMapper;
import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;

@Service
public class HealthIssueService {
    private final HealthIssueRepository healthIssueRepository;
    private final HealthIssueMapper healthIssueMapper;

    @Autowired
    public HealthIssueService(HealthIssueRepository healthIssueRepository) {
        this.healthIssueRepository = healthIssueRepository;
        this.healthIssueMapper = HealthIssueMapper.INSTANCE;
    }

}
