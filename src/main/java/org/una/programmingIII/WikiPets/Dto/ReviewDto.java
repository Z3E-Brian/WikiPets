package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"catBreedDto", "dogBreedDto"})
public class ReviewDto {

    private Long id;
    private CatBreedDto catBreedDto; // DTO para CatBreed, puede ser null si la reseña es para un perro
    private DogBreedDto dogBreedDto; // DTO para DogBreed, puede ser null si la reseña es para un gato
    private UserDto userDto;
    private Integer rating;
    private String comment;
    private LocalDate createDate;
    private LocalDate lastUpdate;
}
