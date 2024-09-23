package org.una.programmingIII.WikiPets.Service;

import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.CatBreed;

import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CatBreedServiceImplementation implements CatBreedService {
    private final CatBreedRepository catBreedRepository;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final Faker faker = new Faker();

    @Autowired
    public CatBreedServiceImplementation(CatBreedRepository catBreedRepository, GenericMapperFactory mapperFactory) {
        this.catBreedRepository = catBreedRepository;
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
    }

    @Override
    public CatBreedDto getBreedById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid CatBreed ID");
        }
        return catBreedRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundElementException("Dog Breed Not Found with id: " + id));
    }

    @Override
    public CatBreed getBreedEntityById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid CatBreed ID");
        }
        return catBreedRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Dog Breed Not Found with id: " + id));
    }

    @Override
    public CatBreedDto createCatBreed(CatBreedDto catBreedDto) {
        if (catBreedDto.getName().isBlank()) {
            throw new InvalidInputException("Invalid catBreed Name");
        }
        catBreedDto.setCreatedDate(LocalDate.now());
        catBreedDto.setModifiedDate(LocalDate.now());
        CatBreed catBreed = convertToEntity(catBreedDto);
        CatBreed savedCatBreed = catBreedRepository.save(catBreed);
        return convertToDto(savedCatBreed);
    }

    @Override
    public Boolean deleteCatBreed(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Invalid CatBreed ID");
        }
        CatBreed catBreed = catBreedRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Dog Breed Not Found with id: " + id));

        removeCatBreedReferences(catBreed, id);
        catBreedRepository.deleteById(id);
        return true;
    }

    private void removeCatBreedReferences(CatBreed catBreed, Long id) {
        if (catBreed.getAdoptionCenters() != null) {
            catBreed.getAdoptionCenters().forEach(adoptionCenter -> {
                if (adoptionCenter.getAvailableCatBreeds() != null) {
                    adoptionCenter.getAvailableCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
                }
            });
        }
        if (catBreed.getHealthIssues() != null) {
            catBreed.getHealthIssues().forEach(healthIssue -> {
                if (healthIssue.getSuitableCatBreeds() != null) {
                    healthIssue.getSuitableCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
                }
            });
        }
        if (catBreed.getNutritionGuides() != null) {
            catBreed.getNutritionGuides().forEach(nutritionGuide -> {
                if (nutritionGuide.getRecommendedCatBreeds() != null) {
                    nutritionGuide.getRecommendedCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
                }
            });
        }
        if (catBreed.getUsers() != null) {
            catBreed.getUsers().forEach(user -> {
                if (user.getFavoriteCatBreeds() != null) {
                    user.getFavoriteCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
                }
            });
        }
        if (catBreed.getTrainingGuides() != null) {
            catBreed.getTrainingGuides().forEach(trainingGuide -> {
                if (trainingGuide.getCatBreeds() != null) {
                    trainingGuide.getCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
                }
            });
        }
        if (catBreed.getBehaviorGuides() != null) {
            catBreed.getBehaviorGuides().forEach(behaviorGuide -> {
                if (behaviorGuide.getSuitableCatBreeds() != null) {
                    behaviorGuide.getSuitableCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
                }
            });
        }
        if (catBreed.getCareTips() != null) {
            catBreed.getCareTips().forEach(careTip -> {
                if (careTip.getRelevantCatBreeds() != null) {
                    careTip.getRelevantCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
                }
            });
        }
        if (catBreed.getGroomingGuides() != null) {
            catBreed.getGroomingGuides().forEach(groomingGuide -> {
                if (groomingGuide.getSuitableCatBreeds() != null) {
                    groomingGuide.getSuitableCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
                }
            });
        }
        if (catBreed.getFeedingSchedule() != null) {
            if (catBreed.getFeedingSchedule().getCatBreeds() != null) {
                catBreed.getFeedingSchedule().getCatBreeds().removeIf(catBreed1 -> catBreed1.getId().equals(id));
            }
        }
    }


    @Override
    public CatBreedDto updateCatBreed(CatBreedDto catBreedDto) {
        if (catBreedDto.getId() <= 0) {
            throw new InvalidInputException("Invalid catBreed ID");
        }

        if (catBreedDto.getName().isBlank()) {
            throw new BlankInputException("Cannot create CatBreed with empty name");
        }
        CatBreed oldCatBreed = catBreedRepository.findById(catBreedDto.getId()).orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + catBreedDto.getId()));
        CatBreed newCatBreed = convertToEntity(catBreedDto);
        newCatBreed.setCreatedDate(oldCatBreed.getCreatedDate());
        newCatBreed.setModifiedDate(LocalDate.now());
        copyCollections(oldCatBreed, newCatBreed);

        return convertToDto(catBreedRepository.save(newCatBreed));
    }

    @Override
    public Map<String, Object> getAllCatBreeds(int page, int size, int limit) {
        Page<CatBreed> catBreedPage = catBreedRepository.findAll(PageRequest.of(page, size));
        catBreedPage.forEach(catBreed -> {
            catBreed.setAdoptionCenters(limitListOrDefault(catBreed.getAdoptionCenters(), limit));
            catBreed.setHealthIssues(limitListOrDefault(catBreed.getHealthIssues(), limit));
            catBreed.setNutritionGuides(limitListOrDefault(catBreed.getNutritionGuides(), limit));
            catBreed.setUsers(limitListOrDefault(catBreed.getUsers(), limit));
            catBreed.setTrainingGuides(limitListOrDefault(catBreed.getTrainingGuides(), limit));
            catBreed.setBehaviorGuides(limitListOrDefault(catBreed.getBehaviorGuides(), limit));
            catBreed.setCareTips(limitListOrDefault(catBreed.getCareTips(), limit));
            catBreed.setGroomingGuides(limitListOrDefault(catBreed.getGroomingGuides(), limit));
        });
        Map<String, Object> response = new HashMap<>();
        response.put("catBreeds", catBreedPage.map(this::convertToDto).getContent());
        response.put("totalPages", catBreedPage.getTotalPages());
        response.put("totalElements", catBreedPage.getTotalElements());
        return response;
    }

    private <T> List<T> limitListOrDefault(List<T> list, int limit) {
        return list == null ? new ArrayList<>() : limitList(list, limit);
    }

    private <T> List<T> limitList(List<T> list, int limit) {
        return list.stream().limit(limit).collect(Collectors.toList());
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

    private CatBreedDto convertToDto(CatBreed catBreed) {
        return catBreedMapper.convertToDTO(catBreed);
    }

    private CatBreed convertToEntity(CatBreedDto catBreedDto) {
        return catBreedMapper.convertToEntity(catBreedDto);
    }


    @Override
    @Transactional
    public void generateFalseCatBreed(int amount) {
        String[] coats = {"Short", "Medium", "Long"};
        String[] temperaments = {"Friendly", "Independent", "Curious", "Affectionate"};
        String[] lifeSpans = {"12-15 years", "15-20 years", "10-12 years"};

        for (int i = 0; i < amount; i++) {
            CatBreed catBreed = new CatBreed();
            catBreed.setName(faker.cat().name());
            catBreed.setOrigin(faker.cat().breed());
            catBreed.setLifeSpan(lifeSpans[faker.number().numberBetween(0, lifeSpans.length)]);
            catBreed.setSize(faker.number().numberBetween(1, 5));
            catBreed.setCoat(coats[faker.number().numberBetween(0, coats.length)]);
            catBreed.setColor(faker.color().name());
            catBreed.setTemperament(temperaments[faker.number().numberBetween(0, temperaments.length)]);
            catBreed.setDescription(faker.lorem().paragraph());
            catBreed.setCreatedDate(LocalDate.now());
            catBreed.setModifiedDate(LocalDate.now());

            catBreedRepository.save(catBreed);
        }
    }
}
