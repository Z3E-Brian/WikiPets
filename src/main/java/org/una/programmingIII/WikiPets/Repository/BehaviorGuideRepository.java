package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
@Repository
public interface BehaviorGuideRepository extends JpaRepository<BehaviorGuide, Long> {

}
