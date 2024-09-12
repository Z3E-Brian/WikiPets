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
import org.una.programmingIII.WikiPets.Dto.ImageDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Input.ImageInput;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Service.ImageService;
import org.una.programmingIII.WikiPets.Service.ImageServiceImplementation;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ImageController {
    private final ImageService imageService;
    private final GenericMapper<ImageInput, ImageDto> dogBreedMapper;
    
    @Autowired
    ImageController(GenericMapperFactory mapperFactory, ImageServiceImplementation imageServiceImplementation) {
        this.dogBreedMapper = mapperFactory.createMapper(ImageInput.class, ImageDto.class);
        this.imageService = imageServiceImplementation;
    }

    @QueryMapping
    public Map<String, Object> getImages(@Argument int page, @Argument int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ImageDto> imagePage = imageService.getAllImages(pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("images", imagePage.getContent());
            response.put("totalPages", imagePage.getTotalPages());
            response.put("totalElements", imagePage.getTotalElements());
            return response;
        } catch (Exception e) {
            throw new CustomException("Could not find image" + e.getMessage());
        }
    }
    @QueryMapping
    public ImageDto getImageById(@Argument Long id) {
        try {
            return imageService.getImageByid(id);
        } catch (Exception e) {
            throw new CustomException("Could not find image " +id+". "+ e.getMessage());
        }
    }
    @MutationMapping
    public ImageDto addDogBreedToImage(@Argument Long id, @Argument Long idDogBreed) {
        try {
            return imageService.addDogBreedToImage(id, idDogBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update image with id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public ImageDto addCatBreedToImage(@Argument Long id, @Argument Long idCatBreed) {
        try {
            return imageService.addCatBreedToImage(id, idCatBreed);
        } catch (Exception e) {
            throw new CustomException("Could not update image with id: " + id + ". " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public ImageDto updateImage(@Argument ImageInput input) {
        try {
            ImageDto imageDto = dogBreedMapper.convertToDTO(input);
            return imageService.updateImage(imageDto);
        } catch (Exception e) {
            throw new CustomException("Could not image "+ e.getMessage());
        }
    }
    @MutationMapping
    public ImageDto createImage(@Argument ImageInput input) {
        try {
            ImageDto imageDto = dogBreedMapper.convertToDTO(input);
            return imageService.createImage(imageDto);
        } catch (Exception e) {
            throw new CustomException("Could not create image" + e.getMessage());
        }
    }

    @MutationMapping
    public boolean deleteImage(@Argument Long id) {
        try {
            imageService.deleteImage(id);
            return true;
        } catch (Exception e) {
            throw new CustomException("Could not delete image "+id+". " + e.getMessage());
        }
    }
}
