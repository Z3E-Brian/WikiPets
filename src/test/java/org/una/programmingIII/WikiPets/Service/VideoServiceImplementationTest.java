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
import org.una.programmingIII.WikiPets.Model.Video;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Repository.VideoRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VideoServiceImplementationTest {
/*
    @Mock
    private VideoRepository videoRepository;

    @Mock
    private GenericMapper<Video, VideoDto> videoMapper;

    @Mock
    private GenericMapperFactory mapperFactory;

    @InjectMocks
    private VideoServiceImplementation videoServiceImplementation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(mapperFactory.createMapper(Video.class, VideoDto.class)).thenReturn(videoMapper);
   //    videoServiceImplementation = new VideoServiceImplementation(videoRepository, mapperFactory);
    }

    @Test
    void getAllVideosTest() {
        when(videoMapper.convertToDTO(any())).thenReturn(new VideoDto());
        VideoDto videoDto = new VideoDto();
        Video video = new Video();
        when(videoRepository.findAll()).thenReturn(Collections.singletonList(video));

        List<VideoDto> result = videoServiceImplementation.getAllVideos();

        assertEquals(1, result.size());
        assertEquals(videoDto, result.get(0));
    }

    @Test
    void getVideoByIdTest() {
        when(videoMapper.convertToDTO(any())).thenReturn(new VideoDto());
        Video video = new Video();
        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));

        VideoDto result = videoServiceImplementation.getVideoByid(1L);

        assertNotNull(result);
    }

    @Test
    void getVideoById_NotFoundTest() {
        when(videoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> videoServiceImplementation.getVideoByid(1L));

        assertEquals("Video Not Found with id: 1", exception.getMessage());
    }

    @Test
    void createVideoTest() {
        when(videoMapper.convertToDTO(any())).thenReturn(new VideoDto());
        when(videoMapper.convertToEntity(any())).thenReturn(new Video());
        Video video = new Video();
        VideoDto videoDto = new VideoDto();
        when(videoRepository.save(video)).thenReturn(video);

        VideoDto result = videoServiceImplementation.createVideo(videoDto);

        assertNotNull(result);
    }

    @Test
    void updateVideoTest() {
        when(videoMapper.convertToDTO(any())).thenReturn(new VideoDto());
        when(videoMapper.convertToEntity(any())).thenReturn(new Video());
        Video video = new Video();
        when(videoRepository.save(video)).thenReturn(video);

        VideoDto result = videoServiceImplementation.updateVideo(new VideoDto());

        assertNotNull(result);
    }

    @Test
    void deleteVideoTest() {
        doNothing().when(videoRepository).deleteById(1L);

        videoServiceImplementation.deleteVideo(1L);

        verify(videoRepository, times(1)).deleteById(1L);
    }*/
}
