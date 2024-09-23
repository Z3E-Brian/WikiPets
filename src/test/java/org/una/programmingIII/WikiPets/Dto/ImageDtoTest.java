package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageDtoTest {
    private ImageDto imageDto;

    @BeforeEach
    void setUp() {
        imageDto = new ImageDto(1L, "http://example.com/image.jpg", "A cute cat", null, null, LocalDate.now(), LocalDate.now());
    }

    @Test
    public void testGetters() {
        assertEquals(1L, imageDto.getId());
        assertEquals("http://example.com/image.jpg", imageDto.getUrl());
        assertEquals("A cute cat", imageDto.getDescription());
        assertEquals(LocalDate.now(), imageDto.getCreateDate());
        assertEquals(LocalDate.now(), imageDto.getLastUpdate());
    }

    @Test
    public void testSetters() {
        imageDto.setId(2L);
        imageDto.setUrl("http://example.com/new_image.jpg");
        imageDto.setDescription("A cute dog");

        assertEquals(2L, imageDto.getId());
        assertEquals("http://example.com/new_image.jpg", imageDto.getUrl());
        assertEquals("A cute dog", imageDto.getDescription());
    }

    @Test
    public void testNoArgsConstructor() {
        ImageDto newImageDto = new ImageDto();
        assertEquals(null, newImageDto.getId());
        assertEquals(null, newImageDto.getUrl());
        assertEquals(null, newImageDto.getDescription());
        assertEquals(null, newImageDto.getCreateDate());
        assertEquals(null, newImageDto.getLastUpdate());
    }

    @Test
    public void testAllArgsConstructor() {
        LocalDate now = LocalDate.now();
        ImageDto anotherImageDto = new ImageDto(1L, "http://example.com/image.jpg", "A cute cat", null, null, now, now);
        assertEquals(1L, anotherImageDto.getId());
        assertEquals("http://example.com/image.jpg", anotherImageDto.getUrl());
        assertEquals("A cute cat", anotherImageDto.getDescription());
    }
}
