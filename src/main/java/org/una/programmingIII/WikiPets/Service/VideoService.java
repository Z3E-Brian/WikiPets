package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Model.Video;

import java.util.List;
import java.util.Map;

public interface VideoService {

    VideoDto getVideoByid(Long id);

    VideoDto createVideo(VideoDto videoDto);

    VideoDto updateVideo(VideoDto videoDto);

    VideoDto addDogBreedToVideo(Long id, Long idDogBreed);

    VideoDto addCatBreedToVideo(Long id, Long idDogBreed);

    boolean deleteVideo(Long id);

    Map<String, Object> getAllVideos(int page, int size);

    Map<String, Object> getVideosByDogBreed(Long id, int page, int size);

    Map<String, Object> getVideosByCatBreed(Long id, int page, int size);

    /*VideoDto removeDogBreedFromVideo(Long id, Long idDogBreed);

    VideoDto removeCatBreedFromVideo(Long id, Long idCatBreed);*/

}
