package org.una.programmingIII.WikiPets.Service;

import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
import org.una.programmingIII.WikiPets.Repository.AdoptionCenterRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionCenterServiceImplementation implements AdoptionCenterService {

    private final AdoptionCenterRepository adoptionCenterRepository;
    private final GenericMapper<AdoptionCenter, AdoptionCenterDto> adoptionCenterMapper;

    public AdoptionCenterServiceImplementation(AdoptionCenterRepository adoptionCenterRepository, GenericMapperFactory mapperFactory) {
        this.adoptionCenterRepository = adoptionCenterRepository;
        this.adoptionCenterMapper =  mapperFactory.createMapper(AdoptionCenter.class, AdoptionCenterDto.class);
    }
    @Override
    public AdoptionCenterDto getAdoptionCenterById(Long id) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(id).orElseThrow();
        return adoptionCenterMapper.convertToDTO(adoptionCenter);
    }
    @Override
    public List<AdoptionCenterDto> getAllAdoptionCenters() {
        return adoptionCenterRepository.findAll().stream()
                .map(adoptionCenterMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public AdoptionCenterDto createAdoptionCenter(AdoptionCenterDto adoptionCenterDto) {
        AdoptionCenter adoptionCenter = adoptionCenterMapper.convertToEntity(adoptionCenterDto);
        AdoptionCenter savedAdoptionCenter = adoptionCenterRepository.save(adoptionCenter);
        return adoptionCenterMapper.convertToDTO(savedAdoptionCenter);
    }
    @Override
    public AdoptionCenterDto updateAdoptionCenter(AdoptionCenterDto adoptionCenterDto) {
        AdoptionCenter adoptionCenter = adoptionCenterMapper.convertToEntity(adoptionCenterDto);
        adoptionCenter = adoptionCenterRepository.save(adoptionCenter);
        return adoptionCenterMapper.convertToDTO(adoptionCenter);
    }
    @Override
    public void deleteAdoptionCenter(Long id) {
        adoptionCenterRepository.deleteById(id);
    }
}
