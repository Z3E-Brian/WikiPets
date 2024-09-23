package org.una.programmingIII.WikiPets.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Controller.ReviewController;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.ReviewInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Service.ReviewService;

import java.util.HashMap;
import java.util.Map;

public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @Mock
    private GenericMapper<ReviewInput, ReviewDto> reviewMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetReviews() {
        // Arrange
        int page = 1;
        int size = 10;
        Map<String, Object> expectedReviews = new HashMap<>();
        when(reviewService.getReviews(page, size)).thenReturn(expectedReviews);

        // Act
        Map<String, Object> result = reviewController.getReviews(page, size);

        // Assert
        assertEquals(expectedReviews, result);
        verify(reviewService).getReviews(page, size);
    }

    @Test
    void testGetReviewsException() {
        int page = 1;
        int size = 10;
        when(reviewService.getReviews(page, size)).thenThrow(new NotFoundElementException("Error"));

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            reviewController.getReviews(page, size);
        });
        assertEquals("Could not retrieve reviewsError", exception.getMessage());
    }

    @Test
    void testGetReviewById() {
        Long id = 1L;
        ReviewDto expectedReview = new ReviewDto();
        when(reviewService.getReviewById(id)).thenReturn(expectedReview);

        ReviewDto result = reviewController.getReviewById(id);

        assertEquals(expectedReview, result);
        verify(reviewService).getReviewById(id);
    }

    @Test
    void testCreateReview() {
        ReviewInput input = new ReviewInput();
        ReviewDto expectedReviewDto = new ReviewDto();
        when(reviewMapper.convertToDTO(input)).thenReturn(expectedReviewDto);
        when(reviewService.createReview(expectedReviewDto)).thenReturn(expectedReviewDto);

        ReviewDto result = reviewController.createReview(input);

        assertEquals(expectedReviewDto, result);
        verify(reviewMapper).convertToDTO(input);
        verify(reviewService).createReview(expectedReviewDto);
    }

    @Test
    void testUpdateReview() {
        ReviewInput input = new ReviewInput();
        ReviewDto expectedReviewDto = new ReviewDto();
        when(reviewMapper.convertToDTO(input)).thenReturn(expectedReviewDto);
        when(reviewService.updateReview(expectedReviewDto)).thenReturn(expectedReviewDto);
      ReviewDto result = reviewController.updateReview(input);

        assertEquals(expectedReviewDto, result);
        verify(reviewMapper).convertToDTO(input);
        verify(reviewService).updateReview(expectedReviewDto);
    }

    @Test
    void testDeleteReview() {
        Long id = 1L;
        when(reviewService.deleteReview(id)).thenReturn(true);

        Boolean result = reviewController.deleteReview(id);

        assertTrue(result);
        verify(reviewService).deleteReview(id);
    }
}
