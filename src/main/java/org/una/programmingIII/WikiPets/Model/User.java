package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(length = 100, nullable = false)
    private String password;

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

    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDate createDate;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Review> reviews;
}