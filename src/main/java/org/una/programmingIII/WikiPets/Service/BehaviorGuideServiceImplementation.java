package org.una.programmingIII.WikiPets.Service;

import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Mapper.BehaviorGuideMapper;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BehaviorGuideServiceImplementation implements BehaviorGuideService {

    private final BehaviorGuideRepository behaviorGuideRepository;
    private final BehaviorGuideMapper behaviorGuideMapper;

    public BehaviorGuideServiceImplementation(BehaviorGuideRepository behaviorGuideRepository) {
        this.behaviorGuideRepository = behaviorGuideRepository;
        this.behaviorGuideMapper =  BehaviorGuideMapper.INSTANCE;
    }
    @Override
    public BehaviorGuideDto getBehaviorGuideById(Long id) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id).orElseThrow();
        return behaviorGuideMapper.toBehaviorGuideDto(behaviorGuide);
    }
    @Override
    public List<BehaviorGuideDto> getAllBehaviorGuides() {
        return behaviorGuideRepository.findAll().stream()
                .map(behaviorGuideMapper::toBehaviorGuideDto)
                .collect(Collectors.toList());
    }
    @Override
    public BehaviorGuideDto saveBehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        BehaviorGuide behaviorGuide = behaviorGuideMapper.toBehaviorGuide(behaviorGuideDto);
        behaviorGuide = behaviorGuideRepository.save(behaviorGuide);
        return behaviorGuideMapper.toBehaviorGuideDto(behaviorGuide);
    }
    @Override
    public void deleteBehaviorGuide(Long id) {
        behaviorGuideRepository.deleteById(id);
    }
}
