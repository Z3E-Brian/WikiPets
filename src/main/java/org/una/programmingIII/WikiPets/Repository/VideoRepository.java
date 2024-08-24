package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.Image;
import org.una.programmingIII.WikiPets.Model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}