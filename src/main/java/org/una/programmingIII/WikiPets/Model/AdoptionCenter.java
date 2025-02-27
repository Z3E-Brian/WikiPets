package org.una.programmingIII.WikiPets.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"availableDogBreeds", "availableCatBreeds"})
@Table(name = "adoption_centers")
public class AdoptionCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150, nullable = false)
    private String name;
    @Column(length = 200, nullable = false)
    private String location;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "adoption_centers_dog_breeds",
            joinColumns = @JoinColumn(name = "adoption_center_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id"))
    private List<DogBreed> availableDogBreeds;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "adoption_centers_cat_breeds",
            joinColumns = @JoinColumn(name = "adoption_center_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id"))
    private List<CatBreed> availableCatBreeds;
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDate createDate;
    @Column(name = "last_update")
    private LocalDate lastUpdate;
}
