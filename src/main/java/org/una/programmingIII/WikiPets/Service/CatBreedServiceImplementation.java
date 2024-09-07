package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;

import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.time.LocalDate;

@Service
public class CatBreedServiceImplementation implements CatBreedService {
    private final CatBreedRepository catBreedRepository;
    private final GenericMapper<CatBreed,CatBreedDto> catBreedMapper;

    @Autowired
    public CatBreedServiceImplementation(CatBreedRepository catBreedRepository, GenericMapperFactory mapperFactory) {
        this.catBreedRepository = catBreedRepository;
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
    }

    @Override
    public Page<CatBreedDto> getAllCatBreeds(Pageable pageable) {
        Page<CatBreed> catBreedPage = catBreedRepository.findAll(pageable);
        return catBreedPage.map(this::convertToDto);
    }


    @Override
    public CatBreedDto getBreedById(Long id) {
        return catBreedRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new RuntimeException("Cat Breed Not Found with id: " + id));
    }

    @Override
    public CatBreedDto createCatBreed(CatBreedDto catBreedDto) {
        catBreedDto.setCreatedDate(LocalDate.now());
        catBreedDto.setModifiedDate(LocalDate.now());
        CatBreed catBreed = convertToEntity(catBreedDto);
        CatBreed savedCatBreed = catBreedRepository.save(catBreed);
        return convertToDto(savedCatBreed);
    }

    @Override
    public void deleteCatBreed(Long id) {
        CatBreed catBreed = catBreedRepository.findById(id).orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + id));
        catBreed.getAdoptionCenters().stream().map(adoptionCenter -> adoptionCenter.getAvailableCatBreeds().iterator()).forEach(iterator -> {
            while (iterator.hasNext()) {
                if (iterator.next().equals(catBreed)) {
                    iterator.remove();
                }
            }
        });
        catBreedRepository.deleteById(id);
    }

    @Override
    public CatBreedDto updateCatBreed(CatBreedDto catBreedDto) {
        CatBreed oldCatBreed = catBreedRepository.findById(catBreedDto.getId()).orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + catBreedDto.getId()));
        CatBreed newCatBreed = convertToEntity(catBreedDto);
        newCatBreed.setCreatedDate(oldCatBreed.getCreatedDate());
        newCatBreed.setModifiedDate(LocalDate.now());
        copyCollections(oldCatBreed, newCatBreed);

        return convertToDto(catBreedRepository.save(newCatBreed));
    }

    private CatBreedDto convertToDto(CatBreed catBreed) {return catBreedMapper.convertToDTO(catBreed);
    }

    private CatBreed convertToEntity(CatBreedDto catBreedDto) {return catBreedMapper.convertToEntity(catBreedDto);
    }
    private void copyCollections(CatBreed oldCatBreed, CatBreed newCatBreed) {
        newCatBreed.setAdoptionCenters(oldCatBreed.getAdoptionCenters());
        newCatBreed.setHealthIssues(oldCatBreed.getHealthIssues());
        newCatBreed.setNutritionGuides(oldCatBreed.getNutritionGuides());
        newCatBreed.setUsers(oldCatBreed.getUsers());
        newCatBreed.setTrainingGuides(oldCatBreed.getTrainingGuides());
        newCatBreed.setBehaviorGuides(oldCatBreed.getBehaviorGuides());
        newCatBreed.setCareTips(oldCatBreed.getCareTips());
        newCatBreed.setGroomingGuides(oldCatBreed.getGroomingGuides());
        newCatBreed.setFeedingSchedules(oldCatBreed.getFeedingSchedules());
        newCatBreed.setImages(oldCatBreed.getImages());
        newCatBreed.setVideos(oldCatBreed.getVideos());
        newCatBreed.setReviews(oldCatBreed.getReviews());
    }
}
