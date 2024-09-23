package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeedingScheduleTest {

    private FeedingSchedule feedingSchedule;
    private CatBreed catBreed;
    private DogBreed dogBreed;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");

        dogBreed = new DogBreed();
        dogBreed.setId(1L);
        dogBreed.setName("Golden Retriever");

        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(catBreed);

        List<DogBreed> dogBreeds = new ArrayList<>();
        dogBreeds.add(dogBreed);

        feedingSchedule = new FeedingSchedule();
        feedingSchedule.setId(1L);
        feedingSchedule.setCatBreeds(catBreeds);
        feedingSchedule.setDogBreeds(dogBreeds);
        feedingSchedule.setAgeGroup("Puppy");
        feedingSchedule.setFeedingTimes("Twice a day");
        feedingSchedule.setCreateDate(nowDate);
        feedingSchedule.setLastUpdate(nowDate);
    }

    @Test
    public void gettersTest() {
        assertEquals(1L, feedingSchedule.getId());
        assertEquals("Puppy", feedingSchedule.getAgeGroup());
        assertEquals("Twice a day", feedingSchedule.getFeedingTimes());
        assertEquals("Siamese", feedingSchedule.getCatBreeds().get(0).getName());
        assertEquals("Golden Retriever", feedingSchedule.getDogBreeds().get(0).getName());
    }

    @Test
    public void settersTest() {
        LocalDate nowDate = LocalDate.now();

        feedingSchedule.setAgeGroup("Adult");
        feedingSchedule.setFeedingTimes("Once a day");

        assertEquals("Adult", feedingSchedule.getAgeGroup());
        assertEquals("Once a day", feedingSchedule.getFeedingTimes());
    }

    @Test
    public void equalsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        FeedingSchedule feedingSchedule1 = new FeedingSchedule(1L, feedingSchedule.getCatBreeds(), feedingSchedule.getDogBreeds(), "Puppy", "Twice a day", nowDate, nowDate);
        FeedingSchedule feedingSchedule2 = new FeedingSchedule(1L, feedingSchedule.getCatBreeds(), feedingSchedule.getDogBreeds(), "Puppy", "Twice a day", nowDate, nowDate);

        assertEquals(feedingSchedule1, feedingSchedule2);
        assertEquals(feedingSchedule1.hashCode(), feedingSchedule2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        FeedingSchedule feedingSchedule1 = new FeedingSchedule();
        feedingSchedule1.setId(1L);
        FeedingSchedule feedingSchedule2 = new FeedingSchedule();
        feedingSchedule2.setId(2L);

        assertNotEquals(feedingSchedule1, feedingSchedule2);
        assertNotEquals(feedingSchedule1.hashCode(), feedingSchedule2.hashCode());
    }

    @Test
    public void toStringTest() {
        String result = feedingSchedule.toString();

        assertTrue(result.contains("FeedingSchedule"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("ageGroup=Puppy"));
        assertTrue(result.contains("feedingTimes=Twice a day"));

        assertTrue(result.contains("catBreeds"));
        assertTrue(result.contains("dogBreeds"));
    }
}
