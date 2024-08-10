package org.una.programmingIII.WikiPets.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogBreedDto {
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

    public DogBreedDto(DogBreed dogBreed) {
        this.id = dogBreed.getId();
        this.name = dogBreed.getName();
        this.origin = dogBreed.getOrigin();
        this.size = dogBreed.getSize();
        this.coat = dogBreed.getCoat();
        this.color = dogBreed.getColor();
        this.lifeSpan = dogBreed.getLifeSpan();
        this.temperament = dogBreed.getTemperament();
        this.description = dogBreed.getDescription();
       // this.version = dogBreed.getVersion();
    }
}
