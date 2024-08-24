package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.BehaviorGuideDto;

import java.util.List;

public interface BehaviorGuideService {
    public BehaviorGuideDto getBehaviorGuideById(Long id);

    public List<BehaviorGuideDto> getAllBehaviorGuides();

    public BehaviorGuideDto saveBehaviorGuide(BehaviorGuideDto behaviorGuideDto);

    public void deleteBehaviorGuide(Long id);
}
