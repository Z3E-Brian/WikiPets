package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TrainingGuideDtoTest {
    TrainingGuideDto trainingGuideDto;

    @BeforeEach
    void setUp() {
        List<CatBreedDto> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", LocalDate.now(), LocalDate.now()));

        List<DogBreedDto> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active", "Well-known for being friendly and good with children.", LocalDate.now(), LocalDate.now()));

        trainingGuideDto = new TrainingGuideDto();
        trainingGuideDto.setId(1L);
        trainingGuideDto.setTitle("Basic Training Guide for Pets");
        trainingGuideDto.setContent("This is a basic training guide for both cats and dogs.");
        trainingGuideDto.setCatsBreedDto(catBreeds);
        trainingGuideDto.setDogsBreedDto(dogBreeds);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, trainingGuideDto.getId());
        assertEquals("Basic Training Guide for Pets", trainingGuideDto.getTitle());
        assertEquals("This is a basic training guide for both cats and dogs.", trainingGuideDto.getContent());
        assertEquals(1, trainingGuideDto.getCatsBreedDto().size());
        assertEquals(1, trainingGuideDto.getDogsBreedDto().size());
    }

    @Test
    public void argsSettersTest() {
        trainingGuideDto.setId(2L);
        trainingGuideDto.setTitle("Advanced Training Guide for Cats");
        trainingGuideDto.setContent("This is an advanced training guide specifically for cats.");
        trainingGuideDto.setCatsBreedDto(new ArrayList<>()); // Empty list for cats
        trainingGuideDto.setDogsBreedDto(null); // No dogs

        assertEquals(2L, trainingGuideDto.getId());
        assertEquals("Advanced Training Guide for Cats", trainingGuideDto.getTitle());
        assertEquals("This is an advanced training guide specifically for cats.", trainingGuideDto.getContent());
        assertEquals(0, trainingGuideDto.getCatsBreedDto().size());
        assertEquals(null, trainingGuideDto.getDogsBreedDto());
    }

    @Test
    public void equalsAndHashCodeTest() {
        List<CatBreedDto> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", LocalDate.now(), LocalDate.now()));

        List<DogBreedDto> dogBreeds = new ArrayList<>();
        dogBreeds.add(new DogBreedDto(1L, "Labrador", "Canada", 3, "Short", "Yellow", "10-12 years", "Friendly, Active", "Well-known for being friendly and good with children.", LocalDate.now(), LocalDate.now()));

        TrainingGuideDto trainingGuideDto1 = new TrainingGuideDto(1L, "Basic Training Guide for Pets", "This is a basic training guide for both cats and dogs.", catBreeds, dogBreeds, LocalDate.now(), LocalDate.now());
        TrainingGuideDto trainingGuideDto2 = new TrainingGuideDto(1L, "Basic Training Guide for Pets", "This is a basic training guide for both cats and dogs.", catBreeds, dogBreeds, LocalDate.now(), LocalDate.now());

        assertEquals(trainingGuideDto1, trainingGuideDto2);
        assertEquals(trainingGuideDto1.hashCode(), trainingGuideDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        List<CatBreedDto> catBreeds = new ArrayList<>();
        catBreeds.add(new CatBreedDto(1L, "Siamese", "Thailand", 2, "Short", "Cream with points", "12-16 years", "Affectionate, Social, Vocal", "Popular breed known for its striking appearance and vocal nature.", LocalDate.now(), LocalDate.now()));

        TrainingGuideDto trainingGuideDto1 = new TrainingGuideDto(1L, "Basic Training Guide for Pets", "This is a basic training guide for both cats and dogs.", catBreeds, new ArrayList<>(), LocalDate.now(), LocalDate.now());
        TrainingGuideDto trainingGuideDto2 = new TrainingGuideDto(2L, "Advanced Training Guide for Dogs", "This is an advanced training guide specifically for dogs.", new ArrayList<>(), null, LocalDate.now(), LocalDate.now());

        assertEquals(trainingGuideDto1.hashCode(), trainingGuideDto1.hashCode());
        assertNotEquals(trainingGuideDto1, trainingGuideDto2);
        assertNotEquals(trainingGuideDto1.hashCode(), trainingGuideDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("TrainingGuideDto(id=1, title=Basic Training Guide for Pets, " +
                "content=This is a basic training guide for both cats and dogs., " +
                "catsBreedDto=[CatBreedDto(id=1, name=Siamese, origin=Thailand, size=2, " +
                "coat=Short, color=Cream with points, lifeSpan=12-16 years, temperament=Affectionate, " +
                "Social, Vocal, description=Popular breed known for its striking appearance and vocal nature., " +
                "createdDate=2024-08-15, modifiedDate=2024-08-15)], " +
                "dogsBreedDto=[DogBreedDto(id=1, name=Labrador, origin=Canada, size=3, coat=Short, color=Yellow, " +
                "lifeSpan=10-12 years, temperament=Friendly, Active, " +
                "description=Well-known for being friendly and good with children., " +
                "createdDate=2024-08-15, modifiedDate=2024-08-15)], " +
                "createDate=null, lastUpdate=null)", trainingGuideDto.toString());
    }
}
