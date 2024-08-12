package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroomingGuideDto {
    private Long id;
    private String content;
    private String toolsNeeded;
    private String steps;
    private List<DogBreedDto> suitableDogBreeds;
    private List<CatBreedDto> suitableCatBreeds;
    //private Long version;


    public GroomingGuideDto(GroomingGuide groomingGuide) {
        this.id = groomingGuide.getId();
        this.content = groomingGuide.getContent();
        this.toolsNeeded = groomingGuide.getToolsNeeded();
        this.steps = groomingGuide.getSteps();
        this.suitableDogBreeds = new ArrayList<>();
        this.suitableCatBreeds = new ArrayList<>();
        //this.version = catBreed.getVersion();
    }

}
