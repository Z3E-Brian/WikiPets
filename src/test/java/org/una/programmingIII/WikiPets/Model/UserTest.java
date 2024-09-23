package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setFavoriteCatBreeds(new ArrayList<>());
        user.setFavoriteDogBreeds(new ArrayList<>());
        user.setCreateDate(nowDate);
        user.setLastUpdate(nowDate);
        user.setReviews(new ArrayList<>());
    }

    @Test
    public void gettersTest() {
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(0, user.getFavoriteCatBreeds().size());
        assertEquals(0, user.getFavoriteDogBreeds().size());
        assertEquals(0, user.getReviews().size());
    }

    @Test
    public void settersTest() {
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("newpassword");

        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void equalsAndHashCodeTest() {
        User user1 = new User(1L, "John Doe", "john.doe@example.com", "password123", new ArrayList<>(), new ArrayList<>(), LocalDate.now(), LocalDate.now(), new ArrayList<>());
        User user2 = new User(1L, "John Doe", "john.doe@example.com", "password123", new ArrayList<>(), new ArrayList<>(), LocalDate.now(), LocalDate.now(), new ArrayList<>());

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void toStringTest() {
        String expected = "User(id=1, name=John Doe, email=john.doe@example.com, password=password123," +
                " favoriteCatBreeds=[], favoriteDogBreeds=[], " +
                "createDate=" + user.getCreateDate() + ", lastUpdate="
                + user.getLastUpdate() + ", reviews=[])";
        assertEquals(expected, user.toString());
    }
}
