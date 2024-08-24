package org.una.programmingIII.WikiPets.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
import org.una.programmingIII.WikiPets.Model.Image;
import org.una.programmingIII.WikiPets.Model.ImageDto;
import org.una.programmingIII.WikiPets.Repository.ImageRepository;

import java.util.List;

@Service
public class ImageServiceImplementation implements ImageService {

    private final ImageRepository imageRepository;
    private final GenericMapper<Image, ImageDto> imageMapper;

    @Autowired
    public ImageServiceImplementation(ImageRepository imageRepository, GenericMapperFactory mapperFactory) {
        this.imageRepository = imageRepository;
        this.imageMapper = mapperFactory.createMapper(Image.class, ImageDto.class);
    }

    @Override
    public List<ImageDto> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return imageMapper.convertToDTOList(images);
    }

    @Override
    public ImageDto getImageByid(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found with id: " + id));
        return imageMapper.convertToDTO(image);
    }

    @Override
    public ImageDto createImage(ImageDto imageDto) {
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
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}