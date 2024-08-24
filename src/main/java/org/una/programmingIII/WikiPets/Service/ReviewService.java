package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Mapper.ReviewMapper;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = ReviewMapper.INSTANCE;
    }

    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review Not Found with id: " + id));
        return convertToDto(review);
    }

    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = convertToEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return convertToDto(savedReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public ReviewDto updateReview(ReviewDto reviewDto) {
        Review review = convertToEntity(reviewDto);
        Review updatedReview = reviewRepository.save(review);
        return convertToDto(updatedReview);
    }

    private ReviewDto convertToDto(Review review) {
        return ReviewMapper.INSTANCE.toReviewDto(review);
    }

    private Review convertToEntity(ReviewDto reviewDto) {
        return ReviewMapper.INSTANCE.toReview(reviewDto);
    }
}
