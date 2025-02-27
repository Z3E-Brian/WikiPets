package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"suitableDogBreeds", "suitableCatBreeds"})
@Table(name = "health_issues")
public class HealthIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 2000, nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate createdDate;
    @Column(nullable = false)
    private LocalDate modifiedDate;
    @ManyToMany
    @JoinTable(
            name = "health_issue_dog_breeds",
            joinColumns = @JoinColumn(name = "health_issue_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id")
    )
    private List<DogBreed> suitableDogBreeds;
    @ManyToMany
    @JoinTable(
            name = "health_issue_cat_breeds",
            joinColumns = @JoinColumn(name = "health_issue_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id")
    )
    private List<CatBreed> suitableCatBreeds;
    @Column(length = 200, nullable = false)
    private String symptoms;
    @Column(length = 200, nullable = false)
    private String treatment;
}
