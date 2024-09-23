package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoDtoTest {
    private VideoDto videoDto;

    @BeforeEach
    void setUp() {
        videoDto = new VideoDto(1L, "http://example.com/video.mp4", "Cute Cat Video", null, null, LocalDate.now(), LocalDate.now());
    }

    @Test
    public void testGetters() {
        assertEquals(1L, videoDto.getId());
        assertEquals("http://example.com/video.mp4", videoDto.getUrl());
        assertEquals("Cute Cat Video", videoDto.getTitle());
        assertEquals(LocalDate.now(), videoDto.getCreateDate());
        assertEquals(LocalDate.now(), videoDto.getLastUpdate());
    }

    @Test
    public void testSetters() {
        videoDto.setId(2L);
        videoDto.setUrl("http://example.com/new_video.mp4");
        videoDto.setTitle("New Cute Cat Video");

        assertEquals(2L, videoDto.getId());
        assertEquals("http://example.com/new_video.mp4", videoDto.getUrl());
        assertEquals("New Cute Cat Video", videoDto.getTitle());
    }

    @Test
    public void testNoArgsConstructor() {
        VideoDto newVideoDto = new VideoDto();
        assertEquals(null, newVideoDto.getId());
        assertEquals(null, newVideoDto.getUrl());
        assertEquals(null, newVideoDto.getTitle());
        assertEquals(null, newVideoDto.getCreateDate());
        assertEquals(null, newVideoDto.getLastUpdate());
    }

    @Test
    public void testAllArgsConstructor() {
        LocalDate now = LocalDate.now();
        VideoDto anotherVideoDto = new VideoDto(1L, "http://example.com/video.mp4", "Cute Cat Video", null, null, now, now);
        assertEquals(1L, anotherVideoDto.getId());
        assertEquals("http://example.com/video.mp4", anotherVideoDto.getUrl());
        assertEquals("Cute Cat Video", anotherVideoDto.getTitle());
    }
}
