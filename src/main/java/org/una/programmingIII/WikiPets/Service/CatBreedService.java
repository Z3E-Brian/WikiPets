package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.CatBreedDto;
import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatBreedService {
    private final CatBreedRepository catBreedRepository;

    @Autowired
    public CatBreedService(CatBreedRepository catBreedRepository) {
        this.catBreedRepository = catBreedRepository;
    }

    public List<CatBreedDto> getAllBreeds() {
        List<CatBreed> CatBreeds = catBreedRepository.findAll();
        return CatBreeds.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CatBreedDto getBreedById(Long id) {
        CatBreed catBreed = catBreedRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat Breed Not Found with id: " + id));
        return convertToDto(catBreed);
    }

    public CatBreedDto createCatBreed(CatBreedDto catBreedDto) {
        CatBreed catBreed = convertToEntity(catBreedDto);
        CatBreed savedCatBreed = catBreedRepository.save(catBreed);
        return convertToDto(savedCatBreed);
    }

    public void deleteCatBreed(Long id) {
        catBreedRepository.deleteById(id);
    }

    public CatBreedDto updateCatBreed(CatBreedDto catBreedDto) {
        CatBreed catBreed = convertToEntity(catBreedDto);
        CatBreed updatedCatBreed = catBreedRepository.save(catBreed);
        return convertToDto(updatedCatBreed);
    }

    private CatBreedDto convertToDto(CatBreed catBreed) {
        return new CatBreedDto(
                catBreed.getId(),
                catBreed.getName(),
                catBreed.getOrigin(),
                catBreed.getSize(),
                catBreed.getCoat(),
                catBreed.getColor(),
                catBreed.getLifeSpan(),
                catBreed.getTemperament(),
                catBreed.getDescription()
        );
    }

    private CatBreed convertToEntity(CatBreedDto catBreedDto) {
        return new CatBreed(
                catBreedDto.getId(),
                catBreedDto.getName(),
                catBreedDto.getOrigin(),
                catBreedDto.getSize(),
                catBreedDto.getCoat(),
                catBreedDto.getColor(),
                catBreedDto.getLifeSpan(),
                catBreedDto.getTemperament(),
                catBreedDto.getDescription()
        );
    }
}
