package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.ReviewDto;
import org.una.programmingIII.WikiPets.Dto.UserDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.ReviewInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.ReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final GenericMapper<ReviewInput, ReviewDto> reviewDtoGenericMapper;

    @Autowired
    ReviewController(ReviewService reviewService, GenericMapperFactory mapperFactory) {
        this.reviewService = reviewService;
        this.reviewDtoGenericMapper = mapperFactory.createMapper(ReviewInput.class, ReviewDto.class);
    }

    @QueryMapping
    public Map<String, Object> getReviews(@Argument int page, @Argument int size) {
        try {
            return reviewService.getReviews(page, size);
        } catch (Exception e) {
            throw new CustomException("Could not retrieve reviews" + e.getMessage());
        }
    }

    @QueryMapping
    public ReviewDto getReviewById(@Argument Long id) {
        try {
            return reviewService.getReviewById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public ReviewDto createReview(@Argument ReviewInput input) {
        try {
            ReviewDto reviewDto = convertToDto(input);
            return reviewService.createReview(reviewDto);
        } catch (Exception e) {
            throw new CustomException("Could not create a review" + e.getMessage());
        }
    }

    @MutationMapping
    public ReviewDto updateReview(@Argument ReviewInput input) {
        try {
            ReviewDto reviewDto = convertToDto(input);
            return reviewService.updateReview(reviewDto);
        } catch (Exception e) {
            throw new CustomException("Could not update the review" + e.getMessage());
        }
    }

    @MutationMapping
    public void deleteReview(@Argument Long id) {
        try {
            reviewService.deleteReview(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete the review");
        }
    }

    private ReviewDto convertToDto(ReviewInput reviewInput) {
        return reviewDtoGenericMapper.convertToDTO(reviewInput);
    }


}
