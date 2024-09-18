package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        } catch (Exception e) {
            throw new CustomException("Could not retrieve care tips" + e.getMessage());
        }
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
            throw new CustomException("Could not create a feeding schedule" + e.getMessage());
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


    @MutationMapping
    public FeedingScheduleDto addDogBreedInFeedingSchedule(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return feedingScheduleService.addDogBreedInFeedingSchedule(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add the dog in the Feeding Scheulewith id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public FeedingScheduleDto deleteDogBreedInFeedingSchedule(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return feedingScheduleService.deleteDogBreedInFeedingSchedule(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not delete the dog in the Feeding Scheule with id: " + id + ". " + e.getMessage(), e);
        }
    }


    @MutationMapping
    public FeedingScheduleDto addCatBreedInFeedingSchedule(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return feedingScheduleService.addCatBreedInInFeedingSchedule(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not add the cat in the Feeding Schedule with  id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public FeedingScheduleDto deleteCatBreedInFeedingSchedule(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return feedingScheduleService.deleteCatBreedInFeedingSchedule(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not delete the cat in the Feeding Schedule with id: " + id + ". " + e.getMessage(), e);
        }
    }


    private FeedingScheduleDto convertToDto(FeedingScheduleInput feedingScheduleInput) {
        return feedingScheduleMapper.convertToDTO(feedingScheduleInput);
    }
}
