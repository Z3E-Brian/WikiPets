package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Mapper.MapperConfig;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FeedingScheduleServiceImplementation implements FeedingScheduleService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final GenericMapper<FeedingSchedule, FeedingScheduleDto> feedingScheduleMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final CatBreedService catBreedService;
    private final DogBreedService dogBreedService;

    @Autowired
    public FeedingScheduleServiceImplementation(FeedingScheduleRepository feedingScheduleRepository, GenericMapperFactory mapperFactory, DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
        this.feedingScheduleMapper = mapperFactory.createMapper(FeedingSchedule.class, FeedingScheduleDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
    }

    @Override
    public Map<String, Object> getFeedingSchedules(int page, int size) {
        Page<FeedingSchedule> feedingSchedules = feedingScheduleRepository.findAll(PageRequest.of(page, size));
        feedingSchedules.forEach(feedingSchedule -> {
            feedingSchedule.setCatBreeds(feedingSchedule.getCatBreeds().stream().limit(10).collect(Collectors.toList()));
            feedingSchedule.setDogBreeds(feedingSchedule.getDogBreeds().stream().limit(10).collect(Collectors.toList()));
        });
        return Map.of("feedingSchedules", feedingSchedules.map(this::convertToDto).getContent(), "totalPages", feedingSchedules.getTotalPages(), "totalElements", feedingSchedules.getTotalElements());

    }

    @Override
    public FeedingScheduleDto getFeedingScheduleById(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id).orElseThrow();
        return feedingScheduleMapper.convertToDTO(feedingSchedule);
    }


    @Override
    public FeedingScheduleDto createFeedingSchedule(@NotNull FeedingScheduleDto feedingScheduleDto) {
        feedingScheduleDto.setLastUpdate(LocalDate.now());
        feedingScheduleDto.setCreateDate(LocalDate.now());
        FeedingSchedule feedingSchedule = feedingScheduleMapper.convertToEntity(feedingScheduleDto);
        FeedingSchedule savedFeedingSchedule = feedingScheduleRepository.save(feedingSchedule);
        return feedingScheduleMapper.convertToDTO(savedFeedingSchedule);
    }

    @Override
    public void deleteFeedingSchedule(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found with id: " + id));
        feedingScheduleRepository.delete(feedingSchedule);
    }

    @Override
    public FeedingScheduleDto updateFeedingSchedule(@NotNull FeedingScheduleDto feedingScheduleDto) {
        FeedingSchedule oldFeedingSchedule = feedingScheduleRepository.findById(feedingScheduleDto.getId())
                .orElseThrow(() -> new CustomException("Feeding Schedule with id " + feedingScheduleDto.getId() + " not found"));

        FeedingSchedule newFeedingSchedule = feedingScheduleMapper.convertToEntity(feedingScheduleDto);
        newFeedingSchedule.setCreateDate(oldFeedingSchedule.getCreateDate());
        newFeedingSchedule.setLastUpdate(LocalDate.now());
        newFeedingSchedule.setDogBreeds(oldFeedingSchedule.getDogBreeds());
        newFeedingSchedule.setCatBreeds(oldFeedingSchedule.getCatBreeds());
        return feedingScheduleMapper.convertToDTO(feedingScheduleRepository.save(newFeedingSchedule));
    }


    @Override
    public FeedingScheduleDto addDogBreedInFeedingSchedule(Long feedingScheduleId, Long idDogBreed) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(feedingScheduleId)
                .orElseThrow(() -> new CustomException("FeedingSchedule not found with id: " + feedingScheduleId));

        DogBreed dogBreed = dogBreedService.getBreedEntityById(idDogBreed);

        if (!feedingSchedule.getDogBreeds().contains(dogBreed)) {
            feedingSchedule.getDogBreeds().add(dogBreed);
            dogBreed.setFeedingSchedule(feedingSchedule);
            feedingSchedule.setLastUpdate(LocalDate.now());
        }
        return feedingScheduleMapper.convertToDTO(feedingScheduleRepository.save(feedingSchedule));
    }

    @Override
    public FeedingScheduleDto deleteDogBreedInFeedingSchedule(Long feedingScheduleId, Long idDogBreed) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(feedingScheduleId)
                .orElseThrow(() -> new CustomException("FeedingSchedule not found with id: " + feedingScheduleId));

        DogBreed dogBreed = dogBreedService.getBreedEntityById(idDogBreed);

        if (feedingSchedule.getDogBreeds().contains(dogBreed)) {
            feedingSchedule.getDogBreeds().remove(dogBreed);
            dogBreed.setFeedingSchedule(null);
            feedingSchedule.setLastUpdate(LocalDate.now());
        }
        return feedingScheduleMapper.convertToDTO(feedingScheduleRepository.save(feedingSchedule));
    }

    @Override
    public FeedingScheduleDto deleteCatBreedInFeedingSchedule(Long id, Long idCatBreed) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Feeding Schedule not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        feedingSchedule.getCatBreeds().remove(catBreed);
        feedingSchedule.setLastUpdate(LocalDate.now());
        return feedingScheduleMapper.convertToDTO(feedingScheduleRepository.save(feedingSchedule));
    }


    @Override
    public FeedingScheduleDto addCatBreedInInFeedingSchedule(Long id, Long idCatBreed) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feeding Schedule not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!feedingSchedule.getCatBreeds().contains(catBreed)) {
            feedingSchedule.getCatBreeds().add(catBreed);
        }
        return feedingScheduleMapper.convertToDTO(feedingScheduleRepository.save(feedingSchedule));
    }

    private FeedingScheduleDto convertToDto(FeedingSchedule feedingSchedule) {
        return feedingScheduleMapper.convertToDTO(feedingSchedule);
    }

}