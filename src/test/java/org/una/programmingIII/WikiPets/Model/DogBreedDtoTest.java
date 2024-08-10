package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DogBreedDtoTest {
    DogBreedDto dogBreedDto;
    @BeforeEach
    void setUp() {
        dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Golden Retriever");
        dogBreedDto.setOrigin("Scotland");
        dogBreedDto.setSize(3);
        dogBreedDto.setCoat("Long");
        dogBreedDto.setColor("Golden");
        dogBreedDto.setLifeSpan("10-12 years");
        dogBreedDto.setTemperament("Intelligent, Friendly, Devoted");
        dogBreedDto.setDescription("Popular house dog");}

    @Test
    public void argsGettersTest() {
        assertEquals(1L, dogBreedDto.getId());
        assertEquals("Golden Retriever", dogBreedDto.getName());
        assertEquals("Scotland", dogBreedDto.getOrigin());
        assertEquals(3, dogBreedDto.getSize());
        assertEquals("Long", dogBreedDto.getCoat());
        assertEquals("Golden", dogBreedDto.getColor());
        assertEquals("10-12 years", dogBreedDto.getLifeSpan());
        assertEquals("Intelligent, Friendly, Devoted", dogBreedDto.getTemperament());
        assertEquals("Popular house dog", dogBreedDto.getDescription());
    }

    @Test
    public void argsSettersTest() {
        dogBreedDto.setId(2L);
        dogBreedDto.setName("Labrador Retriever");
        dogBreedDto.setOrigin("Canada");
        dogBreedDto.setSize(2);
        dogBreedDto.setCoat("Short");
        dogBreedDto.setColor("Black");
        dogBreedDto.setLifeSpan("10-12 years");
        dogBreedDto.setTemperament("Intelligent, Friendly, Devoted");
        dogBreedDto.setDescription("Popular house dog");

        assertEquals(2L, dogBreedDto.getId());
        assertEquals("Labrador Retriever", dogBreedDto.getName());
        assertEquals("Canada", dogBreedDto.getOrigin());
        assertEquals(2, dogBreedDto.getSize());
        assertEquals("Short", dogBreedDto.getCoat());
        assertEquals("Black", dogBreedDto.getColor());
        assertEquals("10-12 years", dogBreedDto.getLifeSpan());
        assertEquals("Intelligent, Friendly, Devoted", dogBreedDto.getTemperament());
        assertEquals("Popular house dog", dogBreedDto.getDescription());
    }

    @Test
    public void equalsAndHashCodeTest() {
        DogBreedDto dogBreedDto1 = new DogBreedDto(1L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Intelligent, Friendly, Devoted", "Popular house dog");
        DogBreedDto dogBreedDto2 = new DogBreedDto(1L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Intelligent, Friendly, Devoted", "Popular house dog");

        assertEquals(dogBreedDto1, dogBreedDto2);
        assertEquals(dogBreedDto1.hashCode(), dogBreedDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        DogBreedDto dogBreedDto1 = new DogBreedDto(1L, "Golden Retriever", "Scotland", 3, "Long", "Golden", "10-12 years", "Intelligent, Friendly, Devoted", "Popular house dog");
        DogBreedDto dogBreedDto2 = new DogBreedDto(2L, "Labrador Retriever", "Canada", 2, "Short", "Black", "10-12 years", "Intelligent, Friendly, Devoted", "Popular house dog");

        assertEquals(dogBreedDto1.hashCode(), dogBreedDto1.hashCode());
        assertNotEquals(dogBreedDto1, dogBreedDto2);
        assertNotEquals(dogBreedDto1.hashCode(), dogBreedDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("DogBreedDto(id=1, name=Golden Retriever, origin=Scotland, size=3, coat=Long, color=Golden, lifeSpan=10-12 years, temperament=Intelligent, Friendly, Devoted, description=Popular house dog)", dogBreedDto.toString());
    }
}
