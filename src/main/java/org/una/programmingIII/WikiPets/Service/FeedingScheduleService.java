package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.FeedingScheduleMapper;
import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.una.programmingIII.WikiPets.Model.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedingScheduleService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final FeedingScheduleMapper feedingScheduleMapper;

    @Autowired
    public FeedingScheduleService(FeedingScheduleRepository feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.feedingScheduleMapper = FeedingScheduleMapper.INSTANCE;
    }

    public List<FeedingScheduleDto> getAllFeedingSchedules() {
        List<FeedingSchedule> feedingSchedules = feedingScheduleRepository.findAll();
        return feedingSchedules.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public FeedingScheduleDto getFeedingScheduleById(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found with id: " + id));
        return convertToDto(feedingSchedule);
    }

    public FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto) {
        FeedingSchedule feedingSchedule = convertToEntity(feedingScheduleDto);
        FeedingSchedule savedFeedingSchedule = feedingScheduleRepository.save(feedingSchedule);
        return convertToDto(savedFeedingSchedule);
    }

    public void deleteFeedingSchedule(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found with id: " + id));
        feedingScheduleRepository.delete(feedingSchedule);
    }

    public FeedingScheduleDto updateFeedingSchedule1(FeedingScheduleDto feedingScheduleDto) {
        FeedingSchedule feedingSchedule = convertToEntity(feedingScheduleDto);
        FeedingSchedule updatedFeedingSchedule = feedingScheduleRepository.save(feedingSchedule);
        return convertToDto(updatedFeedingSchedule);
    }

    private FeedingScheduleDto convertToDto(FeedingSchedule feedingSchedule) {
        return feedingScheduleMapper.toFeedingScheduleDto(feedingSchedule);
    }

    private FeedingSchedule convertToEntity(FeedingScheduleDto feedingScheduleDto) {
        return feedingScheduleMapper.toFeedingSchedule(feedingScheduleDto);
    }
}
