package org.una.programmingIII.WikiPets.Input;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BehaviorGuideInput {
    private Long id;
    private String title;
    private String content;
    private String behavioralIssues;
    private String solutions;
}
