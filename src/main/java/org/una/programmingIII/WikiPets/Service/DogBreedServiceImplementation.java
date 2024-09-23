package org.una.programmingIII.WikiPets.Service;

import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DogBreedServiceImplementation implements DogBreedService {

    private final DogBreedRepository dogBreedRepository;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final Faker faker = new Faker();
    @Autowired
    public DogBreedServiceImplementation(DogBreedRepository dogBreedRepository, GenericMapperFactory mapperFactory) {
        this.dogBreedRepository = dogBreedRepository;
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
    }

    @Override
    public DogBreedDto getBreedById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid DogBreed ID");
        }
        return dogBreedRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundElementException("Dog Breed Not Found with id: " + id));
    }

    @Override
    public DogBreed getBreedEntityById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid DogBreed ID");
        }
        return dogBreedRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Dog Breed Not Found with id: " + id));
    }

    @Override
    public DogBreedDto createDogBreed(DogBreedDto dogBreedDto) {
        if (dogBreedDto.getName().isBlank()) {
            throw new InvalidInputException("Invalid dogBreed Name");
        }
        dogBreedDto.setCreatedDate(LocalDate.now());
        dogBreedDto.setModifiedDate(LocalDate.now());
        DogBreed dogBreed = convertToEntity(dogBreedDto);
        DogBreed savedDogBreed = dogBreedRepository.save(dogBreed);
        return convertToDto(savedDogBreed);
    }

 @Override
