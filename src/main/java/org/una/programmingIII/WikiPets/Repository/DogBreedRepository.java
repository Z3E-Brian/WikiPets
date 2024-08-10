package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.DogBreed;

@Repository
public interface DogBreedRepository extends JpaRepository<DogBreed, Long>{
    DogBreed findByBreedName(String breedName);
}
