package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "behavior_guides")
public class BehaviorGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    private String content;
    @Column(length = 200, nullable = false)
    private String behavioralIssues;
    @Column(length = 300, nullable = false)
    private String solutions;
    @ManyToMany
    @JoinTable(name = "behavior_guide_dog_breeds",
            joinColumns = @JoinColumn(name = "behavior_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id"))
    private List<DogBreed> suitableDogBreeds;
    @ManyToMany
    @JoinTable(name = "behavior_guide_cat_breeds",
            joinColumns = @JoinColumn(name = "behavior_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id"))
    private List<DogBreed> suitableCatBreeds;
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDate createDate;
    @Column(name = "last_update")
    private LocalDate lastUpdate;
}
