package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.FeedingScheduleInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Service.FeedingScheduleService;

import java.util.HashMap;
import java.util.Map;

public class FeedingScheduleControllerTest {

    @InjectMocks
    private FeedingScheduleController feedingScheduleController;

    @Mock
    private FeedingScheduleService feedingScheduleService;

    @Mock
    private GenericMapper<FeedingScheduleInput, FeedingScheduleDto> feedingScheduleMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFeedingSchedules() {
        // Arrange
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
}
