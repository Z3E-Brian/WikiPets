package org.una.programmingIII.WikiPets.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;

import java.util.List;

public interface FeedingScheduleService {
    Page<FeedingScheduleDto> getFeedingSchedules(Pageable pageable);

    FeedingScheduleDto getFeedingScheduleById(Long id);

    FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto);

    void deleteFeedingSchedule(Long id);

    FeedingScheduleDto updateFeedingSchedule(FeedingScheduleDto feedingScheduleDto);

    FeedingScheduleDto addDogBreedInFeedingSchedule(Long id, Long idDogBreed);

    FeedingScheduleDto deleteDogBreedInFeedingSchedule(Long id, Long idDogBreed);

}