public Boolean deleteDogBreed(Long id) {
    if (id == null || id <= 0) {
        throw new InvalidInputException("Invalid DogBreed ID");
    }
    DogBreed dogBreed = dogBreedRepository.findById(id)
            .orElseThrow(() -> new NotFoundElementException("Dog Breed Not Found with id: " + id));

    removeDogBreedReferences(dogBreed, id);
    dogBreedRepository.deleteById(id);
    return true;
}

    private void removeDogBreedReferences(DogBreed dogBreed, Long id) {
        if (dogBreed.getAdoptionCenters() != null) {
            dogBreed.getAdoptionCenters().forEach(adoptionCenter -> {
                if (adoptionCenter.getAvailableDogBreeds() != null) {
                    adoptionCenter.getAvailableDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
                }
            });
        }
        if (dogBreed.getHealthIssues() != null) {
            dogBreed.getHealthIssues().forEach(healthIssue -> {
                if (healthIssue.getSuitableDogBreeds() != null) {
                    healthIssue.getSuitableDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
                }
            });
        }
        if (dogBreed.getNutritionGuides() != null) {
            dogBreed.getNutritionGuides().forEach(nutritionGuide -> {
                if (nutritionGuide.getRecommendedDogBreeds() != null) {
                    nutritionGuide.getRecommendedDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
                }
            });
        }
        if (dogBreed.getUsers() != null) {
            dogBreed.getUsers().forEach(user -> {
                if (user.getFavoriteDogBreeds() != null) {
                    user.getFavoriteDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
                }
            });
        }
        if (dogBreed.getTrainingGuides() != null) {
            dogBreed.getTrainingGuides().forEach(trainingGuide -> {
                if (trainingGuide.getDogBreeds() != null) {
                    trainingGuide.getDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
                }
            });
        }
        if (dogBreed.getBehaviorGuides() != null) {
            dogBreed.getBehaviorGuides().forEach(behaviorGuide -> {
                if (behaviorGuide.getSuitableDogBreeds() != null) {
                    behaviorGuide.getSuitableDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
                }
            });
        }
        if (dogBreed.getCareTips() != null) {
            dogBreed.getCareTips().forEach(careTip -> {
                if (careTip.getRelevantDogBreeds() != null) {
                    careTip.getRelevantDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
                }
            });
        }
        if (dogBreed.getGroomingGuides() != null) {
            dogBreed.getGroomingGuides().forEach(groomingGuide -> {
                if (groomingGuide.getSuitableDogBreeds() != null) {
                    groomingGuide.getSuitableDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
                }
            });
        }
        if (dogBreed.getFeedingSchedule() != null) {
            if (dogBreed.getFeedingSchedule().getDogBreeds() != null) {
                dogBreed.getFeedingSchedule().getDogBreeds().removeIf(dogBreed1 -> dogBreed1.getId().equals(id));
            }
        }
    }


    @Override
    public DogBreedDto updateDogBreed(DogBreedDto dogBreedDto) {
        if (dogBreedDto.getId() <= 0) {
            throw new InvalidInputException("Invalid dogBreed ID");
        }

        if (dogBreedDto.getName().isBlank()) {
            throw new BlankInputException("Cannot create DogBreed with empty name");
        }
        DogBreed oldDogBreed = dogBreedRepository.findById(dogBreedDto.getId()).orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + dogBreedDto.getId()));
        DogBreed newDogBreed = convertToEntity(dogBreedDto);
        newDogBreed.setCreatedDate(oldDogBreed.getCreatedDate());
        newDogBreed.setModifiedDate(LocalDate.now());
        copyCollections(oldDogBreed, newDogBreed);

        return convertToDto(dogBreedRepository.save(newDogBreed));
    }

    @Override
    public Map<String, Object> getAllDogBreeds(int page, int size,int limit) {
        Page<DogBreed> dogBreedPage = dogBreedRepository.findAll(PageRequest.of(page, size));
        dogBreedPage.forEach(dogBreed -> {
            dogBreed.setAdoptionCenters(limitListOrDefault(dogBreed.getAdoptionCenters(),limit));
            dogBreed.setHealthIssues(limitListOrDefault(dogBreed.getHealthIssues(),limit));
            dogBreed.setNutritionGuides(limitListOrDefault(dogBreed.getNutritionGuides(),limit));
            dogBreed.setUsers(limitListOrDefault(dogBreed.getUsers(),limit));
            dogBreed.setTrainingGuides(limitListOrDefault(dogBreed.getTrainingGuides(),limit));
            dogBreed.setBehaviorGuides(limitListOrDefault(dogBreed.getBehaviorGuides(),limit));
            dogBreed.setCareTips(limitListOrDefault(dogBreed.getCareTips(),limit));
            dogBreed.setGroomingGuides(limitListOrDefault(dogBreed.getGroomingGuides(),limit));
        });
        Map<String, Object> response = new HashMap<>();
        response.put("dogBreeds", dogBreedPage.map(this::convertToDto).getContent());
        response.put("totalPages", dogBreedPage.getTotalPages());
        response.put("totalElements", dogBreedPage.getTotalElements());
        return response;
    }

    private <T> List<T> limitListOrDefault(List<T> list,int limit) {
        return list == null ? new ArrayList<>() : limitList(list,limit);
    }

    private <T> List<T> limitList(List<T> list,int limit) {
        return list.stream().limit(limit).collect(Collectors.toList());
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

    private DogBreedDto convertToDto(DogBreed dogBreed) {
        return dogBreedMapper.convertToDTO(dogBreed);
    }

    private DogBreed convertToEntity(DogBreedDto dogBreedDto) {
        return dogBreedMapper.convertToEntity(dogBreedDto);
    }
    @Override
    @Transactional
    public void generateFalseDogBreed(int amount) {
        String[] coats = {"Short", "Medium", "Long"};
        String[] temperaments = {"Friendly", "Independent", "Curious", "Affectionate"};
        String[] lifeSpans = {"12-15 years", "15-20 years", "10-12 years"};

        for (int i = 0; i < amount; i++) {
            DogBreed dogBreed = new DogBreed();
            dogBreed.setName(faker.dog().name());
            dogBreed.setOrigin(faker.dog().breed());
            dogBreed.setLifeSpan(lifeSpans[faker.number().numberBetween(0, lifeSpans.length)]);
            dogBreed.setSize(faker.number().numberBetween(1, 5));
            dogBreed.setCoat(coats[faker.number().numberBetween(0, coats.length)]);
            dogBreed.setColor(faker.color().name());
            dogBreed.setTemperament(temperaments[faker.number().numberBetween(0, temperaments.length)]);
            dogBreed.setDescription(faker.lorem().paragraph());
            dogBreed.setCreatedDate(LocalDate.now());
            dogBreed.setModifiedDate(LocalDate.now());

            dogBreedRepository.save(dogBreed);
        }
    }

}
