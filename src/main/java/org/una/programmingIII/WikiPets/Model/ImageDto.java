package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Long id;
    private String url;
    private String description;
    private Boolean isDogBreed;
    private int petBreedId;
    private LocalDate createDate;
    private LocalDate lastUpdate;
}
