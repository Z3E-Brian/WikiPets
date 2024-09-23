package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    private Review review;
    private CatBreed catBreed;
    private DogBreed dogBreed;
    private User user;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        // Configuración de catBreed
        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");

        // Configuración de dogBreed
        dogBreed = new DogBreed();
        dogBreed.setId(1L);
        dogBreed.setName("Labrador");

        // Configuración de user
        user = new User();
        user.setId(1L);
        user.setName("john_doe");

        // Configuración de review
        review = new Review();
        review.setId(1L);
        review.setCatBreed(catBreed);
        review.setDogBreed(dogBreed);
        review.setUser(user);
        review.setRating(5);
        review.setComment("Great breed!");
        review.setCreateDate(nowDate);
        review.setLastUpdate(nowDate);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, review.getId());
        assertEquals("Siamese", review.getCatBreed().getName());
        assertEquals("Labrador", review.getDogBreed().getName());
        assertEquals("john_doe", review.getUser().getName());
        assertEquals(5, review.getRating());
        assertEquals("Great breed!", review.getComment());
    }

    @Test
    public void argsSettersTest() {
        LocalDate nowDate = LocalDate.now();

        CatBreed newCatBreed = new CatBreed();
        newCatBreed.setId(2L);
        newCatBreed.setName("Persian");

        DogBreed newDogBreed = new DogBreed();
        newDogBreed.setId(2L);
        newDogBreed.setName("German Shepherd");

        User newUser = new User();
        newUser.setId(2L);
        newUser.setName("jane_doe");

        review.setId(2L);
        review.setCatBreed(newCatBreed);
        review.setDogBreed(newDogBreed);
        review.setUser(newUser);
        review.setRating(4);
        review.setComment("Lovely breed!");
        review.setCreateDate(nowDate);
        review.setLastUpdate(nowDate);

        assertEquals(2L, review.getId());
        assertEquals("Persian", review.getCatBreed().getName());
        assertEquals("German Shepherd", review.getDogBreed().getName());
        assertEquals("jane_doe", review.getUser().getName());
        assertEquals(4, review.getRating());
        assertEquals("Lovely breed!", review.getComment());
    }

    @Test
    public void equalsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        Review review1 = new Review(1L, catBreed, dogBreed, user, 5, "Great breed!", nowDate, nowDate);
        Review review2 = new Review(1L, catBreed, dogBreed, user, 5, "Great breed!", nowDate, nowDate);

        assertEquals(review1, review2);
        assertEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        Review review1 = new Review(1L, catBreed, dogBreed, user, 5, "Great breed!", nowDate, nowDate);
        Review review2 = new Review(2L, null, null, user, 4, "Lovely breed!", nowDate, nowDate);

        assertNotEquals(review1, review2);
        assertNotEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate nowDate = LocalDate.now();

        String result = review.toString();
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("rating=5"));
        assertTrue(result.contains("comment=Great breed!"));
        assertTrue(result.contains("createDate=" + nowDate));
        assertTrue(result.contains("lastUpdate=" + nowDate));
    }

}
