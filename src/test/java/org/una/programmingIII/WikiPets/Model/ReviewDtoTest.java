//package org.una.programmingIII.WikiPets.Model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class ReviewDtoTest {
//    ReviewDto reviewDto;
//
//    @BeforeEach
//    void setUp() {
////            CatBreedDto catBreedDto = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Seal Point", "15 years", "Active, Vocal", "Elegant and sleek");
////            DogBreedDto dogBreedDto = new DogBreedDto(2L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Friendly, Reliable", "Popular house dog");
////            UserDto userDto = new UserDto(1L, "John Doe", "john.doe@example.com", null, null,1L);
////            reviewDto = new ReviewDto(1L, catBreedDto, dogBreedDto, userDto, 5, "Great breed!", 1L);
//    }
//
//    @Test
//    public void argsGettersTest() {
////            assertEquals(1L, reviewDto.getId());
////            assertEquals(5, reviewDto.getRating());
////            assertEquals("Great breed!", reviewDto.getComment());
////            assertEquals(1L, reviewDto.getCatBreedDto().getId());
////            assertEquals(2L, reviewDto.getDogBreedDto().getId());
////            assertEquals(1L, reviewDto.getUserDto().getId());
//    }
//
//    @Test
//    public void argsSettersTest() {
//        CatBreedDto newCatBreedDto = new CatBreedDto(2L, "Maine Coon", "USA", 4, "Long", "Brown Tabby", "12-15 years", "Friendly, Affectionate", "Large and lovable");
//        DogBreedDto newDogBreedDto = new DogBreedDto(3L, "Bulldog", "UK", 2, "Short", "Brindle", "8-10 years", "Courageous, Friendly", "Loyal and dependable");
//        UserDto newUserDto = new UserDto(2L, "Jane Smith", "jane.smith@example.com", null, null, 1L);
//
//        reviewDto.setId(2L);
//        reviewDto.setRating(4);
//        reviewDto.setComment("Good breed, but has some issues");
//        reviewDto.setCatBreedDto(newCatBreedDto);
//        reviewDto.setDogBreedDto(newDogBreedDto);
//        reviewDto.setUserDto(newUserDto);
//
//        assertEquals(2L, reviewDto.getId());
//        assertEquals(4, reviewDto.getRating());
//        assertEquals("Good breed, but has some issues", reviewDto.getComment());
//        assertEquals(2L, reviewDto.getCatBreedDto().getId());
//        assertEquals(3L, reviewDto.getDogBreedDto().getId());
//        assertEquals(2L, reviewDto.getUserDto().getId());
//    }
//
//    @Test
//    public void equalsAndHashCodeTest() {
//        CatBreedDto catBreedDto = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Seal Point", "15 years", "Active, Vocal", "Elegant and sleek");
//        DogBreedDto dogBreedDto = new DogBreedDto(2L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Friendly, Reliable", "Popular house dog");
//        UserDto userDto = new UserDto(1L, "John Doe", "john.doe@example.com", null, null, 1L);
//
//        ReviewDto reviewDto1 = new ReviewDto(1L, catBreedDto, dogBreedDto, userDto, 5, "Great breed!", 1L);
//        ReviewDto reviewDto2 = new ReviewDto(1L, catBreedDto, dogBreedDto, userDto, 5, "Great breed!", 1L);
//
//        assertEquals(reviewDto1, reviewDto2);
//        assertEquals(reviewDto1.hashCode(), reviewDto2.hashCode());
//    }
//
//    @Test
//    public void notEqualsAndHashCodeTest() {
//        CatBreedDto catBreedDto1 = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Seal Point", "15 years", "Active, Vocal", "Elegant and sleek");
//        DogBreedDto dogBreedDto1 = new DogBreedDto(2L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Friendly, Reliable", "Popular house dog");
//        UserDto userDto1 = new UserDto(1L, "John Doe", "john.doe@example.com", null, null, 1L);
//
//        CatBreedDto catBreedDto2 = new CatBreedDto(2L, "Maine Coon", "USA", 4, "Long", "Brown Tabby", "12-15 years", "Friendly, Affectionate", "Large and lovable");
//        DogBreedDto dogBreedDto2 = new DogBreedDto(3L, "Bulldog", "UK", 2, "Short", "Brindle", "8-10 years", "Courageous, Friendly", "Loyal and dependable");
//        UserDto userDto2 = new UserDto(2L, "Jane Smith", "jane.smith@example.com", null, null, 1L);
//
//        ReviewDto reviewDto1 = new ReviewDto(1L, catBreedDto1, dogBreedDto1, userDto1, 5, "Great breed!", 1L);
//        ReviewDto reviewDto2 = new ReviewDto(2L, catBreedDto2, dogBreedDto2, userDto2, 3, "Not as expected", 2L);
//
//        assertNotEquals(reviewDto1, reviewDto2);
//        assertNotEquals(reviewDto1.hashCode(), reviewDto2.hashCode());
//    }
//
//    @Test
//    public void toStringTest() {
//        assertEquals("ReviewDto(id=1, catBreedDto=CatBreedDto(id=1, " +
//                "name=Siamese, origin=Thailand, size=2, coat=Short, color=Seal Point, " +
//                "lifeSpan=15 years, temperament=Active, Vocal, description=Elegant and sleek), " +
//                "dogBreedDto=DogBreedDto(id=2, name=Golden Retriever, origin=Scotland, size=3, " +
//                "coat=Long, color=Golden, lifeSpan=10-12 years, temperament=Friendly, Reliable, " +
//                "description=Popular house dog), userDto=UserDto(id=1, name=John Doe, " +
//                "email=john.doe@example.com, favoriteDogBreeds=null, favoriteCatBreeds=null, version=1), " +
//                "rating=5, comment=Great breed!, version=1)", reviewDto.toString());
//    }
//}
