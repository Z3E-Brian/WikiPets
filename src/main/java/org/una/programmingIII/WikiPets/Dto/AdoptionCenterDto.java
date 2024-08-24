package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionCenterDto {
    private Long id;
    private String name;
    private String location;
    private List<DogBreedDto> availableDogBreeds;
    private List<CatBreedDto> availableCatBreeds;
    private LocalDate createDate;
    private LocalDate lastUpdate;
}