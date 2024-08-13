package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70, nullable = false)
    private String name;

    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_cat_breeds",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id")
    )
    private List<CatBreed> favoriteCatBreeds;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_dog_breeds",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id")
    )
    private List<DogBreed> favoriteDogBreeds;

    @Version
    @Column(name = "USER_VERSION")
    private Long version;

//
//    public User(UserDto userDto) {
//        this.id = userDto.getId();
//        update(userDto);
//    }
//
//    public void update(UserDto userDto) {
//        this.name = userDto.getName();
//        this.email = userDto.getEmail();
//        this.favoriteCatBreeds = new ArrayList<>();
//        this.favoriteDogBreeds = new ArrayList<>();
//        this.version = userDto.getVersion();
//    }
//
//    public List<CatBreedDto> getFavoriteCatBreedsDto() {
//        List<CatBreedDto> favoriteCatBreedsDto = new ArrayList<>();
//        for (CatBreed catBreed : favoriteCatBreeds) {
//            favoriteCatBreedsDto.add(catBreedMapper.toCatBreedDto(catBreed));
//        }
//        return favoriteCatBreedsDto;
//    }
//
//    public List<DogBreedDto> getFavoriteDogBreedsDto() {
//        List<DogBreedDto> favoriteDogBreedsDto = new ArrayList<>();
//        for (DogBreed dogBreed : favoriteDogBreeds) {
//            favoriteDogBreedsDto.add(new DogBreedDto(dogBreed));
//        }
//        return favoriteDogBreedsDto;
//    }

}
