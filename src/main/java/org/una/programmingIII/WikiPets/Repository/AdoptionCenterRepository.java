package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;

public interface AdoptionCenterRepository extends JpaRepository<AdoptionCenter, Long> {
}
