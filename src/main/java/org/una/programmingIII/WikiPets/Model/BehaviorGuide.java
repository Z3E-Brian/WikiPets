package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(length = 200, nullable = false)
    private String content;
    @Column(length = 150, nullable = false)
    private String solutions;
    @ManyToMany
    @JoinTable(
            name = "common_issues_dog_breeds",
            joinColumns = @JoinColumn(name = "common_issues_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id")
    )
    private List<DogBreed> dogBreedsCommonIssues;
    @ManyToMany
    @JoinTable(
            name = "common_issues_cat_breeds",
            joinColumns = @JoinColumn(name = "common_issues_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id")
    )
    private List<DogBreed> catBreedsCommonIssues;
    @ManyToMany
    @JoinTable(
            name = "behavior_guide_dog_breeds",
            joinColumns = @JoinColumn(name = "behavior_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id")
    )
    private List<DogBreed> suitableDogBreeds;

    @ManyToMany
    @JoinTable(
            name = "behavior_guide_cat_breeds",
            joinColumns = @JoinColumn(name = "behavior_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id")
    )
    private List<DogBreed> suitableCatBreeds;
//    @Version
//    @Column(name = "CAT_BREED_VERSION")
//    private Long version;

    public BehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        this.id = behaviorGuideDto.getId();
        update(behaviorGuideDto);
    }

    public void update(BehaviorGuideDto behaviorGuideDto) {
        this.title = behaviorGuideDto.getTitle();
        this.content = behaviorGuideDto.getContent();
        this.dogBreedsCommonIssues = new ArrayList<>();
        this.catBreedsCommonIssues = new ArrayList<>();
        this.suitableDogBreeds = new ArrayList<>();
        this.suitableCatBreeds = new ArrayList<>();

        //this.version = catBreedDto.getVersion();
    }
}
