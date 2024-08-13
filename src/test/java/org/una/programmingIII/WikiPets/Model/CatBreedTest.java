package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CatBreedTest {
    CatBreed catBreed;

    @BeforeEach
    void setUp() {
        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);
        catBreed.setCoat("Short");
        catBreed.setColor("Cream with points");
        catBreed.setLifeSpan("12-16 years");
        catBreed.setTemperament("Affectionate, Social, Vocal");
        catBreed.setDescription("Popular breed known for its striking appearance and vocal nature.");
        catBreed.setCreatedDate(LocalDate.ofEpochDay(22-10-2021));
        catBreed.setModifiedDate(LocalDate.ofEpochDay(22-10-2021));
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, catBreed.getId());
        assertEquals("Siamese", catBreed.getName());
        assertEquals("Thailand", catBreed.getOrigin());
        assertEquals(2, catBreed.getSize());
        assertEquals("Short", catBreed.getCoat());
        assertEquals("Cream with points", catBreed.getColor());
        assertEquals("12-16 years", catBreed.getLifeSpan());
        assertEquals("Affectionate, Social, Vocal", catBreed.getTemperament());
        assertEquals("Popular breed known for its striking appearance and vocal nature.", catBreed.getDescription());
    }

    @Test
    public void argsSettersTest() {
        catBreed.setId(2L);
        catBreed.setName("Maine Coon");
        catBreed.setOrigin("United States");
        catBreed.setSize(4);
        catBreed.setCoat("Long");
        catBreed.setColor("Tabby");
        catBreed.setLifeSpan("12-15 years");
        catBreed.setTemperament("Gentle, Playful, Friendly");
        catBreed.setDescription("Large and friendly breed known for its gentle temperament.");

        assertEquals(2L, catBreed.getId());
        assertEquals("Maine Coon", catBreed.getName());
        assertEquals("United States", catBreed.getOrigin());
        assertEquals(4, catBreed.getSize());
        assertEquals("Long", catBreed.getCoat());
        assertEquals("Tabby", catBreed.getColor());
        assertEquals("12-15 years", catBreed.getLifeSpan());
        assertEquals("Gentle, Playful, Friendly", catBreed.getTemperament());
        assertEquals("Large and friendly breed known for its gentle temperament.", catBreed.getDescription());
    }

    @Test
    public void equalsAndHashCodeTest() {
        CatBreed catBreed1 = new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.",LocalDate.ofEpochDay(22-10-2021),LocalDate.ofEpochDay(22-10-2021));
        CatBreed catBreed2 = new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.",LocalDate.ofEpochDay(22-10-2021),LocalDate.ofEpochDay(22-10-2021));

        assertEquals(catBreed1, catBreed2);
        assertEquals(catBreed1.hashCode(), catBreed2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        CatBreed catBreed1 = new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.",LocalDate.ofEpochDay(22-10-2021),LocalDate.ofEpochDay(22-10-2021));
        CatBreed catBreed2 = new CatBreed(2L, "Maine Coon", "United States", 4, "Long", "Tabby", "12-15 years", "Gentle, Playful, Friendly", "Large and friendly breed known for its gentle temperament.",LocalDate.ofEpochDay(22-10-2021),LocalDate.ofEpochDay(22-10-2021));

        assertEquals(catBreed1.hashCode(), catBreed1.hashCode());
        assertNotEquals(catBreed1, catBreed2);
        assertNotEquals(catBreed1.hashCode(), catBreed2.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("CatBreed(id=1, name=Siamese, origin=Thailand, size=2, coat=Short, color=Cream with points, lifeSpan=12-16 years, temperament=Affectionate, Social, Vocal, description=Popular breed known for its striking appearance and vocal nature.)", catBreed.toString());
    }
}