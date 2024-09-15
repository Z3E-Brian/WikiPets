package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DogBreedServiceImplementation implements DogBreedService {

    private final DogBreedRepository dogBreedRepository;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;

    @Autowired
    public DogBreedServiceImplementation(DogBreedRepository dogBreedRepository, GenericMapperFactory mapperFactory) {
        this.dogBreedRepository = dogBreedRepository;
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
    }

    @Override
    public DogBreedDto getBreedById(Long id) {
        return dogBreedRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + id));
    }

    @Override
    public DogBreed getBreedEntityById(Long id) {
        return dogBreedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + id));
    }

    @Override
    public DogBreedDto createDogBreed(DogBreedDto dogBreedDto) {
        dogBreedDto.setCreatedDate(LocalDate.now());
        dogBreedDto.setModifiedDate(LocalDate.now());
        DogBreed dogBreed = convertToEntity(dogBreedDto);
        DogBreed savedDogBreed = dogBreedRepository.save(dogBreed);
        return convertToDto(savedDogBreed);
    }

    @Override
    public void deleteDogBreed(Long id) {
        DogBreed dogBreed = dogBreedRepository.findById(id).orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + id));
        dogBreed.getAdoptionCenters().stream().map(adoptionCenter -> adoptionCenter.getAvailableDogBreeds().iterator()).forEach(iterator -> {
            while (iterator.hasNext()) {
                if (iterator.next().equals(dogBreed)) {
                    iterator.remove();
                }
            }
        });
        dogBreedRepository.deleteById(id);
    }

    @Override
    public DogBreedDto updateDogBreed(DogBreedDto dogBreedDto) {
        DogBreed oldDogBreed = dogBreedRepository.findById(dogBreedDto.getId()).orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + dogBreedDto.getId()));
        DogBreed newDogBreed = convertToEntity(dogBreedDto);
        newDogBreed.setCreatedDate(oldDogBreed.getCreatedDate());
        newDogBreed.setModifiedDate(LocalDate.now());
        copyCollections(oldDogBreed, newDogBreed);

        return convertToDto(dogBreedRepository.save(newDogBreed));
    }

    private DogBreedDto convertToDto(DogBreed dogBreed) {
        return dogBreedMapper.convertToDTO(dogBreed);
    }

    private DogBreed convertToEntity(DogBreedDto dogBreedDto) {
        return dogBreedMapper.convertToEntity(dogBreedDto);
    }

@Override
public Map<String, Object> getAllDogBreeds(int page, int size) {
    Page<DogBreed> dogBreedPage = dogBreedRepository.findAll(PageRequest.of(page, size));
    dogBreedPage.forEach(dogBreed -> {
        dogBreed.setAdoptionCenters(limitList(dogBreed.getAdoptionCenters()));
        dogBreed.setHealthIssues(limitList(dogBreed.getHealthIssues()));
        dogBreed.setNutritionGuides(limitList(dogBreed.getNutritionGuides()));
        dogBreed.setUsers(limitList(dogBreed.getUsers()));
        dogBreed.setTrainingGuides(limitList(dogBreed.getTrainingGuides()));
        dogBreed.setBehaviorGuides(limitList(dogBreed.getBehaviorGuides()));
        dogBreed.setCareTips(limitList(dogBreed.getCareTips()));
        dogBreed.setGroomingGuides(limitList(dogBreed.getGroomingGuides()));
    });
    Map<String, Object> response = new HashMap<>();
    response.put("dogBreeds", dogBreedPage.map(this::convertToDto).getContent());
    response.put("totalPages", dogBreedPage.getTotalPages());
    response.put("totalElements", dogBreedPage.getTotalElements());
    return response;
}
    private <T> List<T> limitList(List<T> list) {
        return list.stream().limit(10).collect(Collectors.toList());
    }

    private void copyCollections(DogBreed oldDogBreed, DogBreed newDogBreed) {
        newDogBreed.setAdoptionCenters(oldDogBreed.getAdoptionCenters());
        newDogBreed.setHealthIssues(oldDogBreed.getHealthIssues());
        newDogBreed.setNutritionGuides(oldDogBreed.getNutritionGuides());
        newDogBreed.setUsers(oldDogBreed.getUsers());
        newDogBreed.setTrainingGuides(oldDogBreed.getTrainingGuides());
        newDogBreed.setBehaviorGuides(oldDogBreed.getBehaviorGuides());
        newDogBreed.setCareTips(oldDogBreed.getCareTips());
        newDogBreed.setGroomingGuides(oldDogBreed.getGroomingGuides());
        newDogBreed.setFeedingSchedule(oldDogBreed.getFeedingSchedule());
        newDogBreed.setImages(oldDogBreed.getImages());
        newDogBreed.setVideos(oldDogBreed.getVideos());
        newDogBreed.setReviews(oldDogBreed.getReviews());
    }
}
