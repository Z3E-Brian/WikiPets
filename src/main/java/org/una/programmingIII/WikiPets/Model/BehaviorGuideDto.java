package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BehaviorGuideDto {
    private Long id;
    private String title;
    private String content;
//    private List<Integer> commonIssues;
    private String solutions;
    //private Long version;


    public BehaviorGuideDto(BehaviorGuide behaviorGuide) {
        this.id = behaviorGuide.getId();
        this.title = behaviorGuide.getTitle();
        this.content = behaviorGuide.getContent();
        //this.commonIssues = behaviorGuide.getCommonIssues();
        this.solutions = behaviorGuide.getSolutions();
        //this.version = catBreed.getVersion();
    }

}
