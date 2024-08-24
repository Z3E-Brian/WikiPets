package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final GenericMapper<Review, ReviewDto> reviewMapper;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository, GenericMapperFactory mapperFactory) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = mapperFactory.createMapper(Review.class, ReviewDto.class);
    }

    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(this.reviewMapper::convertToDTO).collect(Collectors.toList());
    }

    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review Not Found with id: " + id));
        return reviewMapper.convertToDTO(review);
    }

    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.convertToEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.convertToDTO(savedReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public ReviewDto updateReview(ReviewDto reviewDto) {
        Review review = reviewMapper.convertToEntity(reviewDto);
        Review updatedReview = reviewRepository.save(review);
        return reviewMapper.convertToDTO(updatedReview);
    }
}