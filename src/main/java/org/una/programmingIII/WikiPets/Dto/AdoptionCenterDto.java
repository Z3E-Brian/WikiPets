package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.programmingIII.WikiPets.Input.AdoptionCenterInput;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"availableDogBreeds", "availableCatBreeds"})
public class AdoptionCenterDto {
    private Long id;
    private String name;
    private String location;
    private List<DogBreedDto> availableDogBreeds;
    private List<CatBreedDto> availableCatBreeds;
    private LocalDate createDate;
    private LocalDate lastUpdate;
}