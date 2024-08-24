package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Model.*;

@Mapper
public interface FeedingScheduleMapper {
    FeedingScheduleMapper INSTANCE = Mappers.getMapper(FeedingScheduleMapper.class);
    FeedingScheduleDto toFeedingScheduleDto(FeedingSchedule feedingSchedule);
    FeedingSchedule toFeedingSchedule(FeedingScheduleDto feedingScheduleDto);
}

