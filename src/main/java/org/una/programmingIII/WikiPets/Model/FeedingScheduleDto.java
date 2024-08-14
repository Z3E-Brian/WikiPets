package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedingScheduleDto {
    Long id;
    CatBreedDto catBreedDto;
    DogBreedDto dogBreedDto;
    private String ageGroup;
    private String feedingTimes;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

}
