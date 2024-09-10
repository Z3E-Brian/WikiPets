package org.una.programmingIII.WikiPets.Service;

import org.hibernate.exception.spi.ViolatedConstraintNameExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Video;
import org.una.programmingIII.WikiPets.Repository.VideoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImplementation implements VideoService {

    private final VideoRepository videoRepository;
    private final GenericMapper<Video, VideoDto> videoMapper;

    @Autowired
    public VideoServiceImplementation(VideoRepository videoRepository, GenericMapperFactory mapperFactory) {
        this.videoRepository = videoRepository;
        this.videoMapper = mapperFactory.createMapper(Video.class, VideoDto.class);
    }

    @Override
    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll().stream()
                .map(videoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VideoDto getVideoByid(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found with id: " + id));
        return videoMapper.convertToDTO(video);
    }

    @Override
    public VideoDto createVideo(VideoDto videoDto) {
        Video video = videoMapper.convertToEntity(videoDto);
        Video savedVideo = videoRepository.save(video);
        return videoMapper.convertToDTO(savedVideo);
    }

    @Override
    public VideoDto updateVideo(VideoDto videoDto) {
        Video trainingGuide = videoMapper.convertToEntity(videoDto);
        Video updatedTrainingGuide = videoRepository.save(trainingGuide);
        return videoMapper.convertToDTO(updatedTrainingGuide);
    }

    @Override
    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }
}