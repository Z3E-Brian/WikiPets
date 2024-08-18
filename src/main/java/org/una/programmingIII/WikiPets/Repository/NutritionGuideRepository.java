package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.NutritionGuide;
@Repository
public interface NutritionGuideRepository extends JpaRepository<NutritionGuide,Long> {
    NutritionGuide findByTitle(String title);
}
