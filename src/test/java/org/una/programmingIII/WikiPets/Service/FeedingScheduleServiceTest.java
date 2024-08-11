package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.FeedingScheduleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedingScheduleServiceTest {

    @Mock
    private FeedingScheduleRepository feedingScheduleRepository;

    @InjectMocks
    private FeedingScheduleService feedingScheduleService;

    private FeedingSchedule feedingSchedule;
    private FeedingScheduleDto feedingScheduleDto;

    @BeforeEach
    public void setUp() {
        CatBreedDto catBreedDto = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.");
        DogBreedDto dogBreedDto = new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active", "Well-known for being friendly and good with children.");
        feedingScheduleDto = new FeedingScheduleDto(1L, catBreedDto, dogBreedDto, "Adult", "Twice a day", 1L);
        feedingSchedule = new FeedingSchedule(feedingScheduleDto);
    }

    @Test
    public void createFeedingScheduleTest() {
        when(feedingScheduleRepository.save(Mockito.any(FeedingSchedule.class))).thenReturn(feedingSchedule);

        FeedingScheduleDto result = feedingScheduleService.createFeedingSchedule(feedingScheduleDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Adult", result.getAgeGroup());
    }

    @Test
    public void getFeedingScheduleByIdTest() {
        when(feedingScheduleRepository.findById(1L)).thenReturn(Optional.of(feedingSchedule));

        FeedingScheduleDto result = feedingScheduleService.getFeedingScheduleById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Adult", result.getAgeGroup());
    }
}
