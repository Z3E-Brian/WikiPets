package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionGuideDto {
    private Long id;
    private String title;
    private String content;
    private String dietaryRequirements;
    private List<DogBreedDto> recommendedDogBreeds;
    private List<CatBreedDto> recommendedCatBreeds;
}
