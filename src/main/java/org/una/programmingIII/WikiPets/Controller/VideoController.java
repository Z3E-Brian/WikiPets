package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.VideoInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.VideoService;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class VideoController {
    private final VideoService videoService;
    private final GenericMapper<VideoInput, VideoDto> videoMapper;

    @Autowired
    public VideoController(VideoService videoService, GenericMapperFactory mapperFactory) {
        this.videoService = videoService;
        this.videoMapper = mapperFactory.createMapper(VideoInput.class, VideoDto.class);
    }

    @QueryMapping
    public Map<String, Object> getVideos(@Argument int page, @Argument int size) {
        try {
            return videoService.getAllVideos(page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find behavior guides" + e.getMessage());
        }
    }
    //doing
    @QueryMapping
    public Map<String, Object> getVideosByDogBreed(@Argument Long id, @Argument int page, @Argument int size) {
        try {
            return videoService.getVideosByDogBreed(id, page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find video with id " + id + ". " + e.getMessage(), e);
        }
    }

    @QueryMapping
    public Map<String, Object> getVideosByCatBreed(@Argument Long id, @Argument int page, @Argument int size) {
        try {
            return videoService.getVideosByCatBreed(id, page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find video with id " + id + ". " + e.getMessage(), e);
        }
    }

    @QueryMapping
    public VideoDto getVideoById(@Argument Long id) {
        try {
            return videoService.getVideoByid(id);
        } catch (Exception e) {
            throw new CustomException("Could not find video with id " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public VideoDto addDogBreedToVideo(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return videoService.addDogBreedToVideo(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update video with id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public VideoDto addCatBreedToVideo(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return videoService.addCatBreedToVideo(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update video with id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public VideoDto createVideo(@Argument VideoInput input) {
        try {
            VideoDto videoDto = convertToDto(input);
            return videoService.createVideo(videoDto);
        } catch (Exception e) {
            throw new CustomException("Could not create video: " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public VideoDto updateVideo(@Argument VideoInput input) {
        try {
            VideoDto videoDto = convertToDto(input);
            return videoService.updateVideo(videoDto);
        } catch (Exception e) {
            throw new CustomException("Could not update video: " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public boolean deleteVideo(@Argument Long id) {
        try {
            return videoService.deleteVideo(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete video with id " + id + ". " + e.getMessage(), e);
        }
    }

    private VideoDto convertToDto(VideoInput videoInput) {
        return videoMapper.convertToDTO(videoInput);
    }
}