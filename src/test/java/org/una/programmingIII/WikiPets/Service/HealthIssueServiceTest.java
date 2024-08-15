package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Mapper.HealthIssueMapper;
import org.una.programmingIII.WikiPets.Model.HealthIssue;
import org.una.programmingIII.WikiPets.Model.HealthIssueDto;
import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HealthIssueServiceTest {
    @Mock
    private HealthIssueRepository healthIssueRepository;

    @Mock
    private HealthIssueMapper healthIssueMapper;

    @InjectMocks
    private HealthIssueService healthIssueService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllHealthIssuesTest() {
        HealthIssue healthIssue = new HealthIssue();
        when(healthIssueRepository.findAll()).thenReturn(Arrays.asList(healthIssue));
        when(healthIssueMapper.toHealthIssueDto(any(HealthIssue.class))).thenReturn(new HealthIssueDto());

        List<HealthIssueDto> healthIssueDtos = healthIssueService.getAllHealthIssues();

        assertNotNull(healthIssueDtos);
        assertEquals(1, healthIssueDtos.size());
        verify(healthIssueRepository, times(1)).findAll();

    }
}
