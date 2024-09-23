//package org.una.programmingIII.WikiPets.Model;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.time.LocalDate;
//
//public class VideoTest {
//    private Video video;
//    private DogBreed dogBreed;
//    private CatBreed catBreed;
//
//    @BeforeEach
//    public void setUp() {
//        dogBreed = new DogBreed();
//        catBreed = new CatBreed();
//        video = new Video();
//        video.setId(1L);
//        video.setUrl("http://example.com/video.jpg");
//        video.setTitle("Sample Image");
//        video.setDogBreed(dogBreed);
//        video.setCatBreed(catBreed);
//        video.setCreateDate(LocalDate.now());
//        video.setLastUpdate(LocalDate.now());
//    }
//
//    @Test
//    public void testVideoCreation() {
//        assertNotNull(video);
//    }
//
//    @Test
//    public void testGetters() {
//        assertEquals(1L, video.getId());
//        assertEquals("http://example.com/video.jpg", video.getUrl());
//        assertEquals("Sample Image", video.getTitle());
//        assertEquals(dogBreed, video.getDogBreed());
//        assertEquals(catBreed, video.getCatBreed());
//        assertEquals(LocalDate.now(), video.getCreateDate());
//        assertEquals(LocalDate.now(), video.getLastUpdate());
//    }
//
//    @Test
//    public void testSetters() {
//        DogBreed newDogBreed = new DogBreed();
//        CatBreed newCatBreed = new CatBreed();
//        LocalDate newDate = LocalDate.of(2022, 1, 1);
//
//        video.setUrl("http://example.com/new_video.jpg");
//        video.setTitle("New Video");
//        video.setDogBreed(newDogBreed);
//        video.setCatBreed(newCatBreed);
//        video.setCreateDate(newDate);
//        video.setLastUpdate(newDate);
//
//        assertEquals("http://example.com/new_video.jpg", video.getUrl());
//        assertEquals("New Video", video.getTitle());
//        assertEquals(newDogBreed, video.getDogBreed());
//        assertEquals(newCatBreed, video.getCatBreed());
//        assertEquals(newDate, video.getCreateDate());
//        assertEquals(newDate, video.getLastUpdate());
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        Video img = new Video();
//        assertNotNull(img);
//    }
//}