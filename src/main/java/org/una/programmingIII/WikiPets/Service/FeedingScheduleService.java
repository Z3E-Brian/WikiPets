package org.una.programmingIII.WikiPets.Service;


import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import java.util.List;
public interface FeedingScheduleService {
    List<FeedingScheduleDto> getAllFeedingSchedules();

    FeedingScheduleDto getFeedingScheduleById(Long id);

    FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto);

    void deleteFeedingSchedule(Long id);

    FeedingScheduleDto updateFeedingSchedule(FeedingScheduleDto feedingScheduleDto);
}