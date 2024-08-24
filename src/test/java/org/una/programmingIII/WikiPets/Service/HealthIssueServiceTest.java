package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Mapper.HealthIssueMapper;
import org.una.programmingIII.WikiPets.Model.HealthIssue;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    @Test
    public void getHealthIssueByIdTest() {
        HealthIssue healthIssue = new HealthIssue();
        when(healthIssueRepository.findById(anyLong())).thenReturn(Optional.of(healthIssue));
        when(healthIssueMapper.toHealthIssueDto(any(HealthIssue.class))).thenReturn(new HealthIssueDto());

        HealthIssueDto healthIssueDto = healthIssueService.getHealthIssueById(1L);

        assertNotNull(healthIssueDto);
        verify(healthIssueRepository, times(1)).findById(anyLong());
    }
    @Test
    public void createHealthIssueTest() {
        HealthIssue healthIssue = new HealthIssue();

        when(healthIssueRepository.save(any(HealthIssue.class))).thenReturn(healthIssue);
        when(healthIssueMapper.toHealthIssueDto(any(HealthIssue.class))).thenReturn(new HealthIssueDto());

        HealthIssueDto healthIssueDto = healthIssueService.createHealthIssue(new HealthIssueDto());

        assertNotNull(healthIssueDto);
        verify(healthIssueRepository, times(1)).save(any(HealthIssue.class));
    }

    @Test
    public void updateHealthIssueTest() {
        HealthIssue healthIssue = new HealthIssue();

        when(healthIssueRepository.save(any(HealthIssue.class))).thenReturn(healthIssue);
        when(healthIssueMapper.toHealthIssueDto(any(HealthIssue.class))).thenReturn(new HealthIssueDto());

        HealthIssueDto healthIssueDto = healthIssueService.updateHealthIssue(new HealthIssueDto());

        assertNotNull(healthIssueDto);
        verify(healthIssueRepository, times(1)).save(any(HealthIssue.class));
    }
    @Test
    public void deleteHealthIssueTest() {

        healthIssueService.deleteHealthIssue(1L);

        verify(healthIssueRepository, times(1)).deleteById(1L);
    }
}
