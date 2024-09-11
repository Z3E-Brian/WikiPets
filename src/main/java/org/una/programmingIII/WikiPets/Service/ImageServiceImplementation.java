package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Dto.*;
import org.una.programmingIII.WikiPets.Dto.ImageDto;
import org.una.programmingIII.WikiPets.Exception.CustomException;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.*;
import org.una.programmingIII.WikiPets.Model.Image;
import org.una.programmingIII.WikiPets.Dto.ImageDto;
import org.una.programmingIII.WikiPets.Repository.ImageRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImplementation implements ImageService {

    private final ImageRepository imageRepository;
    private final GenericMapper<Image, ImageDto> imageMapper;
    private final GenericMapper<DogBreed, DogBreedDto> dogBreedMapper;
    private final GenericMapper<CatBreed, CatBreedDto> catBreedMapper;
    private final DogBreedService dogBreedService;
    private final CatBreedService catBreedService;

    @Autowired
    public ImageServiceImplementation(ImageRepository imageRepository, GenericMapperFactory mapperFactory, DogBreedService dogBreedService, CatBreedService catBreedService) {
        this.imageRepository = imageRepository;
        this.imageMapper = mapperFactory.createMapper(Image.class, ImageDto.class);
        this.dogBreedMapper = mapperFactory.createMapper(DogBreed.class, DogBreedDto.class);
        this.catBreedMapper = mapperFactory.createMapper(CatBreed.class, CatBreedDto.class);
        this.dogBreedService = dogBreedService;
        this.catBreedService = catBreedService;
    }
    private ImageDto convertToDto(Image image) {return imageMapper.convertToDTO(image);
    }

    @Override
    public Page<ImageDto> getAllImages(Pageable pageable) {
        Page<Image> imagePage = imageRepository.findAll(pageable);
        return imagePage.map(this::convertToDto);
    }

    @Override
    public ImageDto getImageByid(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found with id: " + id));
        return imageMapper.convertToDTO(image);
    }

    @Override
    public ImageDto createImage(ImageDto imageDto) {
        imageDto.setCreateDate(LocalDate.now());
        imageDto.setLastUpdate(LocalDate.now());
        Image image = imageMapper.convertToEntity(imageDto);
        Image savedImage = imageRepository.save(image);
        return imageMapper.convertToDTO(savedImage);
    }

    @Override
    public ImageDto updateImage(ImageDto imageDto) {
        Image trainingGuide = imageMapper.convertToEntity(imageDto);
        Image updatedTrainingGuide = imageRepository.save(trainingGuide);
        return imageMapper.convertToDTO(updatedTrainingGuide);
    }

    @Override
    public ImageDto addDogBreedToImage(Long id, Long idDogBreed) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new CustomException("Image not found"));

        DogBreed dogBreed = dogBreedMapper.convertToEntity(dogBreedService.getBreedById(idDogBreed));
        image.setDogBreed(dogBreed);
        image.setCatBreed(null);
        return imageMapper.convertToDTO(imageRepository.save(image));
    }
    @Override
    public ImageDto addCatBreedToImage(Long id, Long idCatBreed) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new CustomException("Image not found"));

        CatBreed catBreed = catBreedMapper.convertToEntity(catBreedService.getBreedById(idCatBreed));
        image.setCatBreed(catBreed);
        image.setDogBreed(null);
        return imageMapper.convertToDTO(imageRepository.save(image));
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}