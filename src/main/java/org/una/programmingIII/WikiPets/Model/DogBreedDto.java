package org.una.programmingIII.WikiPets.Model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogBreedDto {
    private Long id;
    private String name;
    private String origin;
    private Integer size;
    private String coat;
    private String color;
    private String lifeSpan;
    private String temperament;
    private String description;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
//    private List<AdoptionCenterDto> adoptionCenters;
//    private List<HealthIssueDto> healthIssues;
//    private List<NutritionGuideDto> nutritionGuides;
//    private List<UserDto> users;
//    private List<TrainingGuideDto> trainingGuides;
//    private List<BehaviorGuideDto> behaviorGuides;
//    private List<CareTipDto> careTips;
//    private List<GroomingGuideDto> groomingGuides;
//    private List<FeedingScheduleDto> feedingSchedules;
//    private List<ImageDto> images;
//    private List<VideoDto> videos;
//    private List<ReviewDto> reviews;
}
