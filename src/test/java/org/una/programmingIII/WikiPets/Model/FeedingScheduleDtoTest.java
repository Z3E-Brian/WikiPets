package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeedingScheduleDtoTest {

    @Test
    public void feedingScheduleDtoConstructorTest() {
        CatBreedDto catBreedDto = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.");
        DogBreedDto dogBreedDto = new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active", "Well-known for being friendly and good with children.");
        FeedingSchedule feedingSchedule = new FeedingSchedule(1L, new CatBreed(catBreedDto), new DogBreed(dogBreedDto), "Adult", "Twice a day", 1L);

        FeedingScheduleDto feedingScheduleDto = new FeedingScheduleDto(feedingSchedule);

        assertNotNull(feedingScheduleDto);
        assertEquals(1L, feedingScheduleDto.getId());
        assertEquals("Adult", feedingScheduleDto.getAgeGroup());
        assertEquals("Twice a day", feedingScheduleDto.getFeedingTimes());
        assertEquals(1L, feedingScheduleDto.getVersion());
    }
}
