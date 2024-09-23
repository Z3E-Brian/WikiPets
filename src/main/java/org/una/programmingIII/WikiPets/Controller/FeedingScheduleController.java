package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.FeedingScheduleInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.FeedingScheduleService;

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
        try {
            return feedingScheduleService.getFeedingSchedules(page, size);

        } catch (NotFoundElementException e) {
            throw new NotFoundElementException(e.getMessage());
        }
    }

    @QueryMapping
    public FeedingScheduleDto getFeedingScheduleById(@Argument Long id) {
        return feedingScheduleService.getFeedingScheduleById(id);
    }

    @MutationMapping
    public FeedingScheduleDto createFeedingSchedule(@Argument FeedingScheduleInput input) {
        FeedingScheduleDto feedingScheduleDto = convertToDto(input);
        return feedingScheduleService.createFeedingSchedule(feedingScheduleDto);
    }

    @MutationMapping
    public FeedingScheduleDto updateFeedingSchedule(@Argument FeedingScheduleInput input) {
        FeedingScheduleDto feedingScheduleDto = convertToDto(input);
        return feedingScheduleService.updateFeedingSchedule(feedingScheduleDto);
    }

    @MutationMapping
    public Boolean deleteFeedingSchedule(@Argument Long id) {
        return feedingScheduleService.deleteFeedingSchedule(id);
    }


    @MutationMapping
    public FeedingScheduleDto addDogBreedInFeedingSchedule(@Argument Long id, @Argument Long idDogBreed) {
        return feedingScheduleService.addDogBreedInFeedingSchedule(id, idDogBreed);
    }

    @MutationMapping
    public FeedingScheduleDto deleteDogBreedInFeedingSchedule(@Argument Long id, @Argument Long idDogBreed) {
        return feedingScheduleService.deleteDogBreedInFeedingSchedule(id, idDogBreed);
    }


    @MutationMapping
    public FeedingScheduleDto addCatBreedInFeedingSchedule(@Argument Long id, @Argument Long idCatBreed) {
        return feedingScheduleService.addCatBreedInInFeedingSchedule(id, idCatBreed);
    }

    @MutationMapping
    public FeedingScheduleDto deleteCatBreedInFeedingSchedule(@Argument Long id, @Argument Long idCatBreed) {
        return feedingScheduleService.deleteCatBreedInFeedingSchedule(id, idCatBreed);
    }


    private FeedingScheduleDto convertToDto(FeedingScheduleInput feedingScheduleInput) {
        return feedingScheduleMapper.convertToDTO(feedingScheduleInput);
    }
}
