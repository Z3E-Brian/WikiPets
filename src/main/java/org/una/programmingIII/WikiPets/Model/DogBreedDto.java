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
}
