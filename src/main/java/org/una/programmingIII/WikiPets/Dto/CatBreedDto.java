package org.una.programmingIII.WikiPets.Dto;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatBreedDto {
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
    private List<AdoptionCenterDto> adoptionCenters;
    private List<HealthIssueDto> healthIssues;
    private List<NutritionGuideDto> nutritionGuides;
    private List<UserDto> users;
    private List<TrainingGuideDto> trainingGuides;
    private List<BehaviorGuideDto> behaviorGuides;
    private List<CareTipDto> careTips;
    private List<GroomingGuideDto> groomingGuides;
    private FeedingScheduleDto feedingSchedule;
    private List<ImageDto> images;
    private List<VideoDto> videos;
    private List<ReviewDto> reviews;
}

