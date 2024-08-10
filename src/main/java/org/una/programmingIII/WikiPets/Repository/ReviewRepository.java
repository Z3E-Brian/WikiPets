package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findByUserEmail(String email);

    List<Review> findListByUserEmail(String email);

    List<Review> findByRating(Integer rating);

    List<Review> findByCatBreedId(Long catBreedId);

    List<Review> findByDogBreedId(Long dogBreedId);
}
