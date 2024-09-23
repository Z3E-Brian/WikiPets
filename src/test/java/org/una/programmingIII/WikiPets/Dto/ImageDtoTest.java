//package org.una.programmingIII.WikiPets.Dto;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//public class ImageDtoTest {
//
//    private ImageDto imageDto;
//
//    @BeforeEach
//    public void setUp() {
//        imageDto = new ImageDto();
//        imageDto.setId(1L);
//        imageDto.setUrl("http://example.com/image.jpg");
//        imageDto.setDescription("Sample Image");
//        imageDto.setCreateDate(LocalDate.now());
//        imageDto.setLastUpdate(LocalDate.now());
//    }
//
//    @Test
//    public void testImageDtoCreation() {
//        assertNotNull(imageDto);
//    }
//
//    @Test
//    public void testGetters() {
//        assertEquals(1L, imageDto.getId());
//        assertEquals("http://example.com/image.jpg", imageDto.getUrl());
//        assertEquals("Sample Image", imageDto.getDescription());
//        assertTrue(imageDto.getIsDogBreed());
//        assertEquals(101, imageDto.getPetBreedId());
//        assertEquals(LocalDate.now(), imageDto.getCreateDate());
//        assertEquals(LocalDate.now(), imageDto.getLastUpdate());
//    }
//
//    @Test
//    public void testSetters() {
//        LocalDate newDate = LocalDate.of(2022, 1, 1);
//
//        imageDto.setUrl("http://example.com/new_image.jpg");
//        imageDto.setDescription("New Image");
//        imageDto.setIsDogBreed(false);
//        imageDto.setPetBreedId(202);
//        imageDto.setCreateDate(newDate);
//        imageDto.setLastUpdate(newDate);
//
//        assertEquals("http://example.com/new_image.jpg", imageDto.getUrl());
//        assertEquals("New Image", imageDto.getDescription());
//        assertFalse(imageDto.getIsDogBreed());
//        assertEquals(202, imageDto.getPetBreedId());
//        assertEquals(newDate, imageDto.getCreateDate());
//        assertEquals(newDate, imageDto.getLastUpdate());
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        ImageDto dto = new ImageDto();
//        assertNotNull(dto);
//    }
//
//    @Test
//    public void testAllArgsConstructor() {
//        LocalDate date = LocalDate.of(2022, 1, 1);
//        ImageDto dto = new ImageDto(2L, "http://example.com/another_image.jpg", "Another Image", false, 303, date, date);
//
//        assertEquals(2L, dto.getId());
//        assertEquals("http://example.com/another_image.jpg", dto.getUrl());
//        assertEquals("Another Image", dto.getDescription());
//        assertFalse(dto.getIsDogBreed());
//        assertEquals(303, dto.getPetBreedId());
//        assertEquals(date, dto.getCreateDate());
//        assertEquals(date, dto.getLastUpdate());
//    }
//}