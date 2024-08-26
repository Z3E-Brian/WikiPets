package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Image;
import org.una.programmingIII.WikiPets.Dto.ImageDto;
import org.una.programmingIII.WikiPets.Repository.ImageRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplementationTest {

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private GenericMapper<Image, ImageDto> imageMapper;

    @Mock
    private GenericMapperFactory mapperFactory;

    @InjectMocks
    private ImageServiceImplementation imageServiceImplementation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(mapperFactory.createMapper(Image.class, ImageDto.class)).thenReturn(imageMapper);
        imageServiceImplementation = new ImageServiceImplementation(imageRepository, mapperFactory);
    }

    @Test
    void getAllImagesTest() {
        when(imageMapper.convertToDTO(any())).thenReturn(new ImageDto());
        ImageDto imageDto = new ImageDto();
        Image image = new Image();
        when(imageRepository.findAll()).thenReturn(Collections.singletonList(image));

        List<ImageDto> result = imageServiceImplementation.getAllImages();

        assertEquals(1, result.size());
        assertEquals(imageDto, result.get(0));
    }

    @Test
    void getImageByIdTest() {
        when(imageMapper.convertToDTO(any())).thenReturn(new ImageDto());
        Image image = new Image();
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        ImageDto result = imageServiceImplementation.getImageByid(1L);

        assertNotNull(result);
    }

    @Test
    void getImageById_NotFoundTest() {
        when(imageRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> imageServiceImplementation.getImageByid(1L));

        assertEquals("Video Not Found with id: 1", exception.getMessage());
    }

    @Test
    void createImageTest() {
        when(imageMapper.convertToDTO(any())).thenReturn(new ImageDto());
        when(imageMapper.convertToEntity(any())).thenReturn(new Image());
        Image image = new Image();
        ImageDto imageDto = new ImageDto();
        when(imageRepository.save(image)).thenReturn(image);

        ImageDto result = imageServiceImplementation.createImage(imageDto);

        assertNotNull(result);
    }

    @Test
    void updateImageTest() {
        when(imageMapper.convertToDTO(any())).thenReturn(new ImageDto());
        when(imageMapper.convertToEntity(any())).thenReturn(new Image());
        Image image = new Image();
        when(imageRepository.save(image)).thenReturn(image);

        ImageDto result = imageServiceImplementation.updateImage(new ImageDto());

        assertNotNull(result);
    }

    @Test
    void deleteImageTest() {
        doNothing().when(imageRepository).deleteById(1L);

        imageServiceImplementation.deleteImage(1L);

        verify(imageRepository, times(1)).deleteById(1L);
    }
}
