package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();

    ReviewDto getReviewById(Long id);

    ReviewDto createReview(ReviewDto reviewDto);

    ReviewDto updateReview(ReviewDto reviewDto);

    void deleteReview(Long id);

    Page<ReviewDto> getAllReviews(Pageable pageable);

    ReviewDto addReviewToUser(Long userId, Long reviewId);
}