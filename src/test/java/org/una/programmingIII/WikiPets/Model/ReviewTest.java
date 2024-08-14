package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.una.programmingIII.WikiPets.Mapper.ReviewMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReviewTest {
    Review review;

    @BeforeEach
    void setUp() {
        CatBreed catBreed = new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Seal Point", "15 years", "Active, Vocal", "Elegant and sleek", LocalDate.now(),LocalDate.now());
        DogBreed dogBreed = new DogBreed(2L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Friendly, Reliable", "Popular house dog", LocalDate.now(),LocalDate.now());
        User user = new User(1L, "John Doe", "john.doe@example.com", null, null, LocalDateTime.now(), LocalDateTime.now());

        review = new Review(1L, catBreed, dogBreed, user, 5, "Great breed!", LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, review.getId());
        assertEquals(5, review.getRating());
        assertEquals("Great breed!", review.getComment());
        assertEquals(1L, review.getCatBreed().getId());
        assertEquals(2L, review.getDogBreed().getId());
        assertEquals(1L, review.getUser().getId());
    }

    @Test
    public void argsSettersTest() {
        CatBreed newCatBreed = new CatBreed(2L, "Maine Coon", "USA", 4, "Long", "Brown Tabby", "12-15 years", "Friendly, Affectionate", "Large and lovable", LocalDate.now(),LocalDate.now());
        DogBreed newDogBreed = new DogBreed(3L, "Bulldog", "UK", 2, "Short", "Brindle", "8-10 years", "Courageous, Friendly", "Loyal and dependable", LocalDate.now(),LocalDate.now());
        User newUser = new User(2L, "Jane Smith", "jane.smith@example.com", null, null, LocalDateTime.now(), LocalDateTime.now());

        review.setId(2L);
        review.setRating(4);
        review.setComment("Good breed, but has some issues");
        review.setCatBreed(newCatBreed);
        review.setDogBreed(newDogBreed);
        review.setUser(newUser);

        assertEquals(2L, review.getId());
        assertEquals(4, review.getRating());
        assertEquals("Good breed, but has some issues", review.getComment());
        assertEquals(2L, review.getCatBreed().getId());
        assertEquals(3L, review.getDogBreed().getId());
        assertEquals(2L, review.getUser().getId());
    }

    @Test
    public void updateTest() {
        CatBreedDto newCatBreedDto = new CatBreedDto(2L, "Maine Coon", "USA", 4, "Long", "Brown Tabby", "12-15 years", "Friendly, Affectionate", "Large and lovable", LocalDate.now(), LocalDate.now());
        DogBreedDto newDogBreedDto = new DogBreedDto(3L, "Bulldog", "UK", 2, "Short", "Brindle", "8-10 years", "Courageous, Friendly", "Loyal and dependable", LocalDate.now(), LocalDate.now());
        UserDto newUserDto = new UserDto(2L, "Jane Smith", "jane.smith@example.com", null, null, LocalDateTime.now(), LocalDateTime.now());
        ReviewDto reviewDto = new ReviewDto(2L, newCatBreedDto, newDogBreedDto, newUserDto, 4, "Good breed, but has some issues", LocalDateTime.now(), LocalDateTime.now());

        review = ReviewMapper.INSTANCE.toReview(reviewDto);

        assertEquals(4, review.getRating());
        assertEquals("Good breed, but has some issues", review.getComment());
        assertEquals(2L, review.getCatBreed().getId());
        assertEquals(3L, review.getDogBreed().getId());
        assertEquals(2L, review.getUser().getId());
    }

    @Test
    public void equalsAndHashCodeTest() {
        CatBreed catBreed = new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Seal Point", "15 years", "Active, Vocal", "Elegant and sleek", LocalDate.now(), LocalDate.now());
        DogBreed dogBreed = new DogBreed(2L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Friendly, Reliable", "Popular house dog", LocalDate.now(), LocalDate.now());
        User user = new User(1L, "John Doe", "john.doe@example.com", null, null, LocalDateTime.now(), LocalDateTime.now());

        Review review1 = new Review(1L, catBreed, dogBreed, user, 5, "Great breed!", LocalDateTime.now(), LocalDateTime.now());
        Review review2 = new Review(1L, catBreed, dogBreed, user, 5, "Great breed!", LocalDateTime.now(), LocalDateTime.now());

        assertEquals(review1, review2);
        assertEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        CatBreed catBreed1 = new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Seal Point", "15 years", "Active, Vocal", "Elegant and sleek", LocalDate.now(), LocalDate.now());
        DogBreed dogBreed1 = new DogBreed(2L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Friendly, Reliable", "Popular house dog", LocalDate.now(), LocalDate.now());
        User user1 = new User(1L, "John Doe", "john.doe@example.com", null, null, LocalDateTime.now(), LocalDateTime.now());

        CatBreed catBreed2 = new CatBreed(2L, "Maine Coon", "USA", 4, "Long", "Brown Tabby", "12-15 years", "Friendly, Affectionate", "Large and lovable", LocalDate.now(), LocalDate.now());
        DogBreed dogBreed2 = new DogBreed(3L, "Bulldog", "UK", 2, "Short", "Brindle", "8-10 years", "Courageous, Friendly", "Loyal and dependable", LocalDate.now(), LocalDate.now());
        User user2 = new User(2L, "Jane Smith", "jane.smith@example.com", null, null, LocalDateTime.now(), LocalDateTime.now());

        Review review1 = new Review(1L, catBreed1, dogBreed1, user1, 5, "Great breed!", LocalDateTime.now(), LocalDateTime.now());
        Review review2 = new Review(2L, catBreed2, dogBreed2, user2, 3, "Not as expected", LocalDateTime.now(), LocalDateTime.now());

        assertNotEquals(review1, review2);
        assertNotEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("Review(id=1, catBreed=CatBreed(id=1, name=Siamese, origin=Thailand, size=2, coat=Short, color=Seal Point, lifeSpan=15 years, temperament=Active, Vocal, description=Elegant and sleek), dogBreed=DogBreed(id=2, name=Golden Retriever, origin=Scotland, size=3, coat=Long, color=Golden, lifeSpan=10-12 years, temperament=Friendly, Reliable, description=Popular house dog), user=User(id=1, name=John Doe, email=john.doe@example.com, favoriteCatBreeds=null, favoriteDogBreeds=null, version=1), rating=5, comment=Great breed!, createDate=" + review.getCreateDate() + ", lastUpdate=" + review.getLastUpdate() + ")", review.toString());
    }
}
