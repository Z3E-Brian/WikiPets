package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;

import java.util.List;

public interface GroomingGuideService {

    GroomingGuideDto getGroomingGuideById(Long id);

    List<GroomingGuideDto> getAllGroomingGuides();

    GroomingGuideDto updateGroomingGuide(GroomingGuideDto groomingGuideDto);

    GroomingGuideDto createGroomingGuide(GroomingGuideDto groomingGuideDto);

    void deleteGroomingGuide(Long id);
}
