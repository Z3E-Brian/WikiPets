package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReviewTest {/*
    Review review;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        CatBreed catBreed = new CatBreed();
        catBreed.setName("Siamese");

        review = new Review();
        review.setId(1L);
        review.setCatBreed(catBreed);
        review.setUser(new User(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate, null));
        review.setRating(5);
        review.setComment("Excellent breed!");
        review.setCreateDate(nowDate);
        review.setLastUpdate(nowDate);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, review.getId());
        assertEquals("Siamese", review.getCatBreed().getName());
        assertEquals(5, review.getRating());
        assertEquals("Excellent breed!", review.getComment());
    }

    @Test
    public void argsSettersTest() {
        LocalDate nowDate = LocalDate.now();

        DogBreed dogBreed = new DogBreed();
        dogBreed.setName("Labrador");

        review.setId(2L);
        review.setDogBreed(dogBreed);
        review.setRating(4);
        review.setComment("Great dog breed!");

        assertEquals(2L, review.getId());
        assertEquals("Labrador", review.getDogBreed().getName());
        assertEquals(4, review.getRating());
        assertEquals("Great dog breed!", review.getComment());
    }

    @Test
    public void equalsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        Review review1 = new Review(1L, new CatBreed(), null, new User(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate, null), 5, "Excellent breed!", nowDate, nowDate);
        Review review2 = new Review(1L, new CatBreed(), null, new User(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate, null), 5, "Excellent breed!", nowDate, nowDate);

        assertEquals(review1, review2);
        assertEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        Review review1 = new Review(1L, new CatBreed(), null, new User(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate, null), 5, "Excellent breed!", nowDate, nowDate);
        Review review2 = new Review(2L, null, new DogBreed(), new User(2L, "Jane Doe", "jane.doe@example.com", null, null, nowDate, nowDate, null), 4, "Great dog breed!", nowDate, nowDate);

        assertNotEquals(review1, review2);
        assertNotEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate nowDate = LocalDate.now();
        assertEquals("Review(id=1, catBreed=CatBreed(id=null, " +
                "name=Siamese, origin=null, size=null, coat=null, color=null, " +
                "lifeSpan=null, temperament=null, description=null," +
                " createdDate=null, " +
                "modifiedDate=null, adoptionCenters=null, healthIssues=null," +
                " nutritionGuides=null, users=null, trainingGuides=null, " +
                "behaviorGuides=null, careTips=null, groomingGuides=null, feedingSchedules=null," +
                " images=null, videos=null, reviews=null), dogBreed=null, user=User(id=1, name=John Doe, " +
                "email=john.doe@example.com, favoriteCatBreeds=null, favoriteDogBreeds=null, createDate=" + nowDate + ", " +
                "lastUpdate=" + nowDate + ", reviews=null), rating=5, comment=Excellent breed!, " +
                "createDate=" + nowDate + ", lastUpdate=" + nowDate + ")", review.toString());
    }*/
}
