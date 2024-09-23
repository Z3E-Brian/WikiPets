package org.una.programmingIII.WikiPets.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionGuideInput {
    private Long id;
    private String title;
    private String content;
    private String dietaryRequirements;
}
