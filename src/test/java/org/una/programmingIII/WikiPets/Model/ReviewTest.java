package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReviewTest {
    Review review;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        review = new Review();
        review.setId(1L);
        review.setCatBreed(new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate));
        review.setUser(new User(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate));
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

        review.setId(2L);
        review.setDogBreed(new DogBreed(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active, Outgoing", "One of the most popular breeds in the world.", nowDate, nowDate));
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

        Review review1 = new Review(1L, new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, new User(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate), 5, "Excellent breed!", nowDate, nowDate);
        Review review2 = new Review(1L, new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, new User(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate), 5, "Excellent breed!", nowDate, nowDate);

        assertEquals(review1, review2);
        assertEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        Review review1 = new Review(1L, new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, new User(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate), 5, "Excellent breed!", nowDate, nowDate);
        Review review2 = new Review(2L, null, new DogBreed(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active, Outgoing", "One of the most popular breeds in the world.", nowDate, nowDate), new User(2L, "Jane Doe", "jane.doe@example.com", null, null, nowDate, nowDate), 4, "Great dog breed!", nowDate, nowDate);

        assertNotEquals(review1, review2);
        assertNotEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate nowDate = LocalDate.now();
        assertEquals("Review(id=1, catBreed=CatBreed(id=1, " +
                "name=Siamese, origin=Thailand, size=2, coat=Short, color=Cream with points, " +
                "lifeSpan=12-16 years, temperament=Affectionate, Social, Vocal, description=Popular " +
                "breed known for its striking appearance and vocal nature., createdDate=" + nowDate + ", " +
                "modifiedDate=" + nowDate + "), dogBreed=null, user=User(id=1, name=John Doe, " +
                "email=john.doe@example.com, favoriteCatBreeds=null, favoriteDogBreeds=null, createDate=" + nowDate + ", " +
                "lastUpdate=" + nowDate + "), rating=5, comment=Excellent breed!, " +
                "createDate=" + nowDate + ", lastUpdate=" + nowDate + ")", review.toString());
    }
}
