package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Repository.ReviewRepository;

import java.time.LocalDate;
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

    @Override
    public ReviewDto createReview(@NotNull ReviewDto reviewDto) {
        reviewDto.setLastUpdate(LocalDate.now());
        reviewDto.setCreateDate(LocalDate.now());
        Review review = reviewMapper.convertToEntity(reviewDto);
        return reviewMapper.convertToDTO(reviewRepository.save(review));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Page<ReviewDto> getAllReviews(Pageable pageable) {
        Page<Review> review = reviewRepository.findAll(pageable);
        return review.map(reviewMapper::convertToDTO);
    }

    public ReviewDto updateReview(ReviewDto reviewDto) {
        Review review = reviewMapper.convertToEntity(reviewDto);
        Review updatedReview = reviewRepository.save(review);
        return reviewMapper.convertToDTO(updatedReview);
    }
}