package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.AdoptionCenter;
@Repository
public interface AdoptionCenterRepository extends JpaRepository<AdoptionCenter, Long> {
}
