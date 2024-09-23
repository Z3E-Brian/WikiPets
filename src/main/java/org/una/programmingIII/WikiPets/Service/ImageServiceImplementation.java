package org.una.programmingIII.WikiPets.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Dto.ImageDto;
import org.una.programmingIII.WikiPets.Exception.BlankInputException;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Exception.InvalidInputException;
import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Model.Image;
import org.una.programmingIII.WikiPets.Repository.ImageRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceImplementation implements ImageService {

    private final ImageRepository imageRepository;
    private final GenericMapper<Image, ImageDto> imageMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public ImageServiceImplementation(ImageRepository imageRepository, 
                                      GenericMapperFactory mapperFactory, 
                                      DogBreedService dogBreedService, 
                                      CatBreedService catBreedService) {
        this.imageRepository = imageRepository;
        this.imageMapper = mapperFactory.createMapper(Image.class, ImageDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }

    @Override
    public Map<String, Object> getAllImages(int page, int size) {
        Page<Image> images = imageRepository.findAll(PageRequest.of(page, size));
        return Map.of(
                "images", images.map(this::convertToDto).getContent(),
                "totalPages", images.getTotalPages(),
                "totalElements", images.getTotalElements()
        );
    }

    @Override
    public Map<String, Object> getImagesByDogBreed(@NotNull Long id, int page, int size) {
        DogBreed dogBreed = dogBreedService.getBreedEntityById(id);
        Page<Image> images = imageRepository.findImagesByDogBreed(dogBreed, PageRequest.of(page, size));
        return Map.of(
                "images", images.map(this::convertToDto).getContent(),
                "totalPages", images.getTotalPages(),
                "totalElements", images.getTotalElements()
        );
    }

    @Override
    public Map<String, Object> getImagesByCatBreed(@NotNull Long id, int page, int size) {
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(id));
        Page<Image> images = imageRepository.findImagesByCatBreed(catBreed, PageRequest.of(page, size));
        return Map.of(
                "images", images.map(this::convertToDto).getContent(),
                "totalPages", images.getTotalPages(),
                "totalElements", images.getTotalElements()
        );
    }

    @Override
    public ImageDto getImageByid(@NotNull Long id) {
        validateId(id);
        return imageRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundElementException("Image Not Found with id: " + id));
    }

    @Override
    public ImageDto createImage(@NotNull ImageDto imageDto) {
        validateImageDto(imageDto);
        imageDto.setCreateDate(LocalDate.now());
        imageDto.setLastUpdate(LocalDate.now());
        Image image = imageMapper.convertToEntity(imageDto);
        return imageMapper.convertToDTO(imageRepository.save(image));
    }

    @Override
    public boolean deleteImage(@NotNull Long id) {
        validateId(id);
        findImageById(id);
        imageRepository.deleteById(id);
        return true;
    }

    @Override
    public ImageDto updateImage(@NotNull ImageDto imageDto) {
        validateId(imageDto.getId());
        validateImageDto(imageDto);

        Image oldImage = findImageById(imageDto.getId());
        Image newImage = imageMapper.convertToEntity(imageDto);
        newImage.setCreateDate(oldImage.getCreateDate());
        newImage.setLastUpdate(LocalDate.now());
        return convertToDto(imageRepository.save(newImage));
    }

    @Override
    public ImageDto addDogBreedToImage(@NotNull Long id,@NotNull Long idDogBreed) {
        validateId(id);
        validateId(idDogBreed);
        Image image = findImageById(id);
        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        image.setDogBreed(dogBreed);
        image.setCatBreed(null);
        dogBreed.getImages().add(image);
        return imageMapper.convertToDTO(imageRepository.save(image));
    }
    @Override
    public ImageDto addCatBreedToImage(@NotNull Long id,@NotNull Long idCatBreed) {
        validateId(id);
        validateId(idCatBreed);
        Image image = findImageById(id);
        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        image.setCatBreed(catBreed);
        image.setDogBreed(null);
        catBreed.getImages().add(image);
        return imageMapper.convertToDTO(imageRepository.save(image));
    }

    private ImageDto convertToDto(Image image) {
        return imageMapper.convertToDTO(image);
    }

    private Image convertToEntity(ImageDto imageDto) {
        return imageMapper.convertToEntity(imageDto);
    }


    private void validateImageDto(@NotNull ImageDto dto) {
        if (dto.getUrl() == null || dto.getUrl().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            throw new BlankInputException("Can't accept spaces in blank");
        }
    }

    private void validateId(Long id) {
        if (id <= 0) {
            throw new InvalidInputException("Invalid ID");
        }
    }

    private Image findImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundElementException("Adoption Center not found"));
    }

}