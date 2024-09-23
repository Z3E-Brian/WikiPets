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
//import org.una.programmingIII.WikiPets.Model.NutritionGuide;
//import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;
//import org.una.programmingIII.WikiPets.Repository.NutritionGuideRepository;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class NutritionGuideServiceImplementationTest {
//
//    @Mock
//    private NutritionGuideRepository nutritionGuideRepository;
//
//    @Mock
//    private GenericMapper<NutritionGuide, NutritionGuideDto> nutritionGuideMapper;
//
//    @Mock
//    private GenericMapperFactory mapperFactory;
//
//    @InjectMocks
//    private NutritionGuideServiceImplementation nutritionGuideServiceImplementation;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        when(mapperFactory.createMapper(NutritionGuide.class, NutritionGuideDto.class)).thenReturn(nutritionGuideMapper);
//       // nutritionGuideServiceImplementation = new NutritionGuideServiceImplementation(nutritionGuideRepository, mapperFactory);
//
//    }
//
//    @Test
//    void getNutritionGuideByIdTest() {
//        when(nutritionGuideMapper.convertToDTO(any())).thenReturn(new NutritionGuideDto());
//        NutritionGuide nutritionGuide = new NutritionGuide();
//        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
//        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.of(nutritionGuide));
//
//
//        NutritionGuideDto result = nutritionGuideServiceImplementation.getNutritionGuideById(1L);
//
//        assertEquals(nutritionGuideDto, result);
//    }
//
//    @Test
//    void getNutritionGuideById_NotFoundTest() {
//        when(nutritionGuideRepository.findById(1L)).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.getNutritionGuideById(1L));
//
//        assertEquals("Nutrition Guide not found", exception.getMessage());
//    }
//
//    @Test
//    void getNutritionGuideByTitleTest() {
//        when(nutritionGuideMapper.convertToDTO(any())).thenReturn(new NutritionGuideDto());
//        NutritionGuide nutritionGuide = new NutritionGuide();
//        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
//        when(nutritionGuideRepository.findByTitle("title")).thenReturn(nutritionGuide);
//
//        NutritionGuideDto result = nutritionGuideServiceImplementation.getNutritionGuideByTitle("title");
//
//        assertEquals(nutritionGuideDto, result);
//    }
//
//    @Test
//    void getNutritionGuideByTitle_NotFoundTest() {
//        when(nutritionGuideRepository.findByTitle("title")).thenReturn(null);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> nutritionGuideServiceImplementation.getNutritionGuideByTitle("title"));
//
//        assertEquals("Nutrition Guide not found", exception.getMessage());
//    }
//
//    @Test
//    void createNutritionGuideTest() {
//        when(nutritionGuideMapper.convertToDTO(any())).thenReturn(new NutritionGuideDto());
//        when(nutritionGuideMapper.convertToEntity(any())).thenReturn(new NutritionGuide());
//        NutritionGuide nutritionGuide = new NutritionGuide();
//        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
//        when(nutritionGuideRepository.save(nutritionGuide)).thenReturn(nutritionGuide);
//
//        NutritionGuideDto result = nutritionGuideServiceImplementation.createNutritionGuide(nutritionGuideDto);
//
//        assertEquals(nutritionGuideDto, result);
//    }
//
//    @Test
//    void deleteNutritionGuideTest() {
//        doNothing().when(nutritionGuideRepository).deleteById(1L);
//
//        nutritionGuideServiceImplementation.deleteNutritionGuide(1L);
//
//        verify(nutritionGuideRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    void updateNutritionGuideTest() {
//        when(nutritionGuideMapper.convertToDTO(any())).thenReturn(new NutritionGuideDto());
//        when(nutritionGuideMapper.convertToEntity(any())).thenReturn(new NutritionGuide());
//        NutritionGuide nutritionGuide = new NutritionGuide();
//        NutritionGuideDto nutritionGuideDto = new NutritionGuideDto();
//        when(nutritionGuideRepository.save(nutritionGuide)).thenReturn(nutritionGuide);
//
//
//        NutritionGuideDto result = nutritionGuideServiceImplementation.updateNutritionGuide(nutritionGuideDto);
//
//        assertEquals(nutritionGuideDto, result);
//    }
//}