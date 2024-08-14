package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedingScheduleDto {
    Long id;
    CatBreedDto catBreedDto;
    DogBreedDto dogBreedDto;
    private String ageGroup;
    private String feedingTimes;
    private Long version;

}
