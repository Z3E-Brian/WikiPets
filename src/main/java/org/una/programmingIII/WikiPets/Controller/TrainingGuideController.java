package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.TrainingGuideDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.TrainingGuideInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.TrainingGuideService;


import java.util.HashMap;
import java.util.List;
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
        Pageable pageable = PageRequest.of(page, size);
        Page<TrainingGuideDto> trainingGuideDtoPage = trainingGuideService.getAllTrainingGuides(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("trainingGuides", trainingGuideDtoPage.getContent());
        response.put("totalPages", trainingGuideDtoPage.getTotalPages());
        response.put("totalElements", trainingGuideDtoPage.getTotalElements());

        return response;
    }

    @QueryMapping
    public List<TrainingGuideDto> getAllTrainingGuides() {
        try {
            return trainingGuideService.getAllTrainingGuides();
        } catch (Exception e) {
            throw new CustomException("Could not find adoption center" + e.getMessage());
        }
    }

    @QueryMapping
    public TrainingGuideDto getTrainingGuideById(@Argument Long id) {
        try {
            return trainingGuideService.getTrainingGuideById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find adoption center" + e.getMessage());
        }
    }

    @MutationMapping
    public TrainingGuideDto createTrainingGuide(@Argument TrainingGuideInput input) {
        try {
            TrainingGuideDto trainingGuideDto = convertToDto(input);
            return trainingGuideService.createTrainingGuide(trainingGuideDto);
        } catch (Exception e) {
            throw new CustomException("Could not create a trainingGuide" + e.getMessage());
        }
    }

    @MutationMapping
    public TrainingGuideDto updateTrainingGuide(@Argument TrainingGuideInput input) {
        try {
            TrainingGuideDto trainingGuideDto = convertToDto(input);
            return trainingGuideService.updateTrainingGuide(trainingGuideDto);
        } catch (Exception e) {
            throw new CustomException("Could not update the trainingGuide" + e.getMessage());
        }
    }

    @MutationMapping
    public void deleteTrainingGuide(@Argument Long id) {
        try {
            trainingGuideService.deleteTrainingGuide(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete the trainingGuide");
        }
    }

    private TrainingGuideDto convertToDto(TrainingGuideInput traininigGuideInput) {
        return trainingGuideMapper.convertToDTO(traininigGuideInput);
    }


}
