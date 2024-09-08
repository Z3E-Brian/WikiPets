package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedingScheduleServiceImplementation implements FeedingScheduleService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final GenericMapper<FeedingSchedule, FeedingScheduleDto> feedingScheduleMapper;
    private final ReviewServiceImplementation reviewServiceImplementation;

    @Autowired
    public FeedingScheduleServiceImplementation(FeedingScheduleRepository feedingScheduleRepository, GenericMapperFactory mapperFactory, ReviewServiceImplementation reviewServiceImplementation) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.feedingScheduleMapper = mapperFactory.createMapper(FeedingSchedule.class, FeedingScheduleDto.class);
        this.reviewServiceImplementation = reviewServiceImplementation;
    }

    @Override
    public List<FeedingScheduleDto> getAllFeedingSchedules() {
        List<FeedingSchedule> feedingSchedules = feedingScheduleRepository.findAll();
        return feedingScheduleMapper.convertToDTOList(feedingSchedules);
    }

    @Override
    public Page<FeedingScheduleDto> getFeedingSchedules(Pageable pageable) {
        Page<FeedingSchedule> feedingSchedules = feedingScheduleRepository.findAll(pageable);
        return feedingSchedules.map(feedingScheduleMapper::convertToDTO);
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
        //revisar si es necesario hacer algo para los perros y gatos que estaban aca
        return feedingScheduleMapper.convertToDTO(feedingScheduleRepository.save(newFeedingSchedule));
    }
}
