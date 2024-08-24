package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long id);
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto updateReview(ReviewDto reviewDto);
    void deleteReview(Long id);
}
