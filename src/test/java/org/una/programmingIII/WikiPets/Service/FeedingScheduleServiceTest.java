package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Mapper.FeedingScheduleMapper;
import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.una.programmingIII.WikiPets.Dto.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class FeedingScheduleServiceTest {

    @Mock
    private FeedingScheduleRepository feedingScheduleRepository;

    @Mock
    private FeedingScheduleMapper feedingScheduleMapper;

    @InjectMocks
    private FeedingScheduleService feedingScheduleService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllFeedingSchedulesTest() {
        FeedingSchedule feedingSchedule = new FeedingSchedule();
        when(feedingScheduleRepository.findAll()).thenReturn(Arrays.asList(feedingSchedule));
        when(feedingScheduleMapper.toFeedingScheduleDto(any(FeedingSchedule.class)))
                .thenReturn(new FeedingScheduleDto());

        List<FeedingScheduleDto> feedingScheduleDtos = feedingScheduleService.getAllFeedingSchedules();

        assertNotNull(feedingScheduleDtos);
        assertEquals(1, feedingScheduleDtos.size());
        verify(feedingScheduleRepository, times(1)).findAll();
    }

    @Test
    public void getFeedingScheduleByIdTest() {
        FeedingSchedule feedingSchedule = new FeedingSchedule();
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        when(feedingScheduleMapper.toFeedingScheduleDto(any(FeedingSchedule.class)))
                .thenReturn(new FeedingScheduleDto());

        FeedingScheduleDto feedingScheduleDto = feedingScheduleService.getFeedingScheduleById(1L);

        assertNotNull(feedingScheduleDto);
        verify(feedingScheduleRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getFeedingScheduleByIdNotFoundTest() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> feedingScheduleService.getFeedingScheduleById(1L));
    }

    @Test
    public void createFeedingScheduleTest() {
        FeedingSchedule feedingSchedule = new FeedingSchedule();
        FeedingScheduleDto feedingScheduleDto = new FeedingScheduleDto();

        when(feedingScheduleRepository.save(any(FeedingSchedule.class))).thenReturn(feedingSchedule);
        when(feedingScheduleMapper.toFeedingScheduleDto(any(FeedingSchedule.class))).thenReturn(feedingScheduleDto);
        when(feedingScheduleMapper.toFeedingSchedule(any(FeedingScheduleDto.class))).thenReturn(feedingSchedule);

        FeedingScheduleDto createdFeedingScheduleDto = feedingScheduleService.createFeedingSchedule(feedingScheduleDto);

        assertNotNull(createdFeedingScheduleDto);
        verify(feedingScheduleRepository, times(1)).save(any(FeedingSchedule.class));
    }

    @Test
    public void updateFeedingScheduleTest() {
        FeedingSchedule feedingSchedule = new FeedingSchedule();
        FeedingScheduleDto feedingScheduleDto = new FeedingScheduleDto();

        when(feedingScheduleRepository.save(any(FeedingSchedule.class))).thenReturn(feedingSchedule);
        when(feedingScheduleMapper.toFeedingScheduleDto(any(FeedingSchedule.class))).thenReturn(feedingScheduleDto);
        when(feedingScheduleMapper.toFeedingSchedule(any(FeedingScheduleDto.class))).thenReturn(feedingSchedule);

        FeedingScheduleDto updatedFeedingScheduleDto = feedingScheduleService.updateFeedingSchedule1(feedingScheduleDto);

        assertNotNull(updatedFeedingScheduleDto);
        verify(feedingScheduleRepository, times(1)).save(any(FeedingSchedule.class));
    }

    @Test
    public void deleteFeedingScheduleTest() {
        FeedingSchedule feedingSchedule = new FeedingSchedule();
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        doNothing().when(feedingScheduleRepository).delete(any(FeedingSchedule.class));

        feedingScheduleService.deleteFeedingSchedule(1L);

        verify(feedingScheduleRepository, times(1)).findById(anyLong());
        verify(feedingScheduleRepository, times(1)).delete(any(FeedingSchedule.class));
    }

    @Test
    public void deleteFeedingScheduleNotFoundTest() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> feedingScheduleService.deleteFeedingSchedule(1L));
    }
}
