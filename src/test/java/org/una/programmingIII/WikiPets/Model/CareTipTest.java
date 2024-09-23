package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CareTipTest {
    @Test
    void gettersAndSettersTest() {
        List<DogBreed> dogBreeds = new ArrayList<>();
        List<CatBreed> catBreeds = new ArrayList<>();
        CareTip careTip = new CareTip();
        careTip.setId(1L);
        careTip.setTitle("Title");
        careTip.setContent("Content");
        careTip.setRelevantDogBreeds(dogBreeds);
        careTip.setRelevantCatBreeds(catBreeds);
        assertEquals(1L, careTip.getId());
        assertEquals("Title", careTip.getTitle());
        assertEquals("Content", careTip.getContent());
        assertEquals(dogBreeds, careTip.getRelevantDogBreeds());
        assertEquals(catBreeds, careTip.getRelevantCatBreeds());


        careTip.setTitle("New Title");
        assertEquals("New Title", careTip.getTitle());
    }

    @Test
    void relationsTest() {
        DogBreed dogBreed = new DogBreed();
        CatBreed catBreed = new CatBreed();
        List<DogBreed> dogBreeds = new ArrayList<>();
        List<CatBreed> catBreeds = new ArrayList<>();
        dogBreeds.add(dogBreed);
        catBreeds.add(catBreed);

        CareTip careTip = new CareTip();
        careTip.setRelevantDogBreeds(dogBreeds);
        careTip.setRelevantCatBreeds(catBreeds);

        assertEquals(1, careTip.getRelevantDogBreeds().size());
        assertEquals(1, careTip.getRelevantCatBreeds().size());
        assertEquals(dogBreed, careTip.getRelevantDogBreeds().get(0));
        assertEquals(catBreed, careTip.getRelevantCatBreeds().get(0));
    }
}
