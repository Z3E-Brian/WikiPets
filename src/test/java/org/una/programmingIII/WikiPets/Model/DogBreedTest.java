//package org.una.programmingIII.WikiPets.Model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class DogBreedTest {
//    DogBreed dogBreed;
//    @BeforeEach
//    void setUp() {
//        dogBreed = new DogBreed();
//        dogBreed.setId(1L);
//        dogBreed.setName("Golden Retriever");
//        dogBreed.setOrigin("Scotland");
//        dogBreed.setSize(3);
//        dogBreed.setCoat("Long");
//        dogBreed.setColor("Golden");
//        dogBreed.setLifeSpan("10-12 years");
//        dogBreed.setTemperament("Intelligent, Friendly, Devoted");
//        dogBreed.setDescription("Popular house dog");
//        dogBreed.setCreatedDate(LocalDate.ofEpochDay(22-10-2021));
//        dogBreed.setModifiedDate(LocalDate.ofEpochDay(22-10-2021));}
//
//    @Test
//    public void argsGettersTest() {
//        assertEquals(1L, dogBreed.getId());
//        assertEquals("Golden Retriever", dogBreed.getName());
//        assertEquals("Scotland", dogBreed.getOrigin());
//        assertEquals(3, dogBreed.getSize());
//        assertEquals("Long", dogBreed.getCoat());
//        assertEquals("Golden", dogBreed.getColor());
//        assertEquals("10-12 years", dogBreed.getLifeSpan());
//        assertEquals("Intelligent, Friendly, Devoted", dogBreed.getTemperament());
//        assertEquals("Popular house dog", dogBreed.getDescription());
//    }
//
//    @Test
//    public void argsSettersTest() {
//        dogBreed.setId(2L);
//        dogBreed.setName("Labrador Retriever");
//        dogBreed.setOrigin("Canada");
//        dogBreed.setSize(2);
//        dogBreed.setCoat("Short");
//        dogBreed.setColor("Black");
//        dogBreed.setLifeSpan("10-12 years");
//        dogBreed.setTemperament("Intelligent, Friendly, Devoted");
//        dogBreed.setDescription("Popular house dog");
//
//        assertEquals(2L, dogBreed.getId());
//        assertEquals("Labrador Retriever", dogBreed.getName());
//        assertEquals("Canada", dogBreed.getOrigin());
//        assertEquals(2, dogBreed.getSize());
//        assertEquals("Short", dogBreed.getCoat());
//        assertEquals("Black", dogBreed.getColor());
//        assertEquals("10-12 years", dogBreed.getLifeSpan());
//        assertEquals("Intelligent, Friendly, Devoted", dogBreed.getTemperament());
//        assertEquals("Popular house dog", dogBreed.getDescription());
//    }
//}
