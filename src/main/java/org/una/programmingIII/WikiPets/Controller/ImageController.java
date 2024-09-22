package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ImageController {
    private final ImageService imageService;
    private final GenericMapper<ImageInput, ImageDto> imageMapper;

    @Autowired
    public ImageController(ImageService imageService, GenericMapperFactory mapperFactory) {
        this.imageService = imageService;
        this.imageMapper = mapperFactory.createMapper(ImageInput.class, ImageDto.class);
    }

    @QueryMapping
    public Map<String, Object> getImages(@Argument int page, @Argument int size) {
        try {
            return imageService.getAllImages(page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find behavior guides" + e.getMessage());
        }
    }
    //doing
    @QueryMapping
    public Map<String, Object> getImagesByDogBreed(@Argument Long id, @Argument int page, @Argument int size) {
        try {
            return imageService.getImagesByDogBreed(id, page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find image with id " + id + ". " + e.getMessage(), e);
        }
    }

    @QueryMapping
    public Map<String, Object> getImagesByCatBreed(@Argument Long id, @Argument int page, @Argument int size) {
        try {
            return imageService.getImagesByCatBreed(id, page, size);
        } catch (Exception e) {
            throw new CustomException("Could not find image with id " + id + ". " + e.getMessage(), e);
        }
    }

    @QueryMapping
    public ImageDto getImageById(@Argument Long id) {
        try {
            return imageService.getImageByid(id);
        } catch (Exception e) {
            throw new CustomException("Could not find image with id " + id + ". " + e.getMessage(), e);
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
    public ImageDto createImage(@Argument ImageInput input) {
        try {
            ImageDto imageDto = convertToDto(input);
            return imageService.createImage(imageDto);
        } catch (Exception e) {
            throw new CustomException("Could not create image: " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public ImageDto updateImage(@Argument ImageInput input) {
        try {
            ImageDto imageDto = convertToDto(input);
            return imageService.updateImage(imageDto);
        } catch (Exception e) {
            throw new CustomException("Could not update image: " + e.getMessage(), e);
        }
    }

    @MutationMapping
    public boolean deleteImage(@Argument Long id) {
        try {
            imageService.deleteImage(id);
            return true;
        } catch (Exception e) {
            throw new CustomException("Could not delete image with id " + id + ". " + e.getMessage(), e);
        }
    }

    private ImageDto convertToDto(ImageInput imageInput) {
        return imageMapper.convertToDTO(imageInput);
    }
}