package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.DogBreedMapper;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DogBreedService {

    private final DogBreedRepository dogBreedRepository;
    private final DogBreedMapper dogBreedMapper;

    @Autowired
    public DogBreedService(DogBreedRepository dogBreedRepository) {
        this.dogBreedRepository = dogBreedRepository;
        this.dogBreedMapper = DogBreedMapper.INSTANCE;
    }

    public List<DogBreedDto> getAllBreeds() {
        List<DogBreed> dogBreeds = dogBreedRepository.findAll();
        return dogBreeds.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DogBreedDto getBreedById(Long id) {
        DogBreed dogBreed = dogBreedRepository.findById(id).orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + id));
        return convertToDto(dogBreed);
    }

    public DogBreedDto createDogBreed(DogBreedDto dogBreedDto) {
        DogBreed dogBreed = convertToEntity(dogBreedDto);
        DogBreed savedDogBreed = dogBreedRepository.save(dogBreed);
        return convertToDto(savedDogBreed);
    }

    public void deleteDogBreed(Long id) {
        dogBreedRepository.deleteById(id);
    }

    public DogBreedDto updateDogBreed(DogBreedDto dogBreedDto) {
        DogBreed dogBreed = convertToEntity(dogBreedDto);
        DogBreed updatedDogBreed = dogBreedRepository.save(dogBreed);
        return convertToDto(updatedDogBreed);
    }

    private DogBreedDto convertToDto(DogBreed dogBreed) {
        return dogBreedMapper.toDogBreedDto(dogBreed);
    }

    private DogBreed convertToEntity(DogBreedDto dogBreedDto) {
        return dogBreedMapper.toDogBreed(dogBreedDto);
    }
}
