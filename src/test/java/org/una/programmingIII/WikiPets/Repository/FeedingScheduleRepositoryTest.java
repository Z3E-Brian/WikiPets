package org.una.programmingIII.WikiPets.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeedingScheduleRepositoryTest {

    @Mock
    private FeedingScheduleRepository feedingScheduleRepository;

    private FeedingSchedule feedingSchedule;
    private CatBreed catBreed;
    private DogBreed dogBreed;

    @BeforeEach
    void setUp() {
        catBreed = new CatBreed();
        catBreed.setId(1L);

        dogBreed = new DogBreed();
        dogBreed.setId(1L);

        feedingSchedule = new FeedingSchedule();
        feedingSchedule.setId(1L);
        feedingSchedule.setCatBreed(catBreed);
        feedingSchedule.setDogBreed(dogBreed);
        feedingSchedule.setAgeGroup("Kitten");
        feedingSchedule.setFeedingTimes("Twice a day");
        feedingSchedule.setCreateDate(LocalDate.now());
        feedingSchedule.setLastUpdate(LocalDate.now());
    }

    @Test
    public void findByCatBreedIdTest() {
        when(feedingScheduleRepository.findByCatBreedId(1L)).thenReturn(Arrays.asList(feedingSchedule));

        List<FeedingSchedule> result = feedingScheduleRepository.findByCatBreedId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void findByDogBreedIdTest() {
        when(feedingScheduleRepository.findByDogBreedId(1L)).thenReturn(Arrays.asList(feedingSchedule));

        List<FeedingSchedule> result = feedingScheduleRepository.findByDogBreedId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void findByAgeGroupTest() {
        when(feedingScheduleRepository.findByAgeGroup("Kitten")).thenReturn(Arrays.asList(feedingSchedule));

        List<FeedingSchedule> result = feedingScheduleRepository.findByAgeGroup("Kitten");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Kitten", result.get(0).getAgeGroup());
    }

    @Test
    public void findByFeedingTimesContainingTest() {
        when(feedingScheduleRepository.findByFeedingTimesContaining("Twice")).thenReturn(Arrays.asList(feedingSchedule));

        List<FeedingSchedule> result = feedingScheduleRepository.findByFeedingTimesContaining("Twice");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getFeedingTimes().contains("Twice"));
    }

    @Test
    public void existsByIdTest() {
        when(feedingScheduleRepository.existsById(1L)).thenReturn(true);

        boolean exists = feedingScheduleRepository.existsById(1L);

        assertTrue(exists);
    }
}
