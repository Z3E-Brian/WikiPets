package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<CatBreed> favoriteCatBreeds = new ArrayList<>();
        List<DogBreed> favoriteDogBreeds = new ArrayList<>();
        LocalDate now = LocalDate.now();

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setFavoriteCatBreeds(favoriteCatBreeds);
        user.setFavoriteDogBreeds(favoriteDogBreeds);
        user.setCreateDate(now);
        user.setLastUpdate(now);
    }

    @Test
    public void testInitialValues() {
        List<CatBreed> emptyCatBreeds = new ArrayList<>();
        List<DogBreed> emptyDogBreeds = new ArrayList<>();
        LocalDate now = user.getCreateDate(); // Use the same timestamp for consistency

        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(emptyCatBreeds, user.getFavoriteCatBreeds());
        assertEquals(emptyDogBreeds, user.getFavoriteDogBreeds());
        assertEquals(now, user.getCreateDate());
        assertEquals(now, user.getLastUpdate());
    }

    @Test
    public void testSetters() {
        List<CatBreed> favoriteCatBreeds = new ArrayList<>();
        List<DogBreed> favoriteDogBreeds = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate later = now.plusDays(1);

        user.setId(2L);
        user.setName("Jane Smith");
        user.setEmail("jane.smith@example.com");
        user.setFavoriteCatBreeds(favoriteCatBreeds);
        user.setFavoriteDogBreeds(favoriteDogBreeds);
        user.setCreateDate(now);
        user.setLastUpdate(later);

        assertEquals(2L, user.getId());
        assertEquals("Jane Smith", user.getName());
        assertEquals("jane.smith@example.com", user.getEmail());
        assertEquals(favoriteCatBreeds, user.getFavoriteCatBreeds());
        assertEquals(favoriteDogBreeds, user.getFavoriteDogBreeds());
        assertEquals(now, user.getCreateDate());
        assertEquals(later, user.getLastUpdate());
    }

    @Test
    public void testEqualsAndHashCode() {
        List<CatBreed> favoriteCatBreeds = new ArrayList<>();
        List<DogBreed> favoriteDogBreeds = new ArrayList<>();
        LocalDate now = LocalDate.now();

        User user1 = new User(1L, "John Doe", "john.doe@example.com", favoriteCatBreeds, favoriteDogBreeds, now, now);
        User user2 = new User(1L, "John Doe", "john.doe@example.com", favoriteCatBreeds, favoriteDogBreeds, now, now);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testNotEqualsAndHashCode() {
        List<CatBreed> favoriteCatBreeds1 = new ArrayList<>();
        List<DogBreed> favoriteDogBreeds1 = new ArrayList<>();
        List<CatBreed> favoriteCatBreeds2 = new ArrayList<>();
        List<DogBreed> favoriteDogBreeds2 = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate later = now.plusDays(1);

        User user1 = new User(1L, "John Doe", "john.doe@example.com", favoriteCatBreeds1, favoriteDogBreeds1, now, now);
        User user2 = new User(2L, "Jane Smith", "jane.smith@example.com", favoriteCatBreeds2, favoriteDogBreeds2, later, later);

        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testToString() {
        LocalDate now = user.getCreateDate();
        String expectedString = String.format(
                "User(id=1, name=John Doe, email=john.doe@example.com, favoriteCatBreeds=[], favoriteDogBreeds=[], createDate=%s, lastUpdate=%s)",
                now, now
        );

        assertEquals(expectedString, user.toString());
    }
}
