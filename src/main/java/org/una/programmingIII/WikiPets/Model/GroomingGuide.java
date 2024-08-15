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
@Table(name = "grooming_guides")
public class GroomingGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String content;
    @Column(name = "tools_needed", length = 200, nullable = false)
    private String toolsNeeded;
    @Column(length = 250, nullable = false)
    private String steps;
    @ManyToMany
    @JoinTable(name = "grooming_guide_dog_breeds",
            joinColumns = @JoinColumn(name = "grooming_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id"))
    private List<DogBreed> suitableDogBreeds;
    @ManyToMany
    @JoinTable(name = "grooming_guide_cat_breeds",
            joinColumns = @JoinColumn(name = "grooming_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id"))
    private List<DogBreed> suitableCatBreeds;
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDate createDate;
    @Column(name = "last_update")
    private LocalDate lastUpdate;

}
