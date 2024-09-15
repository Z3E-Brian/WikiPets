package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dog_breeds")
public class DogBreed {
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
    @ManyToMany(mappedBy = "availableDogBreeds", cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<AdoptionCenter> adoptionCenters;
    @ManyToMany(mappedBy = "recommendedDogBreeds")
    private List<NutritionGuide> nutritionGuides;
    @ManyToMany(mappedBy = "suitableDogBreeds")
    private List<HealthIssue> healthIssues;
    @ManyToMany(mappedBy = "favoriteDogBreeds")
    private List<User> users;
    @ManyToMany(mappedBy = "dogBreeds")
    private List<TrainingGuide> trainingGuides;
    @ManyToMany(mappedBy = "suitableDogBreeds")
    private List<BehaviorGuide> behaviorGuides;
    @ManyToMany(mappedBy = "relevantDogBreeds")
    private List<CareTip> careTips;
    @ManyToMany(mappedBy = "suitableDogBreeds")
    private List<GroomingGuide> groomingGuides;
    @OneToMany(mappedBy = "dogBreed")
    private List<Image> images;
    @OneToMany(mappedBy = "dogBreed")
    private List<Video> videos;
    @OneToMany(mappedBy = "dogBreed")
    private List<Review> reviews;
    @ManyToOne
    @JoinColumn(name = "feeding_schedule_id")
    private FeedingSchedule feedingSchedule;
}
