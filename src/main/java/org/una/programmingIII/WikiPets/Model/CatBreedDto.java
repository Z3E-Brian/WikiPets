package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
