package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingGuideDto {

    private Long id;
    private String title;
    private String content;
    private List<CatBreedDto> catsBreedDto; // DTO para CatBreed, puede ser null si la guia de entreno es para un perro
    private List<DogBreedDto> dogsBreedDto; // DTO para DogBreed, puede ser null si la guia de entreno es para un gato
    private LocalDate createDate;
    private LocalDate lastUpdate;

}