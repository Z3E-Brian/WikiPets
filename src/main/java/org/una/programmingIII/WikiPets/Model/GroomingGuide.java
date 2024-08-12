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
@Table(name = "grooming_guides")
public class GroomingGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String content;
    @Column(length = 200, nullable = false)
    private String toolsNeeded;
    @Column(length = 250, nullable = false)
    private String steps;
    @ManyToMany
    @JoinTable(
            name = "grooming_guide_dog_breeds",
            joinColumns = @JoinColumn(name = "grooming_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_breed_id")
    )
    private List<DogBreed> suitableDogBreeds;

    @ManyToMany
    @JoinTable(
            name = "grooming_guide_cat_breeds",
            joinColumns = @JoinColumn(name = "grooming_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_breed_id")
    )
    private List<DogBreed> suitableCatBreeds;

//    @Version
//    @Column(name = "GROOMING_GUIDE_VERSION")
//    private Long version;

    public GroomingGuide(GroomingGuideDto GroomingGuideDto) {
        this.id = GroomingGuideDto.getId();
        update(GroomingGuideDto);
    }

    public void update(GroomingGuideDto GroomingGuideDto) {
        this.content = GroomingGuideDto.getContent();
        this.toolsNeeded = GroomingGuideDto.getToolsNeeded();
        this.steps = GroomingGuideDto.getSteps();
        this.suitableDogBreeds = new ArrayList<>();
        this.suitableCatBreeds = new ArrayList<>();
        //this.suitableBreeds = GroomingGuideDto.getSuitableBreeds();
        //this.version = GroomingGuideDto.getVersion();
    }
}
