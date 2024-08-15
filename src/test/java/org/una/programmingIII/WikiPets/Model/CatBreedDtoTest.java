package org.una.programmingIII.WikiPets.Model;

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

    @Test
    public void equalsAndHashCodeTest() {
        CatBreedDto catBreedDto1 = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.",LocalDate.ofEpochDay(22-10-2021),LocalDate.ofEpochDay(22-10-2021));
        CatBreedDto catBreedDto2 = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.",LocalDate.ofEpochDay(22-10-2021),LocalDate.ofEpochDay(22-10-2021));

        assertEquals(catBreedDto1, catBreedDto2);
        assertEquals(catBreedDto1.hashCode(), catBreedDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        CatBreedDto catBreedDto1 = new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.",LocalDate.ofEpochDay(22-10-2021),LocalDate.ofEpochDay(22-10-2021));
        CatBreedDto catBreedDto2 = new CatBreedDto(2L, "Maine Coon", "United States", 4, "Long", "Tabby", "12-15 years", "Gentle, Playful, Friendly", "Large and friendly breed known for its gentle temperament.", LocalDate.ofEpochDay(22-10-2021),LocalDate.ofEpochDay(22-10-2021));

        assertEquals(catBreedDto1.hashCode(), catBreedDto1.hashCode());
        assertNotEquals(catBreedDto1, catBreedDto2);
        assertNotEquals(catBreedDto1.hashCode(), catBreedDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("CatBreedDto(id=1, name=Siamese, origin=Thailand, size=2, coat=Short, color=Cream with points, lifeSpan=12-16 years, temperament=Affectionate, Social, Vocal, description=Popular breed known for its striking appearance and vocal nature., createdDate=1964-07-02, modifiedDate=1964-07-02)", catBreedDto.toString());
    }
}
