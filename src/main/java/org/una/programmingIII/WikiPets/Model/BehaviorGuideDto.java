package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BehaviorGuideDto {
    private Long id;
    private String title;
    private String content;
    private String behavioralIssues;
    private String solutions;
    private List<DogBreedDto> suitableDogBreeds;
    private List<CatBreedDto> suitableCatBreeds;
    private LocalDate createDate;
    private LocalDate lastUpdate;
}
