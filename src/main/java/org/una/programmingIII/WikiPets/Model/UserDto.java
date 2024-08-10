package org.una.programmingIII.WikiPets.Model;

import java.util.ArrayList;
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

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.favoriteDogBreeds = new ArrayList<>();
        this.favoriteCatBreeds = new ArrayList<>();
        this.version = user.getVersion();
    }

    public List<CatBreed> getFavoriteCatBreedsEntity() {
        List<CatBreed> favoriteCatBreeds = new ArrayList<>();
        for (CatBreedDto catBreedDto : this.favoriteCatBreeds) {
            favoriteCatBreeds.add(new CatBreed(catBreedDto));
        }
        return favoriteCatBreeds;
    }

    public List<DogBreed> getFavoriteDogBreedsEntity() {
        List<DogBreed> favoriteDogBreeds = new ArrayList<>();
        for (DogBreedDto dogBreedDto : this.favoriteDogBreeds) {
            favoriteDogBreeds.add(new DogBreed(dogBreedDto));
        }
        return favoriteDogBreeds;
    }

}