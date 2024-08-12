package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroomingGuideDto {
    private Long id;
    private String content;
    private String toolsNeeded;
    private String steps;
    //    List<DogBreed | CatBreed> suitableBreeds;
    //private Long version;


    public GroomingGuideDto(GroomingGuide groomingGuide) {
        this.id = groomingGuide.getId();
        this.content = groomingGuide.getContent();
        this.toolsNeeded = groomingGuide.getToolsNeeded();
        this.steps = groomingGuide.getSteps();
        //this.suitableBreeds = groomingGuide.getSuitableBreeds();
        //this.version = catBreed.getVersion();
    }

}
