package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;

import java.util.List;

public interface AdoptionCenterService {
    AdoptionCenterDto getAdoptionCenterById(Long id);

    List<AdoptionCenterDto> getAllAdoptionCenters();

    AdoptionCenterDto updateAdoptionCenter(AdoptionCenterDto adoptionCenterDto);

    AdoptionCenterDto createAdoptionCenter(AdoptionCenterDto adoptionCenterDto);

    void deleteAdoptionCenter(Long id);
}
