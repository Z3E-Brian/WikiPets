package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//    @Column(length = 10, nullable = false)
//    private List<Integer> getCommonIssues;
    @Column(length = 150, nullable = false)
    private String solutions;
//    list (List<DogBreed | CatBreed>)
//    @Column(length = 50, nullable = false)
//    private List<DogBreed|CatBreed> suitableBreeds;
//    @Version
//    @Column(name = "CAT_BREED_VERSION")
//    private Long version;

    public BehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        this.id = behaviorGuideDto.getId();
        update(behaviorGuideDto);
    }

    public void update(BehaviorGuideDto behaviorGuideDto) {
        this.id = behaviorGuideDto.getId();

        //this.version = catBreedDto.getVersion();
    }
}
