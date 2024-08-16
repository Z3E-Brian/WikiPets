package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Mapper.ReviewMapper;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private ReviewDto reviewDto;
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

        reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setRating(5);
        reviewDto.setComment("Great breed!");
        reviewDto.setUserDto(ReviewMapper.INSTANCE.toReviewDto(review).getUserDto());
    }

    @Test
    public void getAllReviewsTest() {
        when(reviewRepository.findAll()).thenReturn(List.of(review));
        List<ReviewDto> result = reviewService.getAllReviews();

        assertEquals(1, result.size());
        assertEquals(reviewDto.getId(), result.get(0).getId());
        assertTrue(result.get(0).getComment().contains("Great breed!"));
    }

    @Test
    public void getReviewByIdTest() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        ReviewDto result = reviewService.getReviewById(1L);

        assertEquals(reviewDto.getId(), result.getId());
        assertEquals(reviewDto.getComment(), result.getComment());
    }

    @Test
    public void createReviewTest() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        ReviewDto result = reviewService.createReview(reviewDto);

        assertEquals(reviewDto.getId(), result.getId());
        assertEquals(reviewDto.getRating(), result.getRating());
    }

    @Test
    public void deleteReviewTest() {
        doNothing().when(reviewRepository).deleteById(1L);
        reviewService.deleteReview(1L);

        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateReviewTest() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        ReviewDto result = reviewService.updateReview(reviewDto);

        assertEquals(reviewDto.getId(), result.getId());
        assertEquals(reviewDto.getComment(), result.getComment());
    }
}
