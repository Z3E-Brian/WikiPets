package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Model.Video;

import java.util.List;

public interface VideoService {

    VideoDto getVideoByid(Long id);

    VideoDto createVideo(VideoDto videoDto);

    VideoDto updateVideo(VideoDto videoDto);

    VideoDto addDogBreedToVideo(Long id, Long idDogBreed);

    VideoDto addCatBreedToVideo(Long id, Long idDogBreed);

    void deleteVideo(Long id);

    Page<VideoDto> getAllVideos(Pageable pageable);

}
