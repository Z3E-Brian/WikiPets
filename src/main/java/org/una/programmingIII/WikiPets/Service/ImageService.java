package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Model.ImageDto;

import java.util.List;

public interface ImageService {
    List<ImageDto> getAllImages();

    ImageDto getImageByid(Long id);

    ImageDto createImage(ImageDto imageDto);

    ImageDto updateImage(ImageDto imageDto);

    void deleteImage(Long id);
}
