package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BehaviorGuideDto {
    private Long id;
    private String title;
    private String content;
//    private List<Issues> dogBreedCommonIssues;
//    private List<Issues> catBreedCommonIssues;
    private String solutions;
    private List<DogBreedDto> suitableDogBreeds;
    private List<CatBreedDto> suitableCatBreeds;
    //private Long version;


    public BehaviorGuideDto(BehaviorGuide behaviorGuide) {
        this.id = behaviorGuide.getId();
        this.title = behaviorGuide.getTitle();
        this.content = behaviorGuide.getContent();
        //this.commonIssues = behaviorGuide.getCommonIssues();
        this.solutions = behaviorGuide.getSolutions();
        this.suitableDogBreeds = new ArrayList<>();
        this.suitableCatBreeds = new ArrayList<>();
        //this.version = catBreed.getVersion();
    }

}
