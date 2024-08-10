package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long id;
    private CatBreedDto catBreedDto; // DTO para CatBreed, puede ser null si la reseña es para un perro
    private DogBreedDto dogBreedDto; // DTO para DogBreed, puede ser null si la reseña es para un gato
    private UserDto userDto;
    private Integer rating;
    private String comment;
    private Long version;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.catBreedDto = new CatBreedDto(review.getCatBreed());
        this.dogBreedDto = new DogBreedDto(review.getDogBreed());
        this.userDto = new UserDto(review.getUser());
        this.rating = review.getRating();
        this.comment = review.getComment();
    }
}
