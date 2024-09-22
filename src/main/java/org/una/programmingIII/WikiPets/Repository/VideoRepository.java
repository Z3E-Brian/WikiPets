package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.Image;
import org.una.programmingIII.WikiPets.Model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findVideosByDogBreed(DogBreed dogBreed, Pageable pageable);
    Page<Video> findVideosByCatBreed(CatBreed catBreed, Pageable pageable);
}