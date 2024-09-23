package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CatBreedDtoTest {
    CatBreedDto catBreedDto;

    @BeforeEach
    void setUp() {
        catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Siamese");
        catBreedDto.setOrigin("Thailand");
        catBreedDto.setSize(2);
        catBreedDto.setCoat("Short");
        catBreedDto.setColor("Cream with points");
        catBreedDto.setLifeSpan("12-16 years");
        catBreedDto.setTemperament("Affectionate, Social, Vocal");
        catBreedDto.setDescription("Popular breed known for its striking appearance and vocal nature.");
        catBreedDto.setCreatedDate(LocalDate.ofEpochDay(22-10-2021));
        catBreedDto.setModifiedDate(LocalDate.ofEpochDay(22-10-2021));}

    @Test
    public void argsGettersTest() {
        assertEquals(1L, catBreedDto.getId());
        assertEquals("Siamese", catBreedDto.getName());
        assertEquals("Thailand", catBreedDto.getOrigin());
        assertEquals(2, catBreedDto.getSize());
        assertEquals("Short", catBreedDto.getCoat());
        assertEquals("Cream with points", catBreedDto.getColor());
        assertEquals("12-16 years", catBreedDto.getLifeSpan());
        assertEquals("Affectionate, Social, Vocal", catBreedDto.getTemperament());
        assertEquals("Popular breed known for its striking appearance and vocal nature.", catBreedDto.getDescription());
    }

    @Test
    public void argsSettersTest() {
        catBreedDto.setId(2L);
        catBreedDto.setName("Maine Coon");
        catBreedDto.setOrigin("United States");
        catBreedDto.setSize(4);
        catBreedDto.setCoat("Long");
        catBreedDto.setColor("Tabby");
        catBreedDto.setLifeSpan("12-15 years");
        catBreedDto.setTemperament("Gentle, Playful, Friendly");
        catBreedDto.setDescription("Large and friendly breed known for its gentle temperament.");

        assertEquals(2L, catBreedDto.getId());
        assertEquals("Maine Coon", catBreedDto.getName());
        assertEquals("United States", catBreedDto.getOrigin());
        assertEquals(4, catBreedDto.getSize());
        assertEquals("Long", catBreedDto.getCoat());
        assertEquals("Tabby", catBreedDto.getColor());
        assertEquals("12-15 years", catBreedDto.getLifeSpan());
        assertEquals("Gentle, Playful, Friendly", catBreedDto.getTemperament());
        assertEquals("Large and friendly breed known for its gentle temperament.", catBreedDto.getDescription());
    }
}
