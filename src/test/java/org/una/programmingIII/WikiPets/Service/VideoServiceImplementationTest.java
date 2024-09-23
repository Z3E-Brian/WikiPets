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
import org.una.programmingIII.WikiPets.Repository.VideoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VideoServiceImplementationTest {

    @Mock
    private VideoRepository videoRepository;
    @Mock
    private GenericMapperFactory genericMapperFactory;
    @Mock
    private GenericMapper<Video, VideoDto> videoMapper;
    @Mock
    private DogBreedService dogBreedService;
    @Mock
    private CatBreedService catBreedService;
    @Mock
    private GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    @Mock
    private GenericMapper<CatBreed, CatBreedDto> catBreedMapper;

    @InjectMocks
    private VideoServiceImplementation videoServiceImplementation;

    private Video video;
    private VideoDto videoDto;
    private DogBreed dogBreed;
    private CatBreed catBreed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        videoServiceImplementation = new VideoServiceImplementation(
                videoRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService
        );
        ArrayList<Video> videos = new ArrayList<>();

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


        video = new Video();
        video.setId(1L);
        video.setTitle("Video of a Golden Retriever");
        video.setUrl("https://www.google.com");

        videoDto = new VideoDto();
        videoDto.setId(1L);
        videoDto.setTitle("Video of a Golden Retriever");
        videoDto.setUrl("https://www.google.com");

        catBreed.setVideos(videos);
        dogBreed.setVideos(videos);
        video.setCatBreed(catBreed);
        video.setDogBreed(dogBreed);

        when(genericMapperFactory.createMapper(Video.class, VideoDto.class)).thenReturn(videoMapper);
        when(genericMapperFactory.createMapper(DogBreed.class, DogBreedDto.class)).thenReturn(dogBreedMapper);
        when(genericMapperFactory.createMapper(CatBreed.class, CatBreedDto.class)).thenReturn(catBreedMapper);

        videoServiceImplementation = new VideoServiceImplementation(
                videoRepository,
                genericMapperFactory,
                dogBreedService,
                catBreedService);
    }

    @Test
    void getVideoById_Success() {
        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));
        when(videoMapper.convertToDTO(any(Video.class))).thenReturn(videoDto);

        VideoDto result = videoServiceImplementation.getVideoByid(1L);

        assertNotNull(result);
        assertEquals(videoDto, result);
        verify(videoRepository, times(1)).findById(anyLong());
    }

    @Test
    void getVideoById_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> videoServiceImplementation.getVideoByid(null));
    }

    @Test
    void getVideoById_NotFound() {
        when(videoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> videoServiceImplementation.getVideoByid(1L));
    }

    @Test
    void getAllVideos_ValidPage() {
        Page<Video> page = new PageImpl<>(List.of(video));
        when(videoRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(videoMapper.convertToDTO(any(Video.class))).thenReturn(videoDto);

        Map<String, Object> result = videoServiceImplementation.getAllVideos(0, 10);

        assertTrue(result.containsKey("videos"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));
        verify(videoRepository, times(1)).findAll(any(PageRequest.class));
    }
    @Test
    void getAllVideos_InvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> videoServiceImplementation.getAllVideos(-1, 0));
    }
    @Test
    void getAllVideos_EmptyList() {
        Page<Video> emptyPage = new PageImpl<>(List.of());
        when(videoRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);

        Map<String, Object> result = videoServiceImplementation.getAllVideos(0, 10);

        assertTrue(((List<?>)result.get("videos")).isEmpty());
        assertEquals(1, result.get("totalPages"));
        assertEquals(0L, result.get("totalElements"));
    }

    @Test
    void createVideo_Success() {
        when(videoMapper.convertToEntity(videoDto)).thenReturn(video);
        when(videoRepository.save(video)).thenReturn(video);
        when(videoMapper.convertToDTO(video)).thenReturn(videoDto);

        VideoDto result = videoServiceImplementation.createVideo(videoDto);

        assertNotNull(result);
        assertEquals(videoDto, result);
        verify(videoRepository, times(1)).save(any(Video.class));
    }

    @Test
    void createVideo_BlankTitle() {
        videoDto.setTitle("");
        assertThrows(BlankInputException.class, () -> videoServiceImplementation.createVideo(videoDto));
    }

    @Test
    void createVideo_BlankUrl() {
        videoDto.setUrl("");
        assertThrows(BlankInputException.class, () -> videoServiceImplementation.createVideo(videoDto));
    }

    @Test
    void deleteVideo() {
        when(videoRepository.existsById(anyLong())).thenReturn(true);
        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));

        assertTrue(videoServiceImplementation.deleteVideo(1L));
    }

    @Test
    void deleteVideo_NotFound() {
        when(videoRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundElementException.class, () -> videoServiceImplementation.deleteVideo(1234L));
    }

    @Test
    void deleteVideo_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> videoServiceImplementation.deleteVideo(null));
    }

    @Test
    void deleteVideo_ValidId() {
        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));
        doNothing().when(videoRepository).deleteById(anyLong());

        Boolean result = videoServiceImplementation.deleteVideo(1L);

        assertTrue(result);
        verify(videoRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteVideo_IdNotFound() {
        when(videoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> videoServiceImplementation.deleteVideo(450L));
    }

    @Test
    void updateVideo_Success() {
        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));
        when(videoMapper.convertToEntity(videoDto)).thenReturn(video);
        when(videoRepository.save(video)).thenReturn(video);
        when(videoMapper.convertToDTO(video)).thenReturn(videoDto);

        VideoDto result = videoServiceImplementation.updateVideo(videoDto);

        assertNotNull(result);
        assertEquals(videoDto, result);
        verify(videoRepository, times(1)).save(video);
    }

    @Test
    void updateVideo_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> videoServiceImplementation.updateVideo(null));
    }

    @Test
    void updateVideo_InvalidInput() {
        videoDto.setId(0L);
        assertThrows(InvalidInputException.class, () -> videoServiceImplementation.updateVideo(videoDto));
        videoDto.setTitle("");
        videoDto.setId(1L);
        assertThrows(BlankInputException.class, () -> videoServiceImplementation.updateVideo(videoDto));
    }

    @Test
    void updateVideo_NotFound() {
        when(videoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundElementException.class, () -> videoServiceImplementation.updateVideo(videoDto));
    }

    @Test
    void updateVideo_UpdatesLastUpdate() {
        Video oldCenter = new Video();
        oldCenter.setId(1L);
        oldCenter.setLastUpdate(LocalDate.now().minusDays(1));

        when(videoRepository.findById(1L)).thenReturn(Optional.of(oldCenter));
        when(videoMapper.convertToEntity(any())).thenReturn(new Video());
        when(videoRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(videoMapper.convertToDTO(any())).thenReturn(new VideoDto());

        VideoDto newCenter = new VideoDto();
        newCenter.setId(1L);
        newCenter.setTitle("New Title");
        newCenter.setUrl("New URL");

        videoServiceImplementation.updateVideo(newCenter);

        verify(videoRepository).save(argThat(center ->
                center.getLastUpdate().equals(LocalDate.now())
        ));
    }

    @Test
    void addDogBreedInVideo() {
        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);
        when(videoRepository.save(any(Video.class))).thenReturn(video);
        when(videoMapper.convertToDTO(any(Video.class))).thenReturn(videoDto);

        VideoDto result = videoServiceImplementation.addDogBreedToVideo(1L, 1L);

        assertEquals(videoDto, result);
        verify(videoRepository, times(1)).save(any(Video.class));
    }

    @Test
    void addCatBreedInVideo() {
        when(videoRepository.findById(anyLong())).thenReturn(Optional.of(video));
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);
        when(videoRepository.save(any(Video.class))).thenReturn(video);
        when(videoMapper.convertToDTO(any(Video.class))).thenReturn(videoDto);

        VideoDto result = videoServiceImplementation.addCatBreedToVideo(1L, 1L);

        assertEquals(videoDto, result);
        verify(videoRepository, times(1)).save(any(Video.class));
    }


    @Test
    void addDogBreedInVideo_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> videoServiceImplementation.addDogBreedToVideo(null, null));
    }

    @Test
    void addCatBreedInVideo_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> videoServiceImplementation.addCatBreedToVideo(null, null));
    }
    //////////////////////////////////////////
    @Test
    void getVideosByCatBreed_Success() {
        when(catBreedService.getBreedById(anyLong())).thenReturn(new CatBreedDto());
        when(catBreedMapper.convertToEntity(any(CatBreedDto.class))).thenReturn(catBreed);

        Page<Video> videoPage = new PageImpl<>(List.of(video), PageRequest.of(0, 10), 1);
        when(videoRepository.findVideosByCatBreed(any(CatBreed.class), any(PageRequest.class))).thenReturn(videoPage);

        when(videoMapper.convertToDTO(any(Video.class))).thenReturn(videoDto);

        Map<String, Object> result = videoServiceImplementation.getVideosByCatBreed(1L, 0, 10);

        assertNotNull(result);
        assertTrue(result.containsKey("videos"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));
        assertEquals(1L, result.get("totalElements"));
        assertEquals(1, result.get("totalPages"));
        assertEquals(List.of(videoDto), result.get("videos"));

        verify(catBreedService, times(1)).getBreedById(anyLong());
        verify(videoRepository, times(1)).findVideosByCatBreed(any(CatBreed.class), any(PageRequest.class));
    }

    @Test
    void getVideosByDogBreed_Success() {
        when(dogBreedService.getBreedById(anyLong())).thenReturn(new DogBreedDto());
        when(dogBreedMapper.convertToEntity(any(DogBreedDto.class))).thenReturn(dogBreed);

        Page<Video> videoPage = new PageImpl<>(List.of(video), PageRequest.of(0, 10), 1);
        when(videoRepository.findVideosByDogBreed(any(DogBreed.class), any(PageRequest.class))).thenReturn(videoPage);

        when(videoMapper.convertToDTO(any(Video.class))).thenReturn(videoDto);

        Map<String, Object> result = videoServiceImplementation.getVideosByDogBreed(1L, 0, 10);

        assertNotNull(result);
        assertTrue(result.containsKey("videos"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));
        assertEquals(1L, result.get("totalElements"));
        assertEquals(1, result.get("totalPages"));
        assertEquals(List.of(videoDto), result.get("videos"));

        verify(catBreedService, times(1)).getBreedById(anyLong());
        verify(videoRepository, times(1)).findVideosByDogBreed(any(DogBreed.class), any(PageRequest.class));
    }
}