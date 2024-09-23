package org.una.programmingIII.WikiPets.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoInput {
    private Long id;
    private String url;
    private String title;
}
