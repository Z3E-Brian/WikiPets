package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.una.programmingIII.WikiPets.Model.FeedingScheduleDto;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

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
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<FeedingSchedule, FeedingScheduleDto> feedingScheduleMapper;

    @InjectMocks
    private FeedingScheduleService feedingScheduleService;

    private FeedingSchedule feedingSchedule;
    private FeedingScheduleDto feedingScheduleDto;

    @BeforeEach
    public void setup() {
        feedingSchedule = new FeedingSchedule();
        feedingSchedule.setId(1L);
        feedingSchedule.setAgeGroup("Kitten");
        feedingSchedule.setFeedingTimes("Three times a day");

        feedingScheduleDto = new FeedingScheduleDto();
        feedingScheduleDto.setId(1L);
        feedingScheduleDto.setAgeGroup("Kitten");
        feedingScheduleDto.setFeedingTimes("Three times a day");

        MockitoAnnotations.initMocks(this);
        when(mapperFactory.createMapper(FeedingSchedule.class, FeedingScheduleDto.class)).thenReturn(feedingScheduleMapper);
        when(feedingScheduleMapper.convertToDTO(feedingSchedule)).thenReturn(feedingScheduleDto);
        when(feedingScheduleMapper.convertToEntity(feedingScheduleDto)).thenReturn(feedingSchedule);
        feedingScheduleService = new FeedingScheduleService(feedingScheduleRepository, mapperFactory);
    }

    @Test
    public void getAllFeedingSchedulesTest() {
        when(feedingScheduleRepository.findAll()).thenReturn(List.of(feedingSchedule));
        List<FeedingScheduleDto> feedingScheduleDtos = feedingScheduleService.getAllFeedingSchedules();

        assertNotNull(feedingScheduleDtos);
        assertEquals(1, feedingScheduleDtos.size());
        verify(feedingScheduleRepository, times(1)).findAll();
    }

    @Test
    public void getFeedingScheduleByIdTest() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(feedingSchedule));
        FeedingScheduleDto result = feedingScheduleService.getFeedingScheduleById(1L);
        assertNotNull(result);
        verify(feedingScheduleRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getFeedingScheduleByIdNotFoundTest() {
        when(feedingScheduleRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> feedingScheduleService.getFeedingScheduleById(1L));
    }

    @Test
    public void createFeedingScheduleTest() {
        when(feedingScheduleRepository.save(any(FeedingSchedule.class))).thenReturn(feedingSchedule);
        FeedingScheduleDto createdFeedingScheduleDto = feedingScheduleService.createFeedingSchedule(feedingScheduleDto);
        assertEquals(feedingScheduleDto.getId(), feedingSchedule.getId());
        assertNotNull(createdFeedingScheduleDto);
        verify(feedingScheduleRepository, times(1)).save(any(FeedingSchedule.class));
    }

    @Test
    public void updateFeedingScheduleTest() {
        when(feedingScheduleRepository.save(any(FeedingSchedule.class))).thenReturn(feedingSchedule);
        FeedingScheduleDto result = feedingScheduleService.updateFeedingSchedule1(feedingScheduleDto);
        assertEquals(feedingSchedule.getId(), feedingScheduleDto.getId());
        assertEquals(feedingSchedule.getFeedingTimes(), feedingScheduleDto.getFeedingTimes());
        assertNotNull(result);
        verify(feedingScheduleRepository, times(1)).save(any(FeedingSchedule.class));
    }

    @Test
    public void deleteFeedingScheduleTest() {
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
