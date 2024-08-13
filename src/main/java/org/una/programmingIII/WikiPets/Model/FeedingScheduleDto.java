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

//    public FeedingScheduleDto(FeedingSchedule feedingSchedule) {
//        this.id = feedingSchedule.getId();
//        this.catBreedDto = new CatBreedDto(feedingSchedule.getCatBreed());
//        this.dogBreedDto = new DogBreedDto(feedingSchedule.getDogBreed());
//        this.ageGroup = feedingSchedule.getAgeGroup();
//        this.feedingTimes = feedingSchedule.getFeedingTimes();
//        this.version = feedingSchedule.getVersion();
//    }
}
