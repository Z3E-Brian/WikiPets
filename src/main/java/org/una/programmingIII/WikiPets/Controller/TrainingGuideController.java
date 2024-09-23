package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Input.TrainingGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.TrainingGuideService;


import java.util.Map;

@RequiredArgsConstructor
@Controller
public class TrainingGuideController {

    private final TrainingGuideService trainingGuideService;
    private final GenericMapper<TrainingGuideInput, TrainingGuideDto> trainingGuideMapper;

    @Autowired
    TrainingGuideController(TrainingGuideService trainingGuideService, GenericMapperFactory mapperFactory) {
        this.trainingGuideService = trainingGuideService;
        this.trainingGuideMapper = mapperFactory.createMapper(TrainingGuideInput.class, TrainingGuideDto.class);
    }

    @QueryMapping
    public Map<String, Object> getTrainingGuides(@Argument int page, @Argument int size) {
        try {
            return trainingGuideService.getAllTrainingGuides(page, size);
        } catch (NotFoundElementException e) {
            throw new NotFoundElementException("Could not retrieve feeding schedules" + e.getMessage());
        }
    }

    @QueryMapping
    public TrainingGuideDto getTrainingGuideById(@Argument Long id) {
        return trainingGuideService.getTrainingGuideById(id);
    }

    @MutationMapping
    public TrainingGuideDto createTrainingGuide(@Argument TrainingGuideInput input) {
        TrainingGuideDto trainingGuideDto = convertToDto(input);
        return trainingGuideService.createTrainingGuide(trainingGuideDto);
    }

    @MutationMapping
    public TrainingGuideDto updateTrainingGuide(@Argument TrainingGuideInput input) {
        TrainingGuideDto trainingGuideDto = convertToDto(input);
        return trainingGuideService.updateTrainingGuide(trainingGuideDto);
    }

    @MutationMapping
    public Boolean deleteTrainingGuide(@Argument Long id) {
        return trainingGuideService.deleteTrainingGuide(id);
    }

    @MutationMapping
    public TrainingGuideDto addDogBreedInTrainingGuide(@Argument Long id, @Argument Long idDogBreed) {
        return trainingGuideService.addDogBreedInTrainingGuide(id, idDogBreed);
    }

    @MutationMapping
    public TrainingGuideDto deleteDogBreedInTrainingGuide(@Argument Long id, @Argument Long idDogBreed) {
        return trainingGuideService.deleteDogBreedInTrainingGuide(id, idDogBreed);
    }


    @MutationMapping
    public TrainingGuideDto addCatBreedInTrainingGuide(@Argument Long id, @Argument Long idCatBreed) {
        return trainingGuideService.addCatBreedInTrainingGuide(id, idCatBreed);
    }

    @MutationMapping
    public TrainingGuideDto deleteCatBreedInTrainingGuide(@Argument Long id, @Argument Long idCatBreed) {
        return trainingGuideService.deleteCatBreedInTrainingGuide(id, idCatBreed);
    }


    private TrainingGuideDto convertToDto(TrainingGuideInput traininigGuideInput) {
        return trainingGuideMapper.convertToDTO(traininigGuideInput);
    }
}
