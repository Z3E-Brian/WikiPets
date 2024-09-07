package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"relevantDogBreeds", "relevantCatBreeds"})
public class CareTipDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private List<DogBreedDto> relevantDogBreeds;
    private List<CatBreedDto> relevantCatBreeds;
}
