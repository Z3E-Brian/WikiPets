package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.FeedingScheduleInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.FeedingScheduleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class FeedingScheduleController {
    private final FeedingScheduleService feedingScheduleService;
    private final GenericMapper<FeedingScheduleInput, FeedingScheduleDto> feedingScheduleMapper;

    @Autowired
    FeedingScheduleController(FeedingScheduleService feedingScheduleService, GenericMapperFactory mapperFactory) {
        this.feedingScheduleService = feedingScheduleService;
        this.feedingScheduleMapper = mapperFactory.createMapper(FeedingScheduleInput.class, FeedingScheduleDto.class);
    }

    @QueryMapping
    public Map<String, Object> getFeedingSchedules(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FeedingScheduleDto> feedingScheduleDtoPage = feedingScheduleService.getAllFeedingSchedules(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("feeding schedules", feedingScheduleDtoPage.getContent());
        response.put("totalPages", feedingScheduleDtoPage.getTotalPages());
        response.put("totalElements", feedingScheduleDtoPage.getTotalElements());

        return response;
    }

    @QueryMapping
    public List<FeedingScheduleDto> getAllFeedingSchedules() {
        return feedingScheduleService.getAllFeedingSchedules();
    }

    @QueryMapping
    public FeedingScheduleDto getFeedingScheduleById(@Argument Long id) {
        try {
            return feedingScheduleService.getFeedingScheduleById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find a feeding schedule" + e.getMessage());
        }
    }

    @MutationMapping
    public FeedingScheduleDto createFeedingSchedule(@Argument FeedingScheduleInput input) {
        try {
            FeedingScheduleDto feedingScheduleDto = convertToDto(input);
            return feedingScheduleService.createFeedingSchedule(feedingScheduleDto);
        } catch (Exception e) {
            throw new CustomException("Could not create adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public FeedingScheduleDto updateFeedingSchedule(@Argument FeedingScheduleInput input) {
        try {
            FeedingScheduleDto feedingScheduleDto = convertToDto(input);
            return feedingScheduleService.updateFeedingSchedule(feedingScheduleDto);
        } catch (Exception e) {
            throw new CustomException("Could not update feeding schedule" + e.getMessage());
        }
    }

    @MutationMapping
    public void deleteFeedingSchedule(@Argument Long id) {
        try {
            feedingScheduleService.deleteFeedingSchedule(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete feeding schedule" + e.getMessage());
        }
    }

    private FeedingScheduleDto convertToDto(FeedingScheduleInput feedingScheduleInput) {
        return feedingScheduleMapper.convertToDTO(feedingScheduleInput);
    }
}
