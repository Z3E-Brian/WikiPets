package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grooming_guides")
public class GroomingGuide {
    //id, title, content, toolsNeeded, steps, suitableBreeds (List<DogBreed | CatBreed>).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String content;
    @Column(length = 200, nullable = false)
    private String toolsNeeded;
    @Column(length = 250, nullable = false)
    private String steps;
//    List<DogBreed | CatBreed> suitableBreeds
//    @Column(length = 50, nullable = false)
//    private String suitableBreeds;

//    @Version
//    @Column(name = "CAT_BREED_VERSION")
//    private Long version;

    public GroomingGuide(GroomingGuideDto GroomingGuideDto) {
        this.id = GroomingGuideDto.getId();
        update(GroomingGuideDto);
    }

    public void update(GroomingGuideDto GroomingGuideDto) {
        this.content = GroomingGuideDto.getContent();
        this.toolsNeeded = GroomingGuideDto.getToolsNeeded();
        this.steps = GroomingGuideDto.getSteps();
        //this.suitableBreeds = GroomingGuideDto.getSuitableBreeds();
        //this.version = GroomingGuideDto.getVersion();
    }
}
