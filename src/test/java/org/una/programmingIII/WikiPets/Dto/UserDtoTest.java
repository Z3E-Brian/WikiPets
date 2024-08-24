package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
        LocalDate now = LocalDate.now();

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setFavoriteDogBreedsDto(favoriteDogBreeds);
        userDto.setFavoriteCatBreedsDto(favoriteCatBreeds);
        userDto.setCreateDate(now);
        userDto.setLastUpdate(now);
    }

    @Test
    public void argsGettersTest() {
        List<CatBreedDto> emptyCatBreeds = new ArrayList<>();
        List<DogBreedDto> emptyDogBreeds = new ArrayList<>();
        LocalDate now = userDto.getCreateDate(); // Use the same timestamp for consistency

        assertEquals(1L, userDto.getId());
        assertEquals("John Doe", userDto.getName());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals(emptyCatBreeds, userDto.getFavoriteCatBreedsDto());
        assertEquals(emptyDogBreeds, userDto.getFavoriteDogBreedsDto());
        assertEquals(now, userDto.getCreateDate());
        assertEquals(now, userDto.getLastUpdate());
    }

    @Test
    public void argsSettersTest() {
        List<CatBreedDto> favoriteCatBreeds = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate later = now.plusDays(1);

        userDto.setId(2L);
        userDto.setName("Jane Smith");
        userDto.setEmail("jane.smith@example.com");
        userDto.setFavoriteCatBreedsDto(favoriteCatBreeds);
        userDto.setFavoriteDogBreedsDto(favoriteDogBreeds);
        userDto.setCreateDate(now);
        userDto.setLastUpdate(later);

        assertEquals(2L, userDto.getId());
        assertEquals("Jane Smith", userDto.getName());
        assertEquals("jane.smith@example.com", userDto.getEmail());
        assertEquals(favoriteCatBreeds, userDto.getFavoriteCatBreedsDto());
        assertEquals(favoriteDogBreeds, userDto.getFavoriteDogBreedsDto());
        assertEquals(now, userDto.getCreateDate());
        assertEquals(later, userDto.getLastUpdate());
    }

    @Test
    public void equalsAndHashCodeTest() {
        List<CatBreedDto> favoriteCatBreeds = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds = new ArrayList<>();
        LocalDate now = LocalDate.now();

        UserDto userDto1 = new UserDto(1L, "John Doe", "john.doe@example.com", favoriteDogBreeds, favoriteCatBreeds, now, now);
        UserDto userDto2 = new UserDto(1L, "John Doe", "john.doe@example.com", favoriteDogBreeds, favoriteCatBreeds, now, now);

        assertEquals(userDto1, userDto2);
        assertEquals(userDto1.hashCode(), userDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        List<CatBreedDto> favoriteCatBreeds1 = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds1 = new ArrayList<>();
        List<CatBreedDto> favoriteCatBreeds2 = new ArrayList<>();
        List<DogBreedDto> favoriteDogBreeds2 = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate later = now.plusDays(1);

        UserDto userDto1 = new UserDto(1L, "John Doe", "john.doe@example.com", favoriteDogBreeds1, favoriteCatBreeds1, now, now);
        UserDto userDto2 = new UserDto(2L, "Jane Smith", "jane.smith@example.com", favoriteDogBreeds2, favoriteCatBreeds2, later, later);

        assertNotEquals(userDto1, userDto2);
        assertNotEquals(userDto1.hashCode(), userDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate now = userDto.getCreateDate();
        String expectedString = String.format(
                "UserDto(id=1, name=John Doe, email=john.doe@example.com, favoriteDogBreedsDto=[], favoriteCatBreedsDto=[], createDate=%s, lastUpdate=%s)",
                now, now
        );

        assertEquals(expectedString, userDto.toString());
    }
}
