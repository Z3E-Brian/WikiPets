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
@Table(name = "care_tips")
public class CareTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @ManyToMany
    @JoinTable(
            name = "care_tip_dog_breeds",
            joinColumns = @JoinColumn(name = "care_tip_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id")
    )
    private List<DogBreed> relevantDogBreeds;

    @ManyToMany
    @JoinTable(
            name = "care_tip_cat_breeds",
            joinColumns = @JoinColumn(name = "care_tip_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id")
    )
    private List<CatBreed> relevantCatBreeds;
}
