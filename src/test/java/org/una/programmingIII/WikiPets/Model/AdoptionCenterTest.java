package org.una.programmingIII.WikiPets.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdoptionCenterTest {

    private AdoptionCenter adoptionCenter;
    private List<DogBreed> dogBreeds;
    private List<CatBreed> catBreeds;

    @BeforeEach
    public void setUp() {
        dogBreeds = new ArrayList<>();
        catBreeds = new ArrayList<>();
        adoptionCenter = new AdoptionCenter();
        adoptionCenter.setId(1L);
        adoptionCenter.setName("Happy Tails Adoption Center");
        adoptionCenter.setLocation("123 Pet Lane");
        adoptionCenter.setAvailableDogBreeds(dogBreeds);
        adoptionCenter.setAvailableCatBreeds(catBreeds);
        adoptionCenter.setCreateDate(LocalDate.now());
        adoptionCenter.setLastUpdate(LocalDate.now());
    }

    @Test
    public void testAdoptionCenterCreation() {
        assertNotNull(adoptionCenter);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, adoptionCenter.getId());
        assertEquals("Happy Tails Adoption Center", adoptionCenter.getName());
        assertEquals("123 Pet Lane", adoptionCenter.getLocation());
        assertEquals(dogBreeds, adoptionCenter.getAvailableDogBreeds());
        assertEquals(catBreeds, adoptionCenter.getAvailableCatBreeds());
        assertEquals(LocalDate.now(), adoptionCenter.getCreateDate());
        assertEquals(LocalDate.now(), adoptionCenter.getLastUpdate());
    }

    @Test
    public void testSetters() {
        List<DogBreed> newDogBreeds = new ArrayList<>();
        List<CatBreed> newCatBreeds = new ArrayList<>();
        LocalDate newDate = LocalDate.of(2022, 1, 1);

        adoptionCenter.setName("New Name");
        adoptionCenter.setLocation("456 New Lane");
        adoptionCenter.setAvailableDogBreeds(newDogBreeds);
        adoptionCenter.setAvailableCatBreeds(newCatBreeds);
        adoptionCenter.setCreateDate(newDate);
        adoptionCenter.setLastUpdate(newDate);

        assertEquals("New Name", adoptionCenter.getName());
        assertEquals("456 New Lane", adoptionCenter.getLocation());
        assertEquals(newDogBreeds, adoptionCenter.getAvailableDogBreeds());
        assertEquals(newCatBreeds, adoptionCenter.getAvailableCatBreeds());
        assertEquals(newDate, adoptionCenter.getCreateDate());
        assertEquals(newDate, adoptionCenter.getLastUpdate());
    }

    @Test
    public void testNoArgsConstructor() {
        AdoptionCenter center = new AdoptionCenter();
        assertNotNull(center);
    }

    @Test
    public void testAllArgsConstructor() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        List<DogBreed> newDogBreeds = new ArrayList<>();
        List<CatBreed> newCatBreeds = new ArrayList<>();
        AdoptionCenter center = new AdoptionCenter(2L, "Another Center", "789 Another Lane", newDogBreeds, newCatBreeds, date, date);

        assertEquals(2L, center.getId());
        assertEquals("Another Center", center.getName());
        assertEquals("789 Another Lane", center.getLocation());
        assertEquals(newDogBreeds, center.getAvailableDogBreeds());
        assertEquals(newCatBreeds, center.getAvailableCatBreeds());
        assertEquals(date, center.getCreateDate());
        assertEquals(date, center.getLastUpdate());
    }
}