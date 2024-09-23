package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.FeedingScheduleInput;
import org.una.programmingIII.WikiPets.Input.UserInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.FeedingScheduleService;

import java.util.HashMap;
import java.util.Map;

public class FeedingScheduleControllerTest {

    @InjectMocks
    private FeedingScheduleController feedingScheduleController;

    @Mock
    private FeedingScheduleService feedingScheduleService;
    @Mock
    private GenericMapperFactory mapperFactory;
    @Mock
    private GenericMapper<FeedingScheduleInput, FeedingScheduleDto> feedingScheduleMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mapperFactory.createMapper(FeedingScheduleInput.class, FeedingScheduleDto.class)).thenReturn(feedingScheduleMapper);
        feedingScheduleController = new FeedingScheduleController(feedingScheduleService, mapperFactory);

    }

    @Test
    void testGetFeedingSchedules() {
        int page = 1;
        int size = 10;
        Map<String, Object> expectedSchedules = new HashMap<>();
        when(feedingScheduleService.getFeedingSchedules(page, size)).thenReturn(expectedSchedules);


        Map<String, Object> result = feedingScheduleController.getFeedingSchedules(page, size);


        assertEquals(expectedSchedules, result);
        verify(feedingScheduleService).getFeedingSchedules(page, size);
    }

    @Test
    void testGetFeedingSchedulesException() {
        int page = 1;
        int size = 10;
        when(feedingScheduleService.getFeedingSchedules(page, size)).thenThrow(new NotFoundElementException("Could not retrieve feeding schedulesError"));


        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            feedingScheduleController.getFeedingSchedules(page, size);
        });
        assertEquals("Could not retrieve feeding schedulesError", exception.getMessage());
    }

    @Test
    void testGetFeedingScheduleById() {

        Long id = 1L;
        FeedingScheduleDto expectedSchedule = new FeedingScheduleDto();
        when(feedingScheduleService.getFeedingScheduleById(id)).thenReturn(expectedSchedule);

        FeedingScheduleDto result = feedingScheduleController.getFeedingScheduleById(id);

        assertEquals(expectedSchedule, result);
        verify(feedingScheduleService).getFeedingScheduleById(id);
    }

    @Test
    void testCreateFeedingSchedule() {

        FeedingScheduleInput input = new FeedingScheduleInput();
        FeedingScheduleDto expectedScheduleDto = new FeedingScheduleDto();
        when(feedingScheduleMapper.convertToDTO(input)).thenReturn(expectedScheduleDto);
        when(feedingScheduleService.createFeedingSchedule(expectedScheduleDto)).thenReturn(expectedScheduleDto);


        FeedingScheduleDto result = feedingScheduleController.createFeedingSchedule(input);


        assertEquals(expectedScheduleDto, result);
        verify(feedingScheduleMapper).convertToDTO(input);
        verify(feedingScheduleService).createFeedingSchedule(expectedScheduleDto);
    }

    @Test
    void testUpdateFeedingSchedule() {

        FeedingScheduleInput input = new FeedingScheduleInput();
        FeedingScheduleDto expectedScheduleDto = new FeedingScheduleDto();
        when(feedingScheduleMapper.convertToDTO(input)).thenReturn(expectedScheduleDto);
        when(feedingScheduleService.updateFeedingSchedule(expectedScheduleDto)).thenReturn(expectedScheduleDto);


        FeedingScheduleDto result = feedingScheduleController.updateFeedingSchedule(input);


        assertEquals(expectedScheduleDto, result);
        verify(feedingScheduleMapper).convertToDTO(input);
        verify(feedingScheduleService).updateFeedingSchedule(expectedScheduleDto);
    }

    @Test
    void testDeleteFeedingSchedule() {

        Long id = 1L;
        when(feedingScheduleService.deleteFeedingSchedule(id)).thenReturn(true);


        Boolean result = feedingScheduleController.deleteFeedingSchedule(id);


        assertTrue(result);
        verify(feedingScheduleService).deleteFeedingSchedule(id);
    }

    @Test
    void testAddDogBreedInFeedingSchedule() {
        Long scheduleId = 1L;
        Long dogBreedId = 2L;
        FeedingScheduleDto expectedDto = new FeedingScheduleDto();

        when(feedingScheduleService.addDogBreedInFeedingSchedule(scheduleId, dogBreedId)).thenReturn(expectedDto);

        FeedingScheduleDto result = feedingScheduleController.addDogBreedInFeedingSchedule(scheduleId, dogBreedId);

        assertEquals(expectedDto, result);
        verify(feedingScheduleService).addDogBreedInFeedingSchedule(scheduleId, dogBreedId);
    }

    @Test
    void testDeleteDogBreedInFeedingSchedule() {
        Long scheduleId = 1L;
        Long dogBreedId = 2L;
        FeedingScheduleDto expectedDto = new FeedingScheduleDto();

        when(feedingScheduleService.deleteDogBreedInFeedingSchedule(scheduleId, dogBreedId)).thenReturn(expectedDto);

        FeedingScheduleDto result = feedingScheduleController.deleteDogBreedInFeedingSchedule(scheduleId, dogBreedId);

        assertEquals(expectedDto, result);
        verify(feedingScheduleService).deleteDogBreedInFeedingSchedule(scheduleId, dogBreedId);
    }

    @Test
    void testAddCatBreedInFeedingSchedule() {
        Long scheduleId = 1L;
        Long catBreedId = 3L;
        FeedingScheduleDto expectedDto = new FeedingScheduleDto();

        when(feedingScheduleService.addCatBreedInInFeedingSchedule(scheduleId, catBreedId)).thenReturn(expectedDto);

        FeedingScheduleDto result = feedingScheduleController.addCatBreedInFeedingSchedule(scheduleId, catBreedId);

        assertEquals(expectedDto, result);
        verify(feedingScheduleService).addCatBreedInInFeedingSchedule(scheduleId, catBreedId);
    }

    @Test
    void testDeleteCatBreedInFeedingSchedule() {
        Long scheduleId = 1L;
        Long catBreedId = 3L;
        FeedingScheduleDto expectedDto = new FeedingScheduleDto();

        when(feedingScheduleService.deleteCatBreedInFeedingSchedule(scheduleId, catBreedId)).thenReturn(expectedDto);

        FeedingScheduleDto result = feedingScheduleController.deleteCatBreedInFeedingSchedule(scheduleId, catBreedId);

        assertEquals(expectedDto, result);
        verify(feedingScheduleService).deleteCatBreedInFeedingSchedule(scheduleId, catBreedId);
    }

    @Test
    void testFeedingScheduleControllerConstructor() {
        assertNotNull(feedingScheduleController);
    }

}
