package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.CareTip;

@Repository
public interface CareTipRepository extends JpaRepository<CareTip,Long> {
    CareTip findByTitle(String title);
}
