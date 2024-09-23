package org.una.programmingIII.WikiPets.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;

import java.util.List;
import java.util.Map;

public interface FeedingScheduleService {

    Map<String, Object> getFeedingSchedules(int page, int size);

    FeedingScheduleDto getFeedingScheduleById(Long id);

    FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto);

    Boolean deleteFeedingSchedule(Long id);

    FeedingScheduleDto updateFeedingSchedule(FeedingScheduleDto feedingScheduleDto);

    FeedingScheduleDto addDogBreedInFeedingSchedule(Long id, Long idDogBreed);

    FeedingScheduleDto deleteDogBreedInFeedingSchedule(Long id, Long idDogBreed);

    FeedingScheduleDto deleteCatBreedInFeedingSchedule(Long id, Long idCatBreed);

    FeedingScheduleDto addCatBreedInInFeedingSchedule(Long id, Long idCatBreed);


}