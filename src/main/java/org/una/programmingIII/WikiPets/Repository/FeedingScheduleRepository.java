package org.una.programmingIII.WikiPets.Repository;

import org.una.programmingIII.WikiPets.Model.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {

    // Encontrar por ID (método heredado de JpaRepository)
    List<FeedingSchedule> findByCatBreedId(Long catBreedId);
    List<FeedingSchedule> findByDogBreedId(Long dogBreedId);
    List<FeedingSchedule> findByAgeGroup(String ageGroup);
    List<FeedingSchedule> findByFeedingTimesContaining(String text);

    boolean existsById(Long id);
}
