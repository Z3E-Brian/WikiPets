package org.una.programmingIII.WikiPets.Input;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInput {
    private Long id;
    private Integer rating;
    private String comment;
    private Long idDogBreed;
    private Long idCatBreed;
}
