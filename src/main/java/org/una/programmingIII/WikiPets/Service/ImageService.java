package org.una.programmingIII.WikiPets.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.una.programmingIII.WikiPets.Dto.GroomingGuideDto;
import org.una.programmingIII.WikiPets.Dto.ImageDto;

public interface ImageService {

    ImageDto getImageByid(Long id);

    ImageDto createImage(ImageDto imageDto);

    ImageDto updateImage(ImageDto imageDto);

    ImageDto addDogBreedToImage(Long id, Long idDogBreed);

    ImageDto addCatBreedToImage(Long id, Long idDogBreed);

    void deleteImage(Long id);

    Page<ImageDto> getAllImages(Pageable pageable);

}
