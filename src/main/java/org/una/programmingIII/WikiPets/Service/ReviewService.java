package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Review;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface ReviewService {
    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long id);
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto updateReview(ReviewDto reviewDto);
    void deleteReview(Long id);
}