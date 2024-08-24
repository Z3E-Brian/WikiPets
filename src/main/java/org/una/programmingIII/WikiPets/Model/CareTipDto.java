package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareTipDto {
    private Long id;
    private String title;
    private String content;
    private String dietaryRequirements;
    private List<DogBreedDto> relevantDogBreeds;
    private List<CatBreedDto> relevantCatBreeds;
}
