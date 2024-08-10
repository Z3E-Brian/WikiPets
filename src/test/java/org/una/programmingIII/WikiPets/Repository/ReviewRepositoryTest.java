package org.una.programmingIII.WikiPets.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Model.Review;
import org.una.programmingIII.WikiPets.Model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewRepositoryTest {

    @Mock
    private ReviewRepository reviewRepository;

    private Review review;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        review = new Review();
        review.setId(1L);
        review.setRating(5);
        review.setComment("Great breed!");
        review.setUser(user);
    }

    @Test
    public void findByUserEmailTest() {
        when(reviewRepository.findByUserEmail("john.doe@example.com")).thenReturn(review);
        Review result = reviewRepository.findByUserEmail("john.doe@example.com");

        assertEquals(1L, result.getId());
        assertEquals(5, result.getRating());
        assertEquals("Great breed!", result.getComment());
        assertEquals(user, result.getUser());
    }

    @Test
    public void findListByUserEmailTest() {
        when(reviewRepository.findListByUserEmail("john.doe@example.com")).thenReturn(List.of(review));
        List<Review> result = reviewRepository.findListByUserEmail("john.doe@example.com");

        assertEquals(1, result.size());
        assertEquals(review, result.get(0));
    }

    @Test
    public void findByRatingTest() {
        when(reviewRepository.findByRating(5)).thenReturn(List.of(review));
        List<Review> result = reviewRepository.findByRating(5);

        assertEquals(1, result.size());
        assertEquals(review, result.get(0));
    }

    @Test
    public void findByCatBreedIdTest() {
        when(reviewRepository.findByCatBreedId(1L)).thenReturn(List.of(review));
        List<Review> result = reviewRepository.findByCatBreedId(1L);

        assertEquals(1, result.size());
        assertEquals(review, result.get(0));
    }

    @Test
    public void findByDogBreedIdTest() {
        when(reviewRepository.findByDogBreedId(1L)).thenReturn(List.of(review));
        List<Review> result = reviewRepository.findByDogBreedId(1L);

        assertEquals(1, result.size());
        assertEquals(review, result.get(0));
    }
}
