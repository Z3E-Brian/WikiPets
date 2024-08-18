package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nutrition_guides")
public class NutritionGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @ManyToMany
    @JoinTable(
            name = "nutrition_guide_dog_breeds",
            joinColumns = @JoinColumn(name = "nutrition_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id")
    )
    private List<DogBreed> recommendedDogBreeds;

    @ManyToMany
    @JoinTable(
            name = "nutrition_guide_cat_breeds",
            joinColumns = @JoinColumn(name = "nutrition_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id")
    )
    private List<CatBreed> recommendedCatBreeds;

    @Column(length = 50, nullable = false)
    private String dietaryRequirements;
}
