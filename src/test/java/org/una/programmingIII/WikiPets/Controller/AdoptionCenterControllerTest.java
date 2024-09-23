package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.AdoptionCenterInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Service.AdoptionCenterService;

import java.util.HashMap;
import java.util.Map;

public class AdoptionCenterControllerTest {

    @InjectMocks
    private AdoptionCenterController adoptionCenterController;

    @Mock
    private AdoptionCenterService adoptionCenterService;

    @Mock
    private GenericMapper<AdoptionCenterInput, AdoptionCenterDto> adoptionCenterMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAdoptionCenters() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedCenters = new HashMap<>();
        when(adoptionCenterService.getAllAdoptionCenters(page, size)).thenReturn(expectedCenters);

        Map<String, Object> result = adoptionCenterController.getAdoptionCenters(page, size);

        assertEquals(expectedCenters, result);
        verify(adoptionCenterService).getAllAdoptionCenters(page, size);
    }

    @Test
    void testGetAdoptionCentersException() {
        int page = 1;
        int size = 10;
        when(adoptionCenterService.getAllAdoptionCenters(page, size)).thenThrow(new NotFoundElementException(""));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            adoptionCenterController.getAdoptionCenters(page, size);
        });
        assertEquals("Could not retrieve adoption centers", exception.getMessage());
    }

    @Test
    void testGetAdoptionCenterById() {
        Long id = 1L;
        AdoptionCenterDto expectedCenter = new AdoptionCenterDto();
        when(adoptionCenterService.getAdoptionCenterById(id)).thenReturn(expectedCenter);

        AdoptionCenterDto result = adoptionCenterController.getAdoptionCenterById(id);

        assertEquals(expectedCenter, result);
        verify(adoptionCenterService).getAdoptionCenterById(id);
    }

    @Test
    void testCreateAdoptionCenter() {
        AdoptionCenterInput input = new AdoptionCenterInput();
        AdoptionCenterDto expectedCenterDto = new AdoptionCenterDto();
        when(adoptionCenterMapper.convertToDTO(input)).thenReturn(expectedCenterDto);
        when(adoptionCenterService.createAdoptionCenter(expectedCenterDto)).thenReturn(expectedCenterDto);

        AdoptionCenterDto result = adoptionCenterController.createAdoptionCenter(input);

        assertEquals(expectedCenterDto, result);
        verify(adoptionCenterMapper).convertToDTO(input);
        verify(adoptionCenterService).createAdoptionCenter(expectedCenterDto);
    }

    @Test
    void testUpdateAdoptionCenter() {
        AdoptionCenterInput input = new AdoptionCenterInput();
        AdoptionCenterDto expectedCenterDto = new AdoptionCenterDto();
        when(adoptionCenterMapper.convertToDTO(input)).thenReturn(expectedCenterDto);
        when(adoptionCenterService.updateAdoptionCenter(expectedCenterDto)).thenReturn(expectedCenterDto);

        AdoptionCenterDto result = adoptionCenterController.updateAdoptionCenter(input);

        assertEquals(expectedCenterDto, result);
        verify(adoptionCenterMapper).convertToDTO(input);
        verify(adoptionCenterService).updateAdoptionCenter(expectedCenterDto);
    }

    @Test
    void testDeleteAdoptionCenter() {
        Long id = 1L;
        when(adoptionCenterService.deleteAdoptionCenter(id)).thenReturn(true);

        Boolean result = adoptionCenterController.deleteAdoptionCenter(id);

        assertTrue(result);
        verify(adoptionCenterService).deleteAdoptionCenter(id);
    }
}
