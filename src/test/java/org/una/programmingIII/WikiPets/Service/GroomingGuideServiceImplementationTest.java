package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.GroomingGuide;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Repository.GroomingGuideRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GroomingGuideServiceImplementationTest {

    @Mock
    private GroomingGuideRepository groomingGuideRepository;

    @Mock
    private GenericMapperFactory genericMapperFactory;

    @Mock
    private GenericMapper<GroomingGuide, GroomingGuideDto> groomingGuideMapper;


    @InjectMocks
    private GroomingGuideServiceImplementation groomingGuideServiceImplementation;

    private GroomingGuide groomingGuide;
    private GroomingGuideDto groomingGuideDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        groomingGuide = new GroomingGuide();
        groomingGuide.setId(1L);
        groomingGuide.setSteps("Grooming Guide Steps");
        groomingGuide.setContent("A basic guide about the grooming of your pet.");

        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreed());
        groomingGuide.setSuitableCatBreeds(catBreeds);

        List<DogBreed> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreed()    );
        groomingGuide.setSuitableDogBreeds(dogBreeds);

        groomingGuideDto = new GroomingGuideDto();
        groomingGuideDto.setId(1L);
        groomingGuideDto.setSteps("Grooming Guide Steps");
        groomingGuideDto.setContent("A basic guide for training your pet.");

        when(genericMapperFactory.createMapper(GroomingGuide.class, GroomingGuideDto.class)).thenReturn(groomingGuideMapper);
        when(groomingGuideMapper.convertToDTO(groomingGuide)).thenReturn(groomingGuideDto);
        when(groomingGuideMapper.convertToEntity(groomingGuideDto)).thenReturn(groomingGuide);

     //   groomingGuideServiceImplementation = new GroomingGuideServiceImplementation(groomingGuideRepository,genericMapperFactory);
    }

    @Test
    public void createGroomingGuideTest() {
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        GroomingGuideDto result = groomingGuideServiceImplementation.createGroomingGuide(groomingGuideDto);
        assertEquals(groomingGuideDto.getId(), result.getId());
        assertEquals(groomingGuideDto.getSteps(), result.getSteps());
    }

    @Test
    public void updateGroomingGuideTest() {
        when(groomingGuideRepository.save(any(GroomingGuide.class))).thenReturn(groomingGuide);
        GroomingGuideDto result = groomingGuideServiceImplementation.updateGroomingGuide(groomingGuideDto);
        assertEquals(groomingGuideDto.getId(), result.getId());
        assertEquals(groomingGuideDto.getSteps(), result.getSteps());
    }

    @Test
    public void deleteGroomingGuideTest() {
        doNothing().when(groomingGuideRepository).deleteById(1L);
        groomingGuideServiceImplementation.deleteGroomingGuide(1L);
        verify(groomingGuideRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getGroomingGuideByIdTest() {
        when(groomingGuideRepository.findById(1L)).thenReturn(Optional.of(groomingGuide));
        GroomingGuideDto result = groomingGuideServiceImplementation.getGroomingGuideById(1L);
        assertEquals(groomingGuideDto.getId(), result.getId());
        assertEquals(groomingGuideDto.getSteps(), result.getSteps());
    }

/*    @Test
    public void getAllGroomingGuideTest() {
        when(groomingGuideRepository.findAll()).thenReturn(List.of(groomingGuide));
        List<GroomingGuideDto> result = groomingGuideServiceImplementation.getAllGroomingGuides();
        assertEquals(1, result.size());
        assertEquals(groomingGuideDto.getId(), result.get(0).getId());
        assertTrue(result.get(0).getSteps().contains("Grooming Guide Steps"));

    }*/
}
