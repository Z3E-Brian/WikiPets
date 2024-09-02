package org.una.programmingIII.WikiPets.Dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private List<DogBreedDto> favoriteDogBreedsDto;
    private List<CatBreedDto> favoriteCatBreedsDto;
    private LocalDate createDate;
    private LocalDate lastUpdate;
    private List<ReviewDto> reviewsDto;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
        this.favoriteDogBreedsDto = null;
        this.favoriteCatBreedsDto = null;
        this.createDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
        this.reviewsDto = null;
    }
}