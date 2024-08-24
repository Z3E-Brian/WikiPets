package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
import org.una.programmingIII.WikiPets.Repository.DogBreedRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DogBreedServiceImplementation implements DogBreedService {

    private final DogBreedRepository dogBreedRepository;
    private final GenericMapper<DogBreed,DogBreedDto> dogBreedMapper;

    @Autowired
    public DogBreedServiceImplementation(DogBreedRepository dogBreedRepository, GenericMapperFactory mapperFactory) {
        this.dogBreedRepository = dogBreedRepository;
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
    }

    @Override
    public List<DogBreedDto> getAllBreeds() {
        List<DogBreed> dogBreeds = dogBreedRepository.findAll();
        return dogBreeds.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public DogBreedDto getBreedById(Long id) {
        DogBreed dogBreed = dogBreedRepository.findById(id).orElseThrow(() -> new RuntimeException("Dog Breed Not Found with id: " + id));
        return convertToDto(dogBreed);
    }

    @Override
    public DogBreedDto createDogBreed(DogBreedDto dogBreedDto) {
        DogBreed dogBreed = convertToEntity(dogBreedDto);
        DogBreed savedDogBreed = dogBreedRepository.save(dogBreed);
        return convertToDto(savedDogBreed);
    }

    @Override
    public void deleteDogBreed(Long id) {
        dogBreedRepository.deleteById(id);
    }

    @Override
    public DogBreedDto updateDogBreed(DogBreedDto dogBreedDto) {
        DogBreed dogBreed = convertToEntity(dogBreedDto);
        DogBreed updatedDogBreed = dogBreedRepository.save(dogBreed);
        return convertToDto(updatedDogBreed);
    }

    private DogBreedDto convertToDto(DogBreed dogBreed) {
        return dogBreedMapper.convertToDTO(dogBreed);
    }

    private DogBreed convertToEntity(DogBreedDto dogBreedDto) {
        return dogBreedMapper.convertToEntity(dogBreedDto);
    }
}
