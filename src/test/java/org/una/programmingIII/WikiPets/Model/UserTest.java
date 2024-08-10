package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        CatBreed siamese = new CatBreed();
        siamese.setId(1L);
        siamese.setName("Siamese");

        DogBreed goldenRetriever = new DogBreed();
        goldenRetriever.setId(1L);
        goldenRetriever.setName("Golden Retriever");

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setFavoriteCatBreeds(Arrays.asList(siamese));
        user.setFavoriteDogBreeds(Arrays.asList(goldenRetriever));
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(1, user.getFavoriteCatBreeds().size());
        assertEquals(1, user.getFavoriteDogBreeds().size());
    }

    @Test
    public void argsSettersTest() {
        CatBreed maineCoon = new CatBreed();
        maineCoon.setId(2L);
        maineCoon.setName("Maine Coon");

        DogBreed bulldog = new DogBreed();
        bulldog.setId(2L);
        bulldog.setName("Bulldog");

        user.setId(2L);
        user.setName("Jane Smith");
        user.setEmail("jane.smith@example.com");
        user.setFavoriteCatBreeds(Arrays.asList(maineCoon));
        user.setFavoriteDogBreeds(Arrays.asList(bulldog));

        assertEquals(2L, user.getId());
        assertEquals("Jane Smith", user.getName());
        assertEquals("jane.smith@example.com", user.getEmail());
        assertEquals(1, user.getFavoriteCatBreeds().size());
        assertEquals(1, user.getFavoriteDogBreeds().size());
    }

    @Test
    public void equalsAndHashCodeTest() {
        User user1 = new User(1L, "John Doe", "john.doe@example.com",
                Arrays.asList(new CatBreed(1L, "Siamese", null, null, null, null, null, null, null)),
                Arrays.asList(new DogBreed(1L, "Golden Retriever", null, null, null, null, null, null, null)),1L);

        User user2 = new User(1L, "John Doe", "john.doe@example.com",
                Arrays.asList(new CatBreed(1L, "Siamese", null, null, null, null, null, null, null)),
                Arrays.asList(new DogBreed(1L, "Golden Retriever", null, null, null, null, null, null, null)),1L);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        User user1 = new User(1L, "John Doe", "john.doe@example.com",
                Arrays.asList(new CatBreed(1L, "Siamese", null, null, null, null, null, null, null)),
                Arrays.asList(new DogBreed(1L, "Golden Retriever", null, null, null, null, null, null, null)),1L);

        User user2 = new User(2L, "Jane Smith", "jane.smith@example.com",
                Arrays.asList(new CatBreed(2L, "Maine Coon", null, null, null, null, null, null, null)),
                Arrays.asList(new DogBreed(2L, "Bulldog", null, null, null, null, null, null, null)),1L);

        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void toStringTest() {
        String expectedString = "User(id=1, name=John Doe, email=john.doe@example.com, favoriteCatBreeds=[CatBreed(id=1, name=Siamese, origin=null, size=null, coat=null, color=null, lifeSpan=null, temperament=null, description=null)], favoriteDogBreeds=[DogBreed(id=1, name=Golden Retriever, origin=null, size=null, coat=null, color=null, lifeSpan=null, temperament=null, description=null)])";
        assertEquals(expectedString, user.toString());
    }
}
