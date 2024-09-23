//package org.una.programmingIII.WikiPets.Service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
//import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
//import org.una.programmingIII.WikiPets.Model.CatBreed;
//import org.una.programmingIII.WikiPets.Model.DogBreed;
//import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;
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
//public class BehaviorGuideServiceImplementationTest {
//
//    @Mock
//    private BehaviorGuideRepository behaviorGuideRepository;
//
//    @Mock
//    private GenericMapperFactory genericMapperFactory;
//
//    @Mock
//    private GenericMapper<BehaviorGuide, BehaviorGuideDto> behaviorGuideMapper;
//
//
//    @InjectMocks
//    private BehaviorGuideServiceImplementation behaviorGuideServiceImplementation;
//
//    private BehaviorGuide behaviorGuide;
//    private BehaviorGuideDto behaviorGuideDto;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        behaviorGuide = new BehaviorGuide();
//        behaviorGuide.setId(1L);
//        behaviorGuide.setTitle("Pet Behavior");
//        behaviorGuide.setContent("A basic guide about the behavior of your pet.");
//
//        List<CatBreed> catBreeds = new ArrayList<>();
//        catBreeds.add(new CatBreed());
//        behaviorGuide.setSuitableCatBreeds(catBreeds);
//
//        List<DogBreed> dogBreeds = new ArrayList<>();
//        dogBreeds.add(new DogBreed()    );
//        behaviorGuide.setSuitableDogBreeds(dogBreeds);
//
//        behaviorGuideDto = new BehaviorGuideDto();
//        behaviorGuideDto.setId(1L);
//        behaviorGuideDto.setTitle("Pet Behavior");
//        behaviorGuideDto.setContent("A basic guide about the behavior of your pet.");
//
//        when(genericMapperFactory.createMapper(BehaviorGuide.class, BehaviorGuideDto.class)).thenReturn(behaviorGuideMapper);
//        when(behaviorGuideMapper.convertToDTO(behaviorGuide)).thenReturn(behaviorGuideDto);
//        when(behaviorGuideMapper.convertToEntity(behaviorGuideDto)).thenReturn(behaviorGuide);
//
////        behaviorGuideServiceImplementation = new BehaviorGuideServiceImplementation(behaviorGuideRepository,genericMapperFactory);
//    }
//
//    @Test
//    public void createBehaviorGuideTest() {
//        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
//        BehaviorGuideDto result = behaviorGuideServiceImplementation.createBehaviorGuide(behaviorGuideDto);
//        assertEquals(behaviorGuideDto.getId(), result.getId());
//        assertEquals(behaviorGuideDto.getTitle(), result.getTitle());
//    }
//
//    @Test
//    public void updateBehaviorGuideTest() {
//        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
//        BehaviorGuideDto result = behaviorGuideServiceImplementation.updateBehaviorGuide(behaviorGuideDto);
//        assertEquals(behaviorGuideDto.getId(), result.getId());
//        assertEquals(behaviorGuideDto.getTitle(), result.getTitle());
//    }
//
//    @Test
//    public void deleteBehaviorGuideTest() {
//        doNothing().when(behaviorGuideRepository).deleteById(1L);
//        behaviorGuideServiceImplementation.deleteBehaviorGuide(1L);
//        verify(behaviorGuideRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void getBehaviorGuideByIdTest() {
//        when(behaviorGuideRepository.findById(1L)).thenReturn(Optional.of(behaviorGuide));
//        BehaviorGuideDto result = behaviorGuideServiceImplementation.getBehaviorGuideById(1L);
//        assertEquals(behaviorGuideDto.getId(), result.getId());
//        assertEquals(behaviorGuideDto.getTitle(), result.getTitle());
//    }
//
///*    @Test
//    public void getAllBehaviorGuideTest() {
//        when(behaviorGuideRepository.findAll()).thenReturn(List.of(behaviorGuide));
//        List<BehaviorGuideDto> result = behaviorGuideServiceImplementation.getAllBehaviorGuides();
//        assertEquals(1, result.size());
//        assertEquals(behaviorGuideDto.getId(), result.get(0).getId());
//        assertTrue(result.get(0).getTitle().contains("Pet Behavior"));
//
//    }*/
//}
