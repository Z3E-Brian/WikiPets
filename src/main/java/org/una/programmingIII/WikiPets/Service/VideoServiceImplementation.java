package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Model.Video;
import org.una.programmingIII.WikiPets.Repository.VideoRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class VideoServiceImplementation implements VideoService {

    private final VideoRepository videoRepository;
    private final GenericMapper<Video, VideoDto> videoMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public VideoServiceImplementation(VideoRepository videoRepository, GenericMapperFactory mapperFactory, DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.videoRepository = videoRepository;
        this.videoMapper = mapperFactory.createMapper(Video.class, VideoDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }
    private VideoDto convertToDto(Video video) {return videoMapper.convertToDTO(video);
    }

    @Override
    public Map<String, Object> getAllVideos(int page, int size) {
        Page<Video> videos = videoRepository.findAll(PageRequest.of(page, size));
        Map<String, Object> response = new HashMap<>();
        response.put("videos", videos.map(this::convertToDto).getContent());
        response.put("totalPages", videos.getTotalPages());
        response.put("totalElements", videos.getTotalElements());
        return response;
    }

    @Override
    public VideoDto getVideoByid(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found with id: " + id));
        return videoMapper.convertToDTO(video);
    }

    @Override
    public Map<String, Object> getVideosByDogBreed(Long id, int page, int size) {
        DogBreed dogBreed = dogBreedService.getBreedEntityById(id);
        Page<Video> videos = videoRepository.findVideosByDogBreed(dogBreed, PageRequest.of(page, size));
        Map<String, Object> response = new HashMap<>();
        response.put("videos", videos.map(this::convertToDto).getContent());
        response.put("totalPages", videos.getTotalPages());
        response.put("totalElements", videos.getTotalElements());
        return response;
    }

    @Override
    public Map<String, Object> getVideosByCatBreed(Long id, int page, int size) {
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(id));
        Page<Video> videos = videoRepository.findVideosByCatBreed(catBreed, PageRequest.of(page, size));
        Map<String, Object> response = new HashMap<>();
        response.put("videos", videos.map(this::convertToDto).getContent());
        response.put("totalPages", videos.getTotalPages());
        response.put("totalElements", videos.getTotalElements());
        return response;
    }

    @Override
    public VideoDto createVideo(VideoDto videoDto) {
        videoDto.setCreateDate(LocalDate.now());
        videoDto.setLastUpdate(LocalDate.now());
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
    public VideoDto addDogBreedToVideo(Long id, Long idDogBreed) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Video not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        video.setDogBreed(dogBreed);
        video.setCatBreed(null);
        return videoMapper.convertToDTO(videoRepository.save(video));
    }
    @Override
    public VideoDto addCatBreedToVideo(Long id, Long idCatBreed) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Video not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        video.setCatBreed(catBreed);
        video.setDogBreed(null);
        return videoMapper.convertToDTO(videoRepository.save(video));
    }

    @Override
    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }

    /*@Override
    public VideoDto removeDogBreedFromVideo(Long id, Long idDogBreed) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Video not found"));
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (video.getDogBreed() != null && video.getDogBreed().equals(dogBreed)) {
            video.setDogBreed(null);
        } else {
            throw new CustomException("Dog breed not found in video");
        }
        return videoMapper.convertToDTO(videoRepository.save(video));
    }

    @Override
    public VideoDto removeCatBreedFromVideo(Long id, Long idCatBreed) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Video not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (video.getCatBreed() != null && video.getCatBreed().equals(catBreed)) {
            video.setCatBreed(null);
        } else {
            throw new CustomException("Cat breed not found in video");
        }
        return videoMapper.convertToDTO(videoRepository.save(video));
    }*/
}