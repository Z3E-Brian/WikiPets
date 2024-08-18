package org.una.programmingIII.WikiPets.Service;

import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Model.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Mapper.BehaviorGuideMapper;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BehaviorGuideService {

    private final BehaviorGuideRepository behaviorGuideRepository;
    private final BehaviorGuideMapper behaviorGuideMapper;

    public BehaviorGuideService(BehaviorGuideRepository behaviorGuideRepository, BehaviorGuideMapper behaviorGuideMapper) {
        this.behaviorGuideRepository = behaviorGuideRepository;
        this.behaviorGuideMapper = behaviorGuideMapper;
    }

    public BehaviorGuideDto getBehaviorGuideById(Long id) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id).orElseThrow();
        return behaviorGuideMapper.BehaviorGuideDto(behaviorGuide);
    }

    public List<BehaviorGuideDto> getAllBehaviorGuides() {
        return behaviorGuideRepository.findAll().stream()
                .map(behaviorGuideMapper::BehaviorGuideDto)
                .collect(Collectors.toList());
    }

    public BehaviorGuideDto saveBehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        BehaviorGuide behaviorGuide = behaviorGuideMapper.toBehaviorGuide(behaviorGuideDto);
        behaviorGuide = behaviorGuideRepository.save(behaviorGuide);
        return behaviorGuideMapper.BehaviorGuideDto(behaviorGuide);
    }

    public void deleteBehaviorGuide(Long id) {
        behaviorGuideRepository.deleteById(id);
    }
}
