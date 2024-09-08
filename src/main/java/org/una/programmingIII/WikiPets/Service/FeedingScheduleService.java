package org.una.programmingIII.WikiPets.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;

import java.util.List;

public interface FeedingScheduleService {
    List<FeedingScheduleDto> getAllFeedingSchedules();

    Page<FeedingScheduleDto> getAllFeedingSchedules(Pageable pageable);

    FeedingScheduleDto getFeedingScheduleById(Long id);

    FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto);

    void deleteFeedingSchedule(Long id);

    FeedingScheduleDto updateFeedingSchedule(FeedingScheduleDto feedingScheduleDto);
}