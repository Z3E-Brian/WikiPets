package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.TrainingGuide;

import java.util.List;

@Repository
public interface TrainingGuideRepository extends JpaRepository<TrainingGuide, Long> {

    TrainingGuide findByTitle(String title);

    TrainingGuide getGuideById(Long id);

    List<TrainingGuide> findByTitleContainingIgnoreCase(String title);

    List<TrainingGuide> findByCatBreedsId(Long catBreedId);

    List<TrainingGuide> findByDogBreedsId(Long dogBreedId);
}
