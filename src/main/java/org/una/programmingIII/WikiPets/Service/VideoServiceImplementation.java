package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Dto.VideoDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
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
    public VideoServiceImplementation(VideoRepository videoRepository,
                                      GenericMapperFactory mapperFactory,
                                      DogBreedService dogBreedService,
                                      CatBreedService catBreedService) {
        this.videoRepository = videoRepository;
        this.videoMapper = mapperFactory.createMapper(Video.class, VideoDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }

    @Override
    public Map<String, Object> getAllVideos(int page, int size) {
        Page<Video> videos = videoRepository.findAll(PageRequest.of(page, size));
        return Map.of(
                "videos", videos.map(this::convertToDto).getContent(),
                "totalPages", videos.getTotalPages(),
                "totalElements", videos.getTotalElements()
        );
    }

    @Override
    public Map<String, Object> getVideosByDogBreed(@NotNull Long id, int page, int size) {
        DogBreed dogBreed = dogBreedService.getBreedEntityById(id);
        Page<Video> videos = videoRepository.findVideosByDogBreed(dogBreed, PageRequest.of(page, size));
        return Map.of(
                "videos", videos.map(this::convertToDto).getContent(),
                "totalPages", videos.getTotalPages(),
                "totalElements", videos.getTotalElements()
        );
    }

    @Override
    public Map<String, Object> getVideosByCatBreed(@NotNull Long id, int page, int size) {
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(id));
        Page<Video> videos = videoRepository.findVideosByCatBreed(catBreed, PageRequest.of(page, size));
        return Map.of(
                "videos", videos.map(this::convertToDto).getContent(),
                "totalPages", videos.getTotalPages(),
                "totalElements", videos.getTotalElements()
        );
    }

    @Override
    public VideoDto getVideoByid(@NotNull Long id) {
        validateId(id);
        return videoRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundElementException("Video Not Found with id: " + id));
    }

    @Override
    public VideoDto createVideo(@NotNull VideoDto videoDto) {
        validateVideoDto(videoDto);
        videoDto.setCreateDate(LocalDate.now());
        videoDto.setLastUpdate(LocalDate.now());
        Video video = videoMapper.convertToEntity(videoDto);
        return videoMapper.convertToDTO(videoRepository.save(video));
    }

    @Override
    public boolean deleteVideo(@NotNull Long id) {
        validateId(id);
        findVideoById(id);
        videoRepository.deleteById(id);
        return true;
    }

    @Override
    public VideoDto updateVideo(@NotNull VideoDto videoDto) {
        validateId(videoDto.getId());
        validateVideoDto(videoDto);

        Video oldVideo = findVideoById(videoDto.getId());
        Video newVideo = videoMapper.convertToEntity(videoDto);
        newVideo.setCreateDate(oldVideo.getCreateDate());
        newVideo.setLastUpdate(LocalDate.now());
        return convertToDto(videoRepository.save(newVideo));
    }

    @Override
    public VideoDto addDogBreedToVideo(@NotNull Long id,@NotNull Long idDogBreed) {
        validateId(id);
        validateId(idDogBreed);
        Video video = findVideoById(id);
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        video.setDogBreed(dogBreed);
        video.setCatBreed(null);
        dogBreed.getVideos().add(video);
        return videoMapper.convertToDTO(videoRepository.save(video));
    }
    @Override
    public VideoDto addCatBreedToVideo(@NotNull Long id,@NotNull Long idCatBreed) {
        validateId(id);
        validateId(idCatBreed);
        Video video = findVideoById(id);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        video.setCatBreed(catBreed);
        video.setDogBreed(null);
        catBreed.getVideos().add(video);
        return videoMapper.convertToDTO(videoRepository.save(video));
    }

    private VideoDto convertToDto(Video video) {
        return videoMapper.convertToDTO(video);
    }

    private Video convertToEntity(VideoDto videoDto) {
        return videoMapper.convertToEntity(videoDto);
    }


    private void validateVideoDto(@NotNull VideoDto dto) {
        if (dto.getUrl() == null || dto.getUrl().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
    }

    private void validateId(Long id) {
        if (id <= 0) {
            throw new InvalidInputException("Invalid ID");
        }
    }

    private Video findVideoById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Video not found"));
    }

}