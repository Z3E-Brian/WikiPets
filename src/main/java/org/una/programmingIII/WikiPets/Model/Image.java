package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    @Column(name = "is_dog_breed", nullable = false)
    private Boolean isDogBreed;
    @Column(name = "pet_breed_id", length = 100, nullable = false)
    private int petBreedId;
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDate createDate;
    @Column(name = "last_update")
    private LocalDate lastUpdate;
}
