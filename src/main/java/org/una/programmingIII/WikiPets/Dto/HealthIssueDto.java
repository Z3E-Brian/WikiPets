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
@ToString(exclude = {"suitableDogBreeds", "suitableCatBreeds"})
public class HealthIssueDto {
    private Long id;
    private String name;
    private String description;
    private String symptoms;
    private String treatment;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private List<DogBreedDto> suitableDogBreeds;
    private List<CatBreedDto> suitableCatBreeds;
}
