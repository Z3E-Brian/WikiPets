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
@Table(name = "feeding_schedules")
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cat_breed_id")
    private CatBreed catBreed;

    @ManyToOne
    @JoinColumn(name = "dog_breed_id")
    private DogBreed dogBreed;

    @Column(name ="age_group",length = 50, nullable = false)
    private String ageGroup;

    @Column(name ="feeding_times",length = 500, nullable = false)
    private String feedingTimes;

    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDate createDate;

    @Column(name = "last_update")
    private LocalDate lastUpdate;


}
