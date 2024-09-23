package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingGuideTest {

    private TrainingGuide trainingGuide;
    private CatBreed catBreed;
    private DogBreed dogBreed;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");

        dogBreed = new DogBreed();
        dogBreed.setId(1L);
        dogBreed.setName("German Shepherd");

        List<CatBreed> catBreeds = new ArrayList<>();
        catBreeds.add(catBreed);

        List<DogBreed> dogBreeds = new ArrayList<>();
        dogBreeds.add(dogBreed);

        trainingGuide = new TrainingGuide();
        trainingGuide.setId(1L);
        trainingGuide.setTitle("Basic Training");
        trainingGuide.setContent("Train your pet with basic commands.");
        trainingGuide.setCatBreeds(catBreeds);
        trainingGuide.setDogBreeds(dogBreeds);
        trainingGuide.setCreateDate(nowDate);
        trainingGuide.setLastUpdate(nowDate);
    }

    @Test
    public void gettersTest() {
        assertEquals(1L, trainingGuide.getId());
        assertEquals("Basic Training", trainingGuide.getTitle());
        assertEquals("Train your pet with basic commands.", trainingGuide.getContent());
    }

    @Test
    public void settersTest() {
        trainingGuide.setTitle("Advanced Training");
        trainingGuide.setContent("Advanced commands for your pet.");

        assertEquals("Advanced Training", trainingGuide.getTitle());
        assertEquals("Advanced commands for your pet.", trainingGuide.getContent());
    }

    @Test
    public void equalsAndHashCodeTest() {
        TrainingGuide trainingGuide1 = new TrainingGuide(1L, "Basic Training", "Train your pet with basic commands.", trainingGuide.getCatBreeds(), trainingGuide.getDogBreeds(), LocalDate.now(), LocalDate.now());
        TrainingGuide trainingGuide2 = new TrainingGuide(1L, "Basic Training", "Train your pet with basic commands.", trainingGuide.getCatBreeds(), trainingGuide.getDogBreeds(), LocalDate.now(), LocalDate.now());

        assertEquals(trainingGuide1, trainingGuide2);
        assertEquals(trainingGuide1.hashCode(), trainingGuide2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        TrainingGuide trainingGuide1 = new TrainingGuide();
        trainingGuide1.setId(1L);
        TrainingGuide trainingGuide2 = new TrainingGuide();
        trainingGuide2.setId(2L);

        assertNotEquals(trainingGuide1, trainingGuide2);
        assertNotEquals(trainingGuide1.hashCode(), trainingGuide2.hashCode());
    }

    @Test
    public void toStringTest() {
        String result = trainingGuide.toString();
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("title=Basic Training"));
        assertTrue(result.contains("content=Train your pet with basic commands."));
        assertTrue(result.contains("createDate=" + trainingGuide.getCreateDate()));
        assertTrue(result.contains("lastUpdate=" + trainingGuide.getLastUpdate()));
    }

}
