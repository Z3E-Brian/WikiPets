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
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String url;
    @Column(length = 250, nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "dog_breed_id")
    private DogBreed dogBreed;
    @ManyToOne
    @JoinColumn(name = "cat_breed_id")
    private CatBreed catBreed;
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDate createDate;
    @Column(name = "last_update")
    private LocalDate lastUpdate;
}
