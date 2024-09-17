package org.una.programmingIII.WikiPets.Dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"favoriteDogBreeds", "favoriteCatBreeds","reviews"})
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private List<DogBreedDto> favoriteDogBreeds;
    private List<CatBreedDto> favoriteCatBreeds;
    private LocalDate createDate;
    private LocalDate lastUpdate;
    private List<ReviewDto> reviews;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
        this.favoriteDogBreeds = null;
        this.favoriteCatBreeds = null;
        this.createDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
        this.reviews = null;
    }
}