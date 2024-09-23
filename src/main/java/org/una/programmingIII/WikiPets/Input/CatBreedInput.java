package org.una.programmingIII.WikiPets.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatBreedInput {
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
