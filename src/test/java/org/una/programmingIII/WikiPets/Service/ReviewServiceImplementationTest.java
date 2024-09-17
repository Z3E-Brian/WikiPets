package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Review;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ReviewServiceImplementationTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private GenericMapperFactory mapperFactory;

    @Mock
    private GenericMapper<Review, ReviewDto> reviewMapper;
    @Mock
    private GenericMapper<User, UserDto> userMapper;

    @InjectMocks
    private ReviewServiceImplementation reviewServiceImplementation;

    private Review review;
    private ReviewDto reviewDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

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
        reviewDto.setUser(userMapper.convertToDTO(user));

        when(mapperFactory.createMapper(Review.class, ReviewDto.class)).thenReturn(reviewMapper);
        when(reviewMapper.convertToDTO(review)).thenReturn(reviewDto);
        when(reviewMapper.convertToEntity(reviewDto)).thenReturn(review);
        // reviewServiceImplementation = new ReviewServiceImplementation(reviewRepository, mapperFactory);
    }

    @Test
    public void getAllReviewsTest() {
        when(reviewRepository.findAll()).thenReturn(List.of(review));
        List<ReviewDto> result = reviewServiceImplementation.getAllReviews();
        assertEquals(1, result.size());
        assertEquals(reviewDto.getId(), result.get(0).getId());
        assertTrue(result.get(0).getComment().contains("Great breed!"));
    }

    @Test
    public void getReviewByIdTest() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        ReviewDto result = reviewServiceImplementation.getReviewById(1L);
        assertEquals(reviewDto.getId(), result.getId());
        assertEquals(reviewDto.getComment(), result.getComment());
    }

    @Test
    public void createReviewTest() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        ReviewDto result = reviewServiceImplementation.createReview(reviewDto);
        assertEquals(reviewDto.getId(), result.getId());
        assertEquals(reviewDto.getRating(), result.getRating());
    }

    @Test
    public void deleteReviewTest() {
        doNothing().when(reviewRepository).deleteById(1L);
        reviewServiceImplementation.deleteReview(1L);
        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateReviewTest() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        ReviewDto result = reviewServiceImplementation.updateReview(reviewDto);
        assertEquals(reviewDto.getId(), result.getId());
        assertEquals(reviewDto.getComment(), result.getComment());
    }
}