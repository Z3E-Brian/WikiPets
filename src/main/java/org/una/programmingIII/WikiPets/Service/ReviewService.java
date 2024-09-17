package org.una.programmingIII.WikiPets.Service;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;

import java.util.Map;

public interface ReviewService {
    Map<String, Object> getReviews(int page, int size);

    ReviewDto getReviewById(Long id);

    ReviewDto createReview(ReviewDto reviewDto);

    ReviewDto updateReview(ReviewDto reviewDto);

    void deleteReview(Long id);

}