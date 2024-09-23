//package org.una.programmingIII.WikiPets.Service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
//import org.una.programmingIII.WikiPets.Model.CareTip;
//import org.una.programmingIII.WikiPets.Dto.CareTipDto;
//import org.una.programmingIII.WikiPets.Repository.CareTipRepository;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CareTipServiceImplementationTest {
//
//    @Mock
//    private CareTipRepository careTipRepository;
//
//    @Mock
//    private GenericMapperFactory mapperFactory;
//
//    @Mock
//    private GenericMapper<CareTip, CareTipDto> careTipMapper;
//
//    @InjectMocks
//    private CareTipServiceImplementation careTipService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        when(mapperFactory.createMapper(CareTip.class, CareTipDto.class)).thenReturn(careTipMapper);
//       // careTipService = new CareTipServiceImplementation(careTipRepository, mapperFactory);
//    }
//
//
//    @Test
//    void getCareTipByIdTest() {
//        CareTip careTip = new CareTip();
//        CareTipDto careTipDto = new CareTipDto();
//
//        when(careTipRepository.findById(1L)).thenReturn(Optional.of(careTip));
//        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);
//
//        CareTipDto result = careTipService.getCareTipById(1L);
//
//        assertNotNull(result);
//        verify(careTipRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void getCareTipByIdNotFoundTest() {
//        when(careTipRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> careTipService.getCareTipById(1L));
//
//        assertEquals("Care Tip not found", exception.getMessage());
//        verify(careTipRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void getCareTipByTitleTest() {
//        CareTip careTip = new CareTip();
//        CareTipDto careTipDto = new CareTipDto();
//
//        when(careTipRepository.findByTitle("Test Title")).thenReturn(careTip);
//        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);
//
//        CareTipDto result = careTipService.getCareTipByTitle("Test Title");
//
//        assertNotNull(result);
//        verify(careTipRepository, times(1)).findByTitle("Test Title");
//    }
//
//    @Test
//    void getCareTipByTitleNotFoundTest() {
//        when(careTipRepository.findByTitle("Nonexistent Title")).thenReturn(null);
//
//        Exception exception = assertThrows(RuntimeException.class, () -> careTipService.getCareTipByTitle("Nonexistent Title"));
//
//        assertEquals("Care Tip not found", exception.getMessage());
//        verify(careTipRepository, times(1)).findByTitle("Nonexistent Title");
//    }
//
//    @Test
//    void createCareTipTest() {
//        CareTip careTip = new CareTip();
//        CareTipDto careTipDto = new CareTipDto();
//
//        when(careTipMapper.convertToEntity(careTipDto)).thenReturn(careTip);
//        when(careTipRepository.save(careTip)).thenReturn(careTip);
//        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);
//
//        CareTipDto result = careTipService.createCareTip(careTipDto);
//
//        assertNotNull(result);
//        verify(careTipRepository, times(1)).save(careTip);
//    }
//
//    @Test
//    void deleteCareTipTest() {
//        doNothing().when(careTipRepository).deleteById(1L);
//
//        careTipService.deleteCareTip(1L);
//
//        verify(careTipRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    void updateCareTipTest() {
//        CareTip careTip = new CareTip();
//        CareTipDto careTipDto = new CareTipDto();
//
//        when(careTipMapper.convertToEntity(careTipDto)).thenReturn(careTip);
//        when(careTipRepository.save(careTip)).thenReturn(careTip);
//        when(careTipMapper.convertToDTO(careTip)).thenReturn(careTipDto);
//
//        CareTipDto result = careTipService.updateCareTip(careTipDto);
//
//        assertNotNull(result);
//        verify(careTipRepository, times(1)).save(careTip);
//    }
//}