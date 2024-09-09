package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"catBreedDto", "dogBreedDto"})
public class FeedingScheduleDto {
    Long id;
    CatBreedDto catBreedDto;
    DogBreedDto dogBreedDto;
    private String ageGroup;
    private String feedingTimes;
    private LocalDate createDate;
    private LocalDate lastUpdate;

}
