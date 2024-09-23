package org.una.programmingIII.WikiPets.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.HealthIssueInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.HealthIssueService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthIssueControllerTest {

    @InjectMocks
    private HealthIssueController healthIssueController;

    @Mock
    private HealthIssueService healthIssueService;

    @Mock
    private GenericMapper<HealthIssueInput, HealthIssueDto> healthIssueMapper;
    @Mock
    private GenericMapperFactory mapperFactory;

    private HealthIssueDto healthIssueDto;
    private HealthIssueInput healthIssueInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        healthIssueDto = new HealthIssueDto();
        healthIssueInput = new HealthIssueInput();
        when(mapperFactory.createMapper(HealthIssueInput.class, HealthIssueDto.class)).thenReturn(healthIssueMapper);
        healthIssueController = new HealthIssueController(healthIssueService, mapperFactory);
    }

    @Test
    void testHealthIssueControllerConstructor() {
        assertNotNull(healthIssueController);
    }

    @Test
    void testGetHealthIssuesSuccess() {
        Map<String, Object> healthIssuesMap = new HashMap<>();
        when(healthIssueService.getAllHealthIssues(0, 10)).thenReturn(healthIssuesMap);

        Map<String, Object> result = healthIssueController.getHealthIssues(0, 10);

        assertNotNull(result);
        verify(healthIssueService, times(1)).getAllHealthIssues(0, 10);
    }

    @Test
    void testGetHealthIssuesNotFound() {
        when(healthIssueService.getAllHealthIssues(0, 10)).thenThrow(new RuntimeException("No health issues found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            healthIssueController.getHealthIssues(0, 10);
        });

        assertEquals("Could not retrieve health issuesNo health issues found", exception.getMessage());
    }

    @Test
    void testGetHealthIssueByIdSuccess() {
        when(healthIssueService.getHealthIssueById(1L)).thenReturn(healthIssueDto);

        HealthIssueDto result = healthIssueController.getHealthIssueById(1L);

        assertNotNull(result);
        verify(healthIssueService, times(1)).getHealthIssueById(1L);
    }

    @Test
    void testGetHealthIssueByIdInvalidInput() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            healthIssueController.getHealthIssueById(0L);
        });

        assertEquals("Invalid Health Issue id. ", exception.getMessage());
    }

    @Test
    void testCreateHealthIssueSuccess() {
        when(healthIssueMapper.convertToDTO(any(HealthIssueInput.class))).thenReturn(healthIssueDto);
        when(healthIssueService.createHealthIssue(any(HealthIssueDto.class))).thenReturn(healthIssueDto);

        HealthIssueDto result = healthIssueController.createHealthIssue(healthIssueInput);

        assertNotNull(result);
        verify(healthIssueMapper, times(1)).convertToDTO(healthIssueInput);
        verify(healthIssueService, times(1)).createHealthIssue(healthIssueDto);
    }

    @Test
    void testUpdateHealthIssueSuccess() {
        when(healthIssueMapper.convertToDTO(any(HealthIssueInput.class))).thenReturn(healthIssueDto);
        when(healthIssueService.updateHealthIssue(any(HealthIssueDto.class))).thenReturn(healthIssueDto);

        HealthIssueDto result = healthIssueController.updateHealthIssue(healthIssueInput);

        assertNotNull(result);
        verify(healthIssueMapper, times(1)).convertToDTO(healthIssueInput);
        verify(healthIssueService, times(1)).updateHealthIssue(healthIssueDto);
    }

    @Test
    void testDeleteHealthIssueSuccess() {
        when(healthIssueService.deleteHealthIssue(1L)).thenReturn(true);

        boolean result = healthIssueController.deleteHealthIssue(1L);

        assertTrue(result);
        verify(healthIssueService, times(1)).deleteHealthIssue(1L);
    }

    @Test
    void testAddDogBreedInHealthIssueSuccess() {
        when(healthIssueService.addSuitableDogBreed(1L, 2L)).thenReturn(healthIssueDto);

        HealthIssueDto result = healthIssueController.addDogBreedInHealthIssue(1L, 2L);

        assertNotNull(result);
        verify(healthIssueService, times(1)).addSuitableDogBreed(1L, 2L);
    }

    @Test
    void testAddDogBreedInHealthIssueFailure() {
        when(healthIssueService.addSuitableDogBreed(1L, 2L)).thenThrow(new NotFoundElementException("Could not add dog breed to health issueCat breed not found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            healthIssueController.addDogBreedInHealthIssue(1L, 2L);
        });

        assertEquals("Could not add dog breed to health issueCat breed not found", exception.getMessage());
    }

    @Test
    void testAddCatBreedInHealthIssueSuccess() {
        when(healthIssueService.addSuitableCatBreed(1L, 2L)).thenReturn(healthIssueDto);

        HealthIssueDto result = healthIssueController.addCatBreedInHealthIssue(1L, 2L);

        assertNotNull(result);
        verify(healthIssueService, times(1)).addSuitableCatBreed(1L, 2L);
    }

    @Test
    void testAddCatBreedInHealthIssueFailure() {
        when(healthIssueService.addSuitableCatBreed(1L, 2L)).thenThrow(new NotFoundElementException("Could not add cat breed to health issueCat breed not found"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            healthIssueController.addCatBreedInHealthIssue(1L, 2L);
        });

        assertEquals("Could not add cat breed to health issueCat breed not found", exception.getMessage());
    }
}
