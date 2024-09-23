package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.CareTipDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.CareTipInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.CareTipService;

import java.util.HashMap;
import java.util.Map;

public class CareTipControllerTest {

    @InjectMocks
    private CareTipController careTipController;

    @Mock
    private CareTipService careTipService;

    @Mock
    private GenericMapper<CareTipInput, CareTipDto> careTipMapper;

    @Mock
    private GenericMapperFactory mapperFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(CareTipInput.class, CareTipDto.class)).thenReturn(careTipMapper);
        careTipController = new CareTipController(careTipService, mapperFactory);
    }


    @Test
    void testGetAllCareTips() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedCareTips = new HashMap<>();
        when(careTipService.getAllCareTips(page, size)).thenReturn(expectedCareTips);

        Map<String, Object> result = careTipController.getAllCareTips(page, size);

        assertEquals(expectedCareTips, result);
        verify(careTipService).getAllCareTips(page, size);
    }

    @Test
    void testGetAllCareTipsNotFoundException() {
        int page = 1;
        int size = 10;
        when(careTipService.getAllCareTips(page, size)).thenThrow(new NotFoundElementException("No care tips found"));

        Exception exception = assertThrows(NotFoundElementException.class, () -> {
            careTipController.getAllCareTips(page, size);
        });
        assertEquals("Could not retrieve care tipsNo care tips found", exception.getMessage());
    }

    @Test
    void testGetCareTipById() {
        Long id = 1L;
        CareTipDto expectedCareTip = new CareTipDto();
        when(careTipService.getCareTipById(id)).thenReturn(expectedCareTip);

        CareTipDto result = careTipController.getCareTipById(id);

        assertEquals(expectedCareTip, result);
        verify(careTipService).getCareTipById(id);
    }

    @Test
    void testGetCareTipByTitle() {
        String title = "Care Tip Title";
        CareTipDto expectedCareTip = new CareTipDto();
        when(careTipService.getCareTipByTitle(title)).thenReturn(expectedCareTip);

        CareTipDto result = careTipController.getCareTipByTitle(title);

        assertEquals(expectedCareTip, result);
        verify(careTipService).getCareTipByTitle(title);
    }

    @Test
    void testGetCareTipByTitleException() {
        String title = "Care Tip Title";
        when(careTipService.getCareTipByTitle(title)).thenThrow(new RuntimeException("Service error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            careTipController.getCareTipByTitle(title);
        });
        assertEquals("Could not find care tipService error", exception.getMessage());
    }

    @Test
    void testCreateCareTip() {
        CareTipInput input = new CareTipInput();
        CareTipDto expectedCareTipDto = new CareTipDto();
        when(careTipMapper.convertToDTO(input)).thenReturn(expectedCareTipDto);
        when(careTipService.createCareTip(expectedCareTipDto)).thenReturn(expectedCareTipDto);

        CareTipDto result = careTipController.createCareTip(input);

        assertEquals(expectedCareTipDto, result);
        verify(careTipMapper).convertToDTO(input);
        verify(careTipService).createCareTip(expectedCareTipDto);
    }

    @Test
    void testUpdateCareTip() {
        CareTipInput input = new CareTipInput();
        CareTipDto expectedCareTipDto = new CareTipDto();
        when(careTipMapper.convertToDTO(input)).thenReturn(expectedCareTipDto);
        when(careTipService.updateCareTip(expectedCareTipDto)).thenReturn(expectedCareTipDto);

        CareTipDto result = careTipController.updateCareTip(input);

        assertEquals(expectedCareTipDto, result);
        verify(careTipMapper).convertToDTO(input);
        verify(careTipService).updateCareTip(expectedCareTipDto);
    }

    @Test
    void testDeleteCareTip() {
        Long id = 1L;
        when(careTipService.deleteCareTip(id)).thenReturn(true);

        Boolean result = careTipController.deleteCareTip(id);

        assertTrue(result);
        verify(careTipService).deleteCareTip(id);
    }

    @Test
    void testAddDogBreedInCareTip() {
        Long careTipId = 1L;
        Long dogBreedId = 2L;
        CareTipDto expectedCareTipDto = new CareTipDto();
        when(careTipService.addDogBreedInCareTip(careTipId, dogBreedId)).thenReturn(expectedCareTipDto);

        CareTipDto result = careTipController.addDogBreedInCareTip(careTipId, dogBreedId);

        assertEquals(expectedCareTipDto, result);
        verify(careTipService).addDogBreedInCareTip(careTipId, dogBreedId);
    }

    @Test
    void testAddCatBreedInCareTip() {
        Long careTipId = 1L;
        Long catBreedId = 2L;
        CareTipDto expectedCareTipDto = new CareTipDto();
        when(careTipService.addCatBreedInCareTip(careTipId, catBreedId)).thenReturn(expectedCareTipDto);

        CareTipDto result = careTipController.addCatBreedInCareTip(careTipId, catBreedId);

        assertEquals(expectedCareTipDto, result);
        verify(careTipService).addCatBreedInCareTip(careTipId, catBreedId);
    }

    @Test
    void testGetCareTipByIdNotFoundException() {
        Long id = 1L;
        when(careTipService.getCareTipById(id)).thenThrow(new NotFoundElementException("Care tip not found"));

        Exception exception = assertThrows(NotFoundElementException.class, () -> {
            careTipController.getCareTipById(id);
        });
        assertEquals("Care tip not found", exception.getMessage());
    }

    @Test
    void testCreateCareTipException() {
        CareTipInput input = new CareTipInput();
        when(careTipMapper.convertToDTO(input)).thenThrow(new RuntimeException("Conversion error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            careTipController.createCareTip(input);
        });
        assertEquals("Could not create care tipConversion error", exception.getMessage());
    }

    @Test
    void testUpdateCareTipException() {
        CareTipInput input = new CareTipInput();
        CareTipDto expectedCareTipDto = new CareTipDto();
        when(careTipMapper.convertToDTO(input)).thenReturn(expectedCareTipDto);
        when(careTipService.updateCareTip(expectedCareTipDto)).thenThrow(new RuntimeException("Update error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            careTipController.updateCareTip(input);
        });
        assertEquals("Could not update care tipUpdate error", exception.getMessage());
    }

    @Test
    void testDeleteCareTipNotFound() {
        Long id = 1L;
        when(careTipService.deleteCareTip(id)).thenReturn(false);

        Boolean result = careTipController.deleteCareTip(id);

        assertFalse(result);
        verify(careTipService).deleteCareTip(id);
    }

    @Test
    void testAddDogBreedInCareTipException() {
        Long careTipId = 1L;
        Long dogBreedId = 2L;
        when(careTipService.addDogBreedInCareTip(careTipId, dogBreedId)).thenThrow(new RuntimeException("Service error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            careTipController.addDogBreedInCareTip(careTipId, dogBreedId);
        });
        assertEquals("Could not add dog breed in care tipService error", exception.getMessage());
    }

    @Test
    void testAddCatBreedInCareTipException() {
        Long careTipId = 1L;
        Long catBreedId = 2L;
        when(careTipService.addCatBreedInCareTip(careTipId, catBreedId)).thenThrow(new RuntimeException("Service error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            careTipController.addCatBreedInCareTip(careTipId, catBreedId);
        });
        assertEquals("Could not add cat breed in care tipService error", exception.getMessage());
    }

    @Test
    void testUserControllerConstructor() {
        assertNotNull(careTipController);
    }

}
