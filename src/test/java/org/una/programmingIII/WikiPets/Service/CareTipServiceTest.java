package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Mapper.CareTipMapper;
import org.una.programmingIII.WikiPets.Model.CareTip;
import org.una.programmingIII.WikiPets.Model.CareTipDto;
import org.una.programmingIII.WikiPets.Repository.CareTipRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CareTipServiceTest {

    @Mock
    private CareTipRepository careTipRepository;

    @Mock
    private CareTipMapper careTipMapper;

    @InjectMocks
    private CareTipService careTipService;

    @Test
    void getAllCareTipsTest() {
        CareTip careTip = new CareTip();
        CareTipDto careTipDto = new CareTipDto();
        when(careTipRepository.findAll()).thenReturn(Collections.singletonList(careTip));

        List<CareTipDto> result = careTipService.getAllCareTips();

        assertEquals(1, result.size());
        assertEquals(careTipDto, result.get(0));
    }

    @Test
    void getCareTipByIdTest() {
        CareTip careTip = new CareTip();
        CareTipDto careTipDto = new CareTipDto();
        when(careTipRepository.findById(1L)).thenReturn(Optional.of(careTip));


        CareTipDto result = careTipService.getCareTipById(1L);

        assertEquals(careTipDto, result);
    }

    @Test
    void getCareTipById_NotFoundTest() {
        when(careTipRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> careTipService.getCareTipById(1L));

        assertEquals("Care Tip not found", exception.getMessage());
    }

    @Test
    void getCareTipByTitleTest() {
        CareTip careTip = new CareTip();
        CareTipDto careTipDto = new CareTipDto();
        when(careTipRepository.findByTitle("title")).thenReturn(careTip);

        CareTipDto result = careTipService.getCareTipByTitle("title");

        assertEquals(careTipDto, result);
    }

    @Test
    void getCareTipByTitle_NotFoundTest() {
        when(careTipRepository.findByTitle("title")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> careTipService.getCareTipByTitle("title"));

        assertEquals("Care Tip not found", exception.getMessage());
    }

    @Test
    void createCareTipTest() {
        CareTip careTip = new CareTip();
        CareTipDto careTipDto = new CareTipDto();
        when(careTipRepository.save(careTip)).thenReturn(careTip);

        CareTipDto result = careTipService.createCareTip(careTipDto);

        assertEquals(careTipDto, result);
    }

    @Test
    void deleteCareTipTest() {
        doNothing().when(careTipRepository).deleteById(1L);

        careTipService.deleteCareTip(1L);

        verify(careTipRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateCareTipTest() {
        CareTip careTip = new CareTip();
        CareTipDto careTipDto = new CareTipDto();
        when(careTipRepository.save(careTip)).thenReturn(careTip);

        CareTipDto result = careTipService.updateCareTip(careTipDto);

        assertEquals(careTipDto, result);
    }
}