package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Page<Image> findImagesByDogBreed(DogBreed dogBreed, Pageable pageable);
    Page<Image> findImagesByCatBreed(CatBreed catBreed, Pageable pageable);
}