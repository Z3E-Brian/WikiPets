package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;

import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CatBreedServiceImplementation implements CatBreedService {
    private final CatBreedRepository catBreedRepository;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;

    @Autowired
    public CatBreedServiceImplementation(CatBreedRepository catBreedRepository, GenericMapperFactory mapperFactory) {
        this.catBreedRepository = catBreedRepository;
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
    }

    @Override
    public Map<String, Object> getAllCatBreeds(int page, int size) {
        Page<CatBreed> catBreedPage = catBreedRepository.findAll(PageRequest.of(page, size));
        catBreedPage.forEach(catBreed -> {
            catBreed.setAdoptionCenters(limitList(catBreed.getAdoptionCenters()));
            catBreed.setHealthIssues(limitList(catBreed.getHealthIssues()));
            catBreed.setNutritionGuides(limitList(catBreed.getNutritionGuides()));
            catBreed.setUsers(limitList(catBreed.getUsers()));
            catBreed.setTrainingGuides(limitList(catBreed.getTrainingGuides()));
            catBreed.setBehaviorGuides(limitList(catBreed.getBehaviorGuides()));
            catBreed.setCareTips(limitList(catBreed.getCareTips()));
            catBreed.setGroomingGuides(limitList(catBreed.getGroomingGuides()));
        });
        return Map.of(
                "catBreeds", catBreedPage.map(this::convertToDto).getContent(),
                "totalPages", catBreedPage.getTotalPages(),
                "totalElements", catBreedPage.getTotalElements()
        );
    }

    private <T> List<T> limitList(List<T> list) {
        return list.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public CatBreedDto getBreedById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid CatBreed ID");
        }
        return catBreedRepository.findById(id).map(this::convertToDto).orElseThrow(()
                -> new NotFoundElementException("Cat Breed Not Found with id: " + id));
    }

    @Override
    public CatBreedDto createCatBreed(CatBreedDto catBreedDto) {

        if (catBreedDto.getName().isBlank()) {
            throw new BlankInputException("Cannot create CatBreed with empty name");
        }
        catBreedDto.setCreatedDate(LocalDate.now());
        catBreedDto.setModifiedDate(LocalDate.now());
        CatBreed catBreed = convertToEntity(catBreedDto);
        CatBreed savedCatBreed = catBreedRepository.save(catBreed);
        return convertToDto(savedCatBreed);
    }

    @Override
    public Boolean deleteCatBreed(Long id) {
        CatBreed catBreed = catBreedRepository.findById(id).orElseThrow(() -> new NotFoundElementException("Dog Breed Not Found with id: " + id));
        catBreed.getAdoptionCenters().stream().map(adoptionCenter -> adoptionCenter.getAvailableCatBreeds().iterator()).forEach(iterator -> {
            while (iterator.hasNext()) {
                if (iterator.next().equals(catBreed)) {
                    iterator.remove();
                }
            }
        });
        catBreedRepository.deleteById(id);
        return true;
    }

    @Override
    public CatBreedDto updateCatBreed(CatBreedDto catBreedDto) {

        if (catBreedDto.getId() <= 0) {
            throw new InvalidInputException("Invalid catBreed ID");
        }

        if (catBreedDto.getName().isBlank()) {
            throw new BlankInputException("Cannot create CatBreed with empty name");
        }

        CatBreed oldCatBreed = catBreedRepository.findById(catBreedDto.getId()).orElseThrow(() -> new NotFoundElementException("Dog Breed Not Found with id: " + catBreedDto.getId()));
        CatBreed newCatBreed = convertToEntity(catBreedDto);
        newCatBreed.setCreatedDate(oldCatBreed.getCreatedDate());
        newCatBreed.setModifiedDate(LocalDate.now());
        copyCollections(oldCatBreed, newCatBreed);

        return convertToDto(catBreedRepository.save(newCatBreed));
    }

    private CatBreedDto convertToDto(CatBreed catBreed) {
        return catBreedMapper.convertToDTO(catBreed);
    }

    private CatBreed convertToEntity(CatBreedDto catBreedDto) {
        return catBreedMapper.convertToEntity(catBreedDto);
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
        newCatBreed.setFeedingSchedule(oldCatBreed.getFeedingSchedule());
        newCatBreed.setImages(oldCatBreed.getImages());
        newCatBreed.setVideos(oldCatBreed.getVideos());
        newCatBreed.setReviews(oldCatBreed.getReviews());
    }
}
