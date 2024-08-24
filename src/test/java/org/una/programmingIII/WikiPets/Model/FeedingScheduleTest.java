package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FeedingScheduleTest {
    FeedingSchedule feedingSchedule;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();
        CatBreed catBreed = new CatBreed();
        catBreed.setName("Siamese");

        feedingSchedule = new FeedingSchedule();
        feedingSchedule.setId(1L);
        feedingSchedule.setCatBreed(catBreed);
        feedingSchedule.setAgeGroup("Kitten");
        feedingSchedule.setFeedingTimes("Three times a day");
        feedingSchedule.setCreateDate(nowDate);
        feedingSchedule.setLastUpdate(nowDate);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, feedingSchedule.getId());
        assertEquals("Siamese", feedingSchedule.getCatBreed().getName());
        assertEquals("Kitten", feedingSchedule.getAgeGroup());
        assertEquals("Three times a day", feedingSchedule.getFeedingTimes());
    }

    @Test
    public void argsSettersTest() {
        LocalDate nowDate = LocalDate.now();
        DogBreed dogBreed = new DogBreed();
        dogBreed.setName("Labrador");

        feedingSchedule.setId(2L);
        feedingSchedule.setDogBreed(dogBreed);
        feedingSchedule.setAgeGroup("Puppy");
        feedingSchedule.setFeedingTimes("Twice a day");

        assertEquals(2L, feedingSchedule.getId());
        assertEquals("Labrador", feedingSchedule.getDogBreed().getName());
        assertEquals("Puppy", feedingSchedule.getAgeGroup());
        assertEquals("Twice a day", feedingSchedule.getFeedingTimes());
    }

    @Test
    public void equalsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        FeedingSchedule feedingSchedule1 = new FeedingSchedule(1L, new CatBreed(), null, "Kitten", "Three times a day", nowDate, nowDate);
        FeedingSchedule feedingSchedule2 = new FeedingSchedule(1L, new CatBreed(), null, "Kitten", "Three times a day", nowDate, nowDate);

        assertEquals(feedingSchedule1, feedingSchedule2);
        assertEquals(feedingSchedule1.hashCode(), feedingSchedule2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();
        FeedingSchedule feedingSchedule1 = new FeedingSchedule(1L, new CatBreed(), null, "Kitten", "Three times a day", nowDate, nowDate);
        FeedingSchedule feedingSchedule2 = new FeedingSchedule(2L, null, new DogBreed(), "Puppy", "Twice a day", nowDate, nowDate);

        assertNotEquals(feedingSchedule1, feedingSchedule2);
        assertNotEquals(feedingSchedule1.hashCode(), feedingSchedule2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate nowDate = LocalDate.now();
        //assertEquals("FeedingSchedule(id=1, catBreed=CatBreed(id=1, name=Siamese, origin=Thailand, size=2, coat=Short, color=Cream with points, lifeSpan=12-16 years, temperament=Affectionate, Social, Vocal, description=Popular breed known for its striking appearance and vocal nature., createdDate=" + nowDate + ", modifiedDate=" + nowDate + "), dogBreed=null, ageGroup=Kitten, feedingTimes=Three times a day, createDate=" + nowDate + ", lastUpdate=" + nowDate + ")", feedingSchedule.toString());
    }
}