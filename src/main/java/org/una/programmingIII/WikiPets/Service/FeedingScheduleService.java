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
public interface FeedingScheduleService {
    List<FeedingScheduleDto> getAllFeedingSchedules();

    FeedingScheduleDto getFeedingScheduleById(Long id);

    FeedingScheduleDto createFeedingSchedule(FeedingScheduleDto feedingScheduleDto);

    void deleteFeedingSchedule(Long id);

    FeedingScheduleDto updateFeedingSchedule(FeedingScheduleDto feedingScheduleDto);
}