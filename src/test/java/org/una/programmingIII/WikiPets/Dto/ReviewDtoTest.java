package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReviewDtoTest {
    ReviewDto reviewDto;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setCatBreedDto(new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate));
        reviewDto.setUserDto(new UserDto(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate,null));
        reviewDto.setRating(5);
        reviewDto.setComment("Excellent breed!");
        reviewDto.setCreateDate(nowDate);
        reviewDto.setLastUpdate(nowDate);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, reviewDto.getId());
        assertEquals("Siamese", reviewDto.getCatBreedDto().getName());
        assertEquals(5, reviewDto.getRating());
        assertEquals("Excellent breed!", reviewDto.getComment());
    }

    @Test
    public void argsSettersTest() {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        reviewDto.setId(2L);
        reviewDto.setDogBreedDto(new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active, Outgoing", "One of the most popular breeds in the world.", nowDate, nowDate,null,null,null,null,null,null,null,null,null,null,null,null));
        reviewDto.setRating(4);
        reviewDto.setComment("Great dog breed!");

        assertEquals(2L, reviewDto.getId());
        assertEquals("Labrador", reviewDto.getDogBreedDto().getName());
        assertEquals(4, reviewDto.getRating());
        assertEquals("Great dog breed!", reviewDto.getComment());
    }

    @Test
    public void equalsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        ReviewDto reviewDto1 = new ReviewDto(1L, new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, new UserDto(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate,null), 5, "Excellent breed!", nowDate, nowDate);
        ReviewDto reviewDto2 = new ReviewDto(1L, new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, new UserDto(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate,null), 5, "Excellent breed!", nowDate, nowDate);

        assertEquals(reviewDto1, reviewDto2);
        assertEquals(reviewDto1.hashCode(), reviewDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        ReviewDto reviewDto1 = new ReviewDto(1L, new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, new UserDto(1L, "John Doe", "john.doe@example.com", null, null, nowDate, nowDate,null), 5, "Excellent breed!", nowDate, nowDate);
        ReviewDto reviewDto2 = new ReviewDto(2L, null, new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active, Outgoing", "One of the most popular breeds in the world.", nowDate, nowDate,null,null,null,null,null,null,null,null,null,null,null,null), new UserDto(2L, "Jane Doe", "jane.doe@example.com", null, null, nowDate, nowDate,null), 4, "Great dog breed!", nowDate, nowDate);

        assertNotEquals(reviewDto1, reviewDto2);
        assertNotEquals(reviewDto1.hashCode(), reviewDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate nowDate = LocalDate.now();

        assertEquals("ReviewDto(id=1, catBreedDto=CatBreedDto(id=1, name=Siamese, origin=Thailand, size=2, coat=Short, color=Cream with points, lifeSpan=12-16 years, temperament=Affectionate, Social, Vocal, description=Popular breed known for its striking appearance and vocal nature., createdDate=" + nowDate + ", modifiedDate=" + nowDate + "), dogBreedDto=null, userDto=UserDto(id=1, name=John Doe, email=john.doe@example.com, favoriteDogBreedsDto=null, favoriteCatBreedsDto=null, createDate=" + nowDate + ", lastUpdate=" + nowDate + ", reviewsDto=null), rating=5, comment=Excellent breed!, createDate=" + nowDate + ", lastUpdate=" + nowDate + ")", reviewDto.toString());
    }
}
