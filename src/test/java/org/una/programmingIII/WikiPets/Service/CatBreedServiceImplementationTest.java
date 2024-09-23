//package org.una.programmingIII.WikiPets.Service;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
//import org.una.programmingIII.WikiPets.Model.CatBreed;
//
//import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CatBreedServiceImplementationTest {
//
//    @Mock
//    private CatBreedRepository catBreedRepository;
//
//    @Mock
//    private GenericMapperFactory mapperFactory;
//    @Mock
//    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
//    @InjectMocks
//    private CatBreedServiceImplementation catBreedServiceImplementation;
//
//    private CatBreedDto catBreedDto;
//    private CatBreed catBreed;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        catBreedDto = new CatBreedDto();
//        catBreedDto.setId(1L);
//        catBreedDto.setName("Siamese");
//        catBreedDto.setOrigin("Thailand");
//        catBreedDto.setSize(2);
//        catBreedDto.setCoat("Short");
//        catBreedDto.setColor("Cream with points");
//        catBreedDto.setLifeSpan("12-16 years");
//        catBreedDto.setTemperament("Affectionate, Social, Vocal");
//        catBreedDto.setDescription("Popular breed known for its striking appearance and vocal nature.");
//        catBreedDto.setCreatedDate(LocalDate.ofEpochDay(2020-10-22));
//        catBreedDto.setModifiedDate(LocalDate.ofEpochDay(2020-10-22));
//        catBreed = new CatBreed();
//        catBreed.setId(1L);
//        catBreed.setName("Siamese");
//        catBreed.setOrigin("Thailand");
//        catBreed.setSize(2);
//        MockitoAnnotations.openMocks(this);
//
//        when(mapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);
//
//        catBreedServiceImplementation = new CatBreedServiceImplementation(catBreedRepository, mapperFactory);
//
//    }
//
//    @Test
//    public void createCatBreedTest() {
//        when(catBreedMapper.convertToEntity(Mockito.any(CatBreedDto.class))).thenReturn(catBreed);
//        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenReturn(catBreedDto);
//        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);
//
//        CatBreedDto result = catBreedServiceImplementation.createCatBreed(catBreedDto);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals("Siamese", result.getName());
//    }
//
//    @Test
//    public void updateCatBreedTest() {
//        when(catBreedMapper.convertToEntity(Mockito.any(CatBreedDto.class))).thenReturn(catBreed);
//        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenReturn(catBreedDto);
//        when(catBreedRepository.save(Mockito.any(CatBreed.class))).thenReturn(catBreed);
//
//        CatBreedDto result = catBreedServiceImplementation.updateCatBreed(catBreedDto);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals("Siamese", result.getName());
//    }
//
//    @Test
//    public void getBreedByIdTest() {
//        when(catBreedMapper.convertToDTO(Mockito.any(CatBreed.class))).thenReturn(catBreedDto);
//        when(catBreedRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(catBreed));
//
//        CatBreedDto result = catBreedServiceImplementation.getBreedById(1L);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//    }
//
//
//    @Test
//    public void deleteCatBreedTest() {
//        doNothing().when(catBreedRepository).deleteById(1L);
//        catBreedServiceImplementation.deleteCatBreed(1L);
//        verify(catBreedRepository, times(1)).deleteById(1L);
//    }
//}
//
