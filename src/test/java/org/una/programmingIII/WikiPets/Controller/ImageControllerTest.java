package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.ImageDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.ImageInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Image;
import org.una.programmingIII.WikiPets.Service.ImageService;

import java.util.HashMap;
import java.util.Map;

public class ImageControllerTest {

    @InjectMocks
    private ImageController imageController;

    @Mock
    private ImageService imageService;

    @Mock
    private GenericMapper<ImageInput, ImageDto> imageMapper;

    @Mock
    private GenericMapperFactory mapperFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(ImageInput.class, ImageDto.class)).thenReturn(imageMapper);
        imageController = new ImageController(imageService, mapperFactory);
    }

    @Test
    void testImageControllerConstructor() {
        assertNotNull(imageController);
    }

    @Test
    void testGetImages() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedImages = new HashMap<>();
        when(imageService.getAllImages(page, size)).thenReturn(expectedImages);
        Map<String, Object> result = imageController.getImages(page, size);

        assertEquals(expectedImages, result);
        verify(imageService).getAllImages(page, size);
    }

    @Test
    void testGetImagesException() {

        int page = 1;
        int size = 10;
        when(imageService.getAllImages(page, size)).thenThrow(new RuntimeException("Error"));


        Exception exception = assertThrows(CustomException.class, () -> {
            imageController.getImages(page, size);
        });
        assertEquals("Could not find behavior guidesError", exception.getMessage());
    }

    @Test
    void testGetImagesByDogBreed() {

        Long id = 1L;
        int page = 1;
        int size = 10;
        Map<String, Object> expectedImages = new HashMap<>();
        when(imageService.getImagesByDogBreed(id, page, size)).thenReturn(expectedImages);


        Map<String, Object> result = imageController.getImagesByDogBreed(id, page, size);


        assertEquals(expectedImages, result);
        verify(imageService).getImagesByDogBreed(id, page, size);
    }

    @Test
    void testGetImagesByDogBreedException() {

        Long id = 1L;
        int page = 1;
        int size = 10;
        when(imageService.getImagesByDogBreed(id, page, size)).thenThrow(new RuntimeException("Error"));


        Exception exception = assertThrows(CustomException.class, () -> {
            imageController.getImagesByDogBreed(id, page, size);
        });
        assertEquals("Could not find image with id 1. Error", exception.getMessage());
    }

    @Test
    void testGetImageById() {

        Long id = 1L;
        ImageDto expectedImage = new ImageDto();
        when(imageService.getImageByid(id)).thenReturn(expectedImage);

        ImageDto result = imageController.getImageById(id);

        assertEquals(expectedImage, result);
        verify(imageService).getImageByid(id);
    }

    @Test
    void testGetImageByIdException() {
        Long id = 1L;
        when(imageService.getImageByid(id)).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            imageController.getImageById(id);
        });
        assertEquals("Could not find image with id 1. Error", exception.getMessage());
    }

    @Test
    void testCreateImage() {
        ImageInput input = new ImageInput();
        ImageDto expectedImageDto = new ImageDto();
        when(imageMapper.convertToDTO(input)).thenReturn(expectedImageDto);
        when(imageService.createImage(expectedImageDto)).thenReturn(expectedImageDto);

        ImageDto result = imageController.createImage(input);
        assertEquals(expectedImageDto, result);
        verify(imageMapper).convertToDTO(input);
        verify(imageService).createImage(expectedImageDto);
    }

    @Test
    void testCreateImageException() {
        ImageInput input = new ImageInput();
        when(imageMapper.convertToDTO(input)).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            imageController.createImage(input);
        });
        assertEquals("Could not create image: Error", exception.getMessage());
    }

    @Test
    void testUpdateImage() {
        ImageInput input = new ImageInput();
        ImageDto expectedImageDto = new ImageDto();
        when(imageMapper.convertToDTO(input)).thenReturn(expectedImageDto);
        when(imageService.updateImage(expectedImageDto)).thenReturn(expectedImageDto);

        ImageDto result = imageController.updateImage(input);

        assertEquals(expectedImageDto, result);
        verify(imageMapper).convertToDTO(input);
        verify(imageService).updateImage(expectedImageDto);
    }

    @Test
    void testUpdateImageException() {
        ImageInput input = new ImageInput();
        when(imageMapper.convertToDTO(input)).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            imageController.updateImage(input);
        });
        assertEquals("Could not update image: Error", exception.getMessage());
    }

    @Test
    void testDeleteImage() {
        Long id = 1L;
        doNothing().when(imageService).deleteImage(id);

        boolean result = imageController.deleteImage(id);
        assertTrue(result);
        verify(imageService).deleteImage(id);
    }

    @Test
    void testDeleteImageException() {
        Long id = 1L;
        doThrow(new RuntimeException("Error")).when(imageService).deleteImage(id);

        Exception exception = assertThrows(CustomException.class, () -> {
            imageController.deleteImage(id);
        });
        assertEquals("Could not delete image with id 1. Error", exception.getMessage());
    }
}
