package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.CatBreed;

@Repository
public interface CatBreedRepository extends JpaRepository<CatBreed, Long> {
    CatBreed findByCatBreedName(String breedName);
}
