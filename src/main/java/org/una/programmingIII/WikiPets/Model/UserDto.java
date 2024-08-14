package org.una.programmingIII.WikiPets.Model;

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
    private List<DogBreedDto> favoriteDogBreeds;
    private List<CatBreedDto> favoriteCatBreeds;
    private Long version;
}