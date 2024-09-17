package org.una.programmingIII.WikiPets.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingGuideInput {
    private Long id;
    private String title;
    private String content;
}
