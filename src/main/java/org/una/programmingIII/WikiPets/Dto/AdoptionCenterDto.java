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

    public AdoptionCenterDto(String name, String location) {
        this.name = name;
        this.location = location;
        this.availableDogBreeds = null;
        this.availableCatBreeds = null;
        this.createDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
    }
    public AdoptionCenterDto(Long id,String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.availableDogBreeds = null;
        this.availableCatBreeds = null;
        this.createDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
    }
}