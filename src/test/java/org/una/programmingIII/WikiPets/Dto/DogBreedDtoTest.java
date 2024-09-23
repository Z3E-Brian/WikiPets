package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        dogBreedDto.setDescription("Popular house dog");
        dogBreedDto.setCreatedDate(LocalDate.ofEpochDay(22-10-2021));
        dogBreedDto.setModifiedDate(LocalDate.ofEpochDay(22-10-2021));}

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

}
