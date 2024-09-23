package org.una.programmingIII.WikiPets.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"recommendedDogBreeds", "recommendedCatBreeds"})
public class NutritionGuideDto {
    private Long id;
    private String title;
    private String content;
    private String dietaryRequirements;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private List<DogBreedDto> recommendedDogBreeds;
    private List<CatBreedDto> recommendedCatBreeds;
}
