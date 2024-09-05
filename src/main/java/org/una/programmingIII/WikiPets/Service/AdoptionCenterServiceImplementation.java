package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.AdoptionCenterRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdoptionCenterServiceImplementation implements AdoptionCenterService {

    private final AdoptionCenterRepository adoptionCenterRepository;
    private final GenericMapper<AdoptionCenter, AdoptionCenterDto> adoptionCenterMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final DogBreedService dogBreedService;

    @Autowired
    public AdoptionCenterServiceImplementation(AdoptionCenterRepository repository, GenericMapperFactory mapperFactory, DogBreedService breedService) {
        this.adoptionCenterRepository = repository;
        this.dogBreedService = breedService;
        this.adoptionCenterMapper = mapperFactory.createMapper(AdoptionCenter.class, AdoptionCenterDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
    }

    @Override
    public AdoptionCenterDto getAdoptionCenterById(Long id) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(id).orElseThrow();
        return adoptionCenterMapper.convertToDTO(adoptionCenter);
    }

    @Override
    public List<AdoptionCenterDto> getAllAdoptionCenters() {
        List<AdoptionCenter> adoptionCenters = adoptionCenterRepository.findAll();
        List<AdoptionCenterDto> adoptionCenterDtos = adoptionCenterMapper.convertToDTOList(adoptionCenters);

        for (AdoptionCenterDto adoptionCenterDto : adoptionCenterDtos) {
            AdoptionCenter adoptionCenter = adoptionCenters.stream()
                    .filter(ac -> ac.getId().equals(adoptionCenterDto.getId()))
                    .findFirst()
                    .orElse(null);

            if (adoptionCenter != null && adoptionCenter.getAvailableDogBreeds() != null) {
                adoptionCenterDto.setAvailableDogBreeds(adoptionCenter.getAvailableDogBreeds().stream()
                        .map(dogBreedMapper::convertToDTO)
                        .collect(Collectors.toList()));
            }
            System.out.println(adoptionCenterDto.getAvailableDogBreeds());
        }
        return adoptionCenterDtos;
}

    @Override
    public AdoptionCenterDto createAdoptionCenter(AdoptionCenterDto adoptionCenterDto) {
        AdoptionCenter adoptionCenter = adoptionCenterMapper.convertToEntity(adoptionCenterDto);
        adoptionCenterDto.setLastUpdate(LocalDate.now());
        adoptionCenterDto.setCreateDate(LocalDate.now());
        adoptionCenterDto.setAvailableCatBreeds(null);
        adoptionCenterDto.setAvailableDogBreeds(null);
        return adoptionCenterMapper.convertToDTO(adoptionCenterRepository.save(adoptionCenter));
    }

@Override
public AdoptionCenterDto updateAdoptionCenter(AdoptionCenterDto adoptionCenterDto) {
    AdoptionCenter oldCenter = adoptionCenterRepository.findById(adoptionCenterDto.getId()).orElseThrow();
    AdoptionCenter newCenter = adoptionCenterMapper.convertToEntity(adoptionCenterDto);
    newCenter.setCreateDate(oldCenter.getCreateDate());
    newCenter.getAvailableCatBreeds().addAll(oldCenter.getAvailableCatBreeds());
    newCenter.getAvailableDogBreeds().addAll(oldCenter.getAvailableDogBreeds());
    newCenter.setLastUpdate(LocalDate.now());
    return adoptionCenterMapper.convertToDTO(adoptionCenterRepository.save(newCenter));
}
    @Override
    public AdoptionCenterDto addDogBreedInAdoptionCenter(Long id, Long idDogBreed) {
        AdoptionCenterDto adoptionCenterDto = getAdoptionCenterById(id);
        DogBreedDto dogBreedDto = dogBreedService.getBreedById(idDogBreed);
        adoptionCenterDto.getAvailableDogBreeds().add(dogBreedDto);
        return updateAdoptionCenter(adoptionCenterDto);
    }

    @Override
    public List<DogBreedDto> getAvailableDogBreeds(Long adoptionCenterId) {
        AdoptionCenter adoptionCenter = adoptionCenterRepository.findById(adoptionCenterId).orElseThrow();
        return adoptionCenter.getAvailableDogBreeds().stream()
                .map(dogBreedMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdoptionCenter(Long id) {
        adoptionCenterRepository.deleteById(id);
    }
}
