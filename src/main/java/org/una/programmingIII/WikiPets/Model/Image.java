package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {
    //id, url, description, breed (DogBreed | CatBreed).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String url;
    @Column(length = 250, nullable = false)
    private String description;
    @Column(length = 1, nullable = false)
    private Boolean isDogBreed;
    @Column(length = 100, nullable = false)
    private String petBreed;
//    @Version
//    @Column(name = "IMAGE_VERSION")
//    private Long version;

    public Image(ImageDto imageDto) {
        this.id = imageDto.getId();
        update(imageDto);
    }

    public void update(ImageDto imageDto) {
        this.url = imageDto.getUrl();
        this.description = imageDto.getDescription();
        this.isDogBreed = imageDto.getIsDogBreed();
        this.petBreed = imageDto.getPetBreed();
        //this.version = GroomingGuideDto.getVersion();
    }
}
