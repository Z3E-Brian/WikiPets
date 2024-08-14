package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthIssueDto {
    private Long id;
    private String name;
    private String description;
    private String symptoms;
    private String treatment;
    private List<DogBreed> suitableDogBreeds;
    private List<CatBreed> suitableCatBreeds;
}
