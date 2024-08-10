package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatBreedDto {
    private Long id;
    private String name;
    private String origin;
    private Integer size;
    private String coat;
    private String color;
    private String lifeSpan;
    private String temperament;
    private String description;
    //private Long version;


    public CatBreedDto(CatBreed catBreed) {
        this.id = catBreed.getId();
        this.name = catBreed.getName();
        this.origin = catBreed.getOrigin();
        this.size = catBreed.getSize();
        this.coat = catBreed.getCoat();
        this.color = catBreed.getColor();
        this.lifeSpan = catBreed.getLifeSpan();
        this.temperament = catBreed.getTemperament();
        this.description = catBreed.getDescription();
        //this.version = catBreed.getVersion();
    }

}
