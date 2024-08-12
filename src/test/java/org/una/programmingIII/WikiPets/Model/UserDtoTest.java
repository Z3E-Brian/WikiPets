package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserDtoTest {
    UserDto userDto;

    @BeforeEach
    void setUp() {
        List<CatBreedDto> favoriteCatBreeds = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds = new ArrayList<>();

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setFavoriteCatBreeds(favoriteCatBreeds);
        userDto.setFavoriteDogBreeds(favoriteDogBreeds);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, userDto.getId());
        assertEquals("John Doe", userDto.getName());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals(new ArrayList<>(), userDto.getFavoriteCatBreeds());
        assertEquals(new ArrayList<>(), userDto.getFavoriteDogBreeds());
    }

    @Test
    public void argsSettersTest() {
        List<CatBreedDto> favoriteCatBreeds = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds = new ArrayList<>();

        userDto.setId(2L);
        userDto.setName("Jane Smith");
        userDto.setEmail("jane.smith@example.com");
        userDto.setFavoriteCatBreeds(favoriteCatBreeds);
        userDto.setFavoriteDogBreeds(favoriteDogBreeds);

        assertEquals(2L, userDto.getId());
        assertEquals("Jane Smith", userDto.getName());
        assertEquals("jane.smith@example.com", userDto.getEmail());
        assertEquals(favoriteCatBreeds, userDto.getFavoriteCatBreeds());
        assertEquals(favoriteDogBreeds, userDto.getFavoriteDogBreeds());
    }

    @Test
    public void equalsAndHashCodeTest() {
        List<CatBreedDto> favoriteCatBreeds = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds = new ArrayList<>();

        UserDto userDto1 = new UserDto(1L, "John Doe", "john.doe@example.com", favoriteDogBreeds, favoriteCatBreeds,1L);
        UserDto userDto2 = new UserDto(1L, "John Doe", "john.doe@example.com", favoriteDogBreeds, favoriteCatBreeds,1L);

        assertEquals(userDto1, userDto2);
        assertEquals(userDto1.hashCode(), userDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        List<CatBreedDto> favoriteCatBreeds1 = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds1 = new ArrayList<>();
        List<CatBreedDto> favoriteCatBreeds2 = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds2 = new ArrayList<>();

        UserDto userDto1 = new UserDto(1L, "John Doe", "john.doe@example.com", favoriteDogBreeds1, favoriteCatBreeds1,1L);
        UserDto userDto2 = new UserDto(2L, "Jane Smith", "jane.smith@example.com", favoriteDogBreeds2, favoriteCatBreeds2,1L);

        assertEquals(userDto1.hashCode(), userDto1.hashCode());
        assertNotEquals(userDto1, userDto2);
        assertNotEquals(userDto1.hashCode(), userDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("UserDto(id=1, name=John Doe, email=john.doe@example.com, favoriteDogBreeds=[], favoriteCatBreeds=[], version=null)", userDto.toString());
    }
}
