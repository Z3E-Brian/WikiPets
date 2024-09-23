package org.una.programmingIII.WikiPets.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedingScheduleInput {
    Long id;
    CatBreedDto catBreedDto;
    DogBreedDto dogBreedDto;
    private String ageGroup;
    private String feedingTimes;
}
