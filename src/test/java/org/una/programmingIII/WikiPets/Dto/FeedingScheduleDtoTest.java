//package org.una.programmingIII.WikiPets.Dto;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class FeedingScheduleDtoTest {
//    FeedingScheduleDto feedingScheduleDto;
//
//    @BeforeEach
//    void setUp() {
//        LocalDate nowDate = LocalDate.now();
//
//        feedingScheduleDto = new FeedingScheduleDto();
//        feedingScheduleDto.setId(1L);
//        feedingScheduleDto.setCatBreedDto(new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate));
//        feedingScheduleDto.setAgeGroup("Kitten");
//        feedingScheduleDto.setFeedingTimes("Three times a day");
//        feedingScheduleDto.setCreateDate(nowDate);
//        feedingScheduleDto.setLastUpdate(nowDate);
//    }
//
//    @Test
//    public void argsGettersTest() {
//        assertEquals(1L, feedingScheduleDto.getId());
//        assertEquals("Siamese", feedingScheduleDto.getCatBreedDto().getName());
//        assertEquals("Kitten", feedingScheduleDto.getAgeGroup());
//        assertEquals("Three times a day", feedingScheduleDto.getFeedingTimes());
//    }
//
//@Test
//public void argsSettersTest() {
//    LocalDate nowDate = LocalDate.now();
//
//    feedingScheduleDto.setId(2L);
//    feedingScheduleDto.setDogBreedDto(new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active, Outgoing", "One of the most popular breeds in the world.", nowDate, nowDate,null,null,null,null,null,null,null,null,null,null,null,null));
//    feedingScheduleDto.setAgeGroup("Puppy");
//    feedingScheduleDto.setFeedingTimes("Twice a day");
//
//    assertEquals(2L, feedingScheduleDto.getId());
//    assertEquals("Labrador", feedingScheduleDto.getDogBreedDto().getName());
//    assertEquals("Puppy", feedingScheduleDto.getAgeGroup());
//    assertEquals("Twice a day", feedingScheduleDto.getFeedingTimes());
//}
//    @Test
//    public void equalsAndHashCodeTest() {
//        LocalDate nowDate = LocalDate.now();
//
//        FeedingScheduleDto feedingScheduleDto1 = new FeedingScheduleDto(1L, new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, "Kitten", "Three times a day", nowDate, nowDate);
//        FeedingScheduleDto feedingScheduleDto2 = new FeedingScheduleDto(1L, new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, "Kitten", "Three times a day", nowDate, nowDate);
//
//        assertEquals(feedingScheduleDto1, feedingScheduleDto2);
//        assertEquals(feedingScheduleDto1.hashCode(), feedingScheduleDto2.hashCode());
//    }
//
//    @Test
//    public void notEqualsAndHashCodeTest() {
//        LocalDate nowDate = LocalDate.now();
//
//        FeedingScheduleDto feedingScheduleDto1 = new FeedingScheduleDto(1L, new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", nowDate, nowDate), null, "Kitten", "Three times a day", nowDate, nowDate);
//        FeedingScheduleDto feedingScheduleDto2 = new FeedingScheduleDto(2L, null, new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active, Outgoing", "One of the most popular breeds in the world.", nowDate, nowDate,null,null,null,null,null,null,null,null,null,null,null,null), "Puppy", "Twice a day", nowDate, nowDate);
//
//        assertNotEquals(feedingScheduleDto1, feedingScheduleDto2);
//        assertNotEquals(feedingScheduleDto1.hashCode(), feedingScheduleDto2.hashCode());
//    }
//
//    @Test
//    public void toStringTest() {
//        LocalDate nowDate = LocalDate.now();
//
//        assertEquals("FeedingScheduleDto(id=1, catBreedDto=CatBreedDto(id=1, " +
//                "name=Siamese, origin=Thailand, size=2, coat=Short, color=Cream with points, " +
//                "lifeSpan=12-16 years, temperament=Affectionate, Social, Vocal, " +
//                "description=Popular breed known for its striking appearance and vocal nature., createdDate=" + nowDate + ", modifiedDate=" + nowDate + "), " +
//                "dogBreedDto=null, ageGroup=Kitten, feedingTimes=Three times a day, createDate=" + nowDate + ", lastUpdate=" + nowDate + ")",
//                feedingScheduleDto.toString());
//    }
//}
