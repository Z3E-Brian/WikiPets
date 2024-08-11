package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(length = 50, nullable = false)
    private String ageGroup;

    @Column(length = 500, nullable = false)
    private String feedingTimes;

    @Version
    @Column(name = "FEEDING_SCHEDULE_VERSION")
    private Long version;

    public FeedingSchedule(FeedingScheduleDto feedingScheduleDto) {
        this.id = feedingScheduleDto.getId();
        update(feedingScheduleDto);
    }

    public void update(FeedingScheduleDto feedingScheduleDto) {
        this.catBreed = feedingScheduleDto.getCatBreedDto() != null ? new CatBreed(feedingScheduleDto.getCatBreedDto()) : null;
        this.dogBreed = feedingScheduleDto.getDogBreedDto() != null ? new DogBreed(feedingScheduleDto.getDogBreedDto()) : null;
        this.ageGroup = feedingScheduleDto.getAgeGroup();
        this.feedingTimes = feedingScheduleDto.getFeedingTimes();
        this.version = feedingScheduleDto.getVersion();
    }
}
