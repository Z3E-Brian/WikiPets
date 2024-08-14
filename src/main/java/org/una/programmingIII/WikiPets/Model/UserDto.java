package org.una.programmingIII.WikiPets.Model;

import java.time.LocalDateTime;
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
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
}