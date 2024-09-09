package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.GroomingGuide;
import org.una.programmingIII.WikiPets.Repository.GroomingGuideRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroomingGuideServiceImplementation implements GroomingGuideService {

    private final GroomingGuideRepository groomingGuideRepository;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final GenericMapper<GroomingGuide, GroomingGuideDto> groomingGuideMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public GroomingGuideServiceImplementation(GroomingGuideRepository groomingGuideRepository, GenericMapperFactory mapperFactory, DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.groomingGuideRepository = groomingGuideRepository;
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);

        this.groomingGuideMapper = mapperFactory.createMapper(GroomingGuide.class, GroomingGuideDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }

    @Override
    public GroomingGuideDto getGroomingGuideById(Long id) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new CustomException("Grooming guide with id " + id + " not found"));
        return groomingGuideMapper.convertToDTO(groomingGuide);
    }

    @Override
    public List<GroomingGuideDto> getAllGroomingGuides() {
        List<GroomingGuide> groomingGuides = groomingGuideRepository.findAll();
        return groomingGuides.stream()
                .map(groomingGuideMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GroomingGuideDto createGroomingGuide(GroomingGuideDto groomingGuideDto) {
        groomingGuideDto.setCreateDate(LocalDate.now());
        groomingGuideDto.setLastUpdate(LocalDate.now());
        GroomingGuide groomingGuide = groomingGuideMapper.convertToEntity(groomingGuideDto);
        return groomingGuideMapper.convertToDTO(groomingGuideRepository.save(groomingGuide));
    }
    @Override
    public GroomingGuideDto addSuitableDogBreedToGroomingGuide(Long id, Long idDogBreed) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new CustomException("Adoption center not found"));

        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        if (!groomingGuide.getSuitableDogBreeds().contains(dogBreed)) {
            groomingGuide.getSuitableDogBreeds().add(dogBreed);
        }

        return groomingGuideMapper.convertToDTO(groomingGuideRepository.save(groomingGuide));
    }
    @Override
    public GroomingGuideDto addSuitableCatBreedToGroomingGuide(Long id, Long idCatBreed) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(id)
                .orElseThrow(() -> new CustomException("Adoption center not found"));
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        if (!groomingGuide.getSuitableCatBreeds().contains(catBreed)) {
            groomingGuide.getSuitableCatBreeds().add(catBreed);
        }
        return groomingGuideMapper.convertToDTO(groomingGuideRepository.save(groomingGuide));
    }

    @Override
    public List<DogBreedDto> getSuitableDogBreeds(Long groomingGuideId) {
        GroomingGuide groomingGuide = groomingGuideRepository.findById(groomingGuideId).orElseThrow();
        return groomingGuide.getSuitableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GroomingGuideDto updateGroomingGuide(GroomingGuideDto groomingGuideDto) {
        GroomingGuide oldGuide = groomingGuideRepository.findById(groomingGuideDto.getId())
                .orElseThrow(() -> new CustomException("Grooming guide with id " + groomingGuideDto.getId() + " not found"));

        GroomingGuide newGuide = groomingGuideMapper.convertToEntity(groomingGuideDto);
        newGuide.setCreateDate(oldGuide.getCreateDate());
        newGuide.setLastUpdate(LocalDate.now());

        return groomingGuideMapper.convertToDTO(groomingGuideRepository.save(newGuide));
    }

    @Override
    public void deleteGroomingGuide(Long id) {
        groomingGuideRepository.deleteById(id);
    }

    @Override
    public Page<GroomingGuideDto> getAllGroomingGuides(Pageable pageable) {
        Page<GroomingGuide> groomingGuides = groomingGuideRepository.findAll(pageable);
        return groomingGuides.map(groomingGuideMapper::convertToDTO);
    }
}
