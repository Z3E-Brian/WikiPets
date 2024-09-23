package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.GroomingGuide;
@Repository
public interface GroomingGuideRepository extends JpaRepository<GroomingGuide, Long> {
}
