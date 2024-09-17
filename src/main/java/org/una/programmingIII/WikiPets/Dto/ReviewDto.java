package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"catBreed", "dogBreed"})
public class ReviewDto {

    private Long id;
    private CatBreedDto catBreed;
    private DogBreedDto dogBreed;
    private UserDto user;
    private Integer rating;
    private String comment;
    private LocalDate createDate;
    private LocalDate lastUpdate;
}
