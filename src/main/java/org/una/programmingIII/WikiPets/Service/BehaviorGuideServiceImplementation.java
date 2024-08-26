package org.una.programmingIII.WikiPets.Service;

import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BehaviorGuideServiceImplementation implements BehaviorGuideService {

    private final BehaviorGuideRepository behaviorGuideRepository;
    private final GenericMapper<BehaviorGuide,BehaviorGuideDto> behaviorGuideMapper;

    public BehaviorGuideServiceImplementation(BehaviorGuideRepository behaviorGuideRepository, GenericMapperFactory mapperFactory) {
        this.behaviorGuideRepository = behaviorGuideRepository;
        this.behaviorGuideMapper =  mapperFactory.createMapper(BehaviorGuide.class, BehaviorGuideDto.class);
    }
    @Override
    public BehaviorGuideDto getBehaviorGuideById(Long id) {
        BehaviorGuide behaviorGuide = behaviorGuideRepository.findById(id).orElseThrow();
        return behaviorGuideMapper.convertToDTO(behaviorGuide);
    }
    @Override
    public List<BehaviorGuideDto> getAllBehaviorGuides() {
        return behaviorGuideRepository.findAll().stream()
                .map(behaviorGuideMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public BehaviorGuideDto createBehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        BehaviorGuide behaviorGuide = behaviorGuideMapper.convertToEntity(behaviorGuideDto);
        BehaviorGuide savedBehaviorGuide = behaviorGuideRepository.save(behaviorGuide);
        return behaviorGuideMapper.convertToDTO(savedBehaviorGuide);
    }
    @Override
    public BehaviorGuideDto updateBehaviorGuide(BehaviorGuideDto behaviorGuideDto) {
        BehaviorGuide behaviorGuide = behaviorGuideMapper.convertToEntity(behaviorGuideDto);
        behaviorGuide = behaviorGuideRepository.save(behaviorGuide);
        return behaviorGuideMapper.convertToDTO(behaviorGuide);
    }
    @Override
    public void deleteBehaviorGuide(Long id) {
        behaviorGuideRepository.deleteById(id);
    }
}
