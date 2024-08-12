package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Long id;
    private String url;
    private String description;
    private Boolean isDogBreed;
    private String petBreed;
    //private Long version;


    public ImageDto(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
        this.description = image.getDescription();
        this.isDogBreed = image.getIsDogBreed();
        this.petBreed = image.getPetBreed();
        //this.version = catBreed.getVersion();
    }

}
