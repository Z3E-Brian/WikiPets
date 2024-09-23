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
@ToString(exclude = {"catBreeds", "dogBreeds"})
public class TrainingGuideDto {

    private Long id;
    private String title;
    private String content;
    private List<CatBreedDto> catBreeds;
    private List<DogBreedDto> dogBreeds;
    private LocalDate createDate;
    private LocalDate lastUpdate;

}