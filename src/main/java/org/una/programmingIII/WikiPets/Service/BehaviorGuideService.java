package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;

import java.util.List;

public interface BehaviorGuideService {
    BehaviorGuideDto getBehaviorGuideById(Long id);

    List<BehaviorGuideDto> getAllBehaviorGuides();

    BehaviorGuideDto updateBehaviorGuide(BehaviorGuideDto behaviorGuideDto);

    BehaviorGuideDto createBehaviorGuide(BehaviorGuideDto behaviorGuideDto);

    void deleteBehaviorGuide(Long id);
}
