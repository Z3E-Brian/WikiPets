package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dog_breeds")
public class DogBreed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String origin;
    @Column(length = 4, nullable = false)
    private Integer size;
    @Column(length = 50, nullable = false)
    private String coat;
    @Column(length = 50, nullable = false)
    private String color;
    @Column(length = 50, nullable = false, name = "life_span")
    private String lifeSpan;
    @Column(length = 50, nullable = false)
    private String temperament;
    @Column(length = 2000, nullable = false)
    private String description;
//    @Version
//    @Column(name = "DOG_BREED_VERSION")
//    private Long version;

    public DogBreed(DogBreedDto dogBreedDto) {
        this.id = dogBreedDto.getId();
        update(dogBreedDto);
    }

    public void update(DogBreedDto dogBreedDto) {
        this.name = dogBreedDto.getName();
        this.origin = dogBreedDto.getOrigin();
        this.size = dogBreedDto.getSize();
        this.coat = dogBreedDto.getCoat();
        this.color = dogBreedDto.getColor();
        this.lifeSpan = dogBreedDto.getLifeSpan();
        this.temperament = dogBreedDto.getTemperament();
        this.description = dogBreedDto.getDescription();
        //this.version=dogBreedDto.getVersion();
    }
}
