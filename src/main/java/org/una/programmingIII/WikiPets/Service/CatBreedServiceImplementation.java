package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.CatBreedMapper;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.CatBreedDto;
import org.una.programmingIII.WikiPets.Repository.CatBreedRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatBreedServiceImplementation implements CatBreedService {
    private final CatBreedRepository catBreedRepository;
    private final CatBreedMapper catBreedMapper;

    @Autowired
    public CatBreedServiceImplementation(CatBreedRepository catBreedRepository) {
        this.catBreedRepository = catBreedRepository;
        this.catBreedMapper = CatBreedMapper.INSTANCE;
    }

    @Override
    public List<CatBreedDto> getAllBreeds() {
        List<CatBreed> CatBreeds = catBreedRepository.findAll();
        return CatBreeds.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CatBreedDto getBreedById(Long id) {
        CatBreed catBreed = catBreedRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat Breed Not Found with id: " + id));
        return convertToDto(catBreed);
    }

    @Override
    public CatBreedDto createCatBreed(CatBreedDto catBreedDto) {
        CatBreed catBreed = convertToEntity(catBreedDto);
        CatBreed savedCatBreed = catBreedRepository.save(catBreed);
        return convertToDto(savedCatBreed);
    }

    @Override
    public void deleteCatBreed(Long id) {
        catBreedRepository.deleteById(id);
    }

    @Override
    public CatBreedDto updateCatBreed(CatBreedDto catBreedDto) {
        CatBreed catBreed = convertToEntity(catBreedDto);
        CatBreed updatedCatBreed = catBreedRepository.save(catBreed);
        return convertToDto(updatedCatBreed);
    }

    private CatBreedDto convertToDto(CatBreed catBreed) {
        return catBreedMapper.toCatBreedDto(catBreed);
    }

    private CatBreed convertToEntity(CatBreedDto catBreedDto) {
        return catBreedMapper.toCatBreed(catBreedDto);
    }
}
