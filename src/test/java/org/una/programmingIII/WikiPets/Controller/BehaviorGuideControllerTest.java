package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.BehaviorGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Service.BehaviorGuideService;

import java.util.HashMap;
import java.util.Map;

public class BehaviorGuideControllerTest {

    @InjectMocks
    private BehaviorGuideController behaviorGuideController;

    @Mock
    private BehaviorGuideService behaviorGuideService;

    @Mock
    private GenericMapper<BehaviorGuideInput, BehaviorGuideDto> behaviorGuideMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBehaviorGuides() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedGuides = new HashMap<>();
        when(behaviorGuideService.getAllBehaviorGuides(page, size)).thenReturn(expectedGuides);

        Map<String, Object> result = behaviorGuideController.getAllBehaviorGuides(page, size);

        assertEquals(expectedGuides, result);
        verify(behaviorGuideService).getAllBehaviorGuides(page, size);
    }

    @Test
    void testGetBehaviorGuidesException() {
        int page = 1;
        int size = 10;
        when(behaviorGuideService.getAllBehaviorGuides(page, size)).thenThrow(new NotFoundElementException(""));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            behaviorGuideController.getAllBehaviorGuides(page, size);
        });
        assertEquals("Could not retrieve behavior guides", exception.getMessage());
    }

    @Test
    void testGetBehaviorGuideById() {
        Long id = 1L;
        BehaviorGuideDto expectedGuide = new BehaviorGuideDto();
        when(behaviorGuideService.getBehaviorGuideById(id)).thenReturn(expectedGuide);

        BehaviorGuideDto result = behaviorGuideController.getBehaviorGuideById(id);

        assertEquals(expectedGuide, result);
        verify(behaviorGuideService).getBehaviorGuideById(id);
    }

    @Test
    void testCreateBehaviorGuide() {
        BehaviorGuideInput input = new BehaviorGuideInput();
        BehaviorGuideDto expectedGuideDto = new BehaviorGuideDto();
        when(behaviorGuideMapper.convertToDTO(input)).thenReturn(expectedGuideDto);
        when(behaviorGuideService.createBehaviorGuide(expectedGuideDto)).thenReturn(expectedGuideDto);

        BehaviorGuideDto result = behaviorGuideController.createBehaviorGuide(input);

        assertEquals(expectedGuideDto, result);
        verify(behaviorGuideMapper).convertToDTO(input);
        verify(behaviorGuideService).createBehaviorGuide(expectedGuideDto);
    }

    @Test
    void testUpdateBehaviorGuide() {

        BehaviorGuideInput input = new BehaviorGuideInput();
        BehaviorGuideDto expectedGuideDto = new BehaviorGuideDto();
        when(behaviorGuideMapper.convertToDTO(input)).thenReturn(expectedGuideDto);
        when(behaviorGuideService.updateBehaviorGuide(expectedGuideDto)).thenReturn(expectedGuideDto);


        BehaviorGuideDto result = behaviorGuideController.updateBehaviorGuide(input);


        assertEquals(expectedGuideDto, result);
        verify(behaviorGuideMapper).convertToDTO(input);
        verify(behaviorGuideService).updateBehaviorGuide(expectedGuideDto);
    }

    @Test
    void testDeleteBehaviorGuide() {

        Long id = 1L;
        when(behaviorGuideService.deleteBehaviorGuide(id)).thenReturn(true);


        Boolean result = behaviorGuideController.deleteBehaviorGuide(id);

        assertTrue(result);
        verify(behaviorGuideService).deleteBehaviorGuide(id);
    }
}
