package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.TrainingGuide;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingGuideRepository extends JpaRepository<TrainingGuide, Long> {
}
