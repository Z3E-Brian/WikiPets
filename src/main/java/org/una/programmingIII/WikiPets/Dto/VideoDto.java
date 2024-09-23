package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
    private Long id;
    private String url;
    private String title;
    private DogBreedDto dogBreedDto;
    private CatBreedDto catBreedDto;
    private LocalDate createDate;
    private LocalDate lastUpdate;
}