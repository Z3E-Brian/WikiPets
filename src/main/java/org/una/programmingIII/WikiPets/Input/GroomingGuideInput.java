package org.una.programmingIII.WikiPets.Input;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroomingGuideInput {
    private Long id;
    private String content;
    private String toolsNeeded;
    private String steps;
}
