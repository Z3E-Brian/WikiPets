package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Review;
import org.una.programmingIII.WikiPets.Repository.ReviewRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReviewServiceImplementationTest {

    @InjectMocks
    private ReviewServiceImplementation reviewServiceImplementation;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private GenericMapper<Review, ReviewDto> reviewMapper;

    @Mock
    GenericMapperFactory mapperFactory;

    private Review review;
    private ReviewDto reviewDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        review = new Review();
        review.setId(1L);
        review.setComment("Great!");
        review.setRating(5);
        review.setCreateDate(LocalDate.now());
        review.setLastUpdate(LocalDate.now());

        reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setComment("Great!");
        reviewDto.setRating(5);
        when(mapperFactory.createMapper(Review.class, ReviewDto.class)).thenReturn(reviewMapper);
        reviewServiceImplementation = new ReviewServiceImplementation(reviewRepository, mapperFactory);
    }


    @Test
    void testGetReviewById_Success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewMapper.convertToDTO(review)).thenReturn(reviewDto);

        ReviewDto result = reviewServiceImplementation.getReviewById(1L);

        assertNotNull(result);
        assertEquals(reviewDto.getComment(), result.getComment());
    }

    @Test
    void testGetReviewById_NotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            reviewServiceImplementation.getReviewById(1L);
        });

        assertEquals("Review Not Found with id: 1", exception.getMessage());
    }

    @Test
    void testCreateReview_Success() {
        when(reviewMapper.convertToEntity(reviewDto)).thenReturn(review);
        when(reviewRepository.save(any())).thenReturn(review);
        when(reviewMapper.convertToDTO(review)).thenReturn(reviewDto);

        ReviewDto result = reviewServiceImplementation.createReview(reviewDto);

        assertNotNull(result);
        assertEquals(reviewDto.getComment(), result.getComment());
    }

    @Test
    void testCreateReview_BlankComment() {
        reviewDto.setComment("");

        BlankInputException exception = assertThrows(BlankInputException.class, () -> {
            reviewServiceImplementation.createReview(reviewDto);
        });

        assertEquals("Comment cannot be empty", exception.getMessage());
    }

    @Test
    void testCreateReview_InvalidRating() {
        reviewDto.setRating(11);

        BlankInputException exception = assertThrows(BlankInputException.class, () -> {
            reviewServiceImplementation.createReview(reviewDto);
        });

        assertEquals("Review only accept 1-10 rating", exception.getMessage());
    }

    @Test
    void testDeleteReview_Success() {
        when(reviewRepository.existsById(1L)).thenReturn(true);

        boolean result = reviewServiceImplementation.deleteReview(1L);

        assertTrue(result);
        verify(reviewRepository).deleteById(1L);
    }

    @Test
    void testDeleteReview_NotFound() {
        when(reviewRepository.existsById(1L)).thenReturn(false);

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            reviewServiceImplementation.deleteReview(1L);
        });

        assertEquals("Review not found with id: 1", exception.getMessage());
    }

    @Test
    void testUpdateReview_Success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewMapper.convertToEntity(reviewDto)).thenReturn(review);
        when(reviewRepository.save(any())).thenReturn(review);
        when(reviewMapper.convertToDTO(review)).thenReturn(reviewDto);

        ReviewDto result = reviewServiceImplementation.updateReview(reviewDto);

        assertNotNull(result);
        assertEquals(reviewDto.getComment(), result.getComment());
    }

    @Test
    void testUpdateReview_BlankComment() {
        reviewDto.setComment("");

        BlankInputException exception = assertThrows(BlankInputException.class, () -> {
            reviewServiceImplementation.updateReview(reviewDto);
        });

        assertEquals("Comment cannot be empty", exception.getMessage());
    }

    @Test
    void testUpdateReview_NotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundElementException exception = assertThrows(NotFoundElementException.class, () -> {
            reviewServiceImplementation.updateReview(reviewDto);
        });

        assertEquals("Review with id 1 not found", exception.getMessage());
    }

    @Test
    void testGetReviews() {

        List<Review> reviewList = List.of(review);
        Page<Review> reviewPage = new PageImpl<>(reviewList);

        when(reviewRepository.findAll(any(PageRequest.class))).thenReturn(reviewPage);

        Map<String, Object> result = reviewServiceImplementation.getReviews(0, 2);

        assertNotNull(result);
        assertTrue(result.containsKey("reviews"));
        assertTrue(result.containsKey("totalPages"));
        assertTrue(result.containsKey("totalElements"));

        assertEquals(1, ((List<?>) result.get("reviews")).size());
        assertEquals(reviewPage.getTotalPages(), result.get("totalPages"));
        assertEquals(reviewPage.getTotalElements(), result.get("totalElements"));

        verify(reviewRepository, times(1)).findAll(any(PageRequest.class));
    }

}
