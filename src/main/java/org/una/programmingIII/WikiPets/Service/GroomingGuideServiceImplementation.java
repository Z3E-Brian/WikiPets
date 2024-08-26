package org.una.programmingIII.WikiPets.Service;


import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.GroomingGuide;
import org.una.programmingIII.WikiPets.Repository.GroomingGuideRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroomingGuideServiceImplementation implements GroomingGuideService {
    private final GroomingGuideRepository groomingGuideRepository;
    private final GenericMapper<GroomingGuide, GroomingGuideDto> groomingGuideMapper;

    public GroomingGuideServiceImplementation(GroomingGuideRepository groomingGuideRepository, GenericMapperFactory mapperFactory) {
        this.groomingGuideRepository = groomingGuideRepository;
        this.groomingGuideMapper =  mapperFactory.createMapper(GroomingGuide.class, GroomingGuideDto.class);
    }
    @Override
    public GroomingGuideDto getGroomingGuideById(Long id) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id).orElseThrow();
        return groomingGuideMapper.convertToDTO(groomingGuide);
    }
    @Override
    public List<GroomingGuideDto> getAllGroomingGuides() {
        return groomingGuideRepository.findAll().stream()
                .map(groomingGuideMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public GroomingGuideDto createGroomingGuide(GroomingGuideDto groomingGuideDto) {
        GroomingGuide groomingGuide = groomingGuideMapper.convertToEntity(groomingGuideDto);
        GroomingGuide savedGroomingGuide = groomingGuideRepository.save(groomingGuide);
        return groomingGuideMapper.convertToDTO(savedGroomingGuide);
    }
    @Override
    public GroomingGuideDto updateGroomingGuide(GroomingGuideDto groomingGuideDto) {
        GroomingGuide groomingGuide = groomingGuideMapper.convertToEntity(groomingGuideDto);
        groomingGuide = groomingGuideRepository.save(groomingGuide);
        return groomingGuideMapper.convertToDTO(groomingGuide);
    }
    @Override
    public void deleteGroomingGuide(Long id) {
        groomingGuideRepository.deleteById(id);
    }
}
