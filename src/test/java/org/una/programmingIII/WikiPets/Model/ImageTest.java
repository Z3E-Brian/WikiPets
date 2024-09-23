//package org.una.programmingIII.WikiPets.Model;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.time.LocalDate;
//
//public class ImageTest {
//
//    private Image image;
//    private DogBreed dogBreed;
//    private CatBreed catBreed;
//
//    @BeforeEach
//    public void setUp() {
//        dogBreed = new DogBreed();
//        catBreed = new CatBreed();
//        image = new Image();
//        image.setId(1L);
//        image.setUrl("http://example.com/image.jpg");
//        image.setDescription("Sample Image");
//        image.setDogBreed(dogBreed);
//        image.setCatBreed(catBreed);
//        image.setCreateDate(LocalDate.now());
//        image.setLastUpdate(LocalDate.now());
//    }
//
//    @Test
//    public void testImageCreation() {
//        assertNotNull(image);
//    }
//
//    @Test
//    public void testGetters() {
//        assertEquals(1L, image.getId());
//        assertEquals("http://example.com/image.jpg", image.getUrl());
//        assertEquals("Sample Image", image.getDescription());
//        assertEquals(dogBreed, image.getDogBreed());
//        assertEquals(catBreed, image.getCatBreed());
//        assertEquals(LocalDate.now(), image.getCreateDate());
//        assertEquals(LocalDate.now(), image.getLastUpdate());
//    }
//
//    @Test
//    public void testSetters() {
//        DogBreed newDogBreed = new DogBreed();
//        CatBreed newCatBreed = new CatBreed();
//        LocalDate newDate = LocalDate.of(2022, 1, 1);
//
//        image.setUrl("http://example.com/new_image.jpg");
//        image.setDescription("New Image");
//        image.setDogBreed(newDogBreed);
//        image.setCatBreed(newCatBreed);
//        image.setCreateDate(newDate);
//        image.setLastUpdate(newDate);
//
//        assertEquals("http://example.com/new_image.jpg", image.getUrl());
//        assertEquals("New Image", image.getDescription());
//        assertEquals(newDogBreed, image.getDogBreed());
//        assertEquals(newCatBreed, image.getCatBreed());
//        assertEquals(newDate, image.getCreateDate());
//        assertEquals(newDate, image.getLastUpdate());
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        Image img = new Image();
//        assertNotNull(img);
//    }
//
//}