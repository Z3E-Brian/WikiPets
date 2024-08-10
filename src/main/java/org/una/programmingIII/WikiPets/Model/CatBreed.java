package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cat_breeds")
public class CatBreed {
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
//    @Column(name = "CAT_BREED_VERSION")
//    private Long version;

    public CatBreed(CatBreedDto catBreedDto) {
        this.id = catBreedDto.getId();
        update(catBreedDto);
    }

    public void update(CatBreedDto catBreedDto) {
        this.name = catBreedDto.getName();
        this.origin = catBreedDto.getOrigin();
        this.size = catBreedDto.getSize();
        this.coat = catBreedDto.getCoat();
        this.color = catBreedDto.getColor();
        this.lifeSpan = catBreedDto.getLifeSpan();
        this.temperament = catBreedDto.getTemperament();
        this.description = catBreedDto.getDescription();
        //this.version = catBreedDto.getVersion();
    }
}
