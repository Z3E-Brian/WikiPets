package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReviewDtoTest {

    private ReviewDto reviewDto;
    private CatBreedDto catBreedDto;
    private DogBreedDto dogBreedDto;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        // Configuración de catBreedDto
        catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Siamese");

        // Configuración de dogBreedDto
        dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Labrador");

        // Configuración de userDto
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("john_doe");

        // Configuración de reviewDto
        reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setCatBreed(catBreedDto);
        reviewDto.setDogBreed(dogBreedDto);
        reviewDto.setUser(userDto);
        reviewDto.setRating(5);
        reviewDto.setComment("Great breed!");
        reviewDto.setCreateDate(nowDate);
        reviewDto.setLastUpdate(nowDate);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, reviewDto.getId());
        assertEquals("Siamese", reviewDto.getCatBreed().getName());
        assertEquals("Labrador", reviewDto.getDogBreed().getName());
        assertEquals("john_doe", reviewDto.getUser().getName());
        assertEquals(5, reviewDto.getRating());
        assertEquals("Great breed!", reviewDto.getComment());
    }

    @Test
    public void argsSettersTest() {
        LocalDate nowDate = LocalDate.now();

        CatBreedDto newCatBreed = new CatBreedDto();
        newCatBreed.setId(2L);
        newCatBreed.setName("Persian");

        DogBreedDto newDogBreed = new DogBreedDto();
        newDogBreed.setId(2L);
        newDogBreed.setName("German Shepherd");

        UserDto newUser = new UserDto();
        newUser.setId(2L);
        newUser.setName("jane_doe");

        reviewDto.setId(2L);
        reviewDto.setCatBreed(newCatBreed);
        reviewDto.setDogBreed(newDogBreed);
        reviewDto.setUser(newUser);
        reviewDto.setRating(4);
        reviewDto.setComment("Lovely breed!");
        reviewDto.setCreateDate(nowDate);
        reviewDto.setLastUpdate(nowDate);

        assertEquals(2L, reviewDto.getId());
        assertEquals("Persian", reviewDto.getCatBreed().getName());
        assertEquals("German Shepherd", reviewDto.getDogBreed().getName());
        assertEquals("jane_doe", reviewDto.getUser().getName());
        assertEquals(4, reviewDto.getRating());
        assertEquals("Lovely breed!", reviewDto.getComment());
    }

    @Test
    public void equalsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        ReviewDto reviewDto1 = new ReviewDto(1L, catBreedDto, dogBreedDto, userDto, 5, "Great breed!", nowDate, nowDate);
        ReviewDto reviewDto2 = new ReviewDto(1L, catBreedDto, dogBreedDto, userDto, 5, "Great breed!", nowDate, nowDate);

        assertEquals(reviewDto1, reviewDto2);
        assertEquals(reviewDto1.hashCode(), reviewDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        ReviewDto reviewDto1 = new ReviewDto(1L, catBreedDto, dogBreedDto, userDto, 5, "Great breed!", nowDate, nowDate);
        ReviewDto reviewDto2 = new ReviewDto(2L, null, null, userDto, 4, "Lovely breed!", nowDate, nowDate);

        assertNotEquals(reviewDto1, reviewDto2);
        assertNotEquals(reviewDto1.hashCode(), reviewDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate nowDate = LocalDate.now();

        String expected = "ReviewDto(id=1, user=" + userDto + ", rating=5, comment=Great breed!, createDate=" + nowDate + ", lastUpdate=" + nowDate + ")";
        assertEquals(expected, reviewDto.toString());
    }
}
