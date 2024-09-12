package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.una.programmingIII.WikiPets.Service.VideoServiceImplementation;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class VideoController {
    private final VideoService videoService;
    private final GenericMapper<VideoInput, VideoDto> dogBreedMapper;

    @Autowired
    VideoController(GenericMapperFactory mapperFactory, VideoServiceImplementation videoServiceImplementation) {
        this.dogBreedMapper = mapperFactory.createMapper(VideoInput.class, VideoDto.class);
        this.videoService = videoServiceImplementation;
    }

    @QueryMapping
    public Map<String, Object> getVideos(@Argument int page, @Argument int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<VideoDto> videoPage = videoService.getAllVideos(pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("videos", videoPage.getContent());
            response.put("totalPages", videoPage.getTotalPages());
            response.put("totalElements", videoPage.getTotalElements());
            return response;
        } catch (Exception e) {
            throw new CustomException("Could not find video" + e.getMessage());
        }
    }
    @QueryMapping
    public VideoDto getVideoById(@Argument Long id) {
        try {
            return videoService.getVideoByid(id);
        } catch (Exception e) {
            throw new CustomException("Could not find video " +id+". "+ e.getMessage());
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
    public VideoDto updateVideo(@Argument VideoInput input) {
        try {
            VideoDto videoDto = dogBreedMapper.convertToDTO(input);
            return videoService.updateVideo(videoDto);
        } catch (Exception e) {
            throw new CustomException("Could not video "+ e.getMessage());
        }
    }
    @MutationMapping
    public VideoDto createVideo(@Argument VideoInput input) {
        try {
            VideoDto videoDto = dogBreedMapper.convertToDTO(input);
            return videoService.createVideo(videoDto);
        } catch (Exception e) {
            throw new CustomException("Could not create video" + e.getMessage());
        }
    }

    @MutationMapping
    public boolean deleteVideo(@Argument Long id) {
        try {
            videoService.deleteVideo(id);
            return true;
        } catch (Exception e) {
            throw new CustomException("Could not delete video "+id+". " + e.getMessage());
        }
    }
}
