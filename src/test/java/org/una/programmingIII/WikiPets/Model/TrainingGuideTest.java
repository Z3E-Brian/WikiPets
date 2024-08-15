package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TrainingGuideTest {
    TrainingGuide trainingGuide;

    @BeforeEach
    void setUp() {
        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", LocalDate.now(), LocalDate.now()));

        List<DogBreed> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreed(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active", "Well-known for being friendly and good with children.", LocalDate.now(), LocalDate.now()));

        trainingGuide = new TrainingGuide();
        trainingGuide.setId(1L);
        trainingGuide.setTitle("Basic Training Guide for Pets");
        trainingGuide.setContent("This is a basic training guide for both cats and dogs.");
        trainingGuide.setCatBreeds(catBreeds);
        trainingGuide.setDogBreeds(dogBreeds);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, trainingGuide.getId());
        assertEquals("Basic Training Guide for Pets", trainingGuide.getTitle());
        assertEquals("This is a basic training guide for both cats and dogs.", trainingGuide.getContent());
        assertEquals(1, trainingGuide.getCatBreeds().size());
        assertEquals(1, trainingGuide.getDogBreeds().size());
    }

    @Test
    public void argsSettersTest() {
        trainingGuide.setId(2L);
        trainingGuide.setTitle("Advanced Training Guide for Cats");
        trainingGuide.setContent("This is an advanced training guide specifically for cats.");
        trainingGuide.setCatBreeds(new ArrayList<>()); // Empty list for cats
        trainingGuide.setDogBreeds(null); // No dogs

        assertEquals(2L, trainingGuide.getId());
        assertEquals("Advanced Training Guide for Cats", trainingGuide.getTitle());
        assertEquals("This is an advanced training guide specifically for cats.", trainingGuide.getContent());
        assertEquals(0, trainingGuide.getCatBreeds().size());
        assertEquals(null, trainingGuide.getDogBreeds());
    }

    @Test
    public void equalsAndHashCodeTest() {
        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", LocalDate.now(), LocalDate.now()));

        List<DogBreed> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreed(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active", "Well-known for being friendly and good with children.", LocalDate.now(), LocalDate.now()));

        TrainingGuide trainingGuide1 = new TrainingGuide(1L, "Basic Training Guide for Pets", "This is a basic training guide for both cats and dogs.", catBreeds, dogBreeds, LocalDate.now(), LocalDate.now());
        TrainingGuide trainingGuide2 = new TrainingGuide(1L, "Basic Training Guide for Pets", "This is a basic training guide for both cats and dogs.", catBreeds, dogBreeds, LocalDate.now(), LocalDate.now());

        assertEquals(trainingGuide1, trainingGuide2);
        assertEquals(trainingGuide1.hashCode(), trainingGuide2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreed(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", LocalDate.now(), LocalDate.now()));

        TrainingGuide trainingGuide1 = new TrainingGuide(1L, "Basic Training Guide for Pets", "This is a basic training guide for both cats and dogs.", catBreeds, new ArrayList<>(), LocalDate.now(), LocalDate.now());
        TrainingGuide trainingGuide2 = new TrainingGuide(2L, "Advanced Training Guide for Dogs", "This is an advanced training guide specifically for dogs.", new ArrayList<>(), null, LocalDate.now(), LocalDate.now());

        assertEquals(trainingGuide1.hashCode(), trainingGuide1.hashCode());
        assertNotEquals(trainingGuide1, trainingGuide2);
        assertNotEquals(trainingGuide1.hashCode(), trainingGuide2.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("TrainingGuide(id=1, " +
                        "title=Basic Training Guide for Pets, " +
                        "content=This is a basic training guide for both cats and dogs.," +
                        " catBreeds=[CatBreed(id=1, name=Siamese, origin=Thailand, size=2, " +
                        "coat=Short, color=Cream with points, lifeSpan=12-16 years, temperament=Affectionate," +
                        " Social, Vocal, description=Popular breed known for its striking appearance and vocal nature.," +
                        " createdDate=2024-08-15, modifiedDate=2024-08-15)], " +
                        "dogBreeds=[DogBreed(id=1, name=Labrador, origin=Canada, size=3, coat=Short, color=Yellow, " +
                        "lifeSpan=10-12 years, temperament=Friendly, " +
                        "Active, description=Well-known for being friendly and good with children., " +
                        "createdDate=2024-08-15, modifiedDate=2024-08-15)], createDate=null, lastUpdate=null)"
                , trainingGuide.toString());
    }
}
