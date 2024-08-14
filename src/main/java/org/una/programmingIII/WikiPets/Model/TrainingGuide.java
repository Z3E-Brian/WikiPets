package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "traning_guides")
public class TrainingGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String content;

    @ManyToMany
    @JoinTable(
            name = "training_guide_cat_breeds",
            joinColumns = @JoinColumn(name = "trainingguide_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id")
    )
    private List<CatBreed> catBreeds;

    @ManyToMany
    @JoinTable(
            name = "training_guide_dog_breeds",
            joinColumns = @JoinColumn(name = "trainingguide_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id")
    )
    private List<DogBreed> dogBreeds;

    @Version
    @Column(name = "TRAINING_GUIDE_VERSION")
    private Long version;

}
