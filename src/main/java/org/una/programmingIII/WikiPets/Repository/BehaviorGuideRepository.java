package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;

public interface BehaviorGuideRepository extends JpaRepository<BehaviorGuide, Long> {
    BehaviorGuide findByBehaviorGuideTitle(String BehaviorGuideTitle);
}
