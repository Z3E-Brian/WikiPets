package org.una.programmingIII.WikiPets.Service;

import org.una.programmingIII.WikiPets.Dto.ImageDto;

import java.util.Map;

public interface ImageService {

    ImageDto getImageByid(Long id);

    ImageDto createImage(ImageDto imageDto);

    ImageDto updateImage(ImageDto imageDto);

    ImageDto addDogBreedToImage(Long id, Long idDogBreed);

    ImageDto addCatBreedToImage(Long id, Long idDogBreed);

    void deleteImage(Long id);

    Map<String, Object> getAllImages(int page, int size);

    Map<String, Object> getImagesByDogBreed(Long id, int page, int size);

    Map<String, Object> getImagesByCatBreed(Long id, int page, int size);


    /*ImageDto removeDogBreedFromImage(Long id, Long idDogBreed);

    ImageDto removeCatBreedFromImage(Long id, Long idCatBreed);*/
}
