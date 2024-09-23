package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroomingGuideDto {
    private Long id;
    private String content;
    private String toolsNeeded;
    private String steps;
    private List<DogBreedDto> suitableDogBreeds;
    private List<CatBreedDto> suitableCatBreeds;
    private LocalDate createDate;
    private LocalDate lastUpdate;


}
