package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.ImageRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ImageServiceImplementationTest {

    @Mock
    private ImageRepository imageRepository;
    @Mock
    private GenericMapperFactory genericMapperFactory;
    @Mock
    private GenericMapper<Image, ImageDto> imageMapper;
    @Mock
    private DogBreedService dogBreedService;
    @Mock
    private CatBreedService catBreedService;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;

    @InjectMocks
    private ImageServiceImplementation imageServiceImplementation;

    private Image image;
    private ImageDto imageDto;
    private DogBreed dogBreed;
    private CatBreed catBreed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        imageServiceImplementation = new ImageServiceImplementation(
                imageRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService
        );
        ArrayList<Image> images = new ArrayList<>();

        dogBreed = new DogBreed();
        dogBreed.setId(1L);
        dogBreed.setName("Golden Retriever");
        dogBreed.setOrigin("Scotland");
        dogBreed.setSize(3);
        dogBreed.setCoat("Long");
        dogBreed.setColor("Golden");
        dogBreed.setLifeSpan("10-12 years");
        dogBreed.setTemperament("Intelligent, Friendly, Devoted");
        dogBreed.setDescription("Popular house dog");

        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);
        catBreed.setCoat("Short");
        catBreed.setColor("Cream with Dark Points");
        catBreed.setLifeSpan("15-20 years");
        catBreed.setTemperament("Social, Affectionate, Vocal");
        catBreed.setDescription("Known for their striking appearance and vocal nature");


        image = new Image();
        image.setId(1L);
        image.setDescription("Image of a Golden Retriever");
        image.setUrl("https://www.google.com");

        imageDto = new ImageDto();
        imageDto.setId(1L);
        imageDto.setDescription("Image of a Golden Retriever");
        imageDto.setUrl("https://www.google.com");

        catBreed.setImages(images);
        dogBreed.setImages(images);
        image.setCatBreed(catBreed);
        image.setDogBreed(dogBreed);

        when(genericMapperFactory.createMapper(Image.class, ImageDto.class)).thenReturn(imageMapper);
        when(genericMapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        when(genericMapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        imageServiceImplementation = new ImageServiceImplementation(
                imageRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService);
    }

    @Test
    void getImageById_Success() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));
        when(imageMapper.convertToDTO(any(Image.class))).thenReturn(imageDto);

        ImageDto result = imageServiceImplementation.getImageByid(1L);

        assertNotNull(result);
        assertEquals(imageDto, result);
        verify(imageRepository, times(1)).findById(anyLong());
    }

    @Test
    void getImageById_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> imageServiceImplementation.getImageByid(null));
    }

    @Test
    void getImageById_NotFound() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> imageServiceImplementation.getImageByid(1L));
    }

    @Test
    void getAllImages_ValidPage() {
        Page<Image> page = new PageImpl<>(List.of(image));
        when(imageRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(imageMapper.convertToDTO(any(Image.class))).thenReturn(imageDto);

        Map<String, Object> result = imageServiceImplementation.getAllImages(0, 10);

        assertTrue(result.containsKey("images"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));
        verify(imageRepository, times(1)).findAll(any(PageRequest.class));
    }
    @Test
    void getAllImages_InvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> imageServiceImplementation.getAllImages(-1, 0));
    }
    @Test
    void getAllImages_EmptyList() {
        Page<Image> emptyPage = new PageImpl<>(List.of());
        when(imageRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);

        Map<String, Object> result = imageServiceImplementation.getAllImages(0, 10);

        assertTrue(((List<?>)result.get("images")).isEmpty());
        assertEquals(1, result.get("totalPages"));
        assertEquals(0L, result.get("totalElements"));
    }

    @Test
    void createImage_Success() {
        when(imageMapper.convertToEntity(imageDto)).thenReturn(image);
        when(imageRepository.save(image)).thenReturn(image);
        when(imageMapper.convertToDTO(image)).thenReturn(imageDto);

        ImageDto result = imageServiceImplementation.createImage(imageDto);

        assertNotNull(result);
        assertEquals(imageDto, result);
        verify(imageRepository, times(1)).save(any(Image.class));
    }

    @Test
    void createImage_BlankDescription() {
        imageDto.setDescription("");
        assertThrows(BlankInputException.class, () -> imageServiceImplementation.createImage(imageDto));
    }

    @Test
    void createImage_BlankUrl() {
        imageDto.setUrl("");
        assertThrows(BlankInputException.class, () -> imageServiceImplementation.createImage(imageDto));
    }

    @Test
    void deleteImage() {
        when(imageRepository.existsById(anyLong())).thenReturn(true);
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));

        assertTrue(imageServiceImplementation.deleteImage(1L));
    }

    @Test
    void deleteImage_NotFound() {
        when(imageRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundElementException.class, () -> imageServiceImplementation.deleteImage(1234L));
    }

    @Test
    void deleteImage_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> imageServiceImplementation.deleteImage(null));
    }

    @Test
    void deleteImage_ValidId() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));
        doNothing().when(imageRepository).deleteById(anyLong());

        Boolean result = imageServiceImplementation.deleteImage(1L);

        assertTrue(result);
        verify(imageRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteImage_IdNotFound() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> imageServiceImplementation.deleteImage(450L));
    }

    @Test
    void updateImage_Success() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));
        when(imageMapper.convertToEntity(imageDto)).thenReturn(image);
        when(imageRepository.save(image)).thenReturn(image);
        when(imageMapper.convertToDTO(image)).thenReturn(imageDto);

        ImageDto result = imageServiceImplementation.updateImage(imageDto);

        assertNotNull(result);
        assertEquals(imageDto, result);
        verify(imageRepository, times(1)).save(image);
    }

    @Test
    void updateImage_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> imageServiceImplementation.updateImage(null));
    }

    @Test
    void updateImage_InvalidInput() {
        imageDto.setId(0L);
        assertThrows(InvalidInputException.class, () -> imageServiceImplementation.updateImage(imageDto));
        imageDto.setDescription("");
        imageDto.setId(1L);
        assertThrows(BlankInputException.class, () -> imageServiceImplementation.updateImage(imageDto));
    }

    @Test
    void updateImage_NotFound() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> imageServiceImplementation.updateImage(imageDto));
    }

    @Test
    void updateImage_UpdatesLastUpdate() {
        Image oldCenter = new Image();
        oldCenter.setId(1L);
        oldCenter.setLastUpdate(LocalDate.now().minusDays(1));

        when(imageRepository.findById(1L)).thenReturn(Optional.of(oldCenter));
        when(imageMapper.convertToEntity(any())).thenReturn(new Image());
        when(imageRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(imageMapper.convertToDTO(any())).thenReturn(new ImageDto());

        ImageDto newCenter = new ImageDto();
        newCenter.setId(1L);
        newCenter.setDescription("New Description");
        newCenter.setUrl("New URL");

        imageServiceImplementation.updateImage(newCenter);

        verify(imageRepository).save(argThat(center ->
                center.getLastUpdate().equals(LocalDate.now())
        ));
    }

    @Test
    void addDogBreedInImage() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(imageRepository.save(any(Image.class))).thenReturn(image);
        when(imageMapper.convertToDTO(any(Image.class))).thenReturn(imageDto);

        ImageDto result = imageServiceImplementation.addDogBreedToImage(1L, 1L);

        assertEquals(imageDto, result);
        verify(imageRepository, times(1)).save(any(Image.class));
    }

    @Test
    void addCatBreedInImage() {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(imageRepository.save(any(Image.class))).thenReturn(image);
        when(imageMapper.convertToDTO(any(Image.class))).thenReturn(imageDto);

        ImageDto result = imageServiceImplementation.addCatBreedToImage(1L, 1L);

        assertEquals(imageDto, result);
        verify(imageRepository, times(1)).save(any(Image.class));
    }


    @Test
    void addDogBreedInImage_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> imageServiceImplementation.addDogBreedToImage(null, null));
    }

    @Test
    void addCatBreedInImage_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> imageServiceImplementation.addCatBreedToImage(null, null));
    }
