package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FeedingScheduleDtoTest {
    FeedingScheduleDto feedingScheduleDto;
    private DogBreedDto dogBreedDto;
    private CatBreedDto catBreedDto;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        List<CatBreedDto> catBreeds = new ArrayList<>();
        CatBreedDto catBreed = new CatBreedDto();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(5);
        catBreed.setCoat("Short");
        catBreed.setColor("Cream with darker points");
        catBreed.setLifeSpan("12-20 years");
        catBreed.setTemperament("Social, Affectionate");
        catBreed.setDescription("Siamese cats are known for their striking appearance and vocal personality.");
        catBreed.setCreatedDate(LocalDate.now());
        catBreed.setModifiedDate(LocalDate.now());
        catBreeds.add(catBreed);

        List<DogBreedDto> dogBreedDtos = new ArrayList<>();
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Siamese");
        dogBreedDto.setOrigin("Thailand");
        dogBreedDto.setSize(5);
        dogBreedDto.setCoat("Short");
        dogBreedDto.setColor("Cream with darker points");
        dogBreedDto.setLifeSpan("12-20 years");
        dogBreedDto.setTemperament("Social, Affectionate");
        dogBreedDto.setDescription("Siamese cats are known for their striking appearance and vocal personality.");
        dogBreedDto.setCreatedDate(LocalDate.now());
        dogBreedDto.setModifiedDate(LocalDate.now());
        dogBreedDtos.add(dogBreedDto);

        feedingScheduleDto = new FeedingScheduleDto();
        feedingScheduleDto.setId(1L);
        feedingScheduleDto.setCatBreeds(catBreeds);
        feedingScheduleDto.setAgeGroup("Kitten");
        feedingScheduleDto.setFeedingTimes("Three times a day");
        feedingScheduleDto.setCreateDate(nowDate);
        feedingScheduleDto.setLastUpdate(nowDate);

        catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Siamese");
        catBreedDto.setOrigin("Thailand");
        catBreedDto.setSize(5);
        catBreedDto.setCoat("Short");
        catBreedDto.setColor("Cream with darker points");
        catBreedDto.setLifeSpan("12-20 years");
        catBreedDto.setTemperament("Social, Affectionate");
        catBreedDto.setDescription("Siamese cats are known for their striking appearance and vocal personality.");
        catBreedDto.setCreatedDate(LocalDate.now());
        catBreedDto.setModifiedDate(LocalDate.now());
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, feedingScheduleDto.getId());
        assertEquals("Siamese", feedingScheduleDto.getCatBreeds().get(0).getName());
        assertEquals("Kitten", feedingScheduleDto.getAgeGroup());
        assertEquals("Three times a day", feedingScheduleDto.getFeedingTimes());
    }

    @Test
    public void argsSettersTest() {
        LocalDate nowDate = LocalDate.now();

        List<DogBreedDto> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active, Outgoing", "One of the most popular breeds in the world.", nowDate, nowDate, null, null, null, null, null, null, null, null, null, null, null, null));

        feedingScheduleDto.setId(2L);
        feedingScheduleDto.setDogBreeds(dogBreeds);
        feedingScheduleDto.setAgeGroup("Puppy");
        feedingScheduleDto.setFeedingTimes("Twice a day");

        assertEquals(2L, feedingScheduleDto.getId());
        assertEquals("Labrador", feedingScheduleDto.getDogBreeds().get(0).getName());
        assertEquals("Puppy", feedingScheduleDto.getAgeGroup());
        assertEquals("Twice a day", feedingScheduleDto.getFeedingTimes());
    }

    @Test
    public void equalsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        List<CatBreedDto> catBreeds1 = new ArrayList<>();
        catBreeds1.add(catBreedDto);
        FeedingScheduleDto feedingScheduleDto1 = new FeedingScheduleDto(1L, catBreeds1, null, "Kitten", "Three times a day", nowDate, nowDate);
        FeedingScheduleDto feedingScheduleDto2 = new FeedingScheduleDto(1L, catBreeds1, null, "Kitten", "Three times a day", nowDate, nowDate);

        assertEquals(feedingScheduleDto1, feedingScheduleDto2);
        assertEquals(feedingScheduleDto1.hashCode(), feedingScheduleDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        List<CatBreedDto> catBreeds1 = new ArrayList<>();
        catBreeds1.add(catBreedDto);
        List<DogBreedDto> dogBreeds2 = new ArrayList<>();
        dogBreeds2.add(dogBreedDto);
        FeedingScheduleDto feedingScheduleDto1 = new FeedingScheduleDto(1L, catBreeds1, null, "Kitten", "Three times a day", nowDate, nowDate);
        FeedingScheduleDto feedingScheduleDto2 = new FeedingScheduleDto(2L, null, dogBreeds2, "Puppy", "Twice a day", nowDate, nowDate);

        assertNotEquals(feedingScheduleDto1, feedingScheduleDto2);
        assertNotEquals(feedingScheduleDto1.hashCode(), feedingScheduleDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate nowDate = LocalDate.now();

        List<CatBreedDto> catBreeds = new ArrayList<>();


        catBreeds.add(catBreedDto);
        feedingScheduleDto.setCatBreeds(catBreeds);

        String expected = "FeedingScheduleDto(id=1, ageGroup=Kitten, feedingTimes=Three times a day, createDate=" + nowDate + ", lastUpdate=" + nowDate + ")";
        assertEquals(expected, feedingScheduleDto.toString());
    }
}
