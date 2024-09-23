//package org.una.programmingIII.WikiPets.Service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
//import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
//import org.una.programmingIII.WikiPets.Model.CatBreed;
//import org.una.programmingIII.WikiPets.Model.DogBreed;
//import org.una.programmingIII.WikiPets.Repository.AdoptionCenterRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class AdoptionCenterServiceImplementationTest {
//
//    @Mock
//    private AdoptionCenterRepository adoptionCenterRepository;
//
//    @Mock
//    private GenericMapperFactory genericMapperFactory;
//
//    @Mock
//    private GenericMapper<AdoptionCenter, AdoptionCenterDto> adoptionCenterMapper;
//
//
//    @InjectMocks
//    private AdoptionCenterServiceImplementation adoptionCenterServiceImplementation;
//
//    private AdoptionCenter adoptionCenter;
//    private AdoptionCenterDto adoptionCenterDto;
//    private DogBreedService dogBreedService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        adoptionCenter = new AdoptionCenter();
//        adoptionCenter.setId(1L);
//        adoptionCenter.setName("Pet Local");
//        adoptionCenter.setLocation("60.5335, -107.2027");
//
//        List<CatBreed> catBreeds = new ArrayList<>();
//        catBreeds.add(new CatBreed());
//        adoptionCenter.setAvailableCatBreeds(catBreeds);
//
//        List<DogBreed> dogBreeds = new ArrayList<>();
//        dogBreeds.add(new DogBreed()    );
//        adoptionCenter.setAvailableDogBreeds(dogBreeds);
//
//        adoptionCenterDto = new AdoptionCenterDto();
//        adoptionCenterDto.setId(1L);
//        adoptionCenterDto.setName("Pet Local");
//        adoptionCenterDto.setLocation("60.5335, -107.2027");
//
//        when(genericMapperFactory.createMapper(AdoptionCenter.class, AdoptionCenterDto.class)).thenReturn(adoptionCenterMapper);
//        when(adoptionCenterMapper.convertToDTO(adoptionCenter)).thenReturn(adoptionCenterDto);
//        when(adoptionCenterMapper.convertToEntity(adoptionCenterDto)).thenReturn(adoptionCenter);
//
//        //adoptionCenterServiceImplementation = new AdoptionCenterServiceImplementation(adoptionCenterRepository,genericMapperFactory,dogBreedService);
//    }
//
//    @Test
//    public void createAdoptionCenterTest() {
//        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
//        AdoptionCenterDto result = adoptionCenterServiceImplementation.createAdoptionCenter(adoptionCenterDto);
//        assertEquals(adoptionCenterDto.getId(), result.getId());
//        assertEquals(adoptionCenterDto.getName(), result.getName());
//        assertEquals(adoptionCenterDto.getLocation(), result.getLocation());
//    }
//
//    @Test
//    public void updateAdoptionCenterTest() {
//        when(adoptionCenterRepository.save(any(AdoptionCenter.class))).thenReturn(adoptionCenter);
//        AdoptionCenterDto result = adoptionCenterServiceImplementation.updateAdoptionCenter(adoptionCenterDto);
//        assertEquals(adoptionCenterDto.getId(), result.getId());
//        assertEquals(adoptionCenterDto.getName(), result.getName());
//        assertEquals(adoptionCenterDto.getLocation(), result.getLocation());
//    }
//
//    @Test
//    public void deleteAdoptionCenterTest() {
//        doNothing().when(adoptionCenterRepository).deleteById(1L);
//        adoptionCenterServiceImplementation.deleteAdoptionCenter(1L);
//        verify(adoptionCenterRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void getAdoptionCenterByIdTest() {
//        when(adoptionCenterRepository.findById(1L)).thenReturn(Optional.of(adoptionCenter));
//        AdoptionCenterDto result = adoptionCenterServiceImplementation.getAdoptionCenterById(1L);
//        assertEquals(adoptionCenterDto.getId(), result.getId());
//        assertEquals(adoptionCenterDto.getName(), result.getName());
//        assertEquals(adoptionCenterDto.getLocation(), result.getLocation());
//    }
//
//    /*@Test
//    public void getAllAdoptionCenterTest() {
//        when(adoptionCenterRepository.findAll()).thenReturn(List.of(adoptionCenter));
//        List<AdoptionCenterDto> result = adoptionCenterServiceImplementation.getAllAdoptionCenters();
//        assertEquals(1, result.size());
//        assertEquals(adoptionCenterDto.getId(), result.get(0).getId());
//        assertEquals(adoptionCenterDto.getLocation(), result.get(0).getLocation());
//        assertTrue(result.get(0).getName().contains("Pet Local"));
//
//
//    }*/
//}
