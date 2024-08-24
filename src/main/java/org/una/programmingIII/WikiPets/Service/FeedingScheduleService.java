package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedingScheduleService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final GenericMapper<FeedingSchedule, FeedingScheduleDto> feedingScheduleMapper;

    @Autowired
    public FeedingScheduleService(FeedingScheduleRepository feedingScheduleRepository, GenericMapperFactory mapperFactory) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.feedingScheduleMapper = mapperFactory.createMapper(FeedingSchedule.class, FeedingScheduleDto.class);
    }

    public List<FeedingScheduleDto> getAllFeedingSchedules() {
        List<FeedingSchedule> feedingSchedules = feedingScheduleRepository.findAll();
        return feedingSchedules.stream().map(this.feedingScheduleMapper::convertToDTO).collect(Collectors.toList());
    }

    public FeedingScheduleDto getFeedingScheduleById(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found with id: " + id));
        return feedingScheduleMapper.convertToDTO(feedingSchedule);
    }

    public FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto) {
        FeedingSchedule feedingSchedule = feedingScheduleMapper.convertToEntity(feedingScheduleDto);
        FeedingSchedule savedFeedingSchedule = feedingScheduleRepository.save(feedingSchedule);
        return  feedingScheduleMapper.convertToDTO(savedFeedingSchedule);
    }

    public void deleteFeedingSchedule(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found with id: " + id));
        feedingScheduleRepository.delete(feedingSchedule);
    }

    public FeedingScheduleDto updateFeedingSchedule1(FeedingScheduleDto feedingScheduleDto) {
        FeedingSchedule feedingSchedule =  feedingScheduleMapper.convertToEntity(feedingScheduleDto);
        FeedingSchedule updatedFeedingSchedule = feedingScheduleRepository.save(feedingSchedule);
        return feedingScheduleMapper.convertToDTO(updatedFeedingSchedule);
    }
}