//////////////////////////////////////////
@Test
void getImagesByCatBreed_Success() {
    when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
    when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);

    Page<Image> imagePage = new PageImpl<>(List.of(image), PageRequest.of(0, 10), 1);
    when(imageRepository.findImagesByCatBreed(any(CatBreed.class), any(PageRequest.class))).thenReturn(imagePage);

    when(imageMapper.convertToDTO(any(Image.class))).thenReturn(imageDto);

    Map<String, Object> result = imageServiceImplementation.getImagesByCatBreed(1L, 0, 10);

    assertNotNull(result);
    assertTrue(result.containsKey("images"));
    assertTrue(result.containsKey("totalPages"));
    assertTrue(result.containsKey("totalElements"));
    assertEquals(1L, result.get("totalElements"));
    assertEquals(1, result.get("totalPages"));
    assertEquals(List.of(imageDto), result.get("images"));

    verify(catBreedService, times(1)).getBreedById(anyLong());
    verify(imageRepository, times(1)).findImagesByCatBreed(any(CatBreed.class), any(PageRequest.class));
}

    @Test
    void getImagesByDogBreed_Success() {
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);

        Page<Image> imagePage = new PageImpl<>(List.of(image), PageRequest.of(0, 10), 1);
        when(imageRepository.findImagesByDogBreed(any(DogBreed.class), any(PageRequest.class))).thenReturn(imagePage);

        when(imageMapper.convertToDTO(any(Image.class))).thenReturn(imageDto);

        Map<String, Object> result = imageServiceImplementation.getImagesByDogBreed(1L, 0, 10);

        assertNotNull(result);
        assertTrue(result.containsKey("images"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));
        assertEquals(1L, result.get("totalElements"));
        assertEquals(1, result.get("totalPages"));
        assertEquals(List.of(imageDto), result.get("images"));

        verify(catBreedService, times(1)).getBreedById(anyLong());
        verify(imageRepository, times(1)).findImagesByDogBreed(any(DogBreed.class), any(PageRequest.class));
    }
}