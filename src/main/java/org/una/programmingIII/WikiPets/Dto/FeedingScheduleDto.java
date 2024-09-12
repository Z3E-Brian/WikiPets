package org.una.programmingIII.WikiPets.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"catBreeds", "dogBreeds"})
public class FeedingScheduleDto {
    Long id;
    List<CatBreedDto> catBreeds;
    List<DogBreedDto> dogBreeds;
    private String ageGroup;
    private String feedingTimes;
    private LocalDate createDate;
    private LocalDate lastUpdate;

}
