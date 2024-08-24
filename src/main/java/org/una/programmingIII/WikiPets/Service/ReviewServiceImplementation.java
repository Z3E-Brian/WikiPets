package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.ReviewMapper;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = ReviewMapper.INSTANCE;
    }
    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review Not Found with id: " + id));
        return convertToDto(review);
    }
    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = convertToEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return convertToDto(savedReview);
    }
    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    @Override
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
