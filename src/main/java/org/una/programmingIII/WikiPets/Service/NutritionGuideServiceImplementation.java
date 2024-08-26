package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.NutritionGuide;
import org.una.programmingIII.WikiPets.Dto.NutritionGuideDto;
import org.una.programmingIII.WikiPets.Repository.NutritionGuideRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionGuideServiceImplementation implements NutritionGuideService {
    private final NutritionGuideRepository nutritionGuideRepository;
    private final GenericMapper<NutritionGuide,NutritionGuideDto> nutritionGuideMapper;

    @Autowired
    public NutritionGuideServiceImplementation(NutritionGuideRepository nutritionGuideRepository, GenericMapperFactory mapperFactory) {
        this.nutritionGuideRepository = nutritionGuideRepository;
        this.nutritionGuideMapper = mapperFactory.createMapper(NutritionGuide.class, NutritionGuideDto.class);
    }

    @Override
    public List<NutritionGuideDto> getAllNutritionGuides() {
        return nutritionGuideRepository.findAll().stream()
                .map(nutritionGuideMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NutritionGuideDto getNutritionGuideById(Long id) {
        NutritionGuide nutritionGuide = nutritionGuideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutrition Guide not found"));
        return nutritionGuideMapper.convertToDTO(nutritionGuide);
    }

    @Override
    public NutritionGuideDto getNutritionGuideByTitle(String title) {
        NutritionGuide nutritionGuide = nutritionGuideRepository.findByTitle(title);
        if (nutritionGuide == null) {
            throw new RuntimeException("Nutrition Guide not found");
        }
        return nutritionGuideMapper.convertToDTO(nutritionGuide);
    }

    @Override
    public NutritionGuideDto createNutritionGuide(NutritionGuideDto nutritionGuideDto) {
        NutritionGuide nutritionGuide = nutritionGuideMapper.convertToEntity(nutritionGuideDto);
        return nutritionGuideMapper.convertToDTO(nutritionGuideRepository.save(nutritionGuide));
    }

    @Override
    public void deleteNutritionGuide(Long id) {
        nutritionGuideRepository.deleteById(id);
    }

    @Override
    public NutritionGuideDto updateNutritionGuide(NutritionGuideDto nutritionGuideDto) {
        NutritionGuide nutritionGuide = nutritionGuideMapper.convertToEntity(nutritionGuideDto);
        return nutritionGuideMapper.convertToDTO(nutritionGuideRepository.save(nutritionGuide));
    }

}
