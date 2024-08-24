package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Model.Video;

import java.util.List;

public interface VideoService {
    List<VideoDto> getAllVideos();

    VideoDto getVideoByid(Long id);

    VideoDto createVideo(VideoDto videoDto);

    VideoDto updateVideo(VideoDto videoDto);

    void deleteVideo(Long id);
}
