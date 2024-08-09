package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "dog_breeds")
public class DogBreed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String name;
    @Column(length = 100,nullable = false)
    private String origin;
    @Column(length = 4,nullable = false)
    private Integer size;
    @Column(length = 50,nullable = false)
    private String coat;
    @Column(length = 50,nullable = false)
    private String color;
    @Column(length = 50,nullable = false, name = "life_span")
    private String lifeSpan;
    @Column(length = 50,nullable = false)
    private String temperament;
    @Column(length = 2000,nullable = false)
    private String description;
}
