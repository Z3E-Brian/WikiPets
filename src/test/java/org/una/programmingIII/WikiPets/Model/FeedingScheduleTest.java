package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeedingScheduleTest {

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
    public void feedingScheduleConstructorTest() {
        assertNotNull(feedingSchedule);
        assertEquals(1L, feedingSchedule.getId());
        assertEquals("Adult", feedingSchedule.getAgeGroup());
        assertEquals("Twice a day", feedingSchedule.getFeedingTimes());
        assertEquals(1L, feedingSchedule.getVersion());
    }

    @Test
    public void feedingScheduleUpdateTest() {
        feedingScheduleDto.setAgeGroup("Puppy");
        feedingScheduleDto.setFeedingTimes("Three times a day");
        feedingSchedule.update(feedingScheduleDto);

        assertEquals("Puppy", feedingSchedule.getAgeGroup());
        assertEquals("Three times a day", feedingSchedule.getFeedingTimes());
    }
}
