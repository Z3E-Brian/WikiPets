package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.VideoInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Service.VideoService;

import java.util.HashMap;
import java.util.Map;

public class VideoControllerTest {

    @InjectMocks
    private VideoController videoController;

    @Mock
    private VideoService videoService;

    @Mock
    private GenericMapper<VideoInput, VideoDto> videoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetVideos() {
        // Arrange
        int page = 1;
        int size = 10;
        Map<String, Object> expectedVideos = new HashMap<>();
        when(videoService.getAllVideos(page, size)).thenReturn(expectedVideos);

        // Act
        Map<String, Object> result = videoController.getVideos(page, size);

        // Assert
        assertEquals(expectedVideos, result);
        verify(videoService).getAllVideos(page, size);
    }

    @Test
    void testGetVideosException() {
        // Arrange
        int page = 1;
        int size = 10;
        when(videoService.getAllVideos(page, size)).thenThrow(new RuntimeException("Error"));

        // Act & Assert
        Exception exception = assertThrows(CustomException.class, () -> {
            videoController.getVideos(page, size);
        });
        assertEquals("Could not find behavior guidesError", exception.getMessage());
    }

    @Test
    void testGetVideoById() {
        // Arrange
        Long id = 1L;
        VideoDto expectedVideo = new VideoDto();
        when(videoService.getVideoByid(id)).thenReturn(expectedVideo);

        // Act
        VideoDto result = videoController.getVideoById(id);

        // Assert
        assertEquals(expectedVideo, result);
        verify(videoService).getVideoByid(id);
    }

    @Test
    void testGetVideoByIdException() {
        // Arrange
        Long id = 1L;
        when(videoService.getVideoByid(id)).thenThrow(new RuntimeException("Error"));

        // Act & Assert
        Exception exception = assertThrows(CustomException.class, () -> {
            videoController.getVideoById(id);
        });
        assertEquals("Could not find video with id 1. Error", exception.getMessage());
    }

    @Test
    void testCreateVideo() {
        VideoInput input = new VideoInput();
        VideoDto expectedVideoDto = new VideoDto();
        when(videoMapper.convertToDTO(input)).thenReturn(expectedVideoDto);
        when(videoService.createVideo(expectedVideoDto)).thenReturn(expectedVideoDto);

        VideoDto result = videoController.createVideo(input);


        assertEquals(expectedVideoDto, result);
        verify(videoMapper).convertToDTO(input);
        verify(videoService).createVideo(expectedVideoDto);
    }

    @Test
    void testCreateVideoException() {
        VideoInput input = new VideoInput();
        when(videoMapper.convertToDTO(input)).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            videoController.createVideo(input);
        });
        assertEquals("Could not create video: Error", exception.getMessage());
    }

    @Test
    void testUpdateVideo() {
        VideoInput input = new VideoInput();
        VideoDto expectedVideoDto = new VideoDto();
        when(videoMapper.convertToDTO(input)).thenReturn(expectedVideoDto);
        when(videoService.updateVideo(expectedVideoDto)).thenReturn(expectedVideoDto);

        VideoDto result = videoController.updateVideo(input);

        assertEquals(expectedVideoDto, result);
        verify(videoMapper).convertToDTO(input);
        verify(videoService).updateVideo(expectedVideoDto);
    }

    @Test
    void testUpdateVideoException() {
        VideoInput input = new VideoInput();
        when(videoMapper.convertToDTO(input)).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(CustomException.class, () -> {
            videoController.updateVideo(input);
        });
        assertEquals("Could not update video: Error", exception.getMessage());
    }

    @Test
    void testDeleteVideo() {
        Long id = 1L;
        doNothing().when(videoService).deleteVideo(id);

        boolean result = videoController.deleteVideo(id);

        assertTrue(result);
        verify(videoService).deleteVideo(id);
    }

    @Test
    void testDeleteVideoException() {

        Long id = 1L;
        doThrow(new RuntimeException("Error")).when(videoService).deleteVideo(id);

        Exception exception = assertThrows(CustomException.class, () -> {
            videoController.deleteVideo(id);
        });
        assertEquals("Could not delete video with id 1. Error", exception.getMessage());
    }
}
