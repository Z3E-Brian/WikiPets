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
public class FeedingScheduleServiceImplementation implements FeedingScheduleService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final FeedingScheduleMapper feedingScheduleMapper;

    @Autowired
    public FeedingScheduleServiceImplementation(FeedingScheduleRepository feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.feedingScheduleMapper = FeedingScheduleMapper.INSTANCE;
    }

    @Override
    public List<FeedingScheduleDto> getAllFeedingSchedules() {
        List<FeedingSchedule> feedingSchedules = feedingScheduleRepository.findAll();
        return feedingSchedules.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public FeedingScheduleDto getFeedingScheduleById(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found with id: " + id));
        return convertToDto(feedingSchedule);
    }

    @Override
    public FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto) {
        FeedingSchedule feedingSchedule = convertToEntity(feedingScheduleDto);
        FeedingSchedule savedFeedingSchedule = feedingScheduleRepository.save(feedingSchedule);
        return convertToDto(savedFeedingSchedule);
    }

    @Override
    public void deleteFeedingSchedule(Long id) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FeedingSchedule not found with id: " + id));
        feedingScheduleRepository.delete(feedingSchedule);
    }

    @Override
    public FeedingScheduleDto updateFeedingSchedule(FeedingScheduleDto feedingScheduleDto) {
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
