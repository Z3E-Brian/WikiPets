package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_breeds")
public class CatBreed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String origin;
    @Column(length = 4, nullable = false)
    private Integer size;
    @Column(length = 50, nullable = false)
    private String coat;
    @Column(length = 50, nullable = false)
    private String color;
    @Column(length = 50, nullable = false, name = "life_span")
    private String lifeSpan;
    @Column(length = 50, nullable = false)
    private String temperament;
    @Column(length = 2000, nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate createdDate;
    @Column(nullable = false)
    private LocalDate modifiedDate;
    @ManyToMany(mappedBy = "availableCatBreeds", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<AdoptionCenter> adoptionCenters;
    @ManyToMany(mappedBy = "suitableCatBreeds")
    private List<HealthIssue> healthIssues;
    @ManyToMany(mappedBy = "recommendedCatBreeds")
    private List<NutritionGuide> nutritionGuides;
    @ManyToMany(mappedBy = "favoriteCatBreeds")
    private List<User> users;
    @ManyToMany(mappedBy = "catBreeds")
    private List<TrainingGuide> trainingGuides;
    @ManyToMany(mappedBy = "suitableCatBreeds")
    private List<BehaviorGuide> behaviorGuides;
    @ManyToMany(mappedBy = "relevantCatBreeds")
    private List<CareTip> careTips;
    @ManyToMany(mappedBy = "suitableCatBreeds")
    private List<GroomingGuide> groomingGuides;
    @OneToMany(mappedBy = "catBreed")
    private List<Image> images;
    @OneToMany(mappedBy = "catBreed")
    private List<Video> videos;
    @OneToMany(mappedBy = "catBreed")
    private List<Review> reviews;
    @ManyToOne
    @JoinColumn(name = "feeding_schedule_id")
    private FeedingSchedule feedingSchedule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatBreed catBreed = (CatBreed) o;
        return Objects.equals(id, catBreed.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